package org.example.jdk_21_demo;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.jdk_21_demo.chain.AbstractCreateOrderProcessor;
import org.example.jdk_21_demo.chain.dto.CreateDTO;
import org.example.jdk_21_demo.tactics.AbstractTracePushProcessor;
import org.example.jdk_21_demo.template.SheinTracePushTemplateProcessor;
import org.example.jdk_21_demo.template.TiktokTracePushTemplateProcessor;
import org.example.jdk_21_demo.template.dto.SheinDTO;
import org.example.jdk_21_demo.template.dto.TiktokDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Slf4j
class JDk21DemoApplicationTests {

	@Resource(name = "createOrderProcessorChain")
	private AbstractCreateOrderProcessor createOrderProcessorChain;

	@Resource(name = "createOrderProcessorChain2")
	private AbstractCreateOrderProcessor createOrderProcessorChain2;


	@Resource
	private Map<String, AbstractTracePushProcessor> tracePushMap;

	@Resource
	private TiktokTracePushTemplateProcessor tiktokTracePushTemplateProcessor;

	@Resource
	private SheinTracePushTemplateProcessor sheinTracePushTemplateProcessor;

	/**
	 * 责任链模式
	 */
	@Test
	void chainTest() {
		log.info("责任链1====================================");
		createOrderProcessorChain.handle(new CreateDTO("订单验证 -> 订单保存 -> 订单通知"));
		log.info("责任链2====================================");
		createOrderProcessorChain2.handle(new CreateDTO("订单保存 -> 订单通知"));

	}

	/**
	 * 策略模式
	 */
	@Test
	void tactiscTest() {
		AbstractTracePushProcessor shopeeTracePush = tracePushMap.get("shopee");
		AbstractTracePushProcessor tiktokTracePush = tracePushMap.get("tiktok");
		AbstractTracePushProcessor sheinTracePush = tracePushMap.get("shein");

		shopeeTracePush.pushTrace("shopee_order_NO");
		log.info("====================================");
		tiktokTracePush.pushTrace("tiktok_order_NO");
		log.info("====================================");
		sheinTracePush.pushTrace("shein_order_NO");
	}


	/**
	 * 模板方法
	 */
	@Test
	void templateTest() {

		tiktokTracePushTemplateProcessor.push(new TiktokDTO());
		log.info("====================================");


		sheinTracePushTemplateProcessor.push(new SheinDTO());
		log.info("====================================");
	}

}
