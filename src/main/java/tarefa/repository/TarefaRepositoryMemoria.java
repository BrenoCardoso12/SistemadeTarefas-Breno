package tarefa.repository;

import tarefa.model.Tarefa;
import tarefa.model.Prioridade;
import tarefa.model.Status;
import java.util.*;
import java.util.stream.Collectors;

public class TarefaRepositoryMemoria implements TarefaRepository {
    private final Map<Integer, Tarefa> tarefas = new HashMap<>();

    @Override
    public Tarefa salvar(Tarefa tarefa) {
        tarefas.put(tarefa.getId(), tarefa);
        return tarefa;
    }

    @Override
    public List<Tarefa> listarTodas() {
        return new ArrayList<>(tarefas.values());
    }

    @Override
    public Optional<Tarefa> buscarPorId(int id) {
        return Optional.ofNullable(tarefas.get(id));
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        if (!tarefas.containsKey(tarefa.getId())) return false;
        tarefas.put(tarefa.getId(), tarefa);
        return true;
    }

    @Override
    public boolean deletar(int id) {
        return tarefas.remove(id) != null;
    }

    @Override
    public List<Tarefa> listarPorPrioridade(Prioridade prioridade) {
        return tarefas.values().stream()
                .filter(t -> t.getPrioridade() == prioridade)
                .collect(Collectors.toList());
    }

    @Override
    public List<Tarefa> listarPorStatus(Status status) {
        return tarefas.values().stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorTituloEResponsavel(String titulo, String responsavel) {
        return tarefas.values().stream()
                .anyMatch(t -> t.getTitulo().equalsIgnoreCase(titulo)
                        && t.getResponsavel().equalsIgnoreCase(responsavel));
    }
}
