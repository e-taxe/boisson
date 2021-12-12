package com.fstg.taxeboisson.infrastructure.dao;

import com.fstg.taxeboisson.domaine.pojo.TauxTaxeBoisson;
import com.fstg.taxeboisson.domaine.pojo.TauxTaxeBoissonRetardTrim;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonEntity;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonRetardTrimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TauxTaxeBoissonRetardTrimDao extends JpaRepository<TauxTaxeBoissonRetardTrimEntity, Long> {
    TauxTaxeBoissonRetardTrim findByRef(String ref);
    int deleteByRef(String ref);
}
