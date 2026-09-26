/**
 * Aluno de pós-graduação (Especialização/Mestrado/Doutorado).
 *
 * Tempo de curso: contado em MESES, no máximo 24 meses para conclusão.
 * Nota final: conceito A, B, C ou D (não há média numérica).
 * Situação: A ou B aprovado; C recuperação; D reprovado direto.
 *
 * ---------------------------------------------------------------------
 * ITEM 3 DA PRÁTICA: complete esta classe.
 *
 * Repare que aqui NÃO existe média nenhuma: o estado e a regra são
 * diferentes dos outros tipos de aluno, e isso é normal. O que precisa
 * continuar igual é o conjunto de métodos herdado de Aluno.
 * ---------------------------------------------------------------------
 */
public class AlunoPosGraduacao extends Aluno {

    public static final int PRAZO_MAXIMO_MESES = 24;

    // Como na graduação, o mês de curso não é guardado: ele é calculado a
    // partir do início do curso, que fica na superclasse.
    public AlunoPosGraduacao(String matricula, String nome) {
        super(matricula, nome);
    }

    @Override
    public void lancarNota(String valor) {
        // TODO 3.1: aceite apenas "A", "B", "C" ou "D" (ignorando espaços e
        // maiúsculas/minúsculas) e recuse um segundo lançamento de conceito,
        // sempre imprimindo um aviso antes de sair do método.
        // Quando o valor for válido, chame super.lancarNota(...) para guardá-lo.
        if (getConceito() == "-") {
            if (valor == "A" || valor == "B" || valor == "C" || valor == "D") {
                super.lancarNota(valor);
            }
        } else {
            System.err.println("[aviso] O aluno já possui conceito resgistrado.");
        }
    }

    /**
     * O conceito lançado, ou "-" enquanto nada foi lançado. Auxiliar private:
     * para o cliente, quem responde é getDesempenho().
     */
    private String getConceito() {
        // TODO 3.2: devolva o primeiro item de getNotas(), ou "-" se a lista
        // ainda estiver vazia.
        if (!getNotas().isEmpty()) {
            return getNotas().get(0);
        } else {
            return "-";
        }
    }

    @Override
    public String getSituacao() {
        // TODO 3.3: A ou B aprovado, C recuperação, D reprovado.
        // Decida também o que responder quando o conceito ainda não foi lançado
        // e explique a decisão em um comentário curto.
        if (getConceito() == "A" || getConceito() == "B") {
            return APROVADO;
        } else if (getConceito() == "C") {
            return RECUPERACAO;
        } else if (getConceito() == "D") {
            return REPROVADO;
        } else {
            System.out.println("[aviso] O conceito ainda não foi contabilizado. Aguarde.");
            return NAO_AVALIADO;
        }
    }

    @Override
    public String getDesempenho() {
        // TODO 3.4: devolva algo como "Conceito: B".
        return ("Conceito: " + getConceito());
    }

    @Override
    public String getPeriodoAtual() {
        // TODO 3.5: devolva algo como "8o mês".
        return (MES_ATUAL + "o mês");
    }

    @Override
    public int getTempoDecorrido() {
        // TODO 3.6: em qual mês de curso o aluno está? Aqui a conta é a mais
        // direta das três: getMesesDecorridos() (herdado de Aluno) já está em
        // meses, e quem acabou de entrar está no 1o mês.
        System.out.println(getMesesDecorridos() + " meses decorridos");
        return 0;
    }

    @Override
    public int getPrazoMaximo() {
        // TODO item 3.7
        System.out.println("Prazo máximo: " + PRAZO_MAXIMO_MESES);
        return PRAZO_MAXIMO_MESES;
    }

    @Override
    public String getUnidadeDePrazo() {
        // TODO item 3.8
        return "mês/meses";
    }
}
