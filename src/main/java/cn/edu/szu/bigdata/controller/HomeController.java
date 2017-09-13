package cn.edu.szu.bigdata.controller;

import cn.edu.szu.bigdata.entity.UserEntity;
import cn.edu.szu.bigdata.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by longhao on 2017/8/23.
 * Email: longhao1@email.szu.edu.cn
 */
@Controller
public class HomeController {

    @Autowired
    private UserEntityService userEntityService;


    @GetMapping("/")
    public String index(Model model,HttpServletRequest request){
        model.addAttribute("UserEntity",userEntityService.getUserEntity(request));
        return "index";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request,Model model){
        //如果已登录则略过登录，跳转至/
        HttpSession session=request.getSession(true);
        Object obj=session.getAttribute("current_user");

        if(obj != null  && obj instanceof UserEntity){
            return "redirect:/";
        }
        //否则进入登录界面
        String result=request.getParameter("result");
        if(result != null && result.equals("fail")){
            model.addAttribute("success",0);
        }
        return "login";
    }

    @PostMapping("/login.action")
    public String doLogin(UserEntity userEntity, HttpServletRequest request){
        boolean result=userEntityService.login(userEntity);
        if(result){
            userEntityService.addSession(request,userEntity);
            return "redirect:/";
        }
        else{
            return "redirect:/login?result=fail";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        userEntityService.destroySession(request);
        return "redirect:/login";
    }

}