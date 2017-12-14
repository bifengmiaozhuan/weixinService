package weixinService.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import weixinService.util.CheckSignatureUtil;
import weixinService.util.MessageUtil;
import weixinService.util.XmlUtil;

public class WeixinServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	/*
	 * ��Ӧget����΢��Ĭ��tokenУ��ʱʹ��get����
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//����΢������У����Ϣ�����ù涨����
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		PrintWriter out = resp.getWriter();
		//��΢��ָ���Ĺ������У�鲢������Ӧ
		if(CheckSignatureUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		} 
	}
	/*
	 * ��Ӧpost����΢������Ϣ�Ͳ˵��������ǲ���post����
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		String message = "success";
		try {
			//��΢�ŷ��ص�xml��Ϣת���map
			Map<String, String> map = XmlUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");//��Ϣ��Դ�û���ʶ
			String toUserName = map.get("ToUserName");//��ϢĿ���û���ʶ
			String msgType = map.get("MsgType");//��Ϣ����
			String content = map.get("Content");//��Ϣ����
			
			String eventType = map.get("Event");
			if(MessageUtil.MSGTYPE_EVENT.equals(msgType)){//���Ϊ�¼�����
				if(MessageUtil.MESSAGE_SUBSCIBE.equals(eventType)){//�������¼�
					message = MessageUtil.subscribeForText(toUserName, fromUserName);
				}else if(MessageUtil.MESSAGE_UNSUBSCIBE.equals(eventType)){//����ȡ�������¼�
					message = MessageUtil.unsubscribe(toUserName, fromUserName);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			out.println(message);
			if(out!=null){
				out.close();
			}
		}
	}
}
