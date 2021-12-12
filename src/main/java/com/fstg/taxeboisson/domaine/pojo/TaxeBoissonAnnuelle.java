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
    private String ref;
    private double montantTotaleTaxeAnnuelle;
    private BigDecimal chiffreAffaire;
    private int year;
    private String localRef;
}
