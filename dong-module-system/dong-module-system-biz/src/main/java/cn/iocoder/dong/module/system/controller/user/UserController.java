package cn.iocoder.dong.module.system.controller.user;

import cn.iocoder.dong.framework.common.pojo.CommonResult;
import cn.iocoder.dong.module.system.controller.user.vo.UserVO;
import cn.iocoder.dong.module.system.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public CommonResult<String> create(@RequestBody UserVO userVO,
                                       HttpServletResponse response){
        return success(userService.createUser(userVO));
    }

}
