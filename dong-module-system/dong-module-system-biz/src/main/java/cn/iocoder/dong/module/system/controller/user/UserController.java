package cn.iocoder.dong.module.system.controller.user;

import cn.iocoder.dong.framework.common.pojo.CommonResult;
import cn.iocoder.dong.module.system.dal.dataobject.user.UserDO;
import cn.iocoder.dong.module.system.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static cn.iocoder.dong.framework.common.pojo.CommonResult.success;

//@RestController
//@RequestMapping("/user")
@Tag(name = "管理后台 - 认证")
@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/create")
    public CommonResult<UserDO> create(@RequestParam(name = "username") String username,
                                       @RequestParam(name = "password") String password,
                                       HttpServletResponse response){
        UserDO user = userService.createUser(username,password);
        return success(user);
    }
}
