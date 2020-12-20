package com.heeexy.example.controller.common;

import com.heeexy.example.util.ResponseCode;
import com.heeexy.example.util.RestResponse;

public class BaseController {

    private static final String SUCCESS_MESSAGE = "success";


    public RestResponse success() {
        return new RestResponse(ResponseCode.SUCCESS.code(), SUCCESS_MESSAGE);
    }

    public RestResponse success(Object data) {
        return new RestResponse(ResponseCode.SUCCESS.code(), SUCCESS_MESSAGE, data);
    }

    public RestResponse success(Object data, String message) {
        return new RestResponse(ResponseCode.SUCCESS.code(), message, data);
    }

    /**
     * 客户端参数错误
     *
     * @param message
     * @return
     */
    public RestResponse fail(String message) {
        return new RestResponse(ResponseCode.FAIL.code(), message);
    }

    /**
     *  资源不存在，响应404
     * @param message
     * @return
     */
    public RestResponse notFound(String message){
        return new RestResponse(ResponseCode.NOT_FOUND.code(), message);
    }

    /**
     * 服务器内部错误
     *
     * @param message
     * @return
     */
    public RestResponse error(String message) {
        return new RestResponse(ResponseCode.INTERNAL_SERVER_ERROR.code(), message);
    }

    /**
     * 未认证（签名错误）
     *
     * @return
     */
    public RestResponse unauthorized() {
        return new RestResponse(ResponseCode.UNAUTHORIZED.code(), null);
    }


    public RestResponse unauthorized(String message) {
        return new RestResponse(ResponseCode.UNAUTHORIZED.code(), message);
    }


}
