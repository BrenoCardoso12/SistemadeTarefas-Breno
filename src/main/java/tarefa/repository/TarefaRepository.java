package tarefa.repository;

import tarefa.model.Tarefa;
import tarefa.model.Prioridade;
import tarefa.model.Status;
import java.util.*;

public interface TarefaRepository {
    Tarefa salvar(Tarefa tarefa);
    List<Tarefa> listarTodas();
    Optional<Tarefa> buscarPorId(int id);
    boolean atualizar(Tarefa tarefa);
    boolean deletar(int id);
    List<Tarefa> listarPorPrioridade(Prioridade prioridade);
    List<Tarefa> listarPorStatus(Status status);
    boolean existePorTituloEResponsavel(String titulo, String responsavel);
}
