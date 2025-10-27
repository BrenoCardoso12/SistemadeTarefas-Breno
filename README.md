# 🗂️ Sistema de Gestão de Tarefas Colaborativas

Projeto Java 17 em arquitetura em camadas (Model → Repository → Service → Controller).

## ⚙️ Funcionalidades
- Criar tarefa
- Listar todas
- Buscar por ID
- Atualizar tarefa
- Alterar status (com validações)
- Remover tarefa
- Listar por prioridade ou status

## 🏗️ Como rodar
1. Abra o projeto no IntelliJ (Community).
2. Vá até `src/main/java/tarefa/TarefasApp.java` e rode a aplicação.
3. Use o menu interativo no console.

## 📦 Estrutura
```
src/main/java/tarefa/
  model/ (Prioridade, Status, Tarefa)
  repository/ (TarefaRepository, TarefaRepositoryMemoria)
  service/ (TarefaService)
  controller/ (TarefaController)
  TarefasApp.java
```

## 📤 Como subir no GitHub
```bash
git init
git add .
git commit -m "Versão inicial - Sistema de Tarefas"
git branch -M main
git remote add origin https://github.com/SEU_USUARIO/SistemaTarefas_BrenoCardoso.git
git push -u origin main
```

### 🌿 Fluxo com branch `dev`
```bash
git checkout -b dev
git push -u origin dev
# Depois, no GitHub: "Compare & Pull Request"
```
