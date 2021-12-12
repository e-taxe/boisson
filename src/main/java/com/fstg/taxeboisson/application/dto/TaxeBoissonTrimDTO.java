package com.fstg.taxeboisson.application.dto;


import com.fstg.taxeboisson.domaine.taxeBoissonTrim.montant.TaxeBoissonTrimMontantInput;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaxeBoissonTrimDTO {
    private Date dateDebutTrim;
    private int year;
    private int numTrim;
    private BigDecimal chiffreAffaire;
    private String localRef;

    public static TaxeBoissonTrimMontantInput toTaxeBoissonTrimAddInput(TaxeBoissonTrimDTO taxeBoissonTrimDTO) {
        TaxeBoissonTrimMontantInput taxeBoissonTrimAddInput = new TaxeBoissonTrimMontantInput();
        if (taxeBoissonTrimDTO != null)
            BeanUtils.copyProperties(taxeBoissonTrimDTO, taxeBoissonTrimAddInput);
        return taxeBoissonTrimAddInput;
    }
}

