package cn.jacob.datasource.entity.master;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @ClassName Username
 * @Description: TODO
 * @Author Jacob
 * @Date 2020/2/24
 * @Version V1.0
 **/
@Data
public class Username {

    private Integer id;
    private String username;
    private String password;
}