package cn.ikkyu.sample.test.service.impl;

import base.xyz.ikkyu.util.JsonUtils;
import cn.ikkyu.sample.test.constant.FilterBoxConstant;
import cn.ikkyu.sample.test.dao.po.FilterBoxConfigPo;
import cn.ikkyu.sample.test.dao.po.TbTerminalInfoPo;
import cn.ikkyu.sample.test.dao.repository.FilterBoxConfigRepository;
import cn.ikkyu.sample.test.domain.resp.StoreRespVo;
import cn.ikkyu.sample.test.handle.JdbcMappingHandle;
import cn.ikkyu.sample.test.service.JdbcQueryService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xinming
 * @Date 2020/5/30 11:07
 */
@Slf4j
@Service
public class JdbcQueryServiceImpl implements JdbcQueryService {


    private final DataSource dataSource;

    private final FilterBoxConfigRepository filterBoxConfigRepository;

    private Map<Class<?>,Map<String , Field>> propertyMap = new ConcurrentHashMap<>();

    public JdbcQueryServiceImpl(DataSource dataSource, FilterBoxConfigRepository filterBoxConfigRepository) {
        this.dataSource = dataSource;
        this.filterBoxConfigRepository = filterBoxConfigRepository;
    }







    @Override
    public List<StoreRespVo> query(Map<String, Object> paramMap) {

        Set<String> filterCodeList = paramMap.keySet();

        List<FilterBoxConfigPo> filterBoxList = filterBoxConfigRepository.findByCodeIn(Lists.newArrayList(filterCodeList));

        StringBuffer sb = new StringBuffer();
        //参数数组
        sb.append("select * from tb_terminal_info where 1= 1");
        List<Object> queryParameters = new ArrayList<>(paramMap.size());
        for (int i = 0; i < filterBoxList.size(); i++) {
            FilterBoxConfigPo boxConfigPo = filterBoxList.get(i);
            String code = boxConfigPo.getCode();
            Object parameter = paramMap.get(code);
            buildSql(boxConfigPo, sb, parameter, queryParameters);
        }
        buildPage(paramMap, sb, queryParameters);


        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        ArgumentPreparedStatementSetter statementSetter = new ArgumentPreparedStatementSetter(queryParameters.toArray());

        List<Object> query = jdbcTemplate.query(sb.toString(), statementSetter, (resultSet, num) -> {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnName(i + 1);
                String columnClassName = metaData.getColumnClassName(i + 1);

                int columnType = metaData.getColumnType(i + 1);
            }
/*
            while (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    String columnClassName = metaData.getColumnClassName(i + 1);
                    int columnType = metaData.getColumnType(i + 1);
                }

            }*/

            return new Object();
        });

        List<TbTerminalInfoPo> list = jdbcTemplate.query(sb.toString(), queryParameters.toArray(), new JdbcMappingHandle(TbTerminalInfoPo.class,propertyMap));
        log.info("list {}", JsonUtils.obj2Json(list));
        return null;
    }

    public String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    private void buildSql(FilterBoxConfigPo po, StringBuffer stringBuffer, Object parameter, List<Object> queryParameters) {
        if (Objects.isNull(parameter) || Objects.isNull(po)) {
            return;
        }
        String parameterType = po.getFilterBoxParameterType();
        switch (parameterType) {
            case FilterBoxConstant.STRING:
                stringBuffer.append(po.getExecuteStatement());
                queryParameters.add(parameter.toString());
                break;
            case FilterBoxConstant.BOOL:
                stringBuffer.append(po.getExecuteStatement());
                queryParameters.add(parameter);
                break;
            case FilterBoxConstant.DATE:
                stringBuffer.append(po.getExecuteStatement());
                LocalDateTime localDateTime = Instant.ofEpochMilli((Long) parameter).atZone(ZoneId.systemDefault()).toLocalDateTime();
                queryParameters.add(localDateTime);
                break;
            case FilterBoxConstant.ARRAY:
                int size = ((List) parameter).size();
                queryParameters.addAll(((List) parameter));
                StringBuffer placeholderSb = new StringBuffer();
                for (int i = 0; i < size; i++) {
                    placeholderSb.append(FilterBoxConstant.QUERY_PLACEHOLDER).append(",");
                }
                placeholderSb.deleteCharAt(placeholderSb.lastIndexOf(","));
                String placeholder = placeholderSb.toString();

                String executeStatement = String.format(po.getExecuteStatement(), placeholder);
                stringBuffer.append(executeStatement);
                break;
            case FilterBoxConstant.AREA:
                stringBuffer.append(po.getExecuteStatement());
                List<Object> area = (List<Object>) parameter;
                queryParameters.add(area.get(0));
                queryParameters.add(area.get(1));
                break;
            case FilterBoxConstant.TREE:
                break;
            default:
                break;
        }
    }

    private void buildPage(Map<String, Object> paramMap, StringBuffer stringBuffer, List<Object> queryParameters) {
        if (Objects.nonNull(paramMap.get("page")) && Objects.nonNull(paramMap.get("size"))) {
            stringBuffer.append(" limit ? , ?");
            Integer page = (Integer) paramMap.get("page");
            Integer size = (Integer) paramMap.get("size");
            if (page < 0) {
                page = 1;
            }
            if (size < 0) {
                size = 20;
            }
            queryParameters.add((page - 1) * size);
            queryParameters.add(page * size);
        }
    }
}
