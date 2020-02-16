package cn.jacob.service.impl;

import cn.jacob.entity.User;
import cn.jacob.mapper.UserMapper;
import cn.jacob.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author wzq
 * @Date 2020/2/15
 * @Version V1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
