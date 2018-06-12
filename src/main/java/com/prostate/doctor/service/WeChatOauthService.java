package com.prostate.doctor.service;

import com.prostate.doctor.common.WeChatConstants;
import com.prostate.doctor.util.HttpsConnectionUtils;
import com.prostate.doctor.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class WeChatOauthService {


    /**
     *
     * 微信 Oauth2 授权 获取 access_token
     */
    public Map<String, Object> getAccessToken(String code){
        StringBuffer urlStr = new StringBuffer();
        urlStr.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
        urlStr.append(WeChatConstants.APP_ID);
        urlStr.append("&secret=");
        urlStr.append(WeChatConstants.APP_SECRET);
        urlStr.append("&code=");
        urlStr.append(code);
        urlStr.append("&grant_type=authorization_code");

        String responseStr = HttpsConnectionUtils.conn(urlStr.toString());
        log.debug(responseStr);
        return (Map<String, Object>) JsonUtils.jsonToObj(responseStr);
    }

    /**
     *
     * 微信 Oauth2 授权 刷新 access_token
     * https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
     */
    public Map<String, Object> refreshAccessToken(String accessToken){

        StringBuffer urlStr = new StringBuffer();
        urlStr.append("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=");
        urlStr.append(WeChatConstants.APP_ID);
        urlStr.append("&grant_type=refresh_token&refresh_token=");
        urlStr.append(accessToken);

        String responseStr = HttpsConnectionUtils.conn(urlStr.toString());
        log.debug(responseStr);
        return (Map<String, Object>) JsonUtils.jsonToObj(responseStr);

    }
    /**
     *
     * 微信 Oauth2 授权 获取 用户信息
     * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     */
    public Map<String, Object> getUserInfo(String accessToken,String openid){
        StringBuffer urlStr = new StringBuffer();
        urlStr.append("https://api.weixin.qq.com/sns/userinfo?access_token=");
        urlStr.append(accessToken);
        urlStr.append("&openid=");
        urlStr.append(openid);
        String responseStr = HttpsConnectionUtils.conn(urlStr.toString());
        log.debug(responseStr);
        return (Map<String, Object>) JsonUtils.jsonToObj(responseStr);
    }

    /**
     *
     * 微信 Oauth2 授权 检验 access_token 是否有效
     * https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID
     */
    public void checkAccessToken(String accessToken,String openid){
        StringBuffer urlStr = new StringBuffer();
        urlStr.append("https://api.weixin.qq.com/sns/auth?access_token=");
        urlStr.append(accessToken);
        urlStr.append("&openid=");
        urlStr.append(openid);

        String responseStr = HttpsConnectionUtils.conn(urlStr.toString());
        log.debug(responseStr);
        Map<String,String> map = (Map<String, String>) JsonUtils.jsonToObj(responseStr);
    }

}
