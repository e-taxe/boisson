package com.fstg.taxeboisson.domaine.pojo;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TauxTaxeBoissonRetardTrim {
    private BigDecimal tarifPremierMoisRetard;
    private BigDecimal tarifAutresMoisRetard;
    private java.sql.Date dateDebutApplication;
    private Date dateFinApplication;
}
