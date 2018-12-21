/**
 * @author: maxu1
 * @date: 2018/12/20 13:21
 */

package com.example.demo.controller;

import com.example.demo.entitys.Book;
import com.example.demo.service.BookCategoryService;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	@Autowired
	private BookCategoryService bookCategoryService;

	@GetMapping("/book/search")
	public String search(@RequestParam("search") String search, HttpServletRequest request) {
		Book book;

		if (null == search || StringUtils.isEmpty(search.trim())) {
			request.setAttribute("msg", "输入错误");
			return "msg";
		}
		if(search.matches("[0-9]{13}")){
			book = bookService.findBookByIsbn(search);
		}else{
			book = bookService.findBookByName(search);
		}
		if(book != null){
			request.setAttribute("book", book);
			return "bookInfo";
		} else {
			request.setAttribute("msg", "抱歉未查找到此书");
			return "msg";
		}
	}

	@GetMapping("/book/{id}/bookinfo")
	public String findBookById(@PathVariable("id") Integer bookId, HttpServletRequest request) {
		Book book =  bookService.findBookById(bookId);
		request.setAttribute("book", book);
		return "/admin/bookInfo";
	}

	@GetMapping("/category/{typeId}/list")
	public String findBookByTypeId(@PathVariable("typeId") Integer typeId,HttpServletRequest request) {
		int page = 1;
		int rows = 2;
		//PageInfo<Book> pageInfo = bookService.findBookByTypeId(typeId, page, rows);
		List<Book> list = bookCategoryService.findBookByTypeId(typeId);
		request.setAttribute("list", list);
		return "/admin/list";
	}
}
