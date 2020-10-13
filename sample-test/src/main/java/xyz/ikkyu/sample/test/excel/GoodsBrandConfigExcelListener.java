package xyz.ikkyu.sample.test.excel;

import xyz.ikkyu.sample.test.domain.GoodsBrandConfigExcelModel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GoodsBrandConfigExcelListener extends AnalysisEventListener<GoodsBrandConfigExcelModel> {

    private List<GoodsBrandConfigExcelModel> dataList = new ArrayList<>();

    public List<GoodsBrandConfigExcelModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<GoodsBrandConfigExcelModel> dataList) {
        this.dataList = dataList;
    }


    @Override
    public void invoke(GoodsBrandConfigExcelModel data, AnalysisContext analysisContext) {
        dataList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

}
