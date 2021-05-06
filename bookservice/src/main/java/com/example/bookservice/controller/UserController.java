package com.example.bookservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bookservice.entity.User;
import com.example.bookservice.entity.model.UserMessageModel;
import com.example.bookservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private User user;
    @PostMapping("/register")
    public String preg(@RequestParam("uname")String username,
                       @RequestParam("pswd")String password,
                       @RequestParam("email")String email,
                       @RequestParam("phonenumber")String phonenum,
                       HttpServletResponse response) throws IOException {
        user=new User(username,password,email,phonenum);
        boolean existed = !username.isEmpty() && !password.isEmpty();
        boolean succeed = false;
        if(existed){
            succeed=userService.RegisterUser(user);
        }
        response.setContentType("text/html; charset=UTF-8"); //转码
        PrintWriter out = response.getWriter();
        out.flush();
        out.println("<script>");//转码
        if(succeed && existed){
            out.println("alert('注册成功！');");
            out.println("</script>");
            return "login";
        }
        else{
            out.println("alert('注册失败！');");
            out.println("</script>");
            return "register";
        }
    }
    @PostMapping("/login")
    public String plogin(@RequestParam("uname")String username,
                         @RequestParam("pswd")String password, RedirectAttributes redirectAttributes,
                         HttpSession httpSession,HttpServletResponse response) {
        if (username.equals("")) {
            redirectAttributes.addFlashAttribute("nousername", true);
            return "redirect:login";
        }
        if (password.equals("")) {
            redirectAttributes.addFlashAttribute("nopassword", true);
            return "redirect:login";
        }
        boolean existed = userService.FindUserByusername(username);
        if (!existed) {
            redirectAttributes.addFlashAttribute("notfounduser", true);
            return "redirect:login";
        }
        boolean isRegistered = userService.FindUserByusernameAndpassword(username,password);
        if (!isRegistered) {
            redirectAttributes.addFlashAttribute("passworderror", true);
            return "redirect:login";
        }
        httpSession.setAttribute("username", username);
        httpSession.setAttribute("password", password);
        //获取session时需要利用request，而request作用域内容会随重定向消失，因此session保存的内容需要加入cookie中
        String sessionid = httpSession.getId();
        Cookie cookie = new Cookie("JSESSIONID",sessionid);
        cookie.setMaxAge(60*60*24*7);
        response.addCookie(cookie);
        int privilege = userService.FindPrivilegeByusername(username);
        if(privilege == 1) return "redirect:admin";
        return "redirect:home";
    }
    @GetMapping("/login")
    public String glogin(){
        return "login";
    }
    @GetMapping("/home")
    public String ghome(){
        return "home";
    }
    @GetMapping("/register")
    public String greg(){
        return "register";
    }
    //接收json数据串需要在controller方法处加@ResponseBody注解
    @ResponseBody
    @PostMapping("/test")
    public String test(@RequestBody JSONObject message){
        /* 1.get方法返回值为Object类型，需要进行强制转换
        *  2.get方法内的参数必须与Js脚本内json串的key值对应*/
        boolean ss=userService.FindUserByusername((String)message.get("message"));
        if(ss)return "true";
        else return "false";
    }
    @ResponseBody
    @PostMapping("/getuser")
    public UserMessageModel getuser(@RequestBody JSONObject data){
        String username = (String) data.get("username");
        return userService.GetUserByusername(username);
    }
    @ResponseBody
    @PostMapping("/alter")
    public void alter(@RequestBody JSONObject data){
        String username = (String) data.get("username");
        String password = (String) data.get("password");
        userService.alteruserspassword(username,password);
    }
}
