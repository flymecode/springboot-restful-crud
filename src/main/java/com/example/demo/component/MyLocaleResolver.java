/**
 * @author: maxu1
 * @date: 2018/12/16 8:57
 */

package com.example.demo.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
	@Override
	public Locale resolveLocale(HttpServletRequest httpServletRequest) {
		String l = httpServletRequest.getParameter("l");
		Locale locale = Locale.getDefault();
		if (!StringUtils.isEmpty(l)) {
			String[] splits = l.split("_");
			locale = new Locale(splits[0],splits[1]);
		}
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

	}
}
