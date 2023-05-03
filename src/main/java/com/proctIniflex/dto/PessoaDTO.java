package com.proctIniflex.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {

	@NotNull(message = "O nome não pode ser nula")
	private String nome;

	@NotNull(message = "A data de nascimento não pode ser nula")
	private LocalDate dataNascimento;
	

}
