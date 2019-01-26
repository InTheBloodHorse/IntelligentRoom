package cn.lsu.chicken.room.utils;

import cn.lsu.chicken.room.VO.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg("成功");
        resultVO.setData(object);
        resultVO.setCode(0);
        return resultVO;
    }
    public static ResultVO success(){
        return ResultVOUtil.success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg(msg);
        resultVO.setCode(code);
        return resultVO;
    }

}
