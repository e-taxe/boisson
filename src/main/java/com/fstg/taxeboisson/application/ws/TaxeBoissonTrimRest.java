package com.fstg.taxeboisson.application.ws;

import com.fstg.taxeboisson.application.dto.TaxeBoissonTrimDTO;
import com.fstg.taxeboisson.domaine.core.Result;
import com.fstg.taxeboisson.domaine.taxeBoissonTrim.montant.TaxeBoissonTrimMontantInput;
import com.fstg.taxeboisson.domaine.taxeBoissonTrim.montant.TaxeBoissonTrimMontantProcess;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/taxeBoissonTrim")
@Api("Cette classe permet de tester les process de TaxeBoissonTrim : add")
public class TaxeBoissonTrimRest {
    @Autowired
    private TaxeBoissonTrimMontantProcess taxeBoissonTrimAddProcess;

    @SneakyThrows
    @ApiOperation("adding taxe trim process")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/addTaxeTrim")
    public Result addTaxeTrim(@RequestBody TaxeBoissonTrimDTO taxeBoissonTrimDTO) throws ParseException {
        TaxeBoissonTrimMontantInput taxeBoissonTrimAddInput = TaxeBoissonTrimDTO.toTaxeBoissonTrimAddInput(taxeBoissonTrimDTO);
        return taxeBoissonTrimAddProcess.execute(taxeBoissonTrimAddInput);
    }
}
