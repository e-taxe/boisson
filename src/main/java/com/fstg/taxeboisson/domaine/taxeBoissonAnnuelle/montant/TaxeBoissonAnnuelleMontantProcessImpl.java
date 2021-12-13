package com.fstg.taxeboisson.domaine.taxeBoissonAnnuelle.montant;

import com.fstg.taxeboisson.domaine.core.AbstractProcessImpl;
import com.fstg.taxeboisson.domaine.core.Result;
import com.fstg.taxeboisson.domaine.pojo.TauxTaxeAnnuelle;
import com.fstg.taxeboisson.domaine.pojo.TaxeBoissonAnnuelle;
import com.fstg.taxeboisson.domaine.utils.DateUtils;
import com.fstg.taxeboisson.infrastructure.dao.TauxTaxeAnnuelleDao;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeAnnuelleEntity;
import com.fstg.taxeboisson.infrastructure.facade.TauxTaxeAnnuelleInfra;
import com.fstg.taxeboisson.infrastructure.facade.TaxeBoissonAnnuelleInfra;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public class TaxeBoissonAnnuelleMontantProcessImpl extends AbstractProcessImpl<TaxeBoissonAnnuelleMontantInput> implements TaxeBoissonAnnuelleMontantProcess {
    private TaxeBoissonAnnuelleInfra taxeBoissonAnnuelleInfra;
    private TauxTaxeAnnuelleInfra tauxTaxeAnnuelleInfra;
    private DateUtils dateUtils;

    public TaxeBoissonAnnuelleMontantProcessImpl(TaxeBoissonAnnuelleInfra taxeBoissonAnnuelleInfra,TauxTaxeAnnuelleInfra tauxTaxeAnnuelleInfra) {
        this.taxeBoissonAnnuelleInfra = taxeBoissonAnnuelleInfra;
        this.tauxTaxeAnnuelleInfra = tauxTaxeAnnuelleInfra;
    }

    @Override
    public void validate(TaxeBoissonAnnuelleMontantInput taxeBoissonAnnuelleAddInput, Result result) throws ParseException {


        if (taxeBoissonAnnuelleAddInput.getYear() == 0 || taxeBoissonAnnuelleAddInput.getDateDeclaration() == null|| taxeBoissonAnnuelleAddInput.getLocalRef() == null) {
            result.addErrorMessage("taxeBoissonTrim.is_null");
        }
    }

    @Override
    public void run(TaxeBoissonAnnuelleMontantInput taxeBoissonAnnuelleAddInput, Result result) throws ParseException {
        //adilmx Run Code
        BigDecimal montantTotalTaxeAnnuelle = null,montantTaxeRetardDeclaration = null;
        BigDecimal tarifTaxeLateOneMounth = null, tarifTaxeLateMoreThanTwoMounth = null ,mounthsLate= null;

        TaxeBoissonAnnuelle taxeBoissonAnnuelle = taxeBoissonAnnuelleInfra.findByLocalRefAndYear(taxeBoissonAnnuelleAddInput.getLocalRef(),taxeBoissonAnnuelleAddInput.getYear());
        if(taxeBoissonAnnuelle == null){
            taxeBoissonAnnuelle.setYear(taxeBoissonAnnuelleAddInput.getYear());
            taxeBoissonAnnuelle.setLocalRef(taxeBoissonAnnuelleAddInput.getLocalRef());
            montantTotalTaxeAnnuelle = BigDecimal.valueOf(0);
        }else{
            montantTotalTaxeAnnuelle = taxeBoissonAnnuelle.getMontantTotaleTaxeAnnuelle();
        }
        List<TauxTaxeAnnuelleEntity> tauxTaxeAnnuelleEntities = tauxTaxeAnnuelleInfra.findAll();
        for (TauxTaxeAnnuelleEntity tauxTaxeAnnuelleEntity:tauxTaxeAnnuelleEntities) {
            LocalDate dateDeclaration = dateUtils.dateToLocaleDate(taxeBoissonAnnuelleAddInput.getDateDeclaration());
            LocalDate dateFinApplication = dateUtils.dateToLocaleDate(tauxTaxeAnnuelleEntity.getDateFinApplication());
            LocalDate dateFinAnnee = dateUtils.getLocaleDateWithMounth(12,taxeBoissonAnnuelleAddInput.getYear());
            if(dateUtils.leftGreaterThanRight(dateFinApplication,dateDeclaration)){
                long days = dateUtils.getDaysBetween(dateFinAnnee,dateDeclaration);
                long daysNextTwoMounths = dateUtils.getDaysOfNextTwoMounths(4,taxeBoissonAnnuelleAddInput.getYear());
                LocalDate nextDate = dateUtils.getLocaleDateWithMounth(1 , taxeBoissonAnnuelleAddInput.getYear());
                long daysNextMounth = dateUtils.getDays(nextDate);
                if (days > daysNextTwoMounths) {
                    tarifTaxeLateMoreThanTwoMounth = tauxTaxeAnnuelleEntity.getTarifPremierMoisRetard();
                } else if(days > daysNextMounth) {
                    tarifTaxeLateOneMounth = tauxTaxeAnnuelleEntity.getTarifAutresMoisRetard();
                }
                mounthsLate = BigDecimal.valueOf(dateDeclaration.getMonth().getValue() - dateFinAnnee.getMonth().getValue());
            }
        }
        montantTaxeRetardDeclaration = montantTotalTaxeAnnuelle
                        .multiply(tarifTaxeLateOneMounth)
                .add(montantTotalTaxeAnnuelle
                        .multiply(tarifTaxeLateMoreThanTwoMounth)
                        .multiply(mounthsLate)
                );

        result.addInfoMessage(taxeBoissonAnnuelleInfra.getMessage("taxeBoissonAnnuelle.taxeBoissonTrim.created"));
    }
}
