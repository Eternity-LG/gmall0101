package com.lghonor.gmall.service;

import com.lghonor.gmall.bean.PmsBaseAttrInfo;
import com.lghonor.gmall.bean.PmsBaseAttrValue;
import com.lghonor.gmall.bean.PmsBaseSaleAttr;

import java.util.List;

public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrvaluelist(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();


//    List<PmsBaseAttrValue> attrValue();
}
