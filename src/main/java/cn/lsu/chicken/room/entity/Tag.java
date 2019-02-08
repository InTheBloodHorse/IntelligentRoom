package cn.lsu.chicken.room.entity;

import cn.lsu.chicken.room.utils.KeyUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Tag {
    @Id
    private String id = KeyUtil.genUniqueKey();

    //标签名
    private String name;

}
