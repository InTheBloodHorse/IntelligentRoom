package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.domain.Sign;
import cn.lsu.chicken.room.form.SignQueryForm;

public interface SignService extends BaseService<Sign, Sign, Integer, SignQueryForm> {
    Integer addByApplyId(Integer applyId);

    Integer signSuccess(Integer applyId, Integer userId);
}
