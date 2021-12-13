package com.fstg.taxeboisson.domaine.taxeBoissonTrim.montant;

import com.fstg.taxeboisson.domaine.converter.MapPojo;
import com.fstg.taxeboisson.domaine.core.AbstractProcessImpl;
import com.fstg.taxeboisson.domaine.core.Result;
import com.fstg.taxeboisson.domaine.pojo.TauxTaxeBoisson;
import com.fstg.taxeboisson.domaine.pojo.TaxeBoissonAnnuelle;
import com.fstg.taxeboisson.domaine.pojo.TaxeBoissonTrim;
import com.fstg.taxeboisson.domaine.utils.DateUtils;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonEntity;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonRetardTrimEntity;
import com.fstg.taxeboisson.infrastructure.entity.TaxeBoissonAnnuelleEntity;
import com.fstg.taxeboisson.infrastructure.entity.TaxeBoissonTrimEntity;
import com.fstg.taxeboisson.infrastructure.facade.TauxTaxeBoissonInfra;
import com.fstg.taxeboisson.infrastructure.facade.TauxTaxeBoissonRetardTrimInfra;
import com.fstg.taxeboisson.infrastructure.facade.TaxeBoissonAnnuelleInfra;
import com.fstg.taxeboisson.infrastructure.facade.TaxeBoissonTrimInfra;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class TaxeBoissonTrimMontantProcessImpl extends AbstractProcessImpl<TaxeBoissonTrimMontantInput> implements TaxeBoissonTrimMontantProcess {
    private TaxeBoissonTrimInfra taxeBoissonTrimInfra;
    private TauxTaxeBoissonInfra tauxTaxeBoissonInfra;
    private TauxTaxeBoissonRetardTrimInfra tauxTaxeBoissonRetardTrimInfra;
    private DateUtils dateUtils = new DateUtils();
    MapPojo mapPojo = new MapPojo();
    private TaxeBoissonAnnuelleInfra taxeBoissonAnnuelleInfra;


    public TaxeBoissonTrimMontantProcessImpl(TaxeBoissonTrimInfra taxeBoissonTrimInfra, TauxTaxeBoissonInfra tauxTaxeBoissonInfra, TauxTaxeBoissonRetardTrimInfra tauxTaxeBoissonRetardTrimInfra,TaxeBoissonAnnuelleInfra taxeBoissonAnnuelleInfra) {
        this.taxeBoissonTrimInfra = taxeBoissonTrimInfra;
        this.tauxTaxeBoissonInfra = tauxTaxeBoissonInfra;
        this.tauxTaxeBoissonRetardTrimInfra = tauxTaxeBoissonRetardTrimInfra;
        this.taxeBoissonAnnuelleInfra = taxeBoissonAnnuelleInfra;
    }

    @Override
    public void validate(TaxeBoissonTrimMontantInput taxeBoissonTrimAddInput, Result result) {
        if (taxeBoissonTrimAddInput.getNumTrim() == 0 || taxeBoissonTrimAddInput.getYear() == 0 ||
        taxeBoissonTrimAddInput.getChiffreAffaire() == null || taxeBoissonTrimAddInput.getDatePaiement() ==null ||
                taxeBoissonTrimAddInput.getLocalRef() == null
        ) {
            result.addErrorMessage("taxeBoissonTrim.is_null");
        }
        Date currentDate = new Date();
        if (taxeBoissonTrimAddInput.getDatePaiement().compareTo(currentDate) > 0) {
            result.addErrorMessage("taxeBoissonTrim.date_not_correct");
        }
    }

    @Override
    public void run(TaxeBoissonTrimMontantInput taxeBoissonTrimAddInput, Result result) {
        //init variables
        BigDecimal totaleTaxeTrim;
        BigDecimal tarifTaxe = null;
        BigDecimal tarifTaxeRetardOneMonth =  null;
        BigDecimal tarifTaxeRetardMoreThanOneMonth = null;
        BigDecimal mounthsLate = null;
        Boolean isPaymentOneMonthLate = false;
        Boolean isPaymentMoreThanMonthLate = false;
        TaxeBoissonTrim taxeBoissonTrim = new TaxeBoissonTrim();
        TauxTaxeBoisson tauxTaxeBoisson = null;
        DateUtils dateUtils = null;
        TaxeBoissonTrimEntity taxeBoissonTrimEntity = new TaxeBoissonTrimEntity();
        TaxeBoissonAnnuelleEntity taxeBoissonAnnuelleEntity = new TaxeBoissonAnnuelleEntity();



        List<TauxTaxeBoissonEntity> tauxTaxeBoissons = tauxTaxeBoissonInfra.findAll();
        for (TauxTaxeBoissonEntity tauxTaxeBoissonEntity : tauxTaxeBoissons) {
            //search the tax percentage which is applicable for the specific trimester
            //transform Date to LocaleDate
            LocalDate dateFinTrim = dateUtils.getLocaleDateWithNumTrim(taxeBoissonTrimAddInput.getNumTrim(),taxeBoissonTrimAddInput.getYear());
            LocalDate datePaiement = dateUtils.dateToLocaleDate(taxeBoissonTrimAddInput.getDatePaiement());
            LocalDate dateFinApplication = dateUtils.dateToLocaleDate(tauxTaxeBoisson.getDateFinApplication());
            if (dateUtils.leftGreaterThanRight(dateFinApplication,dateFinTrim)) {
                BeanUtils.copyProperties(tauxTaxeBoissonEntity, tauxTaxeBoisson);
                //get the days between the end of trim and payment day
                LocalDate nextDate = dateUtils.getLocaleDateWithMounth(taxeBoissonTrimAddInput.getNumTrim()*3+1 , taxeBoissonTrimAddInput.getYear());
                long days = dateUtils.getDaysBetween(dateFinTrim,datePaiement);
                long daysNextMount = dateUtils.getDays(nextDate);
                long daysNextTwoMounths = dateUtils.getDaysOfNextTwoMounths(taxeBoissonTrimAddInput.getNumTrim(), taxeBoissonTrimAddInput.getYear());
                //verify if payment is late or not (1 month or more)

                    if (days > daysNextTwoMounths) {
                        isPaymentMoreThanMonthLate = true;
                    } else if(days > daysNextMount) {
                        isPaymentOneMonthLate = true;
                    }
                //get the percentage of the tax

                tarifTaxe = tauxTaxeBoissonEntity.getTarif();
                List<TauxTaxeBoissonRetardTrimEntity> tauxTaxeBoissonRetardTrims = tauxTaxeBoissonRetardTrimInfra.findAll();
                for (TauxTaxeBoissonRetardTrimEntity tauxTaxeBoissonRetardTrim : tauxTaxeBoissonRetardTrims) {
                    LocalDate dateFinApplicationRetard = dateUtils.dateToLocaleDate(tauxTaxeBoissonRetardTrim.getDateFinApplication());
                    if (dateUtils.leftGreaterThanRight(dateFinApplicationRetard,dateFinTrim)){
                        if (isPaymentOneMonthLate) {
                            tarifTaxeRetardOneMonth = tauxTaxeBoissonRetardTrim.getTarifPremierMoisRetard();
                        }
                        if (isPaymentMoreThanMonthLate) {
                            tarifTaxeRetardMoreThanOneMonth = tauxTaxeBoissonRetardTrim.getTarifAutresMoisRetard();
                            mounthsLate = BigDecimal.valueOf(datePaiement.getMonth().getValue() - dateFinTrim.getMonth().getValue());
                        }
                    }
                }

            }
        }
        System.out.println("tarif taxe : "+tarifTaxe);
        //calculate the total of the trim tax
        totaleTaxeTrim = calculateTaxeTrim( taxeBoissonTrim,taxeBoissonTrimAddInput,tarifTaxe,tarifTaxeRetardOneMonth,tarifTaxeRetardMoreThanOneMonth,mounthsLate);
        setTaxeBoissonTrim( taxeBoissonTrimAddInput, tauxTaxeBoisson, isPaymentOneMonthLate, mounthsLate, totaleTaxeTrim);
        taxeBoissonTrimEntity = mapPojo.taxeBoissonTrimPojotoTaxeTaxeBoissonTrimEntity(taxeBoissonTrim);
        //get the annual tax to set the total tax amount
        TaxeBoissonAnnuelle taxeBoissonAnnuelle = taxeBoissonAnnuelleInfra.findByLocalRefAndYear(taxeBoissonTrimAddInput.getLocalRef(),taxeBoissonTrimAddInput.getYear());
        if(taxeBoissonAnnuelle != null){
            taxeBoissonAnnuelle.setMontantTotaleTaxeAnnuelle(taxeBoissonAnnuelle.getMontantTotaleTaxeAnnuelle().add(totaleTaxeTrim));
            //update the annual taxe
            taxeBoissonAnnuelleInfra.update(taxeBoissonAnnuelle);
        }else{
            taxeBoissonAnnuelle.setMontantTotaleTaxeAnnuelle(totaleTaxeTrim);
            taxeBoissonAnnuelle.setYear(taxeBoissonTrimAddInput.getYear());
            taxeBoissonAnnuelle.setLocalRef(taxeBoissonTrimAddInput.getLocalRef());
            taxeBoissonAnnuelleInfra.save(taxeBoissonAnnuelle);
        }

        //save the trim tax
        taxeBoissonTrimInfra.save(taxeBoissonTrimEntity);
        result.addInfoMessage(taxeBoissonTrimInfra.getMessage("taxeBoissonTrim.taxeBoissonTrim.created"));

    }

    private BigDecimal calculateTaxeTrim(TaxeBoissonTrimAddInput taxeBoissonTrimAddInput,BigDecimal tarifTaxe,BigDecimal tarifTaxeRetardOneMonth,BigDecimal tarifTaxeRetardMoreThanOneMonth,
    BigDecimal mounthsLate
    ){
        BigDecimal totaleTaxeTrim = taxeBoissonTrimAddInput.getChiffreAffaire()
                .multiply(tarifTaxe)
                .add(taxeBoissonTrimAddInput.getChiffreAffaire()
                        .multiply(tarifTaxeRetardOneMonth))
                .add(taxeBoissonTrimAddInput.getChiffreAffaire()
                        .multiply(tarifTaxeRetardMoreThanOneMonth)
                        .multiply(mounthsLate)
                );
    }

    private void setTaxeBoissonTrim(TaxeBoissonTrim taxeBoissonTrim , TaxeBoissonTrimAddInput taxeBoissonTrimAddInput,TauxTaxeBoisson tauxTaxeBoisson,boolean isPaymentLate,BigDecimal mounthsLate,BigDecimal totaleTaxeTrim){

        taxeBoissonTrim.setNumTrim(taxeBoissonTrimAddInput.getNumTrim());
        taxeBoissonTrim.setYear(taxeBoissonTrimAddInput.getYear());
        taxeBoissonTrim.setTauxTaxeBoisson(tauxTaxeBoisson);
        taxeBoissonTrim.setChiffreAffaire(taxeBoissonTrimAddInput.getChiffreAffaire());
        taxeBoissonTrim.setLocalRef(taxeBoissonTrimAddInput.getLocalRef());
        taxeBoissonTrim.setPaymentLate(isPaymentLate);
        taxeBoissonTrim.setNbrMoisRetard(mounthsLate);
        //set the result of total tax
        taxeBoissonTrim.setMontantTotaleTaxeTrim(totaleTaxeTrim);
    }
}
