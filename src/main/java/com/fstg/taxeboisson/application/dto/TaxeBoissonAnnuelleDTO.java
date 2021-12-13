package com.fstg.taxeboisson.application.dto;

import com.fstg.taxeboisson.domaine.taxeBoissonAnnuelle.montant.TaxeBoissonAnnuelleMontantInput;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaxeBoissonAnnuelleDTO {
    private BigDecimal montantTotaleTaxeAnnuelle;
    private BigDecimal montantTaxeRetardDeclaration;
    private int year;
    private String localRef;
    private Date dateDeclaration;

    public static TaxeBoissonAnnuelleMontantInput toTaxeBoissonAnnuelleAddInput(TaxeBoissonAnnuelleDTO taxeBoissonAnnuelleDTO) {
        TaxeBoissonAnnuelleMontantInput taxeBoissonAnnuelleAddInput = new TaxeBoissonAnnuelleMontantInput();
        if (taxeBoissonAnnuelleDTO != null)
            BeanUtils.copyProperties(taxeBoissonAnnuelleDTO, taxeBoissonAnnuelleAddInput);
        return taxeBoissonAnnuelleAddInput;
    }
}
