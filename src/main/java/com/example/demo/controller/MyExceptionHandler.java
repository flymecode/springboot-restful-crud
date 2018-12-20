/**
 * @author: maxu1
 * @date: 2018/12/20 8:57
 */

package com.example.demo.controller;

import com.example.demo.exception.UserNotExitsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

	@ResponseStatus(value= HttpStatus. HTTP_VERSION_NOT_SUPPORTED, reason="No such User")
	@ExceptionHandler(UserNotExitsException.class)
	public String handlerException(Exception e, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("code", "user not exist");
		map.put("message", e.getMessage());
		// 添加自定义的错误信息
		request.setAttribute("ext",map);
		return "forward:/error";
	}


}
