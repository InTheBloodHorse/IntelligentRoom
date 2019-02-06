package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.entity.User;
import cn.lsu.chicken.room.utils.KeyUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    //    String SELECT_SQL = "select u.id,u.name,u.phone,u.email,u.password,u.avatar,u.role,u.company_id,company.name " +
//            "from user as u left join company on company.id = u.company_id";
    String SELECT_SQL = "select u.id,u.name as uname,u.phone,u.email,u.password,u.avatar,u.role,u.company_id,company.name " +
            "from user as u left join company on company.id = u.company_id ";

    @Query(value = "select * from user where name = ?", nativeQuery = true)
    List<User> findAllUsersSQL(String name);

    @Query(value = "select * from user where name = ? order by id desc limit 1", nativeQuery = true)
    User findOneUsersSQL(String name);

    @Transactional
    @Query(value = "update user set name = ?2 where id = ?1", nativeQuery = true)
    @Modifying
    Integer updateUserSQL(int id, String name);

    List<User> findByNameAndPhone(String name, String phone);

    User findFirstByPhone(String phone);

    Boolean existsByPhone(String phone);

    List<User> findByCompanyId(Integer companyId);

    @Query(value = SELECT_SQL + "where phone = ? ", nativeQuery = true)
    Object[][] getUserByPhoneSql(String phone);

}
