package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.domain.Sign;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.form.SignQueryForm;
import cn.lsu.chicken.room.service.SignService;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
public class SignController {
    @Autowired
    private SignService signService;

    @PostMapping("/listEntity")
    public ResultVO<Sign> listEntity(@RequestBody SignQueryForm signQueryForm) {
        PageDTO<Sign> data = signService.listEntityByQueryForm(signQueryForm);
        return ResultVOUtil.success(data);
    }

}
