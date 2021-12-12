package com.fstg.taxeboisson.domaine.taxeBoissonAnnuelle.montant;

import com.fstg.taxeboisson.domaine.core.AbstractProcessImpl;
import com.fstg.taxeboisson.domaine.core.Result;
import com.fstg.taxeboisson.infrastructure.facade.TaxeBoissonAnnuelleInfra;

import java.text.ParseException;

public class TaxeBoissonAnnuelleMontantProcessImpl extends AbstractProcessImpl<TaxeBoissonAnnuelleMontantInput> implements TaxeBoissonAnnuelleMontantProcess {
    private TaxeBoissonAnnuelleInfra taxeBoissonAnnuelleInfra;

    public TaxeBoissonAnnuelleMontantProcessImpl(TaxeBoissonAnnuelleInfra taxeBoissonAnnuelleInfra) {
        this.taxeBoissonAnnuelleInfra = taxeBoissonAnnuelleInfra;
    }

    @Override
    public void validate(TaxeBoissonAnnuelleMontantInput taxeBoissonAnnuelleAddInput, Result result) throws ParseException {


        if (taxeBoissonAnnuelleAddInput.getMontantTotaleTaxeAnnuelle() == 0 || taxeBoissonAnnuelleAddInput.getYear() == 0 ||
                taxeBoissonAnnuelleAddInput.getChiffreAffaire() == null ||
                taxeBoissonAnnuelleAddInput.getLocalRef() == null
        ) {
            result.addErrorMessage("taxeBoissonTrim.is_null");
        }
    }

    @Override
    public void run(TaxeBoissonAnnuelleMontantInput taxeBoissonAnnuelleAddInput, Result result) throws ParseException {
        //adilmx Run Code

        result.addInfoMessage(taxeBoissonAnnuelleInfra.getMessage("taxeBoissonAnnuelle.taxeBoissonTrim.created"));
    }
}
