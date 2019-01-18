package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Integer> {

}
