package com.fstg.taxeboisson.infrastructure.dao;

import com.fstg.taxeboisson.domaine.pojo.TauxTaxeBoisson;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TauxTaxeBoissonDao extends JpaRepository<TauxTaxeBoissonEntity, Long> {
    TauxTaxeBoisson findByRef(String ref);
    int deleteByRef(String ref);
}
