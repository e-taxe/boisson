package com.fstg.taxeboisson.infrastructure.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
@Entity
public class TaxeBoissonAnnuelleEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ref;
    private BigDecimal montantTotaleTaxeAnnuelle;
    private int year;
    private BigDecimal montantTaxeRetardDeclaration;
    private String localRef;
    private Date dateDeclaration;
}
