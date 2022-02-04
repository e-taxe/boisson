package com.fstg.taxeboisson.application.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TauxTaxeAnnuelleDTO {
    private BigDecimal tarifPremierMoisRetard;
    private BigDecimal tarifAutresMoisRetard;
    private Date dateDebutApplication;
    private Date dateFinApplication;
}
