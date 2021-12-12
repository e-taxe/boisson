package com.fstg.taxeboisson.application.dto;

import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonEntity;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TauxTaxeBoissonDTO {
    private BigDecimal tarif;
    private Date dateDebutApplication;
    private Date dateFinApplication;

    public static TauxTaxeBoissonEntity toTauxTaxeBoissonEntity(TauxTaxeBoissonDTO tauxTaxeBoissonDTO) {
        TauxTaxeBoissonEntity tauxTaxeBoisson = new TauxTaxeBoissonEntity();
        if (tauxTaxeBoissonDTO != null)
            BeanUtils.copyProperties(tauxTaxeBoissonDTO, tauxTaxeBoisson);
        return tauxTaxeBoisson;
    }
}
