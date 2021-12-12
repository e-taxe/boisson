package com.fstg.taxeboisson.domaine.pojo;



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
public class TaxeBoissonTrim {
    private String ref;
    private BigDecimal montantTotaleTaxeTrim;
    private int year;
    private int numTrim;
    private BigDecimal chiffreAffaire;
    private boolean isPaymentLate;
    private BigDecimal nbrMoisRetard;
    private String localRef;
    private TauxTaxeBoisson tauxTaxeBoisson;
}
