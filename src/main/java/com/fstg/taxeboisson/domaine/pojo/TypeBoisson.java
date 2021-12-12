package com.fstg.taxeboisson.domaine.pojo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TypeBoisson {
    private String ref;
    private String typeBoissson;
}
