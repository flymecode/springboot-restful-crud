/**
 * @author: maxu1
 * @date: 2018/12/20 8:57
 */

package com.example.demo.controller;

import com.example.demo.exception.UserNotExitsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

	@ResponseBody
	@ExceptionHandler(UserNotExitsException.class)
	public String handlerException(Exception e) {
		Map<String, Object> map = new HashMap<>();
		map.put("code", "user not exist");
		map.put("message", e.getMessage());
		return "forward:/error";
	}


}
