package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import com.example.websocket.EchoHandler;

@RestController
@RequestMapping("/messages")
public class MessagesController {

	@Autowired
	EchoHandler echoHandler;

	@RequestMapping(value = "/{message}", method = RequestMethod.GET)
	public String getRoom(@PathVariable("message") String message) throws Exception {
		// 受け取ったメッセージをWebSocketクライアントに送信
		echoHandler.handleTextMessage(null, new TextMessage(message));
		return message;
	}
}
