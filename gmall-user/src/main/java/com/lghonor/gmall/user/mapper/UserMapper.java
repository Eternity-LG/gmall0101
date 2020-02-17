package com.lghonor.gmall.user.mapper;


import com.lghonor.gmall.bean.UmsMember;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<UmsMember> {
    List<UmsMember>selectAllUser();
}
