/**
 * Classe auxiliar para armazenar as variaveis codigoDisciplina e nota.
 * Implementado o construtor, getters e setters.
 */

public class Matriculas {
    String codigoDisciplina;
    double nota;

    //Construtor
    Matriculas(String codigoDisciplina, double nota){
        this.codigoDisciplina = codigoDisciplina;
        this.nota = nota;
    }

     String getCodigoDisciplina() {
        return codigoDisciplina;
    }

     void setCodigoDisciplina(String codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

     double getNota() {
        return nota;
    }

     void setNota(double nota) {
        this.nota = nota;
    }
}
