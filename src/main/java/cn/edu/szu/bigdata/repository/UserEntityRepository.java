package cn.edu.szu.bigdata.repository;

import cn.edu.szu.bigdata.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by longhao on 2017/8/31.
 */
@Repository
@Mapper
public interface UserEntityRepository {

    @Select({
            "select * from user where username=#{username}"
    })
    UserEntity selectUserEntityByName(@Param("username") String username);


    @Select({
            "select count(*) as num",
            "from user",
            "where username=#{username} and password=#{password}"
    })
    int selectCountByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
