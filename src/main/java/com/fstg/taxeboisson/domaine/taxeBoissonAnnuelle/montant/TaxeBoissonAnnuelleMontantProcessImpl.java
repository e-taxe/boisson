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
        }
}
