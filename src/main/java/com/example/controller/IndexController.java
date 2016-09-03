/*
 * file name  : IndexController.java
 */
package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * トップページ用コントローラーです。
 */
@Controller
public class IndexController {
	/**
	 * トップページを表示します。
	 * 
	 * @return テンプレートのパス
	 */
	@RequestMapping("/")
	public String showTopPage() {
		return "index";
	}
}