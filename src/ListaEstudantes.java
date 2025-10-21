import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Collection;


public class ListaEstudantes{
    private final List<Estudante> estudantes = new ArrayList<>();

    public void adicionarEstudante(Estudante e){
        estudantes.add(e);
    }

    //adicionando estudante por id
    void aE(Estudante e){
        estudantes.add(e);
    }

    //removendo estudante por id
    void rEPI(int id){
        estudantes.removeIf(e -> e.getId() == id );
    }

    //obtendo estudante por indice
    public Estudante oEPI(int indice){
        return estudantes.get(indice) ;
    }

    //buscando estudante por nome
    List<Estudante> bEPN(String substring){
        List<Estudante> resultado = new ArrayList<>();
        for(Estudante estudante : estudantes){
            if(estudante.getNome().toLowerCase().contains(substring.toLowerCase())){
                resultado.add(estudante);
            }
        }
        return resultado;
    }

    public Collection<Estudante> obterTodosEstudantes() {
        return new ArrayList<>(this.estudantes);
    }

    //Ordenando estudante por nome
    void oEPN(){
        estudantes.sort(null);
    }
    //obter estudante por id
    Estudante oEPId(int id){
        for (Estudante estudante : estudantes){
            if(estudante.getId() == id){
                return estudante;
            }
        }
        return null;
    }
    public Estudante buscarPorId(int id) {
        for (Estudante e : estudantes) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public List<Estudante> getEstudantes() {
        return this.estudantes;
    }

    public List<Estudante> obterEstudantesOrdenadosPorNome() {
        // Cria uma CÓPIA da lista original
        List<Estudante> listaOrdenada = new ArrayList<>(this.estudantes);

        // Ordena a cópia usando o Comparable que você implementou em Estudante.java
        Collections.sort(listaOrdenada);

        return listaOrdenada;
    }


}
