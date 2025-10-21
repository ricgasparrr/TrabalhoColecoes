import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ListaEstudantes{
    private ArrayList<Estudante> estudantes = new ArrayList<>();

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

    public List<Estudante> obterTodosEstudantes() {
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


}
