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

    public Result(int statusCode, String code , String message) {
        this(statusCode, code,null, message);
    }

}
