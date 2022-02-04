package com.fstg.taxeboisson.infrastructure.vo;


import lombok.Data;

@Data
public class LocaleVo {
    Long id;
    String ref;
    String nom;
    String numeroCaissier;
    String nomCommercial;
    RedevableVo redevable;
    RueVo rue;
    ActiviteVo activite;
    DeclarationVo declaration;

}
