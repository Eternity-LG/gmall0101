package com.lghonor.gmall.user.service;

import com.lghonor.gmall.user.bean.UmsMember;
import com.lghonor.gmall.user.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUser();
    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
}
