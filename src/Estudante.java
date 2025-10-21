

public class Estudante  implements Comparable<Estudante>{
    private int id;
    private String nome;

    Estudante(String nome,int id){
        this.nome=nome;
        this.id=id;
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


    @Override
    public int compareTo(Estudante outroEstudante) {
        return this.nome.compareTo(outroEstudante.getNome());
    }


    @Override
    public String toString() {
        return "ID: " + this.id + " | Nome: " + this.nome;
    }
}
