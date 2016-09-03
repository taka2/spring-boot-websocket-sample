/*
 * file name  : EchoHandler.java
 */
package com.example.websocket;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * エコーハンドラーです。
 */
@Component
public class EchoHandler extends TextWebSocketHandler {
	/**
	 * セッションプールです。
	 */
	private Map<String, WebSocketSession> sessionPool = new ConcurrentHashMap<>();

	/**
	 * 接続が確立したセッションをプールします。
	 * 
	 * @param session
	 *            セッション
	 * @throws Exception
	 *             例外が発生した場合
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		this.sessionPool.put(session.getId(), session);
	}

	/**
	 * 切断された接続をプールから削除します。
	 * 
	 * @param session
	 *            セッション
	 * @param status
	 *            ステータス
	 * @throws Exception
	 *             例外が発生した場合
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		this.sessionPool.remove(session.getId());
	}

	/**
	 * ハンドリングしたテキストメッセージをグローバルキャストします。
	 * 
	 * @param session
	 *            セッション
	 * @param message
	 *            メッセージ
	 */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for (Entry<String, WebSocketSession> entry : this.sessionPool.entrySet()) {
			entry.getValue().sendMessage(message);
		}
	}
}