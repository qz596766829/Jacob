package cn.jacob.datasource.service.master;

import cn.jacob.datasource.entity.cluster.User;
import cn.jacob.datasource.entity.master.Username;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UsernameService extends IService<Username> {
    List<Username> getList();

}
