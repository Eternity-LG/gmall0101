package com.lghonor.gmall.user.controller;

import com.lghonor.gmall.user.bean.UmsMember;
import com.lghonor.gmall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser()
    {
     List<UmsMember> umsMembers=userService.getAllUser();
        return umsMembers;
    }
}
