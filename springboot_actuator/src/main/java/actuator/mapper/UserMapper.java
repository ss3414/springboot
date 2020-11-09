package actuator.mapper;

import actuator.model.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("SELECT `id`, `name`, `password` FROM `user` " +
            "WHERE `id` = #{id}")
    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
    })
    User selectOne(Integer id);

    /* @ResultMap复用@Results */
    @Select("SELECT `id`, `name`, `password` FROM `user`")
    @ResultMap(value = "BaseResultMap")
    List<User> selectList();

}
