package com.fstg.taxeboisson.infrastructure.vo;

import lombok.Data;

import java.util.List;

@Data
public class SecteurVo {
    Long id;
    String nom;
    List<AnnexAdministrativeVo> annexAdministratives ;
    List<ZoneVo> zones;
    CommuneVo commune;
}
