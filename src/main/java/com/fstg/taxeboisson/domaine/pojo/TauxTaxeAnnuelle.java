package com.fstg.taxeboisson.domaine.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

public class TauxTaxeAnnuelle {
    private String ref;
    private BigDecimal tarifPremierMoisRetard;
    private BigDecimal tarifAutresMoisRetard;
    private Date dateDebutApplication;
    private Date dateFinApplication;
}
