package cn.jacob.datasource.mapper.master;

import cn.jacob.datasource.entity.cluster.User;
import cn.jacob.datasource.entity.master.Username;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsernameMapper extends BaseMapper<Username> {
    List<Username> getList();

}