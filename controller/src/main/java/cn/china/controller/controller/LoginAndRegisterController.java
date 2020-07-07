package cn.china.controller.controller;

import cn.china.common.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;


@RestController
public class LoginAndRegisterController {
    @Autowired
    RestTemplate restTemplate;


    @RequestMapping(value ="/login",produces = "application/json;charset=utf-8")
    public String login(@RequestParam String username,@RequestParam String password, HttpServletResponse res){
        res.setHeader("Access-Control-Allow-Origin", "*");

        User user=new User();
        user.setUsername(username);
        user.setPassword(DigestUtils.md5Hex(password.getBytes()));
        User user2=restTemplate.postForObject("http://user/selectByUsername", user,User.class);
       if(user2==null){
           return "no";//no代表用户不存在
       }else{
           if(user2.getPassword().equals(DigestUtils.md5Hex(password.getBytes()))){
               System.out.println("进入比较=====");

               return "yes";//密码正确
           }else{
               return "error";//密码错误
           }
       }

    }





        @RequestMapping(value ="/register",produces = "application/json;charset=utf-8")
    public String register(@RequestParam String username,@RequestParam String password,@RequestParam String phone, HttpServletResponse res){

        System.out.println("=============="+username);
        res.setHeader("Access-Control-Allow-Origin", "*");

           User user2=new User();
           user2.setUsername(username);

        User user=restTemplate.postForObject("http://user/selectByUsername", user2,User.class);
         if(user==null){
             user=new User();
             user.setUsername(username);
             user.setPassword(DigestUtils.md5Hex(password.getBytes()));
             user.setPhone(phone);
             System.out.println("***********"+user);
             int index=restTemplate.postForObject("http://user/insertSelective", user,Integer.class);
             if(index>0){
                 return "ok";//ok代表注册成功
             }else{
                 return "no";//no代表注册失败
             }
         }
        return "exsit";//exsit代表用户已存在
    }


}
