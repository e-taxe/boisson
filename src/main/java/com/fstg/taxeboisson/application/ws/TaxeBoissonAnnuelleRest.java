package com.fstg.taxeboisson.application.ws;

import com.fstg.taxeboisson.application.dto.TaxeBoissonAnnuelleDTO;
import com.fstg.taxeboisson.domaine.core.Result;
import com.fstg.taxeboisson.domaine.taxeBoissonAnnuelle.montant.TaxeBoissonAnnuelleMontantInput;
import com.fstg.taxeboisson.domaine.taxeBoissonAnnuelle.montant.TaxeBoissonAnnuelleMontantProcess;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/taxeBoissonAnnuelle")
@Api("Cette classe permet de tester les process de TaxeBoissonAnnuelle")
public class TaxeBoissonAnnuelleRest {
    @Autowired
    private TaxeBoissonAnnuelleMontantProcess taxeBoissonAnnuelleAddProcess;

    @SneakyThrows
    @ApiOperation("adding taxe trim process")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/addTaxeAnnuelle")
    public Result addTaxeAnnuelle(@RequestBody TaxeBoissonAnnuelleDTO taxeBoissonAnnuelleDTO) throws ParseException {
        TaxeBoissonAnnuelleMontantInput taxeBoissonAnnuelleAddInput = TaxeBoissonAnnuelleDTO.toTaxeBoissonAnnuelleAddInput(taxeBoissonAnnuelleDTO);
        return taxeBoissonAnnuelleAddProcess.execute(taxeBoissonAnnuelleAddInput);
    }
}
