package cn.jacob.datasource.mapper.cluster;

import cn.jacob.datasource.entity.cluster.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> getList();
}