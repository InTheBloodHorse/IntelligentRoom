package cn.lsu.chicken.room.handler;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private Environment environment;


    // 加上 @ResponseStatus(HttpStatus.BAD_REQUEST) 就返回400码
    @ResponseBody
    @ExceptionHandler(value = GlobalException.class)
    public ResultVO handlerSellerException(GlobalException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

    // Gson转化 NumberFormatException
    @ResponseBody
    @ExceptionHandler(value = NumberFormatException.class)
    public ResultVO handlerNumberFormatException() {
        return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
    }

//    @ResponseBody
//    @ExceptionHandler(value = BadSqlGrammarException.class)
//    public ResultVO handlerBadSqlGrammarException() {
//        return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
//    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVO exceptionHandler(Exception exception) {
        if (environment.getProperty("show_error") == "true") {
            exception.printStackTrace();
        }
        return new ResultVO(ResultEnum.SOMETHING_ERROR);
    }
}
