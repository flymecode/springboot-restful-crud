/**
 * @author: maxu1
 * @date: 2018/12/20 13:34
 */

package com.example.demo.controller;

import com.example.demo.entitys.User;
import com.example.demo.entitys.UserInfo;
import com.example.demo.service.UserInfoService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Constants;
import com.example.demo.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Date;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserInfoService userInfoService;
	/**aram response
	 * @return
	 * @throws Exception
	 */

	@ResponseBody
	@PostMapping("/user/check")
	public boolean checkUserName(@RequestParam("username") String username, HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=utf-8");
		return userService.checkUserName(username);
	}

	/**
	 * 用户注册UI
	 * @return
	 */
	@GetMapping("/user/register")
	public String userRegistUI() {
		return "regist";
	}

	/**
	 * 用户注册
	 *
	 * @param request
	 * @return
	 */
	@PostMapping("/user/register")
	public String userRegist(@RequestParam("username") String username,
	                         @RequestParam("password") String password,
	                         HttpServletRequest request) throws SQLException {

		User user = new User();
		user.setUserName(username);
		user.setPassWord(password);
		user.setCode(UUIDUtils.getCode());
		user.setState(0);
		user.setCreateTime(new Date());
		userService.userRegist(user);
		try {
			//EmailUitls.sendAccountActivateEmail(user);
			request.setAttribute("msg", "用户注册成功，请激活！");
		} catch (Exception e) {
			// 注册失败，跳转到提示页面
			request.setAttribute("msg", "用户注册失败，请重新注册！");
		}
		return "admin/msg";
	}

	/**
	 * 邮箱激活
	 *
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	public String activate(@RequestParam("code") String code,
	                       @RequestParam("checkCode") String checkCode,
	                       HttpServletRequest request) throws SQLException {
		User user = userService.findUserByCode(code);
		boolean sure = true;
		//boolean sure = GenerateLinkUtils.verifyCheckcode(user, checkCode);
		if(sure){
			userService.updateUserState(user);
			return "login";
		} else {
			request.setAttribute("msg","激活失败，请重新激活");
			return "msg";
		}
	}

	/**
	 * 查找用户个人信息
	 * @return
	 */
	@GetMapping("/user/{id}/info")
	public void findUserInfo(@PathVariable("id") Integer id ) throws SQLException {
		UserInfo userInfo = userInfoService.findUserInfo(id);
	}



	/**
	 * 用户退出
	 *
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/user/quit")
	public String userQuit(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:index";
	}

	/**
	 * 修改密码
	 *
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/user/{id}/change")
	public String changePassword(@RequestParam("newPassword") String newPassword,
	                             HttpSession session) {
		User user = (User) session.getAttribute(Constants.SESSION_USER);
		user.setPassWord(newPassword);
		Boolean result = userService.updateUser(user);
		if (result) {
			return "login";
		} else {
			return "msg";
		}
	}
}

