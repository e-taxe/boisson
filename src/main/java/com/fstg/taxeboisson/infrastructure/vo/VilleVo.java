package com.fstg.taxeboisson.infrastructure.vo;

import lombok.Data;

import java.util.List;

@Data
public class VilleVo {
    Long id;
    String nom;
    List<CommuneVo> communes;
}
