import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cliente das classes de aluno: o programa que lança notas e imprime o
 * relatório da turma.
 *
 * Ponto central da prática: esta classe NÃO deve saber se está
 * lidando com um AlunoTecnico, um AlunoGraduacao ou um AlunoPosGraduacao.
 * Ela conversa apenas com o tipo Aluno.
 *
 */
public class SistemaNotasView {

    public static void main(String[] args) {
        ArrayList<Aluno> turma = montarTurma();

        imprimirRelatorio(turma);
        imprimirResumoPorSituacao(turma);
        imprimirAlertasDePrazo(turma);
        demonstrarTipoEstaticoEDinamico();
        demonstrarCasosDeBorda();
    }

    private static ArrayList<Aluno> montarTurma() {
        ArrayList<Aluno> turma = new ArrayList<Aluno>();

        // Dois alunos em pontos diferentes do curso - os dois criados pela
        // fábrica, porque esta classe nunca instancia uma subclasse.
        // A única informação de tempo que o cliente informa é o INÍCIO DO
        // CURSO; quem transforma isso em ano, semestre ou mês é cada aluno.
        Aluno einstein = AlunoFactory.criar("TECNICO", "2026001", "Albert Einstein");
        turma.add(einstein); // calouro: a fábrica já o cria começando agora

        Aluno noether = AlunoFactory.criar("TECNICO", "2021005", "Emmy Noether");
        noether.setInicioDoCurso("03/2021"); // 6o ano: prazo estourado
        turma.add(noether);

        Aluno payne = AlunoFactory.criar("GRADUACAO", "2026003", "Cecilia Payne");
        turma.add(payne);

        Aluno mendeleev = AlunoFactory.criar("POS", "2026004", "Dmitri Mendeleev");
        turma.add(mendeleev)

        Aluno bohr = AlunoFactory.criar("GRADUACAO", "2020006", "Niels Bohr");
        bohr.setInicioDoCurso("03/2020");
        turma.add(bohr);

        Aluno lovelace = AlunoFactory.criar("POS", "2020007", "Ada Lovelace");
        lovelace.setInicioDoCurso("03/2020");
        turma.add(lovelace);

        Aluno eduarda = AlunoFactory.criar("INTERCAMBIO", "2026027", "EduardaFarias");
        eduarda.setInicioDoCurso("09/2025"); // 6 meses de curso: dentro do prazo de 12
        turma.add(eduarda);

        // Lançamento de notas: a MESMA chamada serve para qualquer tipo de
        // aluno. Cada objeto interpreta o valor recebido a sua maneira.
        lancarNotas(turma.get(0), "8.0", "7.5", "6.0", "9.0");
        lancarNotas(turma.get(1), "4.0", "3.0", "5.0", "2.0");

        lancarNotas(turma.get(2), "7.8", "6.2", "9.1");
        lancarNotas(turma.get(3), "B");

        lancarNotas(bohr, "5.0", "4.5", "5.5");
        lancarNotas(lovelace, "C");

        lancarNotas(eduarda, "8.0", "7.0");

        return turma;
    }

    /** Lança várias notas de um aluno qualquer, sem saber o tipo dele. */
    private static void lancarNotas(Aluno aluno, String... valores) {
        for (int i = 0; i < valores.length; i++) {
            aluno.lancarNota(valores[i]);
        }
    }

    private static void imprimirRelatorio(ArrayList<Aluno> turma) {
        System.out.println("===== RELATORIO DA TURMA =====");
        System.out.printf(
                "%-10s %-22s %-10s %-22s %s%n",
                "MATRICULA", "NOME", "PERIODO", "DESEMPENHO", "SITUACAO");
        for (Aluno aluno : turma) {
            aluno.imprimirInformacoes();
        }
        System.out.println();
    }

    private static void imprimirResumoPorSituacao(ArrayList<Aluno> turma) {
        System.out.println("===== RESUMO POR SITUACAO =====");
        String[] situacoes = { Aluno.APROVADO, Aluno.RECUPERACAO, Aluno.REPROVADO, Aluno.NAO_AVALIADO };
        HashMap<String, Integer> situacoesAlunos = new HashMap<>();

        for (String s : situacoes) {
            situacoesAlunos.put(s, 0);
        }

        for (Aluno aluno : turma) {
            String s = aluno.getSituacao();
            situacoesAlunos.put(s, situacoesAlunos.get(s) + 1);
        }

        for (String s : situacoes) {
            System.out.println(s + ": " + situacoesAlunos.get(s));
        }

        System.out.println();
    }

    private static void imprimirAlertasDePrazo(ArrayList<Aluno> turma) {
        System.out.println("===== PRAZO DE INTEGRALIZACAO =====");
        for (Aluno aluno : turma) {
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Prazo: " + aluno.getPrazo());
            System.out.println("O aluno se encontra dentro do prazo? R: " + aluno.estaNoPrazo());
            System.out.println("Tempo restante do curso: " + aluno.getTempoRestante());
            System.out.println("Início do curso: " + aluno.getInicioDoCurso());
        }
        System.out.println();
    }

    private static void demonstrarTipoEstaticoEDinamico() {
        System.out.println("===== TIPO ESTATICO x TIPO DINAMICO =====");
        Aluno a = AlunoFactory.criar("TECNICO", "2025008", "Heinrich Hertz");
        a.setInicioDoCurso("03/2025");
        a.lancarNota("10.0");
        System.out.println(a.getClass().getSimpleName() + " -> " + a.getSituacao());

        a = AlunoFactory.criar("POS", "2026009", "Isaac Newton");
        a.setInicioDoCurso("01/2026");
        a.lancarNota("A");
        System.out.println(a.getClass().getSimpleName() + " -> " + a.getSituacao());

        a = new Aluno("2026010", "Caroline Herschel");
        System.out.println(a.getClass().getSimpleName() + " -> " + a.getSituacao());

        a.getClass().getSimpleName();

        System.out.println();
    }

    private static void demonstrarCasosDeBorda() {
        System.out.println("===== CASOS DE BORDA =====");
        System.out.println("Tipos aceitos pela fabrica: " + AlunoFactory.getTiposDisponiveis());

        Aluno desconhecido = AlunoFactory.criar("MESTRADO", "2026011", "Katherine Johnson");
        if (desconhecido == null) {
            System.out.println("Nenhum aluno criado para o tipo MESTRADO (criar devolveu null).");
        } else {
            System.out.println("Aluno criado para o tipo MESTRADO: " + desconhecido);
        }

        Aluno concInvalido = AlunoFactory.criar("POS", "2026012", "Benoît Mandelbrot");
        System.out.println("Lançando um conceito inválido na pós-graduação: ");
        concInvalido.lancarNota("E");
        System.out.println("Conceito lancado: " + concInvalido.getNotas());

        Aluno notaInvalidaETextoInvalido = AlunoFactory.criar("GRADUACAO", "2026013", "Marie Curie");
        System.out.println("Colocando uma nota fora do intervalo válido e inserindo um texto que não é número: ");
        System.out.println("Nota fora do intervalo:");
        notaInvalidaETextoInvalido.lancarNota("11.7");
        System.out.println("Texto que nao e numero:");
        notaInvalidaETextoInvalido.lancarNota("J");
        System.out.println("Notas guardadas: " + notaInvalidaETextoInvalido.getNotas());

        Aluno excessoNotas = AlunoFactory.criar("GRADUACAO", "2026014", "Nicolau Copérnico");
        System.out.println("Colocando notas acima da quantidade válida de notas: ");
        String[] notas = { "10", "8.9", "9", "7.3" };
        for (String n : notas) {
            excessoNotas.lancarNota(n);
        }
        System.out.println("Notas guardadas: " + excessoNotas.getNotas());


        Aluno semTipo = AlunoFactory.criar(null, "2026019", "Leonardo Alencar");
        if (semTipo == null) {
            System.out.println("Nenhum aluno criado para tipo null.");
        }

        Aluno notaNegativa = AlunoFactory.criar("GRADUACAO", "2026018", "Arthur Victor");
        System.out.println("Lancando nota negativa:");
        notaNegativa.lancarNota("-1");
        System.out.println("Notas guardadas: " + notaNegativa.getNotas());

        Aluno doisConceitos = AlunoFactory.criar("POS", "2026017", "Mateus Alves");
        System.out.println("Lancando dois conceitos:");
        doisConceitos.lancarNota("A");
        doisConceitos.lancarNota("D");
        System.out.println("Notas guardadas: " + doisConceitos.getNotas());

        System.out.println();
    }
}
