package cn.lsu.chicken.room.convert.tag;

import cn.lsu.chicken.room.domain.Tag;
import cn.lsu.chicken.room.form.tag.TagForm;
import org.springframework.beans.BeanUtils;

public class TagForm2Tag {
    public static Tag convert(TagForm tagForm) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagForm, tag);
        return tag;
    }
}
