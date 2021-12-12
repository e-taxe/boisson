package com.fstg.taxeboisson.domaine.taxeBoissonTrim.montant;

import com.fstg.taxeboisson.domaine.core.AbstractProcessInput;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TaxeBoissonTrimMontantInput extends AbstractProcessInput {
      private BigDecimal chiffreAffaire;
      private int year;
      private int numTrim;
      private Date datePaiement;
      private String localRef;

}
