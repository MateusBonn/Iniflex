package com.proctIniflex.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.proctIniflex.dto.FuncionarioDTO;
import com.proctIniflex.dto.ResponseFuncaoDTO;
import com.proctIniflex.model.Funcionario;
import com.proctIniflex.repository.IniflexRepository;
import com.proctIniflex.service.IniflexService;

import jakarta.transaction.Transactional;
@Slf4j
@Service
public class IniflexServiceImpl implements IniflexService {

	final IniflexRepository iniflexRepository;

	public IniflexServiceImpl(IniflexRepository iniflexRepository) {
		this.iniflexRepository = iniflexRepository;
	}

	Locale ptBr = new Locale("pt", "BR");
	NumberFormat formatoDecimal = NumberFormat.getNumberInstance(ptBr);
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Transactional
	@Override
	public Funcionario save(Funcionario funcionario) {
		return iniflexRepository.save(funcionario);
	}

	@Override
	public Optional<Funcionario> findByNome(String nome) {
		return iniflexRepository.findByNome(nome);
	}

	@Override
	@Transactional
	public void delete(Funcionario funcionariotModel) {
		iniflexRepository.delete(funcionariotModel);
	}

	@Override
	public String somaSalarios() {

		List<Funcionario> listaSalarios = iniflexRepository.findAll();
		BigDecimal
				totalSalarios = BigDecimal.ZERO;

		for (Funcionario funcionario : listaSalarios) {
			totalSalarios =
					totalSalarios.add(funcionario.getSalario());
		}

		return formatoDecimal.format(totalSalarios);

	}
	
	//CONSEGUI!! NÃO DO JEITO QUE EU QUERIA! MAS CONSEGUI
	public List<ResponseFuncaoDTO> obterTodosFuncionariosFuncao() {
		List<Funcionario> listfuncionarios = iniflexRepository.findByOrderByFuncao();
		Map<String, List<Funcionario>> gruposPorFuncao = listfuncionarios.stream()
	            .collect(Collectors.groupingBy(Funcionario::getFuncao));
	        List<ResponseFuncaoDTO> respostas = gruposPorFuncao.entrySet().stream()
	            .map(grupoPorFuncao -> {
	                String funcao = grupoPorFuncao.getKey();
	                List<FuncionarioDTO> funcionariosDTO = grupoPorFuncao.getValue().stream()
	                    .map(funcionario  -> new FuncionarioDTO(
	                    		funcionario .getNome(),
	                    		funcionario .getDataNascimento(),
	                    		funcionario .getSalario(),
	                    		funcionario.getFuncao()
	                    ))
	                    .collect(Collectors.toList());
	                return new ResponseFuncaoDTO(funcao, funcionariosDTO);
	            })
	            .collect(Collectors.toList());
	        return respostas;
		}

		public List<Funcionario> obterFuncionarioAlfabetica () {
			List<Funcionario> funcionarios = iniflexRepository.findByOrderByNome();
			return funcionarios;
		}

		@Override
		public boolean existsByName (String nome)
		{
			return iniflexRepository.existsByNome(nome);
		}

	public List<String> aumentoSalario(Long aumento){
		BigDecimal percentual = BigDecimal.valueOf(aumento).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
		List<Funcionario> salariosFuncionarios = iniflexRepository.findAll();
		for (Funcionario funcionario : salariosFuncionarios) {
			BigDecimal salarioAtual = funcionario.getSalario();
			funcionario.setSalario(salarioAtual.add(funcionario.getSalario().multiply(
					percentual)));
			iniflexRepository.save(funcionario);
			log.info("Aumento de salário do funcionarios " + funcionario.getNome());
		}
		List<String> salariosAumentados = new ArrayList<>();
		for (Funcionario funcionario : salariosFuncionarios){
			salariosAumentados.add("Funcionario: " + funcionario.getNome() + " Novo salários: " + formatoDecimal.format(funcionario.getSalario()));
		}
		return salariosAumentados;

	}

	public List<String> qtSalario(){
		BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
		List<Funcionario> salarios = iniflexRepository.findAll();
		List<String> qtSalarioMinimo = new ArrayList<>();
		for (Funcionario funcionario : salarios) {
			BigDecimal salario = funcionario.getSalario();
			qtSalarioMinimo.add(funcionario.getNome() + " recebe " + salario.divide(salarioMinimo, 0, RoundingMode.FLOOR).intValue() + " salários mínimos");
		}
		return qtSalarioMinimo;
	}

	public List<Funcionario> outubroDezembro(){
		List<Funcionario> aniversariantesOct = iniflexRepository.findByDateOct();
		List<Funcionario> aniversariantesDec = iniflexRepository.findByDateDec();
		List<Funcionario> aniversariantes = new ArrayList<>();
		aniversariantes.addAll(aniversariantesOct);
		aniversariantes.addAll(aniversariantesDec);
		return aniversariantes;
	}

	public String maiorIdade(){
		List<Funcionario> listaIdades = iniflexRepository.findAll();
		Funcionario funcionarioMaisVelho = listaIdades.get(0);
		int idadeMaisVelho = funcionarioMaisVelho.getDataNascimento()
				.until(LocalDate.now()).getYears();
		for (int i = 1; i < listaIdades.size(); i++) {
			Funcionario funcionarioAtual = listaIdades.get(i);
			int idadeAtual = funcionarioAtual.getDataNascimento()
					.until(LocalDate.now()).getYears();

			if (idadeAtual > idadeMaisVelho) {
				funcionarioMaisVelho = funcionarioAtual;
				idadeMaisVelho = idadeAtual;
			}
		}
		String idadeMaior = "Funcionário mais velho: " + funcionarioMaisVelho.getNome() + ", Idade: "
				+ idadeMaisVelho;


		return idadeMaior;
	}
}
