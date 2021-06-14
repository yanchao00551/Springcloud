package com.ebaycloud.ebaysecurityoauth.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName:com.ebaycloud.ebaysecurityoauth.api
 * @ClassName:UserController
 * @Description:
 * @author: 悟空
 * @date: 2021/4/22 20:00
 * @email: 10947@163.com
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/testrole")
    public String testrole() {
        return "角色ROLE_ADMIN 可以查看";
    }


    /**
     * 获取认证用户相关信息的端点
     *
     * @param user
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author 悟空
     * @description //TODO
     * @date 21:38 2021/4/22
     */
    @RequestMapping(value = {"/info"}, produces = "application/json")
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(
                user.getUserAuthentication().getAuthorities()
        ));
        return userInfo;
    }

}
