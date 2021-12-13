package com.fstg.taxeboisson.infrastructure.facade;

import com.fstg.taxeboisson.domaine.pojo.TaxeBoissonAnnuelle;
import com.fstg.taxeboisson.infrastructure.core.AbstractInfra;
import com.fstg.taxeboisson.infrastructure.entity.TaxeBoissonAnnuelleEntity;

import java.util.List;

public interface TaxeBoissonAnnuelleInfra extends AbstractInfra {
    TaxeBoissonAnnuelle findByReference(String reference);

    List<TaxeBoissonAnnuelle> findByLocalRef(String localRef);

    TaxeBoissonAnnuelle findByLocalRefAndYear(String localRef,int year);

    int deleteByReference(String reference);

    int save(TaxeBoissonAnnuelleEntity taxeBoissonAnnuelleEntity);

    int save(TaxeBoissonAnnuelle taxeBoissonAnnuelle);

    int update(TaxeBoissonAnnuelle taxeBoissonAnnuelle);

    int update(TaxeBoissonAnnuelleEntity taxeBoissonAnnuelleEntity);

    List<TaxeBoissonAnnuelleEntity> findAll();
}
