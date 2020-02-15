package com.lghonor.gamll.service;

import com.lghonor.gmall.bean.UmsMember;
import com.lghonor.gmall.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAllUser();
    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
}
