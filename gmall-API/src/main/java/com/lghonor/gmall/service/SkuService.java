package com.lghonor.gmall.service;

import com.lghonor.gmall.bean.PmsSkuInfo;

import java.util.List;


public interface SkuService {
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

   PmsSkuInfo getSkuById(String skuId);
}
