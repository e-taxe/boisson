package com.fstg.taxeboisson.infrastructure.required.feign.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.fstg.taxeboisson.infrastructure.required.feign.LocalFeignClient;
import com.fstg.taxeboisson.infrastructure.vo.LocaleVo;

@Service
public class LocalFeignConsumer {
	@Autowired
	private LocalFeignClient localFeignClient;
	
	 public LocaleVo findByRef(String ref) {
		 return localFeignClient.findByRef(ref);
	 }
	
}
