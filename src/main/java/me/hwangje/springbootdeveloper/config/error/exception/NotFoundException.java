package me.hwangje.springbootdeveloper.config.error.exception;

import me.hwangje.springbootdeveloper.config.error.ErrorCode;

public class NotFoundException extends BusinessBaseException{
    public NotFoundException(ErrorCode errorCode){
        super(errorCode.getMessage(), errorCode);
    }

    public NotFoundException(){
        super(ErrorCode.ARTICLE_NOT_FOUND);
    }
}
