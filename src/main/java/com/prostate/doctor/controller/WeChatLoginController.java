package com.prostate.doctor.controller;

import com.alibaba.fastjson.JSONObject;
import com.prostate.doctor.cache.redis.RedisSerive;
import com.prostate.doctor.entity.WechatUser;
import com.prostate.doctor.service.WeChatOauthService;
import com.prostate.doctor.service.WechatUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "weChat")
public class WeChatLoginController extends BaseController {

    @Autowired
    private WeChatOauthService weChatOauthService;

    @Autowired
    private WechatUserService wechatUserService;
    @Autowired
    private RedisSerive redisSerive;


    /**
     * 微信授权登陆接口
     *
     * @param request
     * @return
     */
//    @PostMapping(value = "login")
    public Map login(HttpServletRequest request) {

        Map<String, Object> resultMap = new LinkedHashMap<>();
        WechatUser wechatUser = wechatUserService.selectById("3fd5b817800c11e8a09b68cc6e5c9c74");
        String token = request.getSession().getId();
        JSONObject.toJSONString(wechatUser);
        redisSerive.insert(token, JSONObject.toJSONString(wechatUser));
        resultMap.put("code", 20000);
        resultMap.put("msg", "登陆成功");
        resultMap.put("result", token);

        System.out.println(redisSerive.get(token));
        return resultMap;

    }

    /**
     * 微信端获取用户信息
     *
     * @param token
     * @return
     */
    @PostMapping(value = "getUserInfo")
    public Map getPatientDetailByToken(String token) {
        WechatUser wechatUser = redisSerive.getWechatUser(token);
        return querySuccessResponse(wechatUser);
    }

    /**
     * 微信端 获取 医患关系 绑定 二维码 (过期时间5分钟)
     *
     * @param token
     * @return
     */
    @PostMapping(value = "getQRCode")
    public Map getQRCode(String token) {

        WechatUser wechatUser = redisSerive.getWechatUser(token);

        String userId = wechatUser.getId();
        if (userId != null && userId.length() == 32) {
            String cacheId = RandomStringUtils.randomNumeric(6);
            redisSerive.insert(cacheId, wechatUser.getId(), 60 * 5);
            return querySuccessResponse(cacheId);
        }
        return queryEmptyResponse();
    }


    @RequestMapping(value = "oauth")
    public Map redirect(String code, HttpServletRequest request) {

        if (code == null || "".equals(code)) {
            return emptyParamResponse();
        }
        //获取ACCESS_TOKEN
        Map<String, Object> resultMap = weChatOauthService.getAccessToken(code);
        //刷新ACCESS_TOKEN
        resultMap = weChatOauthService.refreshAccessToken(resultMap.get("refresh_token").toString());
        //获取 用户信息
        Map<String, Object> wechatUserInfoMap = weChatOauthService.getUserInfo(resultMap.get("access_token").toString(), resultMap.get("openid").toString());


        //保存用户信息
        String openid = wechatUserInfoMap.get("openid").toString();
        WechatUser wechatUser = wechatUserService.selectByOpenid(openid);
        if (wechatUser != null) {
            log.info("111111");
            //shiro 登陆授权
            String token = request.getSession().getId();
            JSONObject.toJSONString(wechatUser);
            redisSerive.insert(token, JSONObject.toJSONString(wechatUser));
            return insertSuccseeResponse(token);
//                response.sendRedirect("http://www.sicmed.cn:6601/chestnut/index.html?" + token);
        }
        wechatUser = new WechatUser();
        wechatUser.setOpenid(openid);
        String nickname = filterEmoji(wechatUserInfoMap.get("nickname").toString());
        wechatUser.setNickName(nickname);
        wechatUser.setHeadImgUrl(wechatUserInfoMap.get("headimgurl").toString());
        log.info(wechatUser.toString());
        wechatUserService.insertSelective(wechatUser);
        //shiro 登陆授权
        String token = request.getSession().getId();
        JSONObject.toJSONString(wechatUser);
        redisSerive.insert(token, JSONObject.toJSONString(wechatUser));
        return insertSuccseeResponse(token);

    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

}