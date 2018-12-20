/**
 * @author: maxu1
 * @date: 2018/12/20 9:09
 */

package com.example.demo.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class MyErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
		Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
		errorAttributes.put("ext", ext);
		return errorAttributes;
	}


}
