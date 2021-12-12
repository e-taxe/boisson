package com.fstg.taxeboisson.infrastructure.impl;

import com.fstg.taxeboisson.domaine.pojo.TaxeBoissonAnnuelle;
import com.fstg.taxeboisson.infrastructure.core.AbstractInfraImpl;
import com.fstg.taxeboisson.infrastructure.dao.TaxeBoissonAnnuelleDao;
import com.fstg.taxeboisson.infrastructure.entity.TaxeBoissonAnnuelleEntity;
import com.fstg.taxeboisson.infrastructure.facade.TaxeBoissonAnnuelleInfra;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaxeBoissonAnnuelleInfraImpl extends AbstractInfraImpl implements TaxeBoissonAnnuelleInfra {
    @Autowired
    TaxeBoissonAnnuelleDao taxeBoissonAnnuelleDao;

    @Override
    public TaxeBoissonAnnuelle findByReference(String reference) {
        return taxeBoissonAnnuelleDao.findByRef(reference);
    }

    @Override
    public List<TaxeBoissonAnnuelle> findByLocalRef(String localRef) {
        return taxeBoissonAnnuelleDao.findBylocalRef(localRef);
    }

    @Override
    public TaxeBoissonAnnuelle findByLocalRefAndYear(String localRef, int year) {
        return taxeBoissonAnnuelleDao.findByLocalRefAndYear(localRef,year);
    }

    @Override
    public int deleteByReference(String reference) {
        return taxeBoissonAnnuelleDao.deleteByRef(reference);
    }

    @Override
    public int save(TaxeBoissonAnnuelleEntity taxeBoissonAnnuelleEntity) {
        if (findByReference(taxeBoissonAnnuelleEntity.getRef()) != null)
            return -1;
        taxeBoissonAnnuelleDao.save(taxeBoissonAnnuelleEntity);
        return 1;
    }

    @Override
    public int update(TaxeBoissonAnnuelle taxeBoissonAnnuelle) {
        TaxeBoissonAnnuelleEntity taxeBoissonAnnuelleEntity = new TaxeBoissonAnnuelleEntity();
        BeanUtils.copyProperties(taxeBoissonAnnuelle,taxeBoissonAnnuelleEntity);
        return update(taxeBoissonAnnuelleEntity);
    }

    @Override
    public int update(TaxeBoissonAnnuelleEntity taxeBoissonAnnuelleEntity) {
        if (findByReference(taxeBoissonAnnuelleEntity.getRef()) == null)
            return -1;
        taxeBoissonAnnuelleDao.save(taxeBoissonAnnuelleEntity);
        return 1;
    }

    @Override
    public List<TaxeBoissonAnnuelleEntity> findAll() {
        return taxeBoissonAnnuelleDao.findAll();
    }
}
