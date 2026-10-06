/**
 * Aluno de graduação.
 *
 * Tempo de curso: contado em SEMESTRES, apresentado como ano.periodo
 * (ex.: 2026.1), com prazo máximo de 14 semestres.
 * 
 * Nota final: soma das 3 notas de unidade dividida por 3.
 * 
 * Situação:
 * - média >= 6,0 aprovado;
 * - 4,0 <= média < 6,0 recuperação;
 * - média < 4,0 reprovado direto.
 *
 */
public class AlunoGraduacao extends Aluno {

    public static final int TOTAL_UNIDADES = 3;
    public static final int PRAZO_MAXIMO_SEMESTRES = 14;
    public static final double MEDIA_APROVACAO = 6.0;
    public static final double MEDIA_REPROVACAO = 4.0;

    // Esta classe NÃO guarda ano, período nem semestres cursados: os três são
    // calculados a partir do início do curso, que fica na superclasse.
    public AlunoGraduacao(String matricula, String nome) {
        super(matricula, nome);
    }

    @Override
    public void lancarNota(String valor) {
        if (getNotas().size() >= TOTAL_UNIDADES) {
            System.out.println(
                    "[aviso] O curso de graduação tem apenas " + TOTAL_UNIDADES + " unidades. Nota ignorada: " + valor);
            return;
        }
        if (!ehNotaNumericaValida(valor)) {
            System.out.println("[aviso] Nota invalida (use um numero de 0 a 10). Nota ignorada: " + valor);
            return;
        }

        super.lancarNota(valor);
    }

    // Auxiliar private, como em AlunoTecnico: para o cliente, quem responde é
    // getDesempenho(). Ninguém de fora precisa (nem deve) chamar getMedia().
    private double getMedia() {
        double soma = 0.0;

        for (String nota : getNotas()) {
            soma += converterParaNumero(nota);
        }

        return soma / TOTAL_UNIDADES;
    }

    @Override
    public String getSituacao() {
        if (getMedia() >= MEDIA_APROVACAO) {
            return APROVADO;
        }
        if (getMedia() >= MEDIA_REPROVACAO) {
            return RECUPERACAO;
        }
        return REPROVADO;
    }

    @Override
    public String getDesempenho() {
        return String.format("Media: %.2f", getMedia());
    }

    @Override
    public String getPeriodoAtual() {
        // calculado a partir do início do curso.

        int periodoEntrada;
        if (getMesInicio() <= 6) {
            periodoEntrada = 1;
        } else {
            periodoEntrada = 2;
        }

        int semestresAndados = getTempoDecorrido() - 1;

        int total = (periodoEntrada - 1) + semestresAndados;

        int ano = getAnoInicio() + total / 2;
        int periodo = total % 2 + 1;

        return ano + "." + periodo;
    }

    @Override
    public int getTempoDecorrido() {
        return getMesesDecorridos() / 6 + 1;
    }

    @Override
    public int getPrazoMaximo() {
        return PRAZO_MAXIMO_SEMESTRES;
    }

    @Override
    public String getUnidadeDePrazo() {
        return "semestre(s)";
    }
}
