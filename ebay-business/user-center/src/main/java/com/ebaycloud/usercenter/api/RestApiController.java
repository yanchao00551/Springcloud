package com.ebaycloud.usercenter.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackageName:com.ebaycloud.membercenter.api
 * @ClassName:RestController
 * @Description:
 * @author: 悟空
 * @date: 2021/4/15 16:43
 * @email: 10947@163.com
 */
@RestController
@RequestMapping(value = "/user")
public class RestApiController {

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUserById(@PathVariable String id) {
        return "user: " + id;
    }
}
