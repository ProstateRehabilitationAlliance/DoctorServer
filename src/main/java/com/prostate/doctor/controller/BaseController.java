package com.prostate.doctor.controller;


import com.prostate.doctor.cache.redis.RedisSerive;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseController {

    public static Map<String, Object> resultMap;

    @Autowired
    protected RedisSerive redisSerive;

    public Map loginSuccessResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "LOGIN_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    public Map loginFailedResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20005");
        resultMap.put("msg", "LOGIN_FAILED");
        resultMap.put("result", result);
        return resultMap;
    }
    /**
     * 参数为空返回值
     *
     * @return
     */
    public Map emptyParamResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20001");
        resultMap.put("msg", "EMPTY_PARAM");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * 参数错误返回值
     *
     * @return
     */
    public Map failedParamResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "40001");
        resultMap.put("msg", "FAILED_PARAM");
        resultMap.put("result", result);
        return resultMap;
    }
    /**
     * 请求成功返回值
     *
     * @param result
     * @return
     */
    public Map querySuccessResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 分页查询请求成功返回值
     *
     * @param result
     * @return
     */
    public Map querySuccessResponse(Object result, String count) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "SUCCESS");
        resultMap.put("result", result);
        resultMap.put("count", count);
        return resultMap;
    }

    /**
     * 查询请求结果为空返回值
     *
     * @return
     */
    public Map queryEmptyResponse() {
        resultMap = new LinkedHashMap<>();

        resultMap.put("code", "40004");
        resultMap.put("msg", "RESULT_EMPTY");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * 请求失败返回值
     *
     * @return
     */
    public Map failedResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50002");
        resultMap.put("msg", "FAILED_EMPTY");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * INSERT 失败返回值
     *
     * @return
     */
    public Map insertFailedResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50003");
        resultMap.put("msg", "INSERT_FAILED");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * INSERT 失败返回值
     *
     * @return
     */
    public Map insertFailedResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50003");
        resultMap.put("msg", "INSERT_FAILED");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 插入请求成功返回值
     *
     * @return
     */
    public Map insertSuccseeResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "INSERT_SUCCESS");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * 插入请求成功返回值
     *
     * @return
     */

    public Map insertSuccseeResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "INSERT_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * UPDATE成功返回值
     *
     * @return
     */
    public Map updateSuccseeResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "UPDATE_SUCCESS");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * UPDATE成功返回值
     *
     * @param result
     * @return
     */
    public Map updateSuccseeResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "UPDATE_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * UPDATE请求失败返回值
     *
     * @return
     */
    public Map updateFailedResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50004");
        resultMap.put("msg", "UPDATE_FAILED");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * UPDATE请求失败返回值
     *
     * @return
     */
    public Map updateFailedResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50004");
        resultMap.put("msg", "UPDATE_FAILED");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * DELETE成功返回值
     * @return
     */
    public Map deleteSuccseeResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","DELETE_SUCCESS");
        resultMap.put("result",result);
        return resultMap;
    }


    /**
     * DELETE请求失败返回值
     * @return
     */
    public Map deleteFailedResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","50005");
        resultMap.put("msg","DELETE_FAILED");
        resultMap.put("result",result);
        return resultMap;
    }


    public Map failedRequest(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50006");
        resultMap.put("msg", "FAILED_REQUEST");
        resultMap.put("result", result);
        return resultMap;
    }


    public Map registerSuccseeResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "REGISTER_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    public Map registerFiledResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50008");
        resultMap.put("msg", "REGISTER_FAILED");
        resultMap.put("result", result);
        return resultMap;
    }


}
