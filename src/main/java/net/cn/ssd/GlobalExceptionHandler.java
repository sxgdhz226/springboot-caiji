package net.cn.ssd;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常通知
 * 只在连接点抛出异常时才执行异常通知
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> resultError(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("errorMsg","500");
        map.put("参数错误","参数错误");
        return map;
    }

}
