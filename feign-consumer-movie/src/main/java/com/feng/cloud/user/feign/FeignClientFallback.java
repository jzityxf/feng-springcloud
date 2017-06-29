package com.feng.cloud.user.feign;

import org.springframework.stereotype.Component;

import com.feng.cloud.user.entity.User;

@Component
class FeignClientFallback implements UserFeignClient {
  @Override
  public User findById(Long id) {
    User user = new User();
    user.setId(-1L);
    user.setUsername("默认用户");
    return user;
  }
}