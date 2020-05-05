package cn.ikkyu.ftp.poi;

import cn.ikkyu.ftp.domain.aaa;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author xinming
 */
@Slf4j
public class ProjectTest {


    @Test
    public void tet() {

//        List<ProductQualificationReqVo> list = new ArrayList<>(1);
//        ProductQualificationReqVo productQualificationReqVo = new ProductQualificationReqVo();
//        productQualificationReqVo.setDeadLine(1221421L);
//        list.add(productQualificationReqVo);
//        List<aaa> transform = Lists.transform(list, (vo) -> {
//            aaa aaa = new aaa();
//            aaa.setDeadLine(productQualificationReqVo.getDeadLine());
//            return aaa;
//        });
//        System.out.println(JSONObject.toJSONString(transform));

        aaa aaa = new aaa();
        aaa.setDate(122424L);
        System.out.println(JSONObject.toJSONString(aaa));
    }




    @Test
    public void tet2() {

        BigDecimal a = new BigDecimal(2);
        BigDecimal b = new BigDecimal(2);

        System.out.println(a.compareTo(b));
    }

}
