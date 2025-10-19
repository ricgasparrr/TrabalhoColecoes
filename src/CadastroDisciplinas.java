import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class CadastroDisciplinas {
    //a linkedhashset usamos para garantir nao ter disciplinas iguais
    //manter a ordem de entrada
    private final Set<Disciplinas> disciplinas = new LinkedHashSet<>();

    //

    public boolean adicionarDisciplina(Disciplinas d){
        boolean adicionado = disciplinas.add(d);


        //aqui identifica disciplinas iguauis
        if (!adicionado){
            System.out.println("essa disciplina ja esta no codigo" + d.getCodigo() + "a disciplina nao foi adicionada");
        }
        return adicionado;
    }

    public boolean verificarDisciplina(String codigo){
        return disciplinas.contains(new Disciplinas(codigo, null));
    }
    public boolean removerDisciplina(String codigo){
        return disciplinas.remove(new Disciplinas(codigo,null));
    }


    public Collection<Disciplinas> obterTodasDisciplinas(){
        return disciplinas;

    }
}
