package com.fstg.taxeboisson.infrastructure.vo;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ActiviteVo {
    Long id;
    String ref;
    String nom;
    List<LocaleVo> locals;
    Instant createdAt;
    Instant updatedAt;
}
