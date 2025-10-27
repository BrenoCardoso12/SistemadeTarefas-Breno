package tarefa.model;

import java.time.LocalDate;
import java.util.Objects;

public class Tarefa {
    private static int contadorId = 1;

    private int id;
    private String titulo;
    private String descricao;
    private String responsavel;
    private Prioridade prioridade;
    private Status status;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;

    public Tarefa(String titulo, String descricao, String responsavel, Prioridade prioridade) {
        setTitulo(titulo);
        setResponsavel(responsavel);
        this.descricao = descricao;
        this.prioridade = prioridade != null ? prioridade : Prioridade.MEDIA;
        this.status = Status.PENDENTE;
        this.dataCriacao = LocalDate.now();
        this.id = contadorId++;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getResponsavel() { return responsavel; }
    public Prioridade getPrioridade() { return prioridade; }
    public Status getStatus() { return status; }
    public LocalDate getDataCriacao() { return dataCriacao; }
    public LocalDate getDataConclusao() { return dataConclusao; }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().length() < 3)
            throw new IllegalArgumentException("Título inválido!");
        this.titulo = titulo.trim();
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setResponsavel(String responsavel) {
        if (responsavel == null || responsavel.trim().isEmpty())
            throw new IllegalArgumentException("Responsável é obrigatório!");
        this.responsavel = responsavel.trim();
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public void setStatus(Status novoStatus) {
        if (novoStatus == Status.CONCLUIDA && this.status != Status.EM_ANDAMENTO) {
            throw new IllegalStateException("A tarefa só pode ser concluída se estiver EM_ANDAMENTO.");
        }
        this.status = novoStatus;
        if (novoStatus == Status.CONCLUIDA) {
            this.dataConclusao = LocalDate.now();
        }
    }

    @Override
    public String toString() {
        return String.format(
                "%-3d %-20s %-15s %-10s %-15s %-12s %-12s",
                id, titulo, responsavel, prioridade, status,
                dataCriacao, dataConclusao != null ? dataConclusao : "-"
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarefa)) return false;
        Tarefa t = (Tarefa) o;
        return titulo.equalsIgnoreCase(t.titulo) && responsavel.equalsIgnoreCase(t.responsavel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo.toLowerCase(), responsavel.toLowerCase());
    }
}
