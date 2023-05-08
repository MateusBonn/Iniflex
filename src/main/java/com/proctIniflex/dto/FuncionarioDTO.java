package com.proctIniflex.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO extends PessoaDTO {

	@NotNull(message = "O valor do salário não pode ser nulo")
	private BigDecimal salario;

	@NotNull(message = "A função não pode ser nula")
	private String funcao;

	public FuncionarioDTO(String nome, LocalDate dataNascimento,BigDecimal salario, String funcao) {
		super(nome, dataNascimento);
		this.salario = salario;
		this.funcao = funcao;
	}

}
