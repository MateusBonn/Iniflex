package com.proctIniflex.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.proctIniflex.dto.FuncionarioDTO;
import com.proctIniflex.model.FuncionarioModel;

public interface IniflexService {

	List<String> obterFuncionarioAlfabetica();

	FuncionarioModel save(FuncionarioModel funcionariotModel);

	Optional<FuncionarioModel> findByNome(String nome);

	void delete(FuncionarioModel funcionarioModel);

	String somaSalarios();

	List<String> obterTodosOsFuncionarios();

	boolean existsByName(String nome);

	List<String> aumentoSalario(Long porcentagem);

	List<String> qtSalario();

	String maiorIdade();

	List<String> outubroDezembro();
}
