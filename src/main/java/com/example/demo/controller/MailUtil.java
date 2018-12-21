/**
 * @author: maxu1
 * @date: 2018/12/20 13:41
 */

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailUtil {
	@Autowired
	JavaMailSender jms;

	@ResponseBody
	@GetMapping("/send")
	public String send(){
		//建立邮件消息
		SimpleMailMessage mainMessage = new SimpleMailMessage();
		//发送者
		mainMessage.setFrom("1272193309@qq.com");
		//接收者
		mainMessage.setTo("1272193309@qq.com");
		//发送的标题
		mainMessage.setSubject("嗨喽");
		//发送的内容
		mainMessage.setText("hello world");
		jms.send(mainMessage);
		return "1";
	}

}
