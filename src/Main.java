import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String[] estudantesData = {
                "1, Ana","2, Bruno","3, Carla","4, Diego", "5, Elisa"
        };

        String[] disciplinasData = {
                "MAT101, Matemática", "PRG201, Programação", "BD301, Banco de Dados",
                "EDF110, Educação Física",
                //adicionar duplicadas aqui para testar o set
                "BD301, B. de Dados (Duplicata)"
        };

        String[] martriculasData = {
                "1, MAT101, 8.5", "1,PRG201,9.0", "2,PRG201,7.0", "3,BD301, 6.5","4,PRG201,8.0","5,EDF110,10.0",
                //mais dados pra ter mais medias
                "2,MAT101,5.0", "3,MAT101,7.5", "5,PRG201,9.0"
        };

        CadastroDisciplinas cadastroDisciplinas = new CadastroDisciplinas();

        Set<Disciplinas> duplicatasDetectadas = new LinkedHashSet<>();

        for (String linha : disciplinasData){
            String[] partes = linha.split(",",2);
            if (partes.length ==2){
                String codigo = partes[0].trim();
                String nome = partes[1].trim();

                Disciplinas disciplinas = new Disciplinas(codigo, nome);

                boolean adicionado = cadastroDisciplinas.adicionarDisciplina(disciplinas);

                if (!adicionado){
                    duplicatasDetectadas.add(disciplinas);
                }
            }
        }
        exibirDisciplinas(cadastroDisciplinas);

        exibirDuplicatas(duplicatasDetectadas);
    }
    private static void exibirDisciplinas(CadastroDisciplinas cadastro){
        System.out.println("Disciplinas (ordem de entrada");

        for (Disciplinas d: cadastro.obterTodasDisciplinas()){
            System.out.println(d);
        }
    }
    private static void exibirDuplicatas(Set<Disciplinas> duplicatas){
        System.out.println("duplicatas detectadas");
        if (duplicatas.isEmpty()){
            System.out.println("nenhuma duplicata");
        } else {
            for (Disciplinas d : duplicatas){
                System.out.println("codigo" + d.getCodigo() + "nome:" + d.getNome());
            }
        }
    }
}
