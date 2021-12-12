package com.fstg.taxeboisson.application.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TauxTaxeBoissonRetardTrimDTO {
    private BigDecimal tarifPremierMoisRetard;
    private BigDecimal tarifAutresMoisRetard;
    private Date dateDebutApplication;
    private Date dateFinApplication;
}
