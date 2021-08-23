package com.yt.easyExcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author Zjd
 * @create 2021-08-11 14:42
 */
public class TestReadListener extends AnalysisEventListener<UseData> {


    @Override
    public void invoke(UseData useData, AnalysisContext analysisContext) {
        System.out.println(useData);
    }


    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息"+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
