package com.benma.graphql.aspect;

import com.benma.graphql.Exception.AuthorizeException;
import com.benma.graphql.Utils.CookieUtil;
import com.benma.graphql.Utils.HttpUtil;
import com.benma.graphql.constant.CookieConstant;
import com.benma.graphql.constant.RedisConstant;
import com.benma.graphql.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

/**
 * author:szjz
 * date:2019/5/28
 * <p>
 * 通过AOP认证 拦截非法访问数据
 */

@Aspect
@Slf4j
@Component
public class AuthAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    //设置切点 对某个类下的某些方法进行拦截
    @Pointcut(
            //对service层进行拦截
            /*"|| execution(public * com.benma.graphql.service.Impl.UserServiceImpl.*(..))"+
            "&& ! execution(public * com.benma.graphql.service.Impl.UserServiceImpl.save(..))"+
            "&& ! execution(public * com.benma.graphql.service.Impl.UserServiceImpl.findByUsernameAndPassword(..))"+
            "|| execution(public * com.benma.graphql.service.Impl.BookServiceImpl.*(..))"*/

            //利用AOP,无法实现对graphQL *DataFetcher层的拦截，只能对service层进行拦截，对service层的拦截虽然更细粒度，
            // 但是不利于后期整合权限的管理，降低业务逻辑的灵活度，所以利用AOP对GraphQL认证授权不是最佳的处理方案
            /* "execution(public * com.benma.graphql.dataFetcher.BookDataFetcher.*(..))"+
           "|| execution(public * com.benma.graphql.dataFetcher.UserDataFetcher.*(..))"
           + "&& ! execution(public * com.benma.graphql.dataFetcher.UserDataFetcher.login())" +
           "&& ! execution(public * com.benma.graphql.dataFetcher.UserDataFetcher.save())"*/
    )
    public void verify() {}

    @Before("verify()")
    public void doVerify() {

        Cookie cookie = CookieUtil.get(HttpUtil.request(), CookieConstant.TOKEN);
        if (cookie == null) {
            log.error("【用户认证登录】 {}", ResultEnum.AUTHORIZE_FAIL.getMessage());
            throw new AuthorizeException(ResultEnum.AUTHORIZE_FAIL);
        } else {

            String format = String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue());
            String userInfo = redisTemplate.opsForValue().get(format);

            if (userInfo == null) {
                log.error("【用户认证登录】 {}", ResultEnum.USER_IS_NOT_EXIST.getMessage());
                throw new AuthorizeException(ResultEnum.USER_IS_NOT_EXIST);
            }
        }
    }

}
