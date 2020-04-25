package cn.ikkyu.sample.test.api;

import cn.ikkyu.sample.test.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * @author xinming
 * @Date 2019/12/28 9:49
 */
@RestController
@RequestMapping("/api/cat")
public class CatController {

    @Autowired
    private CatService catService;

    @GetMapping("count")
    public Long getCatValuation() {
        return catService.getCatValuation();
    }



    @PostMapping("testGetParameter")
    public void testGetParameter(HttpServletRequest request) throws Exception {
//        String data = request.getParameter("data");

        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }

        System.out.println(wholeStr);
    }

}
