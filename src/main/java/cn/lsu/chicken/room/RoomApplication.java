package cn.lsu.chicken.room;

import cn.lsu.chicken.room.extend.MySimpleRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(value = "cn.lsu.chicken.room", repositoryBaseClass = MySimpleRepository.class)
@SpringBootApplication
@MapperScan(basePackages = "cn.lsu.chicken.room.entity.mapper")
public class RoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomApplication.class, args);
    }

}

