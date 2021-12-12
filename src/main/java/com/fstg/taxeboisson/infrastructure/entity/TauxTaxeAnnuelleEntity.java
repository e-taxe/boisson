package com.fstg.taxeboisson.infrastructure.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

public class TauxTaxeAnnuelleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ref;
    private BigDecimal tarifPremierMoisRetard;
    private BigDecimal tarifAutresMoisRetard;
    private Date dateDebutApplication;
    private Date dateFinApplication;
}
