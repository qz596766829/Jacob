package cn.jacob.datasource.service.master.impl;

import cn.jacob.datasource.entity.master.Username;
import cn.jacob.datasource.mapper.master.UsernameMapper;
import cn.jacob.datasource.service.master.UsernameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
public class UsernameServiceImpl extends ServiceImpl<UsernameMapper, Username> implements UsernameService {

    private UsernameMapper usernameMapper;

    @Override
    public List<Username> getList() {
        return usernameMapper.getList();
    }
}
