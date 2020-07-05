package xyz.ikkyu.sample.test.api;

import xyz.ikkyu.sample.test.domain.req.CatReqVO;
import xyz.ikkyu.sample.test.domain.resp.CatRespVO;
import xyz.ikkyu.sample.test.service.CatService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.List;

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


    @PostMapping("saveCat")
    @ApiOperation(value = "保存cat",notes = "新明")
    public Boolean saveCat(CatReqVO reqVO) {
        return catService.saveCat(reqVO);
    }



    @GetMapping("searchByName")
    @ApiOperation(value = "通过名称查询",notes = "新明")
    public List<CatRespVO> searchByName(@RequestParam("name") String name) {
        return catService.searchByName(name);
    }

}
