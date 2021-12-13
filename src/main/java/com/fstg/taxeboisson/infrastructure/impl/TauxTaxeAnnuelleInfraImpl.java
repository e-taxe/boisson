package com.fstg.taxeboisson.infrastructure.impl;

import com.fstg.taxeboisson.domaine.pojo.TauxTaxeAnnuelle;
import com.fstg.taxeboisson.infrastructure.core.AbstractInfraImpl;
import com.fstg.taxeboisson.infrastructure.dao.TauxTaxeAnnuelleDao;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeAnnuelleEntity;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonEntity;
import com.fstg.taxeboisson.infrastructure.facade.TauxTaxeAnnuelleInfra;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TauxTaxeAnnuelleInfraImpl extends AbstractInfraImpl implements TauxTaxeAnnuelleInfra {
    @Autowired
    TauxTaxeAnnuelleDao tauxTaxeAnnuelleDao;

    @Override
    public TauxTaxeAnnuelle findByReference(String reference) {
        return tauxTaxeAnnuelleDao.findByRef(reference);
    }

    @Override
    public int deleteByReference(String reference) {
        return 0;
    }

    @Override
    public int save(TauxTaxeAnnuelleEntity tauxTaxeAnnuelleEntity) {
        if (findByReference(tauxTaxeAnnuelleEntity.getRef()) != null)
            return -1;
        tauxTaxeAnnuelleDao.save(tauxTaxeAnnuelleEntity);
        return 1;
    }

    @Override
    public int update(TauxTaxeAnnuelle tauxTaxeAnnuelle) {
        TauxTaxeAnnuelleEntity tauxTaxeAnnuelleEntity = new TauxTaxeAnnuelleEntity();
        BeanUtils.copyProperties(tauxTaxeAnnuelle,tauxTaxeAnnuelleEntity);
        return update(tauxTaxeAnnuelleEntity);
    }

    @Override
    public int update(TauxTaxeAnnuelleEntity tauxTaxeAnnuelleEntity) {
        if (findByReference(tauxTaxeAnnuelleEntity.getRef()) != null)
            return -1;
        tauxTaxeAnnuelleDao.save(tauxTaxeAnnuelleEntity);
        return 1;
    }

    @Override
    public List<TauxTaxeAnnuelleEntity> findAll() {
        return tauxTaxeAnnuelleDao.findAll();
    }
}
