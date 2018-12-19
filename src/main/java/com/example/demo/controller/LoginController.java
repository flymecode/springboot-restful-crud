/**
 * @author: maxu1
 * @date: 2018/12/16 9:31
 */

package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

	@PostMapping(value = "/user/login")
	public String login(@RequestParam("username") String username,
	                    @RequestParam("password") String password,
	                    Model model, HttpSession session) {
		if (!StringUtils.isEmpty(username) && "123".equals(password)) {
			session.setAttribute("loginUser", "username");
			return "redirect:/main.html";
		}
		model.addAttribute("msg", "UserName is Error");
		return "login";
	}
}
