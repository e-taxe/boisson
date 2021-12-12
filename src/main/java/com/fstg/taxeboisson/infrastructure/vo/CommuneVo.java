package com.fstg.taxeboisson.infrastructure.vo;

import lombok.Data;

import java.util.List;

@Data
public class CommuneVo {
    Long id;
    String nom;
    List<SecteurVo> secteurs;
    VilleVo ville;
}
