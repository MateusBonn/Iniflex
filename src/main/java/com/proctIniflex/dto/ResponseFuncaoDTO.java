package com.proctIniflex.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseFuncaoDTO {
	
	public String funcao;
	
	public List<FuncionarioDTO> funcionarios;

}
