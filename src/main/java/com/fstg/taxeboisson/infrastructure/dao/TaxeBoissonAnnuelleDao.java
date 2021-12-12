package com.fstg.taxeboisson.infrastructure.dao;

import java.util.List;

import com.fstg.taxeboisson.domaine.pojo.TaxeBoissonAnnuelle;
import com.fstg.taxeboisson.infrastructure.entity.TaxeBoissonAnnuelleEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxeBoissonAnnuelleDao extends JpaRepository<TaxeBoissonAnnuelleEntity, Long>{

    List<TaxeBoissonAnnuelle> findBylocalRef(String localRef);
    TaxeBoissonAnnuelle findByLocalRefAndYear(String localRef,int year);
    TaxeBoissonAnnuelle findByRef(String ref);
    int deleteByRef(String ref);
    
    
}
