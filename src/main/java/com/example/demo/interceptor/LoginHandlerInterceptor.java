/**
 * @author: maxu1
 * @date: 2018/12/16 9:19
 */

package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object user = request.getSession().getAttribute("loginUser");
		if (user == null) {
			request.setAttribute("msg","NO Auth,Please Login In");
			request.getRequestDispatcher("/index.html").forward(request,response);
			return false;
		}
		return true;
	}
}
