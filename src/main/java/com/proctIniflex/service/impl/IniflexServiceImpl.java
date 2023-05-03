package com.proctIniflex.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.proctIniflex.dto.FuncionarioDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.proctIniflex.model.FuncionarioModel;
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
	public FuncionarioModel save(FuncionarioModel funcionarioModel) {
		return iniflexRepository.save(funcionarioModel);
	}

	@Override
	public Optional<FuncionarioModel> findByNome(String nome) {
		return iniflexRepository.findBynome(nome);
	}

	@Override
	@Transactional
	public void delete(FuncionarioModel funcionariotModel) {
		iniflexRepository.delete(funcionariotModel);
	}

	@Override
	public String somaSalarios() {

		List<FuncionarioModel> listaSalarios = iniflexRepository.findAll();
		BigDecimal
				totalSalarios = BigDecimal.ZERO;

		for (FuncionarioModel funcionario : listaSalarios) {
			totalSalarios =
					totalSalarios.add(funcionario.getSalario());
		}

		return formatoDecimal.format(totalSalarios);

	}

	//TENTEI IMPLEMENTAR PARA CRIAR UM MAP E RETORNAR AGRUPADO POR FUNÇAO , MAS NAO CONSEGUI
	public List<String> obterTodosOsFuncionarios() {
		List<FuncionarioModel> listfuncionarios = iniflexRepository.findAll();
		Map<String, List<FuncionarioModel>> funcionariosPorFuncao = new HashMap<>();
		for (FuncionarioModel funcionario : listfuncionarios) {
			String funcao = funcionario.getFuncao();
			List<FuncionarioModel> listaFuncionarios = funcionariosPorFuncao
					.getOrDefault(funcao, new ArrayList<FuncionarioModel>());
			listaFuncionarios.add(funcionario);
			funcionariosPorFuncao.put(funcao, listaFuncionarios);
		}
		List<String> funcoesFuncionarios = new ArrayList<>();
		for (String funcao : funcionariosPorFuncao.keySet()) {
			List<FuncionarioModel> funcionariosDaFuncao = funcionariosPorFuncao
					.get(funcao);
			funcoesFuncionarios.add("Funcionários da função " + funcao + ": ");
			for (FuncionarioModel funcionario : funcionariosDaFuncao) {
				funcoesFuncionarios.add("- " + funcionario.getNome()
						+ ", Salário: R$ "
						+ formatoDecimal.format(funcionario.getSalario()));
			}
		}

			return funcoesFuncionarios;
		}

		public List<String> obterFuncionarioAlfabetica () {
			List<FuncionarioModel> funcionarios = iniflexRepository.findAll();
			Collections.sort(funcionarios, new Comparator<FuncionarioModel>() {
				@Override
				public int compare(FuncionarioModel funcionario, FuncionarioModel funcionario2) {
					return funcionario.getNome().compareTo(funcionario2.getNome());
				}
			});
			List<String> nomesFuncionarios = new ArrayList<>();
			for (FuncionarioModel funcionario : funcionarios) {
				nomesFuncionarios.add(funcionario.getNome());
			}
			return nomesFuncionarios;
		}

		@Override
		public boolean existsByName (String nome)
		{
			return iniflexRepository.existsByNome(nome);
		}

	public List<String> aumentoSalario(Long aumento){
		BigDecimal percentual = BigDecimal.valueOf(aumento).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
		List<FuncionarioModel> salariosFuncionarios = iniflexRepository.findAll();
		for (FuncionarioModel funcionario : salariosFuncionarios) {
			BigDecimal salarioAtual = funcionario.getSalario();
			funcionario.setSalario(salarioAtual.add(funcionario.getSalario().multiply(
					percentual)));
			iniflexRepository.save(funcionario);
			log.info("Aumento de salário do funcionarios " + funcionario.getNome());
		}
		List<String> salariosAumentados = new ArrayList<>();
		for (FuncionarioModel funcionario : salariosFuncionarios){
			salariosAumentados.add("Funcionario: " + funcionario.getNome() + " Novo salários: " + formatoDecimal.format(funcionario.getSalario()));
		}
		return salariosAumentados;

	}

	public List<String> qtSalario(){
		BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
		List<FuncionarioModel> salarios = iniflexRepository.findAll();
		List<String> qtSalarioMinimo = new ArrayList<>();
		for (FuncionarioModel funcionario : salarios) {
			BigDecimal salario = funcionario.getSalario();
			qtSalarioMinimo.add(funcionario.getNome() + " recebe " + salario.divide(salarioMinimo, 0, RoundingMode.FLOOR).intValue() + " salários mínimos");
		}
		return qtSalarioMinimo;
	}

	public List<String> outubroDezembro(){
		List<FuncionarioModel> allFuncionarios = iniflexRepository.findAll();
		List<String> aniversariantes = new ArrayList<>();

		for (FuncionarioModel funcionario : allFuncionarios) {
			if (funcionario.getDataNascimento().getMonthValue() == 10
					|| funcionario.getDataNascimento().getMonthValue() == 12) {
				aniversariantes.add("Nome: " + funcionario.getNome() + " - "
						+ "Data de nascimento: "
						+ LocalDate.parse(funcionario.getDataNascimento().toString()).format(formatter)
						+ " - " + "Salário: "
						+ formatoDecimal.format(funcionario.getSalario())
						+ " - " + "Função: " + funcionario.getFuncao());
			}
		}
		return aniversariantes;
	}

	public String maiorIdade(){
		List<FuncionarioModel> listaIdades = iniflexRepository.findAll();
		FuncionarioModel funcionarioMaisVelho = listaIdades.get(0);
		int idadeMaisVelho = funcionarioMaisVelho.getDataNascimento()
				.until(LocalDate.now()).getYears();
		for (int i = 1; i < listaIdades.size(); i++) {
			FuncionarioModel funcionarioAtual = listaIdades.get(i);
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
