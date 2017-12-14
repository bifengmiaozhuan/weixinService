package weixinService.util;

import java.util.Date;

import weixinService.domain.TextMessage;

/*
 * 消息处理工具类
 */
public class MessageUtil {
	public static final String MSGTYPE_EVENT = "event";//消息类型--事件
	public static final String MESSAGE_SUBSCIBE = "subscribe";//消息事件类型--订阅事件
	public static final String MESSAGE_UNSUBSCIBE = "unsubscribe";//消息事件类型--取消订阅事件
	public static final String MESSAGE_TEXT = "text";//消息类型--文本消息
	
	/*
	 * 组装文本消息
	 */
	public static String textMsg(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return XmlUtil.textMsgToxml(text);
	}
	
	/*
	 * 响应订阅事件--回复文本消息
	 */
	public static String subscribeForText(String toUserName,String fromUserName){
		return textMsg(toUserName, fromUserName, "欢迎关注，精彩内容不容错过！！！");
	}
	
	/*
	 * 响应取消订阅事件
	 */
	public static String unsubscribe(String toUserName,String fromUserName){
		//TODO 可以进行取关后的其他后续业务处理
		System.out.println("用户："+ fromUserName +"取消关注~");
		return "";
	}
}
