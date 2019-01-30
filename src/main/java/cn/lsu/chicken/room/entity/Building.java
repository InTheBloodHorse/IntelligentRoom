package cn.lsu.chicken.room.entity;

import cn.lsu.chicken.room.utils.KeyUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Building {
    @Id
    private String id = KeyUtil.genUniqueKey();

    private String location;
}
