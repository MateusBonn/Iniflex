package com.proctIniflex.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "TB_FUNCIONARIO")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
	@Column(nullable = false, unique = true, length = 10)
    private String nome;
	
	@Column(nullable = false, unique = true, length = 10)
    private LocalDate dataNascimento;
	
	@Column(nullable = false, length = 70)
	private BigDecimal salario;
	
	@Column(nullable = false, length = 70)
	private String funcao;

}
