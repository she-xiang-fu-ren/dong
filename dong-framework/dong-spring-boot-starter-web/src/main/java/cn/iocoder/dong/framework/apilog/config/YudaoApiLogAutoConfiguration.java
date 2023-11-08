package cn.iocoder.dong.framework.apilog.config;

import cn.iocoder.dong.framework.web.config.YudaoWebAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;

//import cn.iocoder.dong.module.infra.api.logger.ApiAccessLogApi;
//import cn.iocoder.dong.module.infra.api.logger.ApiErrorLogApi;

@AutoConfiguration(after = YudaoWebAutoConfiguration.class)
public class YudaoApiLogAutoConfiguration {

//    @Bean
//    public ApiAccessLogFrameworkService apiAccessLogFrameworkService(ApiAccessLogApi apiAccessLogApi) {
//        return new ApiAccessLogFrameworkServiceImpl(apiAccessLogApi);
//    }
//
//    @Bean
//    public ApiErrorLogFrameworkService apiErrorLogFrameworkService(ApiErrorLogApi apiErrorLogApi) {
//        return new ApiErrorLogFrameworkServiceImpl(apiErrorLogApi);
//    }

    /**
     * 创建 ApiAccessLogFilter Bean，记录 API 请求日志
     */
//    @Bean
//    @ConditionalOnProperty(prefix = "yudao.access-log", value = "enable", matchIfMissing = true) // 允许使用 yudao.access-log.enable=false 禁用访问日志
//    public FilterRegistrationBean<ApiAccessLogFilter> apiAccessLogFilter(WebProperties webProperties,
//                                                                         @Value("${spring.application.name}") String applicationName,
//                                                                         ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
//        ApiAccessLogFilter filter = new ApiAccessLogFilter(webProperties, applicationName, apiAccessLogFrameworkService);
//        return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
//    }
//
//    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
//        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
//        bean.setOrder(order);
//        return bean;
//    }

}
