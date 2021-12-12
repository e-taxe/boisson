package com.fstg.taxeboisson.infrastructure.impl;

import com.fstg.taxeboisson.domaine.pojo.TaxeBoissonTrim;
import com.fstg.taxeboisson.infrastructure.core.AbstractInfraImpl;
import com.fstg.taxeboisson.infrastructure.dao.TaxeBoissonTrimDao;
import com.fstg.taxeboisson.infrastructure.entity.TaxeBoissonTrimEntity;
import com.fstg.taxeboisson.infrastructure.facade.TaxeBoissonTrimInfra;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaxeBoissonTrimInfraImpl extends AbstractInfraImpl implements TaxeBoissonTrimInfra {
    @Autowired
    TaxeBoissonTrimDao taxeBoissonTrimDao;

    @Override
    public TaxeBoissonTrim findByReference(String reference) {
        return taxeBoissonTrimDao.findByRef(reference);
    }

    @Override
    public int deleteByReference(String reference) {
        return taxeBoissonTrimDao.deleteByRef(reference);
    }

    @Override
    public int save(TaxeBoissonTrimEntity taxeBoissonTrimEntity) {
        if (findByReference(taxeBoissonTrimEntity.getRef()) != null)
            return -1;
        taxeBoissonTrimDao.save(taxeBoissonTrimEntity);
        return 1;
    }

    @Override
    public int update(TaxeBoissonTrim taxeBoissonTrim) {
        TaxeBoissonTrimEntity taxeBoissonTrimEntity = new TaxeBoissonTrimEntity();
        BeanUtils.copyProperties(taxeBoissonTrim,taxeBoissonTrimEntity);
        return update(taxeBoissonTrimEntity);
    }

    @Override
    public int update(TaxeBoissonTrimEntity taxeBoissonTrimEntity) {
        if (findByReference(taxeBoissonTrimEntity.getRef()) == null)
            return -1;
        taxeBoissonTrimDao.save(taxeBoissonTrimEntity);
        return 1;
    }

    @Override
    public List<TaxeBoissonTrimEntity> findAll() {
        return taxeBoissonTrimDao.findAll();
    }
}
