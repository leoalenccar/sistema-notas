/**
 * Aluno de curso técnico.
 *
 * Tempo de curso: contado em ANOS (1o ano, 2o ano, ...), no máximo 5 anos.
 * Nota final: soma das 4 notas bimestrais dividida por 4.
 * Situação: média >= 6,0 aprovado; caso contrario, recuperação.
 *
 */
public class AlunoTecnico extends Aluno {

    public static final int TOTAL_BIMESTRES = 4;
    public static final int PRAZO_MAXIMO_ANOS = 5;
    public static final double MEDIA_APROVACAO = 6.0;

    public AlunoTecnico(String matricula, String nome) {
        super(matricula, nome);
    }

    @Override
    public void lancarNota(String valor) {
        if (getNotas().size() >= TOTAL_BIMESTRES) {
            System.out.println(
                    "[aviso] O curso tecnico tem apenas " + TOTAL_BIMESTRES
                            + " bimestres. Nota ignorada: " + valor);
            return;
        }
        if (!ehNotaNumericaValida(valor)) {
            System.out.println(
                    "[aviso] Nota invalida (use um numero de 0 a 10). Nota ignorada: " + valor);
            return;
        }
        super.lancarNota(valor);
    }

    private double getMedia() {
        double soma = 0.0;
        for (String nota : getNotas()) {
            soma += converterParaNumero(nota);
        }
        return soma / TOTAL_BIMESTRES;
    }

    @Override
    public String getSituacao() {
        if (getMedia() >= MEDIA_APROVACAO) {
            return APROVADO;
        }
        return RECUPERACAO;
    }

    @Override
    public String getDesempenho() {
        return String.format("Media: %.2f", getMedia());
    }

    @Override
    public String getPeriodoAtual() {
        return getTempoDecorrido() + "o ano";
    }

    @Override
    public int getTempoDecorrido() {
        return getMesesDecorridos() / 12 + 1;
    }

    @Override
    public int getPrazoMaximo() {
        return PRAZO_MAXIMO_ANOS;
    }

    @Override
    public String getUnidadeDePrazo() {
        return "ano(s)";
    }
}
