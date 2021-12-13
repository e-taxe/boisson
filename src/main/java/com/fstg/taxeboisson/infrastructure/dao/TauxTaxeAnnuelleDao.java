package com.fstg.taxeboisson.infrastructure.dao;

import com.fstg.taxeboisson.domaine.pojo.TauxTaxeAnnuelle;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeAnnuelleEntity;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TauxTaxeAnnuelleDao extends JpaRepository<TauxTaxeAnnuelleEntity,Long> {
    TauxTaxeAnnuelle findByRef(String ref);
    int deleteByRef(String ref);
}
