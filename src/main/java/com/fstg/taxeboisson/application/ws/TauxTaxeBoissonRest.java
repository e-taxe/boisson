package com.fstg.taxeboisson.application.ws;

import com.fstg.taxeboisson.application.dto.TauxTaxeBoissonDTO;
import com.fstg.taxeboisson.infrastructure.entity.TauxTaxeBoissonEntity;
import com.fstg.taxeboisson.infrastructure.facade.TauxTaxeBoissonInfra;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taxeBoissonTrim")
@Api("Cette classe permet de tester les process de TauxTaxeBoisson : save")
public class TauxTaxeBoissonRest {
    @Autowired
    private TauxTaxeBoissonInfra tauxTaxeBoissonInfra;

    @ApiOperation("saving taux taxe process")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/saveTauxTaxe")
    public int saveTauxTaxe(@RequestBody TauxTaxeBoissonDTO tauxTaxeBoissonDTO) {
        TauxTaxeBoissonEntity tauxTaxeBoissonEntity = TauxTaxeBoissonDTO.toTauxTaxeBoissonEntity(tauxTaxeBoissonDTO);
        return tauxTaxeBoissonInfra.save(tauxTaxeBoissonEntity);
    }
}
