package com.lghonor.gmall.manage.controller;

import com.lghonor.gmall.bean.PmsBaseAttrInfo;
import com.lghonor.gmall.bean.PmsBaseAttrValue;
import com.lghonor.gmall.bean.PmsBaseSaleAttr;
import com.lghonor.gmall.service.AttrService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
public class AttrController {
    @Reference
    AttrService attrService;

    @ResponseBody
    @RequestMapping("baseSaleAttrList")
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        List<PmsBaseSaleAttr> baseSaleAttrList = attrService.baseSaleAttrList();

        return baseSaleAttrList;
    }

    @ResponseBody
    @RequestMapping("saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo) {
        String success = attrService.saveAttrInfo(pmsBaseAttrInfo);
        return "success";
    }

    @ResponseBody
    @RequestMapping("attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrService.attrInfoList(catalog3Id);
        return pmsBaseAttrInfos;
    }

    @ResponseBody
    @RequestMapping("getAttrValueList")
    public List<PmsBaseAttrValue> getAttrvaluelist(String attrId) {
        List<PmsBaseAttrValue> PmsBaseAttrValues = attrService.getAttrvaluelist(attrId);

        return PmsBaseAttrValues;
    }


}
