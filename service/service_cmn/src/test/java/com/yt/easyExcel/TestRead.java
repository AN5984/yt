package com.yt.easyExcel;

import com.alibaba.excel.EasyExcel;



/**
 * @author Zjd
 * @create 2021-08-11 14:40
 */
public class TestRead {

    public static void main(String[] args) {
        String fileName="D:\\01.xlsx";
        EasyExcel.read(fileName,UseData.class,new TestReadListener()).sheet().doRead();

    }

}

