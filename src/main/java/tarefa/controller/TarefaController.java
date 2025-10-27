package tarefa.controller;

import tarefa.model.*;
import tarefa.service.TarefaService;
import java.util.*;

public class TarefaController {
    private final TarefaService service;
    private final Scanner scanner = new Scanner(System.in);

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE GESTÃO DE TAREFAS ===");
            System.out.println("1. Criar Tarefa");
            System.out.println("2. Listar Todas");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Atualizar Tarefa");
            System.out.println("5. Alterar Status");
            System.out.println("6. Remover Tarefa");
            System.out.println("7. Listar por Prioridade");
            System.out.println("8. Listar por Status");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1 -> criarTarefa();
                    case 2 -> listarTodas();
                    case 3 -> buscarPorId();
                    case 4 -> atualizarTarefa();
                    case 5 -> alterarStatus();
                    case 6 -> removerTarefa();
                    case 7 -> listarPorPrioridade();
                    case 8 -> listarPorStatus();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void criarTarefa() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String desc = scanner.nextLine();
        System.out.print("Responsável: ");
        String resp = scanner.nextLine();
        System.out.print("Prioridade (BAIXA/MEDIA/ALTA): ");
        Prioridade prioridade = Prioridade.valueOf(scanner.nextLine().toUpperCase());

        service.criarTarefa(titulo, desc, resp, prioridade);
        System.out.println("Tarefa criada com sucesso!");
    }

    private void listarTodas() {
        System.out.printf("%-3s %-20s %-15s %-10s %-15s %-12s %-12s%n",
                "ID", "Título", "Responsável", "Prio", "Status", "Criação", "Conclusão");
        service.listarTodas().forEach(System.out::println);
    }

    private void buscarPorId() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        service.buscarPorId(id)
                .ifPresentOrElse(System.out::println, () -> System.out.println("Tarefa não encontrada."));
    }

    private void atualizarTarefa() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        var tarefa = service.buscarPorId(id).orElseThrow(() -> new RuntimeException("Não encontrada."));
        System.out.print("Novo título: ");
        String t = scanner.nextLine();
        System.out.print("Nova descrição: ");
        String d = scanner.nextLine();
        System.out.print("Novo responsável: ");
        String r = scanner.nextLine();
        System.out.print("Nova prioridade (BAIXA/MEDIA/ALTA): ");
        Prioridade p = Prioridade.valueOf(scanner.nextLine().toUpperCase());

        service.atualizarTarefa(tarefa, t, d, r, p);
        System.out.println("Atualizada com sucesso!");
    }

    private void alterarStatus() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Novo status (PENDENTE/EM_ANDAMENTO/CONCLUIDA): ");
        Status s = Status.valueOf(scanner.nextLine().toUpperCase());

        if (service.alterarStatus(id, s))
            System.out.println("Status alterado!");
        else System.out.println("Falha na alteração.");
    }

    private void removerTarefa() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        if (service.removerTarefa(id))
            System.out.println("Removida com sucesso!");
        else System.out.println("Tarefa não encontrada.");
    }

    private void listarPorPrioridade() {
        System.out.print("Prioridade (BAIXA/MEDIA/ALTA): ");
        Prioridade p = Prioridade.valueOf(scanner.nextLine().toUpperCase());
        service.listarPorPrioridade(p).forEach(System.out::println);
    }

    private void listarPorStatus() {
        System.out.print("Status (PENDENTE/EM_ANDAMENTO/CONCLUIDA): ");
        Status s = Status.valueOf(scanner.nextLine().toUpperCase());
        service.listarPorStatus(s).forEach(System.out::println);
    }
}
