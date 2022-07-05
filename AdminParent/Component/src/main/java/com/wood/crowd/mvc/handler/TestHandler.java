package com.wood.crowd.mvc.handler;

import com.wood.crowd.entity.Admin;
import com.wood.crowd.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap) {
        List<Admin> all = adminService.getAll();
        modelMap.addAttribute("adminList", all);
        return "target";
    }

    @ResponseBody
    @RequestMapping("/send/array1.do")
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array){
        // 接收参数时需要在参数名后面加[]
        for (Integer number : array){
            System.out.println(number);
        }
        return "success";
    }
}
