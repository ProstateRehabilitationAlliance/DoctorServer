package com.prostate.doctor.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.entity.WechatUser;
import org.springframework.stereotype.Component;

@Component
public class JsonUtils<E> {

    /**
     * java普通对象和json字符串的互转
     */

    public static String objectToJsonStr(Doctor doctor) {
        return JSONObject.toJSONString(doctor);
    }

    public Doctor jsonStrToObject(String jsonStr) {
        return JSON.parseObject(jsonStr, Doctor.class);

    }


    /**
     * 把java对象转换成json对象，并转化为字符串
     */
    public static String jsonToStr(Object obj) {
        if (obj == null) {
            return "";
        }
        return JSONObject.toJSONString(obj);

    }

    public static Object jsonToObj(String jsonStr) {
        if ("".equals(jsonStr)) {
            return null;
        }
        return JSONObject.parseObject(jsonStr);
    }

    public WechatUser jsonStrToWechatUser(String jsonStr){
        return JSON.parseObject(jsonStr, WechatUser.class);

    }
}
