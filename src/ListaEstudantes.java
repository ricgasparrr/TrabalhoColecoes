import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ListaEstudantes{
    private ArrayList<Estudante> estudantes = new ArrayList<>();

    //Metodo para adicionar estudante por id
    void aE(Estudante e){
        estudantes.add(e);
    }

    //Metodo para remover estudante por id
    void rEPI(int id){
        estudantes.removeIf(e -> e.getId() == id );
    }

    //Metodo para obter estudante por indice
    public Estudante oEPI(int indice){
        return estudantes.get(indice) ;
    }

    //Metodo para buscar estudante por nome
    List<Estudante> bEPN(String substring){
        List<Estudante> resultado = new ArrayList<>();
        for(Estudante estudante : estudantes){
            if(estudante.getNome().toLowerCase().contains(substring.toLowerCase())){
                resultado.add(estudante);
            }
        }
        return resultado;
    }

    public List<Estudante> obterTodosEstudantes() {
        return new ArrayList<>(this.estudantes);
    }

    //Metodo para ordenar os estudantes por nome
    void oEPN(){
        estudantes.sort(null);
    }
    //Metodo para obter estudante por id
    Estudante oEPId(int id){
        for (Estudante estudante : estudantes){
            if(estudante.getId() == id){
                return estudante;
            }
        }
        return null;
    }
    //Metodo responsável por buscar o estudante pelo Id
    public Estudante buscarPorId(int id) {
        for (Estudante e : estudantes) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }


}
