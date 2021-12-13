package com.fstg.taxeboisson.domaine.taxeBoissonAnnuelle.montant;

import com.fstg.taxeboisson.domaine.core.AbstractProcessInput;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TaxeBoissonAnnuelleMontantInput extends AbstractProcessInput {
      private int year;
      private String localRef;
      private Date dateDeclaration;

}
