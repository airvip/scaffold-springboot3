package wang.diff.scaffold.common.exception;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;

@RestControllerAdvice
public class BackendExceptionHandler extends ResponseEntityExceptionHandler implements MessageSourceAware {
    /**
     * use plain-txt class name to prevent ClassNotFound
     */
    public static final String ACCESS_DENIED_EXCEPTION = "org.springframework.security.access.AccessDeniedException";
    public static final String DUPLICATE_KEY_EXCEPTION = "org.springframework.dao.DuplicateKeyException";
    private MessageSource messageSource;

    @Override
    public void setMessageSource(@NotNull MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static boolean isExceptionInstance(Throwable ex, String exceptionName) {
        Class<?> current = ex.getClass();
        while (current != null & current != Throwable.class) {
            if (current.getName().equals(exceptionName)) {
                return true;
            }
            current = current.getSuperclass();
        }
        return false;
    }

    @ExceptionHandler(BizException.class)
    public ResponseEntity<ErrResp> handleBizException(BizException ex, WebRequest request) {
        final String path = getPath(request);
        final String message = getMessage(ex);
        final HttpStatus httpStatus = ex.getHttpStatus();
        final String code = getCode(httpStatus, ex.getBizCode());
        final ErrResp resp = ErrResp.builder()
                .timestamp(new Date())
                .status(httpStatus.value())
                .code(code)
                .error(httpStatus.getReasonPhrase())
                .path(path)
                .message(message).build();
        logDetails(path, message, httpStatus, code);
        return new ResponseEntity<>(resp, httpStatus);
    }

    private void logDetails(String path, String message, HttpStatus httpStatus, String code) {
        if (httpStatus.is4xxClientError()) {
            if (logger.isInfoEnabled()) {
                logger.info(String.format("[%s] %s:%s %s ", path, httpStatus.value(), code, message));
            }
        } else if (httpStatus.is5xxServerError()) {
            if (logger.isWarnEnabled()) {
                logger.warn(String.format("[%s] %s:%s %s ", path, httpStatus.value(), code, message));
            }
        }
    }

    private String getCode(HttpStatus status, String bizCode) {
        if (!StringUtils.hasText(bizCode)) {
            return "http." + status.value();
        }
        return bizCode;
    }


    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrResp> handleSQLException(SQLException ex,
                                                      WebRequest request) {
        return handleBizException(new BizException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Persistence layer error"), request);
    }

    @ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
    public void handDuplicateKeyException(WebRequest request) {
        handleBizException(new BizException(HttpStatus.CONFLICT, "唯一索引异常，请检查请求的数据"), request);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrResp> handleThrowable(Throwable ex, WebRequest request) {
        return defaultExceptionHandle(ex, request);
    }

    private Map.Entry<HttpStatus, Boolean> specialExceptionProcessing(Throwable ex, HttpStatus status) {
        if (isExceptionInstance(ex, ACCESS_DENIED_EXCEPTION)) {
            return new AbstractMap.SimpleEntry<>(HttpStatus.FORBIDDEN, false);
        }
        if (isExceptionInstance(ex, DUPLICATE_KEY_EXCEPTION)) {
            return new AbstractMap.SimpleEntry<>(HttpStatus.CONFLICT, true);
        }
        if (ex instanceof IllegalArgumentException) {
            return new AbstractMap.SimpleEntry<>(HttpStatus.BAD_REQUEST, true);
        }
        if (ex instanceof IllegalStateException) {
            return new AbstractMap.SimpleEntry<>(HttpStatus.BAD_REQUEST, true);
        }
        return new AbstractMap.SimpleEntry<>(status, false);
    }

    private ResponseEntity<ErrResp> defaultExceptionHandle(Throwable ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Map.Entry<HttpStatus, Boolean> sep = specialExceptionProcessing(ex, status);
        status = sep.getKey();
        boolean printStack = sep.getValue();
        final String path = getPath(request);
        final String message = buildMessage(ex);
        final String code = getCode(status, null);
        final ErrResp resp = ErrResp.builder()
                .timestamp(new Date())
                .status(status.value())
                .code(code)
                .error(status.getReasonPhrase())
                .message(message)
                .path(path).build();
        if (status.is4xxClientError()) {
            if (logger.isInfoEnabled()) {
                if (printStack) {
                    logger.info(String.format("[%s] %s:%s %s ", path, status.value(), code, message), ex);
                } else {
                    logger.info(String.format("[%s] %s:%s %s ", path, status.value(), code, message));
                }
            }
        } else {
            if (logger.isWarnEnabled()) {
                logger.warn("unexpected error for request: " + path, ex);
            }
        }
        return new ResponseEntity<>(resp, status);
    }

    private String buildMessage(Throwable ex) {
        Throwable cause = ex;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        final String rst = cause.getMessage();
        if (StringUtils.hasText(rst)) {
            return rst;
        }
        return cause.getClass().getSimpleName();
    }


    private String getMessage(BizException ex) {
        if (!StringUtils.hasText(ex.getBizCode())) {
            return ex.getHttpStatus().getReasonPhrase();
        }
        if (messageSource == null) {
            return ex.getBizCode();
        }
        return messageSource.getMessage(ex.getBizCode(), ex.getArgs(), ex.getBizCode(), LocaleContextHolder.getLocale());
    }

    private String getPath(WebRequest request) {
        if (request instanceof ServletWebRequest) {
            return ((ServletWebRequest) request).getRequest().getRequestURI();
        }
        return request.getContextPath();
    }


    @Data
    @Builder
    public static class ErrResp {
        private final Date timestamp;
        private final int status;
        private final String path;
        private final String code;
        private final String error;
        private final Object message;
    }

}