package cn.edu.szu.bigdata.service;

import cn.edu.szu.bigdata.entity.UserEntity;
import cn.edu.szu.bigdata.repository.UserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by longhao on 2017/8/31.
 */
@Service
@Slf4j
public class UserEntityServiceImpl implements UserEntityService {


    @Autowired
    UserEntityRepository userEntityRepository;

    @Override
    public UserEntity getUserEntity(HttpServletRequest request) {
        HttpSession session=request.getSession();
        UserEntity userEntity=(UserEntity) session.getAttribute("current_user");
        return userEntity;
    }

    @Override
    public boolean login(UserEntity userEntity) {
        log.info("tess");
        int num=userEntityRepository.selectCountByUsernameAndPassword(userEntity.getUsername(),userEntity.getPassword());
        if(1==num){
            log.info("user:"+userEntity.getUsername()+"password:"+userEntity.getPassword()+" login success");
            return true;
        }
        else{
            log.info("user:"+userEntity.getUsername()+"password:"+userEntity.getPassword()+" login failed");
            return false;
        }
    }

    @Override
    public void addSession(HttpServletRequest request, UserEntity userEntity) {
        HttpSession session=request.getSession(true);
        session.setAttribute("current_user",userEntity);
        session.setMaxInactiveInterval(600);
    }

    @Override
    public void destroySession(HttpServletRequest request) {
        HttpSession session=request.getSession(false);
        session.removeAttribute("current_user");
    }
}
