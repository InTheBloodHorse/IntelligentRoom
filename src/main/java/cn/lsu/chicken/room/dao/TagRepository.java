package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, String>, JpaSpecificationExecutor<Tag> {
    Tag findByName(String name);

    void deleteById(String id);

    List<Tag> findByIdIn(List<String> tags);
}
