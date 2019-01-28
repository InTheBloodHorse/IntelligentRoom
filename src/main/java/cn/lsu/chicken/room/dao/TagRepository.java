package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagRepository extends JpaRepository<Tag, Integer>, JpaSpecificationExecutor<Tag> {
    Tag findByName(String name);

    void deleteById(Integer id);
}
