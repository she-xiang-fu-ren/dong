package cn.iocoder.dong.module.system.service.login.dinging;

import cn.iocoder.dong.framework.common.util.http.HttpUtils;
import cn.iocoder.dong.module.system.framework.dinging.DingingCodeProperties;
import cn.iocoder.dong.module.system.service.login.dinging.utils.DdUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginDdServiceImpl implements LoginDdService{

    @Resource
    private DingingCodeProperties dingingCodeProperties;



    @Override
    public String getAuthorizeUrl(String redirectUri) {
        String uri = "https://login.dingtalk.com/oauth2/auth?response_type=code&scope=openid&prompt=consent&client_id="+
                dingingCodeProperties.getClientId()+"&state="+ DdUtils.getUUID();
        return HttpUtils.replaceUrlQuery(uri, "redirect_uri", redirectUri);
    }

}
