/**Classe responsável por gerenciar matriculas e notas dos estudantes
 *Estrutura principal é Map, onde o id do estudante é a key
 * o value é a listaMatriculas e notas do estudante
 */

import java.util.*;

public class HistoricoNotas {
    private Map<Integer, List<Matriculas>> historico;

    public HistoricoNotas() {
        this.historico = new HashMap<>();

    }

    public Set<Integer> obterTodosIds() {
        // Retorna o Set de chaves (IDs dos estudantes) do Map.
        return historico.keySet();
    }

    /**Adiciona uma nova matricula ao historico do estudante(disciplina e nota)
     * Verifica se o estudante já existe(adiciona à lista existente)
     * Se não, o estudante é novo e cria uma nova lista.
     */
    void adicionarMatricula(int idEstudante, String codigoDisciplina, double nota) {
        Matriculas novaMatricula = new Matriculas(codigoDisciplina, nota);
        if (historico.containsKey(idEstudante)) {
            List<Matriculas> listaAtual = historico.get(idEstudante);
            listaAtual.add(novaMatricula);
            System.out.println("O aluno já existe, matrícula adicionada.");
        } else {
            ArrayList<Matriculas> semHistorico = new ArrayList<>();
            semHistorico.add(novaMatricula);
            historico.put(idEstudante, semHistorico);
            System.out.println("Aluno sem histórico, matrícula adicionada.");
        }
    }

    //Obter matricula. Retorna a lista de todas as matrículas de um estudante ao buscar o Id
    List<Matriculas> obterMatricula(int idEstudante) {
        return historico.get(idEstudante);
    }

    /**Metodo obter nota. Busca a nota do estudante em uma disciplina especifica.
     *Usa o Optional<Double> para cuidar do caso onde o aluno ou a disciplina não existem.
     */
    Optional<Double> obterNota(int idEstudante, String codigoDisciplina) {
        List<Matriculas> matriculas = obterMatricula(idEstudante);
        if (matriculas == null) {
            return Optional.empty();
        }
        for (Matriculas m : matriculas) {
            if (m.getCodigoDisciplina().equals(codigoDisciplina)) {
                return Optional.of(m.getNota());
            }
        }
        return Optional.empty();
    }

    /**Metodo remover matricula. Remove uma matricula especifica do historico do estudante.
     * Utiliza um removeIf() para buscar e remover o elemento.
     */
    void removerMatricula(int idEstudante, String codigoDisciplina){
    List<Matriculas> matriculas = obterMatricula(idEstudante);
        if (matriculas != null) {
            matriculas.removeIf(m -> m.getCodigoDisciplina().equals(codigoDisciplina));
        }
    }

    /**
     *Metodo para calcular a media do estudante. Calcula a media aritmetica das notas de um estudante.
     * Retorna 0.0 se não tiver matriculas ou o estudante não for encontrado.
     */
    double mediaEstudante(int idEstudante){
        List<Matriculas> matriculas = obterMatricula(idEstudante);
        if (matriculas == null){
            System.out.println("Não há notas. O aluno está com 0.");
            return 0.0;
        }
        else{
            double somaNotas = 0.0;
            for(Matriculas m: matriculas){
                somaNotas += m.getNota();
            }
                return somaNotas / matriculas.size();
        }
        }
        double mediaDisciplina(String codigoDisciplina){
        double somaNotas = 0.0;
        int contador = 0;

        for (List<Matriculas> matriculasAluno : historico.values()) {
            for (Matriculas m : matriculasAluno){
                if (m.getCodigoDisciplina().equals(codigoDisciplina)){
                    somaNotas += m.getNota();
                    contador++;
                }
            }
        }
        if (contador == 0){
            return 0.0;
        }
        return somaNotas / contador;
    }

    /**Metodo para ranquear os estudantes por media. Gera um ranking dos N melhores estudantes ordenados por média de forma decrescente
     *Coleta todos os Ids usando historico.keySet() e calcula a media de cada um.
     * Armazena o Id e a media em uma lista EstudanteMedia.
     * Ordena usando um Comparator, que compara e inverte.
     * Retorna uma sublista com os N primeiros elementos.
     */
     List<EstudanteMedia> topNEPM(int N){   //cria um arraylist vazio para armazenar os objetos EstudanteMedia dos alunos
        List<EstudanteMedia> ranking = new ArrayList<>();
        for (int id: historico.keySet()){  //retorna um set com os ids no historicoNotas
            double media = mediaEstudante(id);  //ao achar um id, chama o metodo mediaEstudante
            ranking.add(new EstudanteMedia(id,media)); //cria um novo objeto EstudanteMedia com os dados e adiciona a lista de ranking
        }
        ranking.sort(Comparator.comparing(EstudanteMedia::getMedia).reversed());
        int limite = Math.min(N, ranking.size());
        return ranking.subList(0,limite);
    }

}