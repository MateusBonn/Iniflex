package com.proctIniflex.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

	//TENTEI IMPLEMENTAR PARA CRIAR UM MAP E RETORNAR AGRUPADO POR FUNÇAO , MAS NAO CONSEGUI
	public List<Funcionario> obterTodosFuncionariosFuncao() {
		List<Funcionario> listfuncionarios = iniflexRepository.findByOrderByFuncao();
		/*Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
		for (Funcionario funcionario : listfuncionarios) {
			String funcao = funcionario.getFuncao();
			List<Funcionario> listaFuncionarios = funcionariosPorFuncao
					.getOrDefault(funcao, new ArrayList<Funcionario>());
			listaFuncionarios.add(funcionario);
			funcionariosPorFuncao.put(funcao, listaFuncionarios);
		}
		List<String> funcoesFuncionarios = new ArrayList<>();
		for (String funcao : funcionariosPorFuncao.keySet()) {
			List<Funcionario> funcionariosDaFuncao = funcionariosPorFuncao
					.get(funcao);
			funcoesFuncionarios.add("Funcionários da função " + funcao + ": ");
			for (Funcionario funcionario : funcionariosDaFuncao) {
				funcoesFuncionarios.add("- " + funcionario.getNome()
						+ ", Salário: R$ "
						+ formatoDecimal.format(funcionario.getSalario()));
			}
		}*/

			return listfuncionarios;
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

	public List<String> outubroDezembro(){
		List<Funcionario> allFuncionarios = iniflexRepository.findAll();
		List<String> aniversariantes = new ArrayList<>();

		for (Funcionario funcionario : allFuncionarios) {
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
