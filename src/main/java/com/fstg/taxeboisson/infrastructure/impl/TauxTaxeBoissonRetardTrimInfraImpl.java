package com.fstg.taxeboisson.infrastructure.impl;

import com.fstg.taxeboisson.domaine.pojo.TauxTaxeBoissonRetardTrim;
import com.fstg.taxeboisson.infrastructure.core.AbstractInfraImpl;
import com.fstg.taxeboisson.infrastructure.dao.TauxTaxeBoissonRetardTrimDao;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonRetardTrimEntity;
import com.fstg.taxeboisson.infrastructure.facade.TauxTaxeBoissonRetardTrimInfra;
import com.fstg.taxeboisson.infrastructure.facade.TauxTaxeBoissonRetardTrimInfra;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TauxTaxeBoissonRetardTrimInfraImpl extends AbstractInfraImpl implements TauxTaxeBoissonRetardTrimInfra {
    @Autowired
    TauxTaxeBoissonRetardTrimDao tauxTaxeBoissonRetardTrimDao;

    @Override
    public TauxTaxeBoissonRetardTrim findByReference(String reference) {
        return tauxTaxeBoissonRetardTrimDao.findByRef(reference);
    }

    @Override
    public int deleteByReference(String reference) {
        return tauxTaxeBoissonRetardTrimDao.deleteByRef(reference);
    }

    @Override
    public int save(TauxTaxeBoissonRetardTrimEntity tauxTaxeBoissonRetardTrimEntity) {
        if (findByReference(tauxTaxeBoissonRetardTrimEntity.getRef()) != null)
            return -1;
        tauxTaxeBoissonRetardTrimDao.save(tauxTaxeBoissonRetardTrimEntity);
        return 1;
    }

    @Override
    public int update(TauxTaxeBoissonRetardTrim tauxTaxeBoissonRetardTrim) {
        TauxTaxeBoissonRetardTrimEntity tauxTaxeBoissonRetardTrimEntity = new TauxTaxeBoissonRetardTrimEntity();
        BeanUtils.copyProperties(tauxTaxeBoissonRetardTrim,tauxTaxeBoissonRetardTrimEntity);
        return update(tauxTaxeBoissonRetardTrimEntity);
    }

    @Override
    public int update(TauxTaxeBoissonRetardTrimEntity tauxTaxeBoissonRetardTrimEntity) {
        if (findByReference(tauxTaxeBoissonRetardTrimEntity.getRef()) == null)
            return -1;
        tauxTaxeBoissonRetardTrimDao.save(tauxTaxeBoissonRetardTrimEntity);
        return 1;
    }

    @Override
    public List<TauxTaxeBoissonRetardTrimEntity> findAll() {
        return tauxTaxeBoissonRetardTrimDao.findAll();
    }
}
