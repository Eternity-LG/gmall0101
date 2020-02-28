package com.lghonor.gmall.item.controller;

import com.alibaba.fastjson.JSON;
import com.lghonor.gmall.bean.PmsProductSaleAttr;
import com.lghonor.gmall.bean.PmsSkuInfo;
import com.lghonor.gmall.bean.PmsSkuSaleAttrValue;
import com.lghonor.gmall.service.SkuService;
import com.lghonor.gmall.service.SpuService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ItemController {

    @Reference
    SkuService skuService;

    @Reference
    SpuService spuService;

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable String skuId,ModelMap map,HttpServletRequest Request){

        String remoteAddr = Request.getRemoteAddr();
        //Request.getHeader("");//nginx负载均衡
        PmsSkuInfo pmsSkuInfo = skuService.getSkuById(skuId,remoteAddr);
        //sku对象
        map.put("skuInfo",pmsSkuInfo);
        //销售属性列表
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId(),pmsSkuInfo.getId());
        map.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrs);

        //查询当前sku的spu下的所有sku的销售属性
        HashMap<String, String> skuSaleAttrHash = new HashMap<>();

        List<PmsSkuInfo>pmsSkuInfos=spuService.getSkuSaleAttrValueListBySpu(pmsSkuInfo.getProductId());
        for (PmsSkuInfo skuInfo : pmsSkuInfos) {
            String k="";
            String v=skuInfo.getId();
            List<PmsSkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                k +=pmsSkuSaleAttrValue.getSaleAttrValueId()+"|";
            }

            skuSaleAttrHash.put(k,v);
        }
        //将sku的hash表放到页面
        String skuSaleAttrHashJsonStr = JSON.toJSONString(skuSaleAttrHash);
        map.put("skuSaleAttrHashJsonStr",skuSaleAttrHashJsonStr);
        return "item";
    }

    @RequestMapping("index")
    public String index(ModelMap modelMap){

        List<String> list = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list.add("循环数据"+i);
        }
        modelMap.put("list",list);
        modelMap.put("hello","hello thymeleaf !!");
        modelMap.put("check","0");
        return "index";
    }
}
