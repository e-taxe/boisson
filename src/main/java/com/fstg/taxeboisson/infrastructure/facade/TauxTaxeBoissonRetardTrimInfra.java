package com.fstg.taxeboisson.infrastructure.facade;

import com.fstg.taxeboisson.domaine.pojo.TauxTaxeBoissonRetardTrim;
import com.fstg.taxeboisson.infrastructure.core.AbstractInfra;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonRetardTrimEntity;

import java.util.List;

public interface TauxTaxeBoissonRetardTrimInfra extends AbstractInfra {
    TauxTaxeBoissonRetardTrim findByReference(String reference);

    int deleteByReference(String reference);

    int save(TauxTaxeBoissonRetardTrimEntity tauxTaxeBoissonRetardTrimEntity);

    int update(TauxTaxeBoissonRetardTrim tauxTaxeBoissonRetardTrim);

    int update(TauxTaxeBoissonRetardTrimEntity tauxTaxeBoissonRetardTrimEntity);

    List<TauxTaxeBoissonRetardTrimEntity> findAll();
}
