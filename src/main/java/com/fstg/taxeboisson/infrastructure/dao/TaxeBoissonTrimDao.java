package com.fstg.taxeboisson.infrastructure.dao;

import java.util.List;

import com.fstg.taxeboisson.domaine.pojo.TaxeBoissonTrim;
import com.fstg.taxeboisson.infrastructure.entity.TaxeBoissonTrimEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxeBoissonTrimDao  extends JpaRepository<TaxeBoissonTrimEntity, Long>{
	TaxeBoissonTrimEntity findByRef(String ref);
    int deleteByRef(String ref);
}
