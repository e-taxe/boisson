package com.fstg.taxeboisson.infrastructure.impl;

import com.fstg.taxeboisson.domaine.pojo.TauxTaxeBoisson;
import com.fstg.taxeboisson.infrastructure.core.AbstractInfraImpl;
import com.fstg.taxeboisson.infrastructure.dao.TauxTaxeBoissonDao;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonEntity;
import com.fstg.taxeboisson.infrastructure.facade.TauxTaxeBoissonInfra;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TauxTaxeBoissonInfraImpl extends AbstractInfraImpl implements TauxTaxeBoissonInfra {
    @Autowired
    TauxTaxeBoissonDao tauxTaxeBoissonDao;

    @Override
    public TauxTaxeBoisson findByReference(String reference) {
        return tauxTaxeBoissonDao.findByRef(reference);
    }

    @Override
    public int deleteByReference(String reference) {
        return tauxTaxeBoissonDao.deleteByRef(reference);
    }

    @Override
    public int save(TauxTaxeBoissonEntity tauxTaxeBoissonEntity) {
        if (findByReference(tauxTaxeBoissonEntity.getRef()) != null)
            return -1;
        tauxTaxeBoissonDao.save(tauxTaxeBoissonEntity);
        return 1;
    }

    @Override
    public int update(TauxTaxeBoisson tauxTaxeBoisson) {
        TauxTaxeBoissonEntity tauxTaxeBoissonEntity = new TauxTaxeBoissonEntity();
        BeanUtils.copyProperties(tauxTaxeBoisson,tauxTaxeBoissonEntity);
        return update(tauxTaxeBoissonEntity);
    }

    @Override
    public int update(TauxTaxeBoissonEntity tauxTaxeBoissonEntity) {
        if (findByReference(tauxTaxeBoissonEntity.getRef()) == null)
            return -1;
        tauxTaxeBoissonDao.save(tauxTaxeBoissonEntity);
        return 1;
    }

    @Override
    public List<TauxTaxeBoissonEntity> findAll() {
        return tauxTaxeBoissonDao.findAll();
    }
}
