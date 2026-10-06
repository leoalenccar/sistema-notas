/**
 * Fábrica de alunos.
 *
 * O cliente informa apenas o TIPO do aluno. Em um sistema real, este TIPO
 * poderia vir de um formulário ou do banco de dados por exemplo.
 * Então o cliente recebe de volta um Aluno já instanciado. Este é
 * o ÚNICO lugar do sistema que decide qual subclasse instanciar: em todos os
 * outros lugares o programa trabalha com o tipo Aluno e não pergunta mais
 * nada.
 * 
 */
public class AlunoFactory {

    public static Aluno criar(String tipo, String matricula, String nome) {
        if (tipo == null) {
            System.out.println("[aviso] Tipo de aluno nao conhecido: " + tipo + ". Tipos validos: " + getTiposDisponiveis());
            return null;
        }

        String chave = tipo.trim().toUpperCase();

        if (chave.equals("TECNICO")) {
            return new AlunoTecnico(matricula, nome);
        }

        if (chave.equals("GRADUACAO")) {
            return new AlunoGraduacao(matricula, nome);
        }

        if (chave.equals("POS")) {
            return new AlunoPosGraduacao(matricula, nome);
        }

        if (chave.equals("INTERCAMBIO")) {
            return new AlunoIntercambio(matricula, nome);
        }

        System.out.println("[aviso] Tipo de aluno nao conhecido: " + tipo + ". Tipos validos: " + getTiposDisponiveis());

        return null;
    }

    public static String getTiposDisponiveis() {
        return "TECNICO, GRADUACAO, POS, INTERCAMBIO";
    }
}