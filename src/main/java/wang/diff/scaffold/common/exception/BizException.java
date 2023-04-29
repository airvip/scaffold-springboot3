package wang.diff.scaffold.common.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class BizException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -2273208557633301252L;
    private final HttpStatus httpStatus;
    private final String bizCode;
    private final Object[] args;

    public BizException(HttpStatus httpStatus, String bizCode, Object... args) {
        super(bizCode == null ? "" : bizCode);
        this.httpStatus = httpStatus;
        this.bizCode = bizCode;
        this.args = args;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getBizCode() {
        return bizCode;
    }

    public Object[] getArgs() {
        return args;
    }

}

