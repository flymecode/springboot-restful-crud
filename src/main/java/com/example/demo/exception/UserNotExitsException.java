/**
 * @author: maxu1
 * @date: 2018/12/20 8:54
 */

package com.example.demo.exception;

import com.example.demo.utils.Constant;


public class UserNotExitsException extends RuntimeException {

	public UserNotExitsException() {
		super(Constant.USER_NOT_EXIST);
	}
}
