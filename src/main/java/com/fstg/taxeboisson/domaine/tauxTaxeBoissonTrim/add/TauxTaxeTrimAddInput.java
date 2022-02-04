package com.fstg.taxeboisson.domaine.tauxTaxeBoissonTrim.add;

import com.fstg.taxeboisson.domaine.core.AbstractProcessInput;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TauxTaxeTrimAddInput extends AbstractProcessInput {
	private BigDecimal tarif;
    private Date dateDebutApplication;
    private Date dateFinApplication;
}
