package com.fstg.taxeboisson.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class TypeBoissonEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ref;
    private String typeBoissson;
}
