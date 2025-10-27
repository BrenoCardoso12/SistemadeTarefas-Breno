package tarefa;

import tarefa.controller.TarefaController;
import tarefa.repository.TarefaRepositoryMemoria;
import tarefa.service.TarefaService;

public class TarefasApp {
    public static void main(String[] args) {
        var repo = new TarefaRepositoryMemoria();
        var service = new TarefaService(repo);
        var controller = new TarefaController(service);
        controller.executar();
    }
}
