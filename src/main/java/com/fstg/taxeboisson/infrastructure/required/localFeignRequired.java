package com.fstg.taxeboisson.infrastructure.required;

import com.fstg.taxeboisson.infrastructure.vo.LocaleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "LOCALE")
public interface localFeignRequired {
    @GetMapping("/ref/{ref}")
    public LocaleVo findByRef(@PathVariable String ref);
}
