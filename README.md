# Prática 02 — Herança e Polimorfismo

Sistema de lançamento de notas de alunos. A superclasse `Aluno` concentra o que
todo aluno tem em comum; cada subclasse mede o tempo de curso e calcula a nota
final de um jeito diferente.

## Como compilar e executar

```bash
# a partir da pasta do projeto
javac -d out *.java
java -cp out SistemaNotasView
```

O projeto usa apenas recursos disponíveis desde o **JDK 8** e foi testado até o
**JDK 26**. Verifique a sua versão com `java -version`.

## Arquivos

| Arquivo | Situação |
|---|---|
| `Aluno.java` |Superclasse: estado comum (inclusive o início do curso), constantes de situação, os métodos que as subclasses sobrescrevem e os métodos que valem para todos. 
| `AlunoTecnico.java` |Ano letivo, 4 bimestres, prazo de 5 anos.|
| `AlunoGraduacao.java` |Semestre, 3 unidades, prazo de 14 semestres.|
| `AlunoPosGraduacao.java` |Meses, conceito, prazo de 24 meses.|
| `AlunoIntercambio.java` |Meses, 2 avaliações, prazo de 12 meses.|
| `AlunoFactory.java` |Fábrica de alunos.|
| `SistemaNotasView.java` |lança notas e imprime o relatório da turma|

## Regras de negócio

| Tipo | Tempo de curso | Prazo máximo | Nota final | Situação |
|---|---|---|---|---|
| Técnico | ano (1º, 2º, …) | 5 anos | soma de 4 notas bimestrais / 4 | ≥ 6 aprovado; < 6 recuperação |
| Graduação | semestre (2026.1) | 14 semestres | soma de 3 notas de unidade / 3 | ≥ 6 aprovado; 4 ≤ média < 6 recuperação; < 4 reprovado |
| Pós-graduação | mês | 24 meses | conceito A, B, C ou D | A/B aprovado; C recuperação; D reprovado |
| Intercâmbio | mês | 12 meses | soma de 2 notas de avaliações / 2 | média ≥ 7 aprovado|

## DADOS

**Nomes: Arthur Victor Vieira Almeida, Leonardo Alencar de Aquino**

**Versão do JDK utilizada:** (21.0.7)