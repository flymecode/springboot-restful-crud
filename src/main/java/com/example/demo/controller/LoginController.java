/**
 * @author: maxu1
 * @date: 2018/12/16 9:31
 */

package com.example.demo.controller;

import com.example.demo.entitys.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/user/login")
	public String login() throws SQLException {
		return "login";
	}


	@PostMapping(value = "/user/login")
	public String login(User user,Model model, HttpSession session) throws SQLException {
		User c_user = userService.userLogin(user);
		if (null != c_user) {
			session.setAttribute(Constants.SESSION_USER, user);
			return "redirect:/index";
		}
		model.addAttribute("msg", "Login Not Success");
		return "login";
	}
	/**
	 * 用户登陆
	 */
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException {



//			if (null != user) {
//				//TODO
//				if ("#".equals(autoLogin)) {
//					// 发送Cookie给客户端
//					Cookie cookie = new Cookie(Constants.COOKIE_USER, user.getUserName() + "#" + temp);
//					cookie.setMaxAge(60 * 60 * 24);
//					cookie.setPath(request.getContextPath());
//					response.addCookie(cookie);
//					HttpSession session = request.getSession();
//					SessionMapUtils.userLoginHandle(request);
//					SessionMapUtils.USER_SESSION.put(user.getUserName().trim(), session);
//					SessionMapUtils.SESSIONID_USER.put(session.getId(),user.getUserName());
//					session.setAttribute(Constants.SESSION_USER, user);
//					response.sendRedirect("/index.html");
//				} else {
//					// 用户登陆成功，将用户信息放入session
//					HttpSession session = request.getSession();
//					//处理用户登录(保持同一时间同一账号只能在一处登录)
//					SessionMapUtils.userLoginHandle(request);
//					//添加用户与HttpSession的绑定
//					SessionMapUtils.USER_SESSION.put(user.getUserName().trim(), session);
//					//添加sessionId和用户的绑定
//					SessionMapUtils.SESSIONID_USER.put(session.getId(),user.getUserName());
//					session.setAttribute(Constants.SESSION_USER, user);
//					// 用户登陆成功，将用户信息放入session
//					response.sendRedirect("/index.html");
//				}
//
//			}
//
//		} catch (Exception e) {
//			String msg = e.getMessage();
//			// 放入失败的信息
//			request.setAttribute("msg", msg);
//			// 转发到登陆界面
//			return "login";
//		}
		return null;
	}
}
