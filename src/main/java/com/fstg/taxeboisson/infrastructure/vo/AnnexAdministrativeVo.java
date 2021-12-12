package com.fstg.taxeboisson.infrastructure.vo;

import lombok.Data;

import java.util.List;

@Data
public class AnnexAdministrativeVo {
    Long id;
    String nom;
    String numero;
    List<QuartierVo> quartiers;
    SecteurVo secteur;
}
