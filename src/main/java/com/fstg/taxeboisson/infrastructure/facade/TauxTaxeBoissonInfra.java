package com.fstg.taxeboisson.infrastructure.facade;

import com.fstg.taxeboisson.domaine.pojo.TauxTaxeBoisson;
import com.fstg.taxeboisson.infrastructure.core.AbstractInfra;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonEntity;

import java.util.List;

public interface TauxTaxeBoissonInfra extends AbstractInfra {
    TauxTaxeBoisson findByReference(String reference);

    int deleteByReference(String reference);

    int save(TauxTaxeBoissonEntity tauxTaxeBoissonEntity);

    int update(TauxTaxeBoisson tauxTaxeBoisson);

    int update(TauxTaxeBoissonEntity tauxTaxeBoissonEntity);

    List<TauxTaxeBoissonEntity> findAll();
}
