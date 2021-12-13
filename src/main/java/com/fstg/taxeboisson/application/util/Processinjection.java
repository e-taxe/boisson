package com.fstg.taxeboisson.application.util;

import com.fstg.taxeboisson.domaine.taxeBoissonAnnuelle.montant.TaxeBoissonAnnuelleMontantProcess;
import com.fstg.taxeboisson.domaine.taxeBoissonAnnuelle.montant.TaxeBoissonAnnuelleMontantProcessImpl;
import com.fstg.taxeboisson.domaine.taxeBoissonTrim.montant.TaxeBoissonTrimMontantProcess;
import com.fstg.taxeboisson.domaine.taxeBoissonTrim.montant.TaxeBoissonTrimMontantProcessImpl;
import com.fstg.taxeboisson.infrastructure.facade.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Processinjection {

    @Bean
    public TaxeBoissonTrimMontantProcess taxeBoissonTrimAddProcess(TaxeBoissonTrimInfra taxeBoissonTrimInfra, TauxTaxeBoissonInfra tauxTaxeBoissonInfra, TauxTaxeBoissonRetardTrimInfra tauxTaxeBoissonRetardTrimInfra,TaxeBoissonAnnuelleInfra taxeBoissonAnnuelleInfra){
        return new TaxeBoissonTrimMontantProcessImpl(taxeBoissonTrimInfra,tauxTaxeBoissonInfra,tauxTaxeBoissonRetardTrimInfra,taxeBoissonAnnuelleInfra);
    }

    @Bean
    public TaxeBoissonAnnuelleMontantProcess taxeBoissonAnnuelleAddProcess(TaxeBoissonAnnuelleInfra taxeBoissonAnnuelleInfra, TauxTaxeAnnuelleInfra tauxTaxeAnnuelleInfra){
        return new TaxeBoissonAnnuelleMontantProcessImpl(taxeBoissonAnnuelleInfra,tauxTaxeAnnuelleInfra);
    }
}

