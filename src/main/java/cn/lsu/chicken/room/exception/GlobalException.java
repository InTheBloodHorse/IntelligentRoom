package cn.lsu.chicken.room.exception;

import cn.lsu.chicken.room.enums.ResultEnum;

public class GlobalException extends RuntimeException {
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public GlobalException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public GlobalException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
