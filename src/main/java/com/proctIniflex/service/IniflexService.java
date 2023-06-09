package com.proctIniflex.service;

import java.util.List;
import java.util.Optional;

import com.proctIniflex.dto.ResponseFuncaoDTO;
import com.proctIniflex.model.Funcionario;

public interface IniflexService {

	List<Funcionario> obterFuncionarioAlfabetica();

	Funcionario save(Funcionario funcionariotModel);

	Optional<Funcionario> findByNome(String nome);

	void delete(Funcionario funcionario);

	String somaSalarios();

	List<ResponseFuncaoDTO> obterTodosFuncionariosFuncao();

	boolean existsByName(String nome);

	List<String> aumentoSalario(Long porcentagem);

	List<String> qtSalario();

	String maiorIdade();

	List<Funcionario> outubroDezembro();
}
