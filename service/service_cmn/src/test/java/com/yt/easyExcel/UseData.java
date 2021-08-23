package com.yt.easyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Zjd
 * @create 2021-08-11 14:30
 */
@Data
public class UseData {
    @ExcelProperty(value = "用户id",index = 0)
    private int uid;
    @ExcelProperty(value = "用户姓名",index = 1)
    private String uname;
}
