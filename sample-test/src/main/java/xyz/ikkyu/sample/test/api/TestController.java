package xyz.ikkyu.sample.test.api;

import xyz.ikkyu.sample.test.domain.BackArchivesGoodsInfoRespVO;
import xyz.ikkyu.sample.test.service.TestService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author xinming
 * @Date 2019/11/23 15:51
 */
@Slf4j
@Api("测试controller")
@RestController
@RequestMapping("sample/test")
public class TestController {

    @Autowired
    private TestService testService;


    @ApiOperation("测试传输")
    @PostMapping("transfer")
    public void testTransfer(@RequestBody String data) {
        log.info("transfer success. data : {}",data);

        JSONObject jsonObject = JSON.parseObject(data);
        BackArchivesGoodsInfoRespVO backArchivesGoodsInfoRespVO = JSON.parseObject(data, BackArchivesGoodsInfoRespVO.class);

        log.info("JSONObject: {}",JSON.toJSONString(backArchivesGoodsInfoRespVO));

    }

    @ApiOperation("测试传输")
    @GetMapping("test")
    public String test() {
        log.info("--------------");
       return "hello";
    }

    @ApiOperation("测试传输")
    @PostMapping("testMap")
    public String test(@RequestBody Map<String,Object> map) {
        Object list = map.get("list");
        if (list instanceof List) {
            System.out.println(((List) list).get(0));
        }
        log.info("--------------");
        return "hello";
    }

    @ApiOperation("test redis save string")
    @GetMapping("saveString")
    public String saveString(String name,@RequestParam(required = false) String value) {
        return testService.saveString(name,value);
    }


    @ApiOperation("test redis save hash")
    @GetMapping("saveHash")
    public String saveHash(String hkey ,String key ,@RequestParam(required = false) String value) {
        return testService.saveHash(hkey,key,value);
    }

    @ApiOperation("test redis save set")
    @PostMapping("saveSet")
    public String saveSet(@RequestParam String key ,@RequestBody List<Object> values) {
        return testService.saveSet(key,values.toArray());
    }

    @ApiOperation("test redis save Sorted Set")
    @GetMapping("saveSortedSet")
    public String saveSortedSet(String key,@RequestParam(required = false) String value, double sort) {
        return testService.saveSortedSet(key,value,sort);
    }

    @ApiOperation("test redis increment Sorted Set")
    @GetMapping("incrementSortedSet")
    public String incrementSortedSet(String key, String value, double sort) {
        return testService.incrementSortedSet(key,value,sort);
    }


    @ApiOperation("test redis increment string")
    @GetMapping("incrementString")
    public String incrementString(String name) {
        return testService.incrementString(name);
    }


    @ApiOperation("test redis increment hash")
    @GetMapping("incrementHash")
    public String incrementHash(String hkey ,String key) {
        return testService.incrementHash(hkey,key);
    }

    @ApiOperation("testDelayer")
    @GetMapping("testDelayer")
    public Boolean testDelayer() {
        testService.testTransaction();
        return Boolean.TRUE;
    }
}
