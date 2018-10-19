package com.itlwx.common.exception;

public class CategoryException extends BlogException{

    public CategoryException(ErrorCode ecode) {
        super(ecode);
    }

    public CategoryException(String msg) {
        super(msg);
    }

    public CategoryException(Throwable cause) {
        super(cause);
    }

    public CategoryException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
