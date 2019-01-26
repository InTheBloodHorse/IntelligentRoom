package cn.lsu.chicken.room.VO;

import cn.lsu.chicken.room.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -9134255470708038199L;
    //错误码
    private Integer code;
    //提示信息
    private String msg;

    private T data;

    public ResultVO() {

    }

    public ResultVO(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }
}
