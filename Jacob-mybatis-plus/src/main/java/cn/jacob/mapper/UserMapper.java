package cn.jacob.mapper;

import cn.jacob.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description: TODO
 * @Author wzq
 * @Date 2020/2/15
 * @Version V1.0
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
