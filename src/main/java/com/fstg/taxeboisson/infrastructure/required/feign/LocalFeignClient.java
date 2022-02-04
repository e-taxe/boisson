package com.fstg.taxeboisson.infrastructure.required.feign;

import com.fstg.taxeboisson.infrastructure.vo.LocaleVo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="LOCAL-SERVICE")
public interface LocalFeignClient {
    @GetMapping("/api/local/ref/{ref}")
    public LocaleVo findByRef(@PathVariable String ref);
}
