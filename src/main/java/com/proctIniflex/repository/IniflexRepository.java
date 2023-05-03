package com.proctIniflex.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.proctIniflex.dto.FuncionarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proctIniflex.model.FuncionarioModel;

@Repository
public interface IniflexRepository extends JpaRepository<FuncionarioModel, UUID>{
	
	boolean existsByNome(String nome);

	Optional<FuncionarioModel> findBynome(String nome);
	
	List<FuncionarioModel> findAll();
}
