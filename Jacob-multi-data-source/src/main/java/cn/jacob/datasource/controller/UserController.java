package cn.jacob.datasource.controller;

import cn.jacob.datasource.entity.cluster.User;
import cn.jacob.datasource.service.cluster.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Description: TODO
 * @Author jacob
 * @Date 2020/2/15
 * @Version V1.0
 **/
@RestController
@Slf4j
@Api(value = "从数据源", tags = "从数据源")
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/getList")
    @ApiOperation(value = "用户列表",notes = "用户列表")
    public List<User> getList(){
        return userService.getList();
    }

}
