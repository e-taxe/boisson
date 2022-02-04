package com.fstg.taxeboisson.domaine.pojo;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaxeBoissonAnnuelle {
	private Long id; 
	private String ref;
    private BigDecimal montantTotaleTaxeAnnuelle;
    private BigDecimal montantTaxeRetardDeclaration;
    private int year;
    private String localRef;
    private Date dateDeclaration;
}
