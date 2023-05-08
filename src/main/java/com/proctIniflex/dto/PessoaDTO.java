package com.proctIniflex.dto;

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
public class PessoaDTO {

	@NotNull(message = "O nome não pode ser nula")
	private String nome;

	@NotNull(message = "A data de nascimento não pode ser nula")
	private LocalDate dataNascimento;
	

}
