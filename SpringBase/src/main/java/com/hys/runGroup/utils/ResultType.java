package com.hys.runGroup.utils;

import lombok.Data;

@Data
public class ResultType<T> {
    private int code;
    private String message;
    private T data;

    // 成功时调用
    public static <T> ResultType<T> success(T data,String message) {
        ResultType<T> result = new ResultType<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    // 成功时调用，传递信息
    public static <T> ResultType<T> success(String message) {
        return success(null, message);
    }
    // 成功时调用，不带参数
    public static <T> ResultType<T> success() {
        return success(null, "");
    }

    // 失败时调用
    public static <T> ResultType<T> fail(String message) {
        ResultType<T> result = new ResultType<>();
        result.setCode(500);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}
