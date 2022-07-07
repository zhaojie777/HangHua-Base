package com.beetle.hanghua.gateway.filter;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;


/**
 * @auther zhaojie
 * @date 2022/07/06 16:57
 **/
public class DynamicSecurityFilter extends AbstractSecurityInterceptor  {


    @Override
    public Class<?> getSecureObjectClass() {
        return null;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }
}
