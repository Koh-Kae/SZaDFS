package cn.edu.szu.bigdata.service;

import cn.edu.szu.bigdata.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by longhao on 2017/8/31.
 */
public interface UserEntityService {

    UserEntity getUserEntity(HttpServletRequest request);

    boolean login(UserEntity userEntity);

    void addSession(HttpServletRequest request, UserEntity userEntity);

    void destroySession(HttpServletRequest request);
}
