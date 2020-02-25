package cn.jacob.datasource.service.cluster;

import cn.jacob.datasource.entity.cluster.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> getList();
}
