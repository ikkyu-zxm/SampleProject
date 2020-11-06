package xyz.ikkyu.sample.api;

import xyz.ikkyu.sample.domain.resp.StoreRespVo;
import xyz.ikkyu.sample.service.JdbcQueryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author xinming
 * @Date 2020/5/31 14:12
 */
@RestController
@RequestMapping("test/jdbc")
public class JdbcQueryController {


    private final JdbcQueryService jdbcQueryService;


    public JdbcQueryController(JdbcQueryService jdbcQueryService) {
        this.jdbcQueryService = jdbcQueryService;
    }


    @PostMapping("query")
    @ApiOperation(value = "测试jdbc查询",notes = "祝新明")
    public List<StoreRespVo> query(@RequestBody Map<String,Object> param) {
        return jdbcQueryService.query(param);
    }

}
