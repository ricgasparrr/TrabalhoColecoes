import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * A linkedhashset usamos para garantir nao ter disciplinas iguais.
 * Manter a ordem de entrada.
 */
public class CadastroDisciplinas {
    private final Set<Disciplinas> disciplinas = new LinkedHashSet<>();

    /**
     * Tenta adicionar uma nova disciplina à coleção.
     * Falha se já existir uma disciplina com o mesmo código.
     */
    public boolean adicionarDisciplina(Disciplinas d){
        boolean adicionado = disciplinas.add(d);


        //Aqui fica a identificação de disciplinas iguais.
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
