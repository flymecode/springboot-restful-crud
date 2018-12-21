/**
 * @author: maxu1
 * @date: 2018/12/20 13:11
 */

package com.example.demo.controller;


import com.example.demo.entitys.Category;
import com.example.demo.service.BookCategoryService;
import com.example.demo.utils.Constants;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class BookCategoryController {

	@Autowired
	private JedisPool jedisPool;

	@Autowired
	private BookCategoryService bookCategoryService;

	@GetMapping("/category/list")
	public String findCategory(HttpServletResponse response) {

		Jedis jedis = jedisPool.getResource();
		String allCategory = jedis.get(Constants.ALL_CATEGORY);
		if (allCategory == null || StringUtils.isEmpty(allCategory)) {
			List<Category> list = bookCategoryService.findBookCategory();
			allCategory = JSONArray.fromObject(list).toString();
			jedis.set(Constants.ALL_CATEGORY, allCategory);
		}
		return allCategory;

	}

}
