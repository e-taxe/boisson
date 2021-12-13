package com.fstg.taxeboisson.infrastructure.facade;

import com.fstg.taxeboisson.domaine.pojo.TauxTaxeAnnuelle;
import com.fstg.taxeboisson.infrastructure.core.AbstractInfra;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeAnnuelleEntity;

import java.util.List;

public interface TauxTaxeAnnuelleInfra extends AbstractInfra {
    TauxTaxeAnnuelle findByReference(String reference);

    int deleteByReference(String reference);

    int save(TauxTaxeAnnuelleEntity tauxTaxeAnnuelleEntity);

    int update(TauxTaxeAnnuelle tauxTaxeAnnuelle);

    int update(TauxTaxeAnnuelleEntity tauxTaxeAnnuelleEntity);

    List<TauxTaxeAnnuelleEntity> findAll();
}
