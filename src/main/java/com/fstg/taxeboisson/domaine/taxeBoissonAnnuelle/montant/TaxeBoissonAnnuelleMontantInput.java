package com.fstg.taxeboisson.domaine.taxeBoissonAnnuelle.montant;

import com.fstg.taxeboisson.domaine.core.AbstractProcessInput;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxeBoissonAnnuelleMontantInput extends AbstractProcessInput {
      private double montantTotaleTaxeAnnuelle;
      private BigDecimal chiffreAffaire;
      private int year;
      private String localRef;

}
