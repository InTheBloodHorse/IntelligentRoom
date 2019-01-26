package cn.lsu.chicken.room.handler;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.UserException;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO handlerSellerException(UserException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultVO exceptionHandler(HttpServletRequest request, Exception exception) {
        return new ResultVO(ResultEnum.SOMETHING_ERROR);
    }
}
