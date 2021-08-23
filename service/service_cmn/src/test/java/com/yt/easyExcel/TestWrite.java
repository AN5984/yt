package com.yt.easyExcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zjd
 * @create 2021-08-11 14:32
 */
public class TestWrite {
    public static void main(String[] args) {
        String fileName="D:\\01.xlsx";
        List list = new ArrayList<UseData>();
        for (int i = 0; i < 10; i++) {
            UseData useData = new UseData();
            useData.setUid(i);
            useData.setUname("jack"+i);
            list.add(useData);
        }

        EasyExcel.write(fileName,UseData.class).sheet("用户信息").doWrite(list);

    }
}
