package cn.lsu.chicken.room.extend;

import cn.lsu.chicken.room.constant.UserConstant;
import cn.lsu.chicken.room.entity.User;
import cn.lsu.chicken.room.security.DecryptMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

@SuppressWarnings("ALL")
public class MySimpleRepository<T, ID> extends SimpleJpaRepository<T, ID> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;

    @Autowired
    public MySimpleRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    @Override
    public <S extends T> S save(S entity) {
        if (entity.getClass().equals(User.class)) {
            User user = (User) entity;
            //假如是明文密码，则加密
            if (user.getPassword().length() >= UserConstant.PASSWORD_MIN_SIZE && user.getPassword().length() <= UserConstant.PASSWORD_MAX_SIZE) {
                user.setPassword(DecryptMD5.MD5(user.getPassword()));
            }
        }
        return super.save(entity);
    }
}
