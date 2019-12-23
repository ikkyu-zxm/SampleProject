package cn.ikkyu.ftp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinming
 */
@RestController()
@RequestMapping("sample/ftp/file")
public class HelloWorldController {

    
    @GetMapping("world")
    public String helloWorld() {
        return "hello world";
    }

}
