package com.lghonor.gmall.manage.service.impl;

import com.lghonor.gmall.bean.*;
import com.lghonor.gmall.manage.mapper.*;
import com.lghonor.gmall.service.SkuService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.lghonor.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.lghonor.gmall.manage.mapper.PmsSkuInfoMapper;
import com.lghonor.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.lghonor.gmall.manage.mapper.PmsSkuImageMapper;

import java.util.List;


@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;

    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;


    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {

        // 插入skuInfo
        int i = pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
        String skuId = pmsSkuInfo.getId();

        // 插入平台属性关联
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }

        // 插入销售属性关联
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }

        // 插入图片信息
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(pmsSkuInfo.getId());
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }


    }

    @Override
    public PmsSkuInfo getSkuById(String skuId) {
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo skuInfo = pmsSkuInfoMapper.selectOne(pmsSkuInfo);

        PmsSkuImage pmsSkuImage=new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> skuImages = pmsSkuImageMapper.select(pmsSkuImage);
        skuInfo.setSkuImageList(skuImages);

        return skuInfo;
    }
}

//@Service
//public class SkuServiceImpl implements SkuService {
//
//   @Autowired
//   PmsSkuInfoMapper pmsSkuInfoMapper;
//    @Autowired
//    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
//    @Autowired
//    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
//    @Autowired
//    PmsSkuImageMapper pmsSkuImageMapper;
//
//
//    @Override
//    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
//        //插入Skuinfo
//        int i=pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
//
//
//        //插入平台属性
//        List<PmsSkuAttrValue> SkuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
//        for (PmsSkuAttrValue pmsSkuAttrValue : SkuAttrValueList) {
//            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
//            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
//        }
//
//
//        //插入销售属性
//        List<PmsSkuSaleAttrValue> SkuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
//        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : SkuSaleAttrValueList) {
//            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
//            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
//
//        }
//
//
//        //插入图片属性
//        List<PmsSkuImage> SkuImageList = pmsSkuInfo.getSkuImageList();
//        for (PmsSkuImage pmsSkuImage : SkuImageList) {
//            pmsSkuImage.getSkuId(pmsSkuInfo.getId());
//            pmsSkuImageMapper.insertSelective(pmsSkuImage);
//        }
//
//
//    }
//}
