package com.fstg.taxeboisson.infrastructure.facade;

import com.fstg.taxeboisson.domaine.pojo.TaxeBoissonTrim;
import com.fstg.taxeboisson.infrastructure.core.AbstractInfra;
import com.fstg.taxeboisson.infrastructure.entity.TaxeBoissonTrimEntity;

import java.util.List;

public interface TaxeBoissonTrimInfra extends AbstractInfra {
    TaxeBoissonTrim findByReference(String reference);

    int deleteByReference(String reference);

    int save(TaxeBoissonTrimEntity taxeBoissonTrimEntity);

    int update(TaxeBoissonTrim taxeBoissonTrim);

    int update(TaxeBoissonTrimEntity taxeBoissonTrimEntity);

    List<TaxeBoissonTrimEntity> findAll();
}
