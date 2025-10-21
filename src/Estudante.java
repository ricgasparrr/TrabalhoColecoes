/**
 * Classe auxiliar para guardar as variaveis de id e nome.
 * Implementado o construtor de Estudante, getters, setters e Override
 */

public class Estudante  implements Comparable<Estudante>{
    private int id;
    private String nome;

    public Estudante(String nome,int id){
        this.nome=nome;
        this.id=id;
    }


    @Override
    public int compareTo(Estudante outroEstudante) {


        return this.nome.compareTo(outroEstudante.getNome());
    }


    @Override
    public String toString() {
        return "ID: " + this.id + " | Nome: " + this.nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
