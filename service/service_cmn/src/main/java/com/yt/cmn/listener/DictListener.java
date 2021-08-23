package com.yt.cmn.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yt.cmn.mapper.DictMapper;
import org.springframework.beans.BeanUtils;
import yt.model.cmn.Dict;
import yt.vo.cmn.DictEeVo;

public class DictListener extends AnalysisEventListener<DictEeVo> {
    private DictMapper dictMapper;
    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    //一行一行开始读取，从第二行开始
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
