package com.lghonor.gmall.item.controller;

import com.lghonor.gmall.bean.PmsProductSaleAttr;
import com.lghonor.gmall.bean.PmsSkuInfo;
import com.lghonor.gmall.service.SkuService;
import com.lghonor.gmall.service.SpuService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
public class ItemController {
    @Reference
    SkuService skuService;
   @Reference
   SpuService spuService;
    @RequestMapping("{skuId}.html")
    public  String item(@PathVariable String skuId, ModelMap map){
        //从后台查询到数据
        PmsSkuInfo pmsSkuInfo=skuService.getSkuById(skuId);
        //将查到的数据传输到html界面，由html调用
        map.put("skuInfo",pmsSkuInfo);


        //销售属性列表
       List<PmsProductSaleAttr> pmsProductSaleAttrs=spuService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId());
       map.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrs);
        //调用后返回该界面
        return  "item";
    }





    @RequestMapping("index")
    public  String index(ModelMap modelMap){
        List<String>    list =new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("循环数据"+i);
        }
        modelMap.put("list",list);
        modelMap.put("hello","qinggqingcaoyuan");
        modelMap.put("check", "1");
        return  "index";
    }
}
