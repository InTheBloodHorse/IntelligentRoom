package cn.lsu.chicken.room.handler;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private Environment environment;

    @ResponseBody
    @ExceptionHandler(value = GlobalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO handlerSellerException(GlobalException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultVO exceptionHandler(HttpServletRequest request, Exception exception) {
        if (environment.getProperty("show_error") == "true") {
            exception.printStackTrace();
        }
        return new ResultVO(ResultEnum.SOMETHING_ERROR);
    }
}
