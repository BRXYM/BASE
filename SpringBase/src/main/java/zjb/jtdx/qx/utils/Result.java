package zjb.jtdx.qx.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private Integer statusCode;
    private String code;
    private Object list;
    private String message;

    public Result(int statusCode, String code, String message) {
        this(statusCode, code, null, message);
    }

    public static Result success(Object list, String message) {
        return new Result(200, "SUCCESS", list, message);
    }

    public static Result success(String message) {
        return new Result(200, "SUCCESS", null, message);
    }

    public static Result fail(String message) {
        return new Result(500, "FAILURE", null, message);
    }
}
