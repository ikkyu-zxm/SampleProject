package xyz.ikkyu.sample.service.dynamic.injection.controller;

import xyz.ikkyu.sample.service.dynamic.injection.service.CalculateService;
import xyz.ikkyu.sample.service.dynamic.injection.service.TestInjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestInjectionController {

    @Autowired
    private TestInjectionService testInjectionService;

    @Autowired
    private CalculateService calculateService;

    @GetMapping("/testInjection")
    public String getHello() {
        String testList = testInjectionService.getList("code123", "name456");
        String calculateResult = calculateService.getResult("测试");
        return (testList + "," + calculateResult);
    }

}