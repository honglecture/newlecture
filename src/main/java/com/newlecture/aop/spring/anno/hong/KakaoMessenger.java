package com.newlecture.aop.spring.anno.hong;

import org.springframework.stereotype.Component;

@Component("kakao")
public class KakaoMessenger implements Messenger {

	@Override
	public String sendText(String text) {
		return text;
	}

	@Override
	public String receivedText(String text) {
		return text;
	}

}
