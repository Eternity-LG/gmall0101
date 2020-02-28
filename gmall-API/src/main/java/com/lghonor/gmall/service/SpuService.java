package com.lghonor.gmall.service;

import com.lghonor.gmall.bean.*;
import com.lghonor.gmall.bean.PmsProductImage;

import java.util.List;

public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId, String skuId);

    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);
}
