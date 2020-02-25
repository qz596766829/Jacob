package cn.jacob.datasource.service.cluster.impl;

import cn.jacob.datasource.entity.cluster.User;
import cn.jacob.datasource.mapper.cluster.UserMapper;
import cn.jacob.datasource.service.cluster.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author jacob
 * @Date 2020/2/15
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private UserMapper userMapper;

    @Override
    public List<User> getList() {
        return userMapper.getList();
    }
}
