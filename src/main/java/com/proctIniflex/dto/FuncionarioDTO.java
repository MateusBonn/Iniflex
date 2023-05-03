package com.proctIniflex.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO extends PessoaDTO{
	
	@NotNull(message = "O valor do salário não pode ser nulo")
	private BigDecimal salario;
	
	@NotNull(message = "A função não pode ser nula")
    private String funcao;
    

}
