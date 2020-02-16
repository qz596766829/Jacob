package cn.jacob.controller;

import cn.jacob.entity.User;
import cn.jacob.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Description: TODO
 * @Author wzq
 * @Date 2020/2/15
 * @Version V1.0
 **/
@RestController
@Slf4j
@Api(value = "用户管理", tags = "用户管理")
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/list")
    @ApiOperation(value = "用户列表",notes = "用户列表")
    public List<User> list(){
        return userService.list();
    }

}
