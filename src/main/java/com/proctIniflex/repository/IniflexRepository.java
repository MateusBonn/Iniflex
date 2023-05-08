package com.proctIniflex.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proctIniflex.model.Funcionario;

@Repository
public interface IniflexRepository extends JpaRepository<Funcionario, UUID>{
	
	boolean existsByNome(String nome);

	Optional<Funcionario> findByNome(String nome);
	
	List<Funcionario> findAll();

	List<Funcionario> findByOrderByFuncao();

	List<Funcionario> findByOrderByNome();

	@Query("SELECT f FROM Funcionario f WHERE MONTH(f.dataNascimento) = 10")
	List<Funcionario> findByDateOct();

	@Query("SELECT f FROM Funcionario f WHERE MONTH(f.dataNascimento) = 12")
	List<Funcionario> findByDateDec();


}
