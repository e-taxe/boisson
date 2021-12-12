package com.fstg.taxeboisson.infrastructure.vo;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class DeclarationVo {
    Long id;
    String ref;
    Instant createdAt;
    Instant updatedAt;
    List<DeclarationPieceJointeVo> declarationPieceJointes;
}
