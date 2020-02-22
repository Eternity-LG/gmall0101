package com.lghonor.gmall.manage.service.impl;

import com.lghonor.gmall.bean.PmsBaseCatalog1;
import com.lghonor.gmall.bean.PmsBaseCatalog2;
import com.lghonor.gmall.bean.PmsBaseCatalog3;
import com.lghonor.gmall.manage.mapper.PmsCatalog1Mapper;
import com.lghonor.gmall.manage.mapper.PmsCatalog2Mapper;
import com.lghonor.gmall.manage.mapper.PmsCatalog3Mapper;
import com.lghonor.gmall.service.CatalogService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    PmsCatalog1Mapper pmsCatalog1Mapper;
    @Autowired
    PmsCatalog2Mapper pmsCatalog2Mapper;
    @Autowired
    PmsCatalog3Mapper pmsCatalog3Mapper;


    @Override
    public List<PmsBaseCatalog1> getCatalog1() {

        return pmsCatalog1Mapper.selectAll();
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        PmsBaseCatalog2 pmsBaseCatalog2 = new PmsBaseCatalog2();
        pmsBaseCatalog2.setCatalog1Id(catalog1Id);
        List<PmsBaseCatalog2> pmsBaseCatalog2s = pmsCatalog2Mapper.select(pmsBaseCatalog2);
        return pmsBaseCatalog2s;
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        PmsBaseCatalog3 pmsBaseCatalog3 = new PmsBaseCatalog3();
        pmsBaseCatalog3.setCatalog2Id(catalog2Id);

        List<PmsBaseCatalog3> pmsBaseCatalog3s = pmsCatalog3Mapper.select(pmsBaseCatalog3);
        return pmsBaseCatalog3s;
    }
}
