package com.fstg.taxeboisson.infrastructure.vo;

import lombok.Data;

@Data
public class ZoneVo {
    Long id;
    String nom;
    SecteurVo secteur;
}
