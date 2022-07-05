package com.wood.crowd.mvc.handler;

import com.wood.crowd.constant.Constants;
import com.wood.crowd.entity.Admin;
import com.wood.crowd.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminHandler {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login/page")
    public String loginPage() {
        return "admin-login";
    }

    @RequestMapping("/login/doLogin")
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("userPwd") String userPwd,
                          HttpSession session) {
        Admin admin = adminService.login(loginAcct, userPwd);
        session.setAttribute(Constants.ATTR_NAME_LOGIN_ADMIN, admin);
        return "redirect:/admin/mainPage";
    }

    @RequestMapping("/mainPage")
    public String mainPage() {
        return "admin-main";
    }

    @RequestMapping("/loginout")
    public String loginout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login/page";
    }
}
