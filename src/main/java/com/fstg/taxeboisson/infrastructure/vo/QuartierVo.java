package com.fstg.taxeboisson.infrastructure.vo;

import lombok.Data;

import java.util.List;

@Data
public class QuartierVo {
    Long id;
    String nom;
    String numero;
    List<RueVo> rues;
    AnnexAdministrativeVo annexAdministrative;
}
