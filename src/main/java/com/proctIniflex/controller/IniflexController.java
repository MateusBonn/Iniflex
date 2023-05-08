package com.proctIniflex.controller;

import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proctIniflex.dto.FuncionarioDTO;
import com.proctIniflex.model.Funcionario;
import com.proctIniflex.service.IniflexService;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/iniflex")
public class IniflexController {


    private final IniflexService iniflexService;

    public IniflexController(IniflexService iniflexService) {
        this.iniflexService = iniflexService;

    }
    /*
    * Inserir Funcionarios
     */
    @PostMapping("/save")
    public ResponseEntity<Object> saveFuncionarios(@RequestBody @Valid FuncionarioDTO funcionarioDTO) {

        if(iniflexService.existsByName(funcionarioDTO.getNome())){
            log.info("Funcionário" + funcionarioDTO.getNome() + "Já existe no banco");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Funcionário já existente nos registros");
        }

        log.info("Salvando os dados na tabela TB_FUNCIONARIO");
        var funcionarioModel = new Funcionario();
        BeanUtils.copyProperties(funcionarioDTO, funcionarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(iniflexService.save(funcionarioModel));
    }

    /*
     * Excluir funcionarios
     */
    @DeleteMapping("/{nome}")
    public ResponseEntity<Object> deleteFuncionarios(@PathVariable(value = "nome") String nome) {
    	log.info("Deleta informação com base no nome!");
        Optional<Funcionario> funcionarioModelOptional = iniflexService.findByNome(nome);
        if (!funcionarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não encontrado!");
        }
        iniflexService.delete(funcionarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Funcionário deletado com sucesso");
    }

    /*
     * Body de requisições
     */
    @GetMapping("/body")
    public ResponseEntity<Object> getBody() {
    	log.info("Body de dados de envio!");

        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioDTO);
    }

    /*
     * Soma de salarios
     */
    @GetMapping("/soma")
    public ResponseEntity<Object> getSomaSalario() {
    	log.info("Soma de todos os salários registrados no banco de dados");
        return ResponseEntity.status(HttpStatus.OK).body(iniflexService.somaSalarios());
    }

    /*
     * Busca de Funcionarios por função
     */
    @GetMapping("/porFuncao")
    public ResponseEntity<Object> obterFuncionariosPorFuncao() {
        return ResponseEntity.status(HttpStatus.OK).body(iniflexService.obterTodosFuncionariosFuncao());//iniflexService.obterTodosOsFuncionarios();
    }

    /*
     * Busca de Funcionarios por ordem alfabetica
     */
    @GetMapping("/ordem-alfabetica")
    public ResponseEntity<Object> funcionariosPorOrdemAlfabetica(){
    	log.info("Informa os funcionarios em ordem alfabética");
        return ResponseEntity.status(HttpStatus.OK).body(iniflexService.obterFuncionarioAlfabetica());
    }

    /*
     * Aumento do salário de todos os Funcionarios
     * @param porcentagem
     */
    @GetMapping("/{porcentagem}")
    public ResponseEntity<Object> aumentoTodosSalarios(@PathVariable (value = "porcentagem") Long porcentagem){
        return ResponseEntity.status(HttpStatus.OK).body(iniflexService.aumentoSalario(porcentagem));

    }

    /*
     * Quantidade de salário minimo que cada funcionario recebe
     */
    @GetMapping("/qtSalarioMinimo")
    public ResponseEntity<Object> quantificaQtSalarioMinimo(){
        return ResponseEntity.status(HttpStatus.OK).body(iniflexService.qtSalario());
    }

    /*
     * Buca do funcionario mais velho
     */
    @GetMapping("/funcionario-mais-velho")
    public ResponseEntity<Object> funcionarioMaisVelho(){
        return ResponseEntity.status(HttpStatus.OK).body(iniflexService.maiorIdade());
    }

    /*
     * Busca do funcionario que fazem aniversario em outubro e dezembro
     */
    @GetMapping("/outubro-dezembro")
    public ResponseEntity<Object> getOutubroDezembro(){
        return ResponseEntity.status(HttpStatus.OK).body(iniflexService.outubroDezembro());
    }

}
