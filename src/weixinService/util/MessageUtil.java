package weixinService.util;

import java.util.Date;

import weixinService.domain.TextMessage;

public class MessageUtil {
	public static final String MESSAGE_SUBSCIBE = "subscribe";
	public static final String MESSAGE_TEXT = "text";
	
	public static String textMsg(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return XmlUtil.textMsgToxml(text);
	}
	
	public static String Follow(String toUserName,String fromUserName){
		return textMsg(toUserName, fromUserName, "欢迎关注，精彩内容不容错过！！！");
	}
}
