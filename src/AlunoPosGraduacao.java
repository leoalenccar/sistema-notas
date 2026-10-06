/**
 * Aluno de pós-graduação (Especialização/Mestrado/Doutorado).
 *
 * Tempo de curso: contado em MESES, no máximo 24 meses para conclusão.
 * Nota final: conceito A, B, C ou D (não há média numérica).
 * Situação: A ou B aprovado; C recuperação; D reprovado direto.
 *
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
        String conceito = "";
        if (valor != null) {
            conceito = valor.trim().toUpperCase();
        }

        if (getConceito().equals("-")) {
            if (conceito.equals("A") || conceito.equals("B") || conceito.equals("C") || conceito.equals("D")) {
                super.lancarNota(conceito);
            } else {
                System.err.println("[aviso] Nota inválida. Use os conceitos de A a D. Conceito inserido: " + valor);
            }
        } else {
            System.err.println("[aviso] O aluno já possui conceito registrado.");
        }
    }

    private String getConceito() {
        if (!getNotas().isEmpty()) {
            return getNotas().get(0);
        } else {
            return "-";
        }
    }

    @Override
    public String getSituacao() {
        if (getConceito().equals("A") || getConceito().equals("B")) {
            return APROVADO;
        } else if (getConceito().equals("C")) {
            return RECUPERACAO;
        } else if (getConceito().equals("D")) {
            return REPROVADO;
        } else {
            return NAO_AVALIADO;
        }
    }

    @Override
    public String getDesempenho() {
        return "Conceito: " + getConceito();
    }

    @Override
    public String getPeriodoAtual() {
        return getTempoDecorrido() + "o mês";
    }

    @Override
    public int getTempoDecorrido() {
        return getMesesDecorridos() + 1;
    }

    @Override
    public int getPrazoMaximo() {
        return PRAZO_MAXIMO_MESES;
    }

    @Override
    public String getUnidadeDePrazo() {
        return "mês/meses";
    }
}