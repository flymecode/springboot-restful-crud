/**
 * @author: maxu1
 * @date: 2018/12/20 13:31
 */

package com.example.demo.controller;

import com.example.demo.entitys.Book;
import com.example.demo.service.BookService;
import com.example.demo.utils.Constants;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private BookService bookService;

	public String execute(HttpServletRequest request, HttpServletResponse response)  {
		Jedis jedis = jedisPool.getResource();
		List<Book> newBook = JSONArray.toList(JSONArray.fromObject(jedis.get(Constants.NEW_BOOK)), Book.class);
		List<Book> hotBook = JSONArray.toList(JSONArray.fromObject(jedis.get(Constants.HOT_BOOK)), Book.class);
		if (newBook.size() <= 1) {
			newBook = bookService.findNewBook();
			jedis.set(Constants.NEW_BOOK, JSONArray.fromObject(newBook).toString());

		}
		if (hotBook.size() <= 1) {
			hotBook = bookService.findHotBook();
			jedis.set(Constants.HOT_BOOK, JSONArray.fromObject(hotBook).toString());
		}
		jedis.close();
		request.setAttribute("newBook", newBook);
		request.setAttribute("hotBook", hotBook);
		return "index";
	}
}
