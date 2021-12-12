package com.fstg.taxeboisson.infrastructure.vo;

import lombok.Data;

import java.util.List;

@Data
public class RueVo {
    private String nom;
    private String numero;
    private QuartierVo quartier;
    private List<LocaleVo> locals;
}
