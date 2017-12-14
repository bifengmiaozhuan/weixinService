package weixinService.util;

import java.util.ArrayList;
import java.util.Collections;

/*
 * 验证token信息的工具类
 */
public class CheckSignatureUtil {
	public static final String token = "bifengmiaozhuan";
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		ArrayList<String> list = new ArrayList<String>();
		list.add(token);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);
		StringBuilder content = new StringBuilder();
		for(String str:list){
			content.append(str);
		}
		return signature.equals(HashUtil.hash(content.toString(),"SHA1"));
	}
}
