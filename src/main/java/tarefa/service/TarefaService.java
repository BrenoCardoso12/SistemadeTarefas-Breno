package tarefa.service;

import tarefa.model.*;
import tarefa.repository.TarefaRepository;
import java.util.*;

public class TarefaService {
    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public Tarefa criarTarefa(String titulo, String descricao, String responsavel, Prioridade prioridade) {
        if (repository.existePorTituloEResponsavel(titulo, responsavel))
            throw new IllegalArgumentException("Já existe uma tarefa com este título para este responsável!");
        Tarefa tarefa = new Tarefa(titulo, descricao, responsavel, prioridade);
        return repository.salvar(tarefa);
    }

    public List<Tarefa> listarTodas() { return repository.listarTodas(); }

    public Optional<Tarefa> buscarPorId(int id) { return repository.buscarPorId(id); }

    public boolean atualizarTarefa(Tarefa tarefa, String novoTitulo, String novaDesc, String novoResp, Prioridade novaPrio) {
        tarefa.setTitulo(novoTitulo);
        tarefa.setDescricao(novaDesc);
        tarefa.setResponsavel(novoResp);
        tarefa.setPrioridade(novaPrio);
        return repository.atualizar(tarefa);
    }

    public boolean alterarStatus(int id, Status novoStatus) {
        Optional<Tarefa> opt = repository.buscarPorId(id);
        if (opt.isEmpty()) return false;
        Tarefa t = opt.get();
        t.setStatus(novoStatus);
        return repository.atualizar(t);
    }

    public boolean removerTarefa(int id) { return repository.deletar(id); }

    public List<Tarefa> listarPorPrioridade(Prioridade p) { return repository.listarPorPrioridade(p); }

    public List<Tarefa> listarPorStatus(Status s) { return repository.listarPorStatus(s); }
}
