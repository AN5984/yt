package yt.vo.hosp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HospitalSetQueryVo {

    @ApiModelProperty(value = "医院名称")
    private String hospname;

    @ApiModelProperty(value = "医院编号")
    private String hospcode;
}
