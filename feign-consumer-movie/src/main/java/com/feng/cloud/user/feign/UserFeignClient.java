package com.feng.cloud.user.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feng.cloud.FeignConfiguration;
import com.feng.cloud.user.entity.User;

@FeignClient(name = "feng-provider-user",configuration = FeignConfiguration.class
 , fallback = FeignClientFallback.class
 , fallbackFactory = FeignClientFallbackFactory.class)
public interface UserFeignClient {
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public User findById(@PathVariable("id") Long id);
}
