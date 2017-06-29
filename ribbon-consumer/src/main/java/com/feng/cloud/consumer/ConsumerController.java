package com.feng.cloud.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/hello/{name}")
	public String index(@PathVariable("name") String name) {
		return this.restTemplate.getForObject("http://feng-producer/hello?name=" + name, String.class);
	}

	@GetMapping("/log-user-instance")
	public void logUserInstance() {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-user");
		// 打印当前选择的是哪个节点
		LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
	}

}
