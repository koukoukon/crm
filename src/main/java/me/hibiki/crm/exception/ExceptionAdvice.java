package me.hibiki.crm.exception;

import me.hibiki.crm.util.ResultEntity;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 高弘昆
 * @date 2020/7/18 10:45
 */
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public ResultEntity<Object> systemExceptionAdvice(SystemException systemException) {
        return new ResultEntity<Object>().code(-1).message(systemException.getMessage());
    }
    @ExceptionHandler(AuthorizationException.class)
    public ResultEntity<Object> authorizationExceptionAdvice(AuthorizationException authorizationException) {
        return new ResultEntity<Object>().code(-1).message(authorizationException.getMessage());
    }

}
