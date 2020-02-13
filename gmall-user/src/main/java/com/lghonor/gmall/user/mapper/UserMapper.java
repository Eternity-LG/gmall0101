package com.lghonor.gmall.user.mapper;

import com.lghonor.gmall.user.bean.UmsMember;

import java.util.List;

public interface UserMapper {
    List<UmsMember> selectAllUser();
}
