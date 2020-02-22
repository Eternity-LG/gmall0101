package com.lghonor.gmall.manage.service.impl;

import com.lghonor.gmall.bean.*;
import com.lghonor.gmall.manage.mapper.PmsProductImageMapper;
import com.lghonor.gmall.manage.mapper.PmsProductInfoMapper;
import com.lghonor.gmall.manage.mapper.PmsProductSaleAttrMapper;
import com.lghonor.gmall.manage.mapper.PmsProductSaleAttrValueMapper;
import com.lghonor.gmall.service.SpuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Autowired
    PmsProductImageMapper pmsProductImageMapper;
    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        List<PmsProductInfo> pmsProductInfos = pmsProductInfoMapper.select(pmsProductInfo);
        return pmsProductInfos;
    }

    @Override
    public String saveSpuInfo(PmsProductInfo pmsProductInfo) {
        String id = pmsProductInfo.getId();
        if (StringUtils.isBlank(id)) {
            //插入产品信息并且保存
            pmsProductInfoMapper.insertSelective(pmsProductInfo);
            //图片信息保存
            List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
            for (PmsProductImage pmsProductImage : spuImageList) {
                pmsProductImage.setProductId(pmsProductInfo.getId());
                pmsProductImageMapper.insertSelective(pmsProductImage);
            }
            //产品信息属性名保存
            List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
            for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList) {
                pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
                pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);
                //产品信息属性值保存
                List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getspuSaleAttrValueList();
                for (PmsProductSaleAttrValue pmsProductSaleAttrValue : spuSaleAttrValueList) {
                    pmsProductSaleAttrValue.setProductId(pmsProductInfo.getId());
                    pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);

                }
            }
            return "success";


        } else {
            Example example = new Example(pmsProductInfo.getClass());
            example.createCriteria().andEqualTo("id", pmsProductInfo.getId());
            pmsProductInfoMapper.updateByExampleSelective(pmsProductInfo, example);
            //图片信息修改
            List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
            PmsProductImage pmsProductImagedel = new PmsProductImage();
            pmsProductImagedel.setProductId(pmsProductInfo.getId());
            pmsProductImageMapper.delete(pmsProductImagedel);
            for (PmsProductImage pmsProductImage : spuImageList) {
                pmsProductImage.setProductId(pmsProductInfo.getId());
                pmsProductImageMapper.insertSelective(pmsProductImage);
            }
            List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
            PmsProductSaleAttr pmsProductSaleAttrdel = new PmsProductSaleAttr();
            pmsProductSaleAttrdel.setProductId(pmsProductInfo.getId());
            pmsProductSaleAttrMapper.delete(pmsProductSaleAttrdel);
            for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList) {
                pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
                pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);
                //产品信息属性值保存
                List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getspuSaleAttrValueList();
                PmsProductSaleAttrValue pmsProductSaleAttrValuedel = new PmsProductSaleAttrValue();
                pmsProductSaleAttrValuedel.getProductId(pmsProductInfo.getId());
                pmsProductSaleAttrValueMapper.delete(pmsProductSaleAttrValuedel);
                for (PmsProductSaleAttrValue pmsProductSaleAttrValue : spuSaleAttrValueList) {
                    pmsProductSaleAttrValue.setProductId(pmsProductInfo.getId());
                    pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);

                }

            }
        }
        //插入产品信息
        return "success";
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.select(pmsProductSaleAttr);
        for (PmsProductSaleAttr productSaleAttr : pmsProductSaleAttrs) {
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(spuId);
            pmsProductSaleAttrValue.setSaleAttrId(productSaleAttr.getSaleAttrId());
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue);
            productSaleAttr.setspuSaleAttrValueList(pmsProductSaleAttrValues);
        }
        return pmsProductSaleAttrs;
    }

    //sku图片加载
    @Override
    public List<PmsProductImage> spuImageList(String spuId) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(spuId);
        List<PmsProductImage> pmsProductImages = pmsProductImageMapper.select(pmsProductImage);
        return pmsProductImages;
    }

    //sku页面加载时的属性列表

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId) {

        PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(productId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.select(pmsProductSaleAttr);

        for (PmsProductSaleAttr productSaleAttr : pmsProductSaleAttrs) {
            String saleAttrId = productSaleAttr.getSaleAttrId();

            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setSaleAttrId(saleAttrId);
            pmsProductSaleAttrValue.setProductId(productId);
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue);

            productSaleAttr.setspuSaleAttrValueList(pmsProductSaleAttrValues);
//setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        }

//        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.selectSpuSaleAttrListCheckBySku(productId,skuId);
        return pmsProductSaleAttrs;
    }


}


