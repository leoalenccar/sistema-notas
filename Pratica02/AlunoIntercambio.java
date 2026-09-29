/**
 * Aluno de intercâmbio.
 *
 * Tempo de curso: contado em meses, com prazo máximo de 12 meses.
 * Nota final: soma das 2 notas de avaliações dividida por 2.
 * Situação: média >= 7,0 aprovado; caso contrario, reprovado.
 *
 */
public class AlunoIntercambio extends Aluno {

    public static final int PRAZO_MAXIMO_MESES = 12;
    public static final int TOTAL_AVALIACOES = 2;
    public static final double MEDIA_APROVACAO = 7.0;

    public AlunoIntercambio(String matricula, String nome) {
        super(matricula, nome);
    }

    @Override
    public void lancarNota(String valor) {
        if (getNotas().size() >= TOTAL_AVALIACOES) {
            System.out.println(
                    "[aviso] Intercâmbio tem apenas " + TOTAL_AVALIACOES + " avaliacoes. Nota ignorada: " + valor);
            return;
        }
        if (!ehNotaNumericaValida(valor)) {
            System.out.println("[aviso] Nota invalida (use um numero de 0 a 10). Nota ignorada: " + valor);
            return;
        }

        super.lancarNota(valor);
    }

    private double getMedia() {
        double soma = 0.0;
        for (String nota : getNotas()) {
            soma += converterParaNumero(nota);
        }
        // Divide sempre por 4: bimestre sem nota lançada conta como zero.
        return soma / TOTAL_AVALIACOES;
    }

    @Override
    public String getSituacao() {
        if (getMedia() >= MEDIA_APROVACAO) {
            return APROVADO;
        }
        return REPROVADO;
    }

    @Override
    public String getDesempenho() {
        return String.format("Media: %.2f", getMedia());
    }

    @Override
    public String getPeriodoAtual() {
        // TODO 3.5: devolva algo como "8o mês".
        return getMesesDecorridos() - getMesInicio() + "o mês";
    }

    @Override
    public int getTempoDecorrido() {
        return getMesesDecorridos();
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