package com.myimooc.wxdevadvanced.rest;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myimooc.wxdevadvanced.domain.EventMessage;
import com.myimooc.wxdevadvanced.domain.NewsMessage;
import com.myimooc.wxdevadvanced.domain.TextMessage;
import com.myimooc.wxdevadvanced.util.MessageUtils;
import com.myimooc.wxdevadvanced.util.WeixinUtils;

/**
 * 处理消息请求与响应
 * @author ZhangCheng on 2017-08-11
 *
 */
@RestController
public class MessageRest {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageRest.class);
	
	/**
	 * 接收微信服务器发送的POST请求
	 * @throws Exception 
	 */
	@PostMapping("textmessage")
	public Object textmessage(TextMessage msg) throws Exception{
		
		logger.info("请求参数：{}",msg.toString());
		
		// 文本消息
		if(Objects.equals(MessageUtils.MESSAGE_TEXT, msg.getMsgType())){
			TextMessage textMessage = new TextMessage();
			// 关键字 1
			if(Objects.equals("1", msg.getContent())){
				textMessage = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.firstMenu());
				return textMessage;
			}
			// 关键字 2
			if(Objects.equals("2", msg.getContent())){
				NewsMessage newsMessage = MessageUtils.initNewsMessage(msg.getToUserName(), msg.getFromUserName());
				return newsMessage;
			}
			// 关键字 3
			if(Objects.equals("3", msg.getContent())){
				textMessage = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.threeMenu());
				return textMessage;
			}
			// 关键字 翻译
			if(msg.getContent().startsWith("翻译")){
				String word = msg.getContent().replaceAll("^翻译","").trim();
				if("".equals(word)){
					textMessage = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.threeMenu());
					return textMessage;
				}
				textMessage = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(),WeixinUtils.translate(word));
				return textMessage;
			}
			// 关键字 ？? 调出菜单
			if(Objects.equals("？", msg.getContent()) || Objects.equals("?", msg.getContent())){
				textMessage = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.menuText());
				return textMessage;
			}
			
			// 非关键字
			textMessage.setFromUserName(msg.getToUserName());
			textMessage.setToUserName(msg.getFromUserName());
			textMessage.setMsgType(MessageUtils.MESSAGE_TEXT);
			textMessage.setCreateTime(new Date().getTime()+"");
			textMessage.setContent("您发送的消息是：" + msg.getContent());
			return textMessage;
		}
		return null;
	}
	
	/**
	 * 接收微信服务器发送的POST请求
	 */
	@PostMapping("eventmessage")
	public Object eventmessage(Map<String,String> param){
		
		EventMessage msg = new EventMessage();
		BeanUtils.copyProperties(param, msg);
		// 事件推送
		if(Objects.equals(MessageUtils.MESSAGE_EVENT, msg.getMsgType())){
			// 关注
			if(Objects.equals(MessageUtils.MESSAGE_SUBSCRIBE, msg.getEvent())){
				TextMessage text = new TextMessage();
				text = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.menuText());
				return text;
			}
			// 菜单 点击类型
			if(Objects.equals(MessageUtils.MESSAGE_CLICK, msg.getEvent())){
				TextMessage text = new TextMessage();
				text = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.menuText());
				return text;
			}
			// 菜单 视图类型
			if(Objects.equals(MessageUtils.MESSAGE_VIEW, msg.getEvent())){
				String url = param.get("EventKey");
				return MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(),url);
			}
			// 菜单 扫码事件
			if(Objects.equals(MessageUtils.MESSAGE_SCANCODE, msg.getEvent())){
				String key = param.get("EventKey");
				return MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(),key);
			}
			// 菜单 地理位置
			if(Objects.equals(MessageUtils.MESSAGE_LOCATION, msg.getEvent())){
				String Label = param.get("Label");
				return MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(),Label);
			}
		}
		return "no message";
	}
	
}
