package cn.iocoder.dong.module.system.controller.login.dingding;

import cn.hutool.json.JSONUtil;
import cn.iocoder.dong.framework.common.pojo.CommonResult;
import cn.iocoder.dong.module.system.framework.dinging.DingingCodeProperties;
import cn.iocoder.dong.module.system.service.login.dinging.LoginDdService;
import com.aliyun.dingtalkcontact_1_0.models.GetUserHeaders;
import com.aliyun.dingtalkcontact_1_0.models.GetUserResponse;
import com.aliyun.dingtalkoauth2_1_0.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import static cn.iocoder.dong.framework.common.pojo.CommonResult.success;

/**
 * 钉钉登录
 */
@RestController
@RequestMapping("/system/dinging")
public class LoginDdController {

    @Resource
    private LoginDdService loginDdService;

    @Resource
    private DingingCodeProperties dingingCodeProperties;

    @GetMapping("/social-auth-redirect")
    @PermitAll
    @Operation(summary = "钉钉二维码的跳转")
    @Parameters({
            @Parameter(name = "redirectUri", description = "回调路径")
    })
    public CommonResult<String> socialLogin(@RequestParam("redirectUri") String redirectUri) {
        return success(loginDdService.getAuthorizeUrl(redirectUri));
    }

    /**
     * 获取用户token
     * @param authCode
     * @return
     * @throws Exception
     */
    //接口地址：注意/auth与钉钉登录与分享的回调域名地址一致
    @GetMapping(value = "/auth")
    @PermitAll
    public String getAccessToken(@RequestParam(value = "authCode")String authCode,@RequestParam("state")String state) throws Exception {
        com.aliyun.dingtalkoauth2_1_0.Client client = authClient();
        GetUserTokenRequest getUserTokenRequest = new GetUserTokenRequest()

                //应用基础信息-应用信息的AppKey,请务必替换为开发的应用AppKey
                .setClientId(dingingCodeProperties.getClientId())

                //应用基础信息-应用信息的AppSecret，,请务必替换为开发的应用AppSecret
                .setClientSecret(dingingCodeProperties.getClientSecret())
                .setCode(authCode)
                .setGrantType("authorization_code");
        GetUserTokenResponse getUserTokenResponse = client.getUserToken(getUserTokenRequest);
        //获取用户个人token
        String accessToken = getUserTokenResponse.getBody().getAccessToken();
        return  contactUsers1(accessToken,authCode);

    }
    public static com.aliyun.dingtalkoauth2_1_0.Client authClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }
    /**
     * 获取用户通讯录个人信息
     * @param accessToken
     * @param unionId
     * @throws Exception
     */
    public String contactUsers(String accessToken,String unionId) throws Exception {
        com.aliyun.dingtalkoauth2_1_0.Client client = dingtalkcontact2_1_0();
        GetUserHeaders getUserHeaders = new GetUserHeaders();
        getUserHeaders.xAcsDingtalkAccessToken = accessToken;
        GetSsoUserInfoHeaders getSsoUserInfoHeaders = new GetSsoUserInfoHeaders();
        getSsoUserInfoHeaders.xAcsDingtalkAccessToken = accessToken;
        GetSsoUserInfoRequest getSsoUserInfoRequest = new GetSsoUserInfoRequest()
                .setCode(unionId);
        try {
            GetSsoUserInfoResponse ssoUserInfoWithOptions = client.getSsoUserInfoWithOptions(getSsoUserInfoRequest, getSsoUserInfoHeaders, new RuntimeOptions());

            return JSONUtil.toJsonStr(ssoUserInfoWithOptions.getBody());
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        }
        return "";
    }

    /**
     * 根据token获取详情
     * @param accessToken accessToken
     * @param unionId
     * @return
     * @throws Exception
     */
    public String contactUsers1(String accessToken,String unionId) throws Exception {
        com.aliyun.dingtalkcontact_1_0.Client client = createClient();
        GetUserHeaders getUserHeaders = new GetUserHeaders();
        getUserHeaders.xAcsDingtalkAccessToken = accessToken;
        try {
            GetUserResponse me = client.getUserWithOptions("me", getUserHeaders, new RuntimeOptions());
            return JSONUtil.toJsonStr(me.getBody());
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }
        }
        return "";
    }

    public static com.aliyun.dingtalkcontact_1_0.Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkcontact_1_0.Client(config);
    }

    public static  com.aliyun.dingtalkoauth2_1_0.Client dingtalkcontact2_1_0() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }
}
