package com.fstg.taxeboisson.domaine.converter;

import com.fstg.taxeboisson.domaine.pojo.TauxTaxeBoisson;
import com.fstg.taxeboisson.domaine.pojo.TaxeBoissonTrim;
import com.fstg.taxeboisson.domaine.taxeBoissonTrim.montant.TaxeBoissonTrimMontantInput;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonEntity;
import com.fstg.taxeboisson.infrastructure.entity.TaxeBoissonTrimEntity;

public class MapPojo {
    public TaxeBoissonTrim taxeBoissonTrimAddInputtoTaxeTaxeBoissonTrimPojo(TaxeBoissonTrimMontantInput taxeBoissonTrimAddInput){
        TaxeBoissonTrim taxeBoissonTrim = new TaxeBoissonTrim();
        return taxeBoissonTrim;
    }

    public TaxeBoissonTrimEntity taxeBoissonTrimPojotoTaxeTaxeBoissonTrimEntity(TaxeBoissonTrim taxeBoissonTrim){
        TaxeBoissonTrimEntity taxeBoissonTrimEntity = new TaxeBoissonTrimEntity();
        TauxTaxeBoissonEntity tauxTaxeBoissonEntity = tauxTaxeBoissonPojototauxTaxeBoissonEntity(taxeBoissonTrim.getTauxTaxeBoisson());

        taxeBoissonTrimEntity.setNumTrim(taxeBoissonTrim.getNumTrim());
        taxeBoissonTrimEntity.setYear(taxeBoissonTrim.getYear());
        taxeBoissonTrimEntity.setTauxTaxeBoisson(tauxTaxeBoissonEntity);
        taxeBoissonTrimEntity.setChiffreAffaire(taxeBoissonTrim.getChiffreAffaire());
        taxeBoissonTrimEntity.setLocalRef(taxeBoissonTrim.getLocalRef());
        taxeBoissonTrimEntity.setPaymentLate(taxeBoissonTrim.isPaymentLate());
        taxeBoissonTrimEntity.setNbrMoisRetard(taxeBoissonTrim.getNbrMoisRetard());
        taxeBoissonTrimEntity.setMontantTotaleTaxeTrim(taxeBoissonTrim.getMontantTotaleTaxeTrim());

        return taxeBoissonTrimEntity;
    }

    public TauxTaxeBoissonEntity tauxTaxeBoissonPojototauxTaxeBoissonEntity(TauxTaxeBoisson tauxTaxeBoisson){
        TauxTaxeBoissonEntity tauxTaxeBoissonEntity = new TauxTaxeBoissonEntity();

        tauxTaxeBoissonEntity.setRef(tauxTaxeBoisson.getRef());
        tauxTaxeBoissonEntity.setTarif(tauxTaxeBoisson.getTarif());
        tauxTaxeBoissonEntity.setDateDebutApplication(tauxTaxeBoisson.getDateDebutApplication());
        tauxTaxeBoissonEntity.setDateFinApplication(tauxTaxeBoisson.getDateFinApplication());

        return tauxTaxeBoissonEntity;
    }
}
