package cn.jacob.datasource.controller;

import cn.jacob.datasource.entity.master.Username;
import cn.jacob.datasource.service.master.UsernameService;
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
@Api(value = "主数据源", tags = "主数据源")
@RequestMapping("/username")
@AllArgsConstructor
public class UsernameController {

    private UsernameService usernameService;

    @GetMapping("/getList")
    @ApiOperation(value = "用户列表",notes = "用户列表")
    public List<Username> getList(){
        return usernameService.getList();
    }

}
