package com.prostate.doctor.controller;

import com.alibaba.fastjson.JSONObject;
import com.prostate.doctor.cache.redis.RedisSerive;
import com.prostate.doctor.entity.WechatUser;
import com.prostate.doctor.service.WeChatOauthService;
import com.prostate.doctor.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "weChat")
public class WeChatLoginController extends BaseController {

    @Autowired
    private WeChatOauthService weChatOauthService;

    @Autowired
    private WechatUserService wechatUserService;
    @Autowired
    private RedisSerive redisSerive;
//    @Autowired
//    private MemberUserService memberUserService;
    /**
     * 公众号AppId
     */
    private final static String APP_ID = "wx081244d52d700819";

    /**
     * 公众号AppSecret
     */
    public static final String APP_SECRET = "2eb07faaf608215e7091e594a5b650a7";

    public static final String REDIRECT_URI = "http%3a%2f%2fwww.yilaiyiwang.com%2fredirect";

    public static final String AUTHOR_URI = "https://open.weixin.qq.com/connect/oauth2/authorize?";

    public static final String AUTHOR_PARAM = "appid=APP_ID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

//    @RequestMapping(value = "oauth")
//    public ModelAndView redirect(String code, String state) {
//        //获取ACCESS_TOKEN
//        Map<String, Object> resultMap = weChatOauthService.getAccessToken(code);
//        //刷新ACCESS_TOKEN
//        resultMap = weChatOauthService.refreshAccessToken(resultMap.get("refresh_token").toString());
//        //获取 用户信息
//        Map<String, Object> wechatUserInfoMap = weChatOauthService.getUserInfo(resultMap.get("access_token").toString(), resultMap.get("openid").toString());
//        //保存用户信息
//        MemberUser memberUser = new MemberUser();
//        memberUser.setUsername(wechatUserInfoMap.get("openid").toString());
//        memberUser.setPassword(resultMap.get("access_token").toString());
//        memberUser.setNickname(wechatUserInfoMap.get("nickname").toString());
//        memberUser.setOpenid(wechatUserInfoMap.get("openid").toString());
//        memberUser.setNickname(wechatUserInfoMap.get("nickname").toString());
//        memberUser.setSex(wechatUserInfoMap.get("sex").toString());
//        memberUser.setLanguage(wechatUserInfoMap.get("language").toString());
//        memberUser.setCity(wechatUserInfoMap.get("city").toString());
//        memberUser.setProvince(wechatUserInfoMap.get("province").toString());
//        memberUser.setCountry(wechatUserInfoMap.get("country").toString());
//        memberUser.setHeadimgurl(wechatUserInfoMap.get("headimgurl").toString());
//
//        memberUser = memberUserService.getMemberUserByToken(memberUser);
//        if(memberUser==null){
//            memberUser = new MemberUser();
//            memberUser.setUsername(wechatUserInfoMap.get("openid").toString());
//            memberUser.setPassword(resultMap.get("access_token").toString());
//            memberUser.setNickname(wechatUserInfoMap.get("nickname").toString());
//            memberUser.setOpenid(wechatUserInfoMap.get("openid").toString());
//            memberUser.setNickname(wechatUserInfoMap.get("nickname").toString());
//            memberUser.setSex(wechatUserInfoMap.get("sex").toString());
//            memberUser.setLanguage(wechatUserInfoMap.get("language").toString());
//            memberUser.setCity(wechatUserInfoMap.get("city").toString());
//            memberUser.setProvince(wechatUserInfoMap.get("province").toString());
//            memberUser.setCountry(wechatUserInfoMap.get("country").toString());
//            memberUser.setHeadimgurl(wechatUserInfoMap.get("headimgurl").toString());
//            memberUserService.add(memberUser);
//        }else{
//            memberUser.setUsername(wechatUserInfoMap.get("openid").toString());
//            memberUser.setPassword(resultMap.get("access_token").toString());
//            memberUser.setNickname(wechatUserInfoMap.get("nickname").toString());
//            memberUser.setOpenid(wechatUserInfoMap.get("openid").toString());
//            memberUser.setNickname(wechatUserInfoMap.get("nickname").toString());
//            memberUser.setSex(wechatUserInfoMap.get("sex").toString());
//            memberUser.setLanguage(wechatUserInfoMap.get("language").toString());
//            memberUser.setCity(wechatUserInfoMap.get("city").toString());
//            memberUser.setProvince(wechatUserInfoMap.get("province").toString());
//            memberUser.setCountry(wechatUserInfoMap.get("country").toString());
//            memberUser.setHeadimgurl(wechatUserInfoMap.get("headimgurl").toString());
//            memberUserService.update(memberUser);
//        }
//        //shiro 登陆授权
//        try {
//            MemberUserTokenManager.login(memberUser, null);
//        } catch (DisabledAccountException e) {
//            LoggerUtils.error(getClass(), "<=====帐号已经禁用=====>");
//        } catch (Exception e) {
//            LoggerUtils.error(getClass(), "<=====帐号或密码错误=====>" + e);
//        }
//        SecurityUtils.getSubject().getSession().setTimeout(-1000l);
//        memberUser = MemberUserTokenManager.getToken();
//        LoggerUtils.debug(getClass(), memberUser.getUsername());
//        LoggerUtils.debug(getClass(), memberUser.getId());
//        return new ModelAndView("Client/src/information/orderInfo.html");
//    }

    /**
     * 微信授权登陆接口
     *
     * @param request
     * @return
     */
    @PostMapping(value = "login")
    public Map login(HttpServletRequest request) {

        Map<String, Object> resultMap = new LinkedHashMap<>();
        WechatUser wechatUser = wechatUserService.selectById("8871fd0d532c11e8967f00163e08d49b");
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
        redisSerive.insert(wechatUser.getId(), wechatUser.getId(), 60 * 5);

        return querySuccessResponse(wechatUser.getId().substring(10, 28));
    }


}