package com.lghonor.gmall.user.controller;

import com.lghonor.gmall.user.bean.UmsMember;
import com.lghonor.gmall.user.bean.UmsMemberReceiveAddress;
import com.lghonor.gmall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("getReceiveAddressByMemberId")
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(@RequestBody String memberId)
    {
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses=userService.getReceiveAddressByMemberId (memberId);
        return umsMemberReceiveAddresses;
    }
    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser()
    {
     List<UmsMember> umsMembers=userService.getAllUser();
        return umsMembers;
    }
}
