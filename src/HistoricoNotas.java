import java.util.*;

public class HistoricoNotas {
    private Map<Integer, List<Matriculas>> historico;

    public HistoricoNotas() {
        this.historico = new HashMap<>();
    }

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

    //Obter matricula
    List<Matriculas> obterMatricula(int idEstudante) {
        return historico.get(idEstudante);
    }

    //obter nota
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


    void removerMatricula(int idEstudante, String codigoDisciplina){
    List<Matriculas> matriculas = obterMatricula(idEstudante);
        if (matriculas != null) {
            matriculas.removeIf(m -> m.getCodigoDisciplina().equals(codigoDisciplina));
        }
    }

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

}