package com.fstg.taxeboisson.domaine.pojo;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TauxTaxeBoisson {
    private String ref;
    private BigDecimal tarif;
    private Date dateDebutApplication;
    private Date dateFinApplication;
}
