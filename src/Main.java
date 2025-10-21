import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static final String ARQUIVO_SAIDA = "relatorio_final.txt";
    private static final String DISCIPLINA_CSV = "disciplinas.csv";
    private static final String ESTUDANTE_CSV = "estudantes.csv";
    private static final String MATRICULAS_CSV = "matriculas.csv";

    public static void main(String[] args) {

        // 1. Instanciar todas as classes de coleção
        CadastroDisciplinas cadastroDisciplinas = new CadastroDisciplinas();
        ListaEstudantes listaEstudantes = new ListaEstudantes();
        HistoricoNotas historicoNotas = new HistoricoNotas();

        Set<Disciplinas> duplicatasDetectadas = new LinkedHashSet<>();

        try (
                FileWriter fileWriter = new FileWriter(ARQUIVO_SAIDA);
                PrintWriter out = new PrintWriter(fileWriter)
        ) {
            out.println("====== RELATÓRIO ACADÊMICO (TAREFA INTEGRADORA) ======");
            out.println("------------------------------------------------------\n");

            // 2. Carga de Dados
            carregarDisciplinas(cadastroDisciplinas, duplicatasDetectadas, out);
            carregarEstudantes(listaEstudantes, out);
            carregarMatriculas(historicoNotas, out);

            // 3. Geração de Relatórios (Parte D)

            // Passo 3 e 5c (Set)
            exibirDisciplinas(cadastroDisciplinas, out);
            exibirDuplicatas(duplicatasDetectadas, out);

            // Passo 2 (List)
            exibirEstudantesOrdenados(listaEstudantes, out);

            // Passos 4, 5a, 5b (Map)
            exibirMediasAlunos(listaEstudantes, historicoNotas, out);
            exibirAlunosAcimaDaMedia(listaEstudantes, historicoNotas, 8.0, out);
            exibirMediasDisciplinas(cadastroDisciplinas, historicoNotas, out);


            out.println("\n------------------------------------------------------");
            out.println("Relatório gerado com sucesso no arquivo: " + ARQUIVO_SAIDA);

        } catch (IOException e) {
            System.err.println("ERRO FATAL DE I/O: Não foi possível ler ou escrever arquivos. Detalhes: " + e.getMessage());
        }
    }

    // ====================================================================
    // MÉTODOS AUXILIARES DE CARGA (I/O)
    // ====================================================================

    private static void carregarDisciplinas(CadastroDisciplinas cadastro, Set<Disciplinas> duplicatas, PrintWriter out) {
        out.println(">> CARREGANDO DISCIPLINAS...");
        try (BufferedReader br = new BufferedReader(new FileReader(DISCIPLINA_CSV))) {
            br.readLine(); // Pula o cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",", 2);
                if (partes.length == 2) {
                    Disciplinas disciplinas = new Disciplinas(partes[0].trim(), partes[1].trim());
                    if (!cadastro.adicionarDisciplina(disciplinas)) {
                        // O método adicionarDisciplina já imprime uma mensagem, mas aqui registramos a duplicata.
                        duplicatas.add(disciplinas);
                    }
                }
            }
        } catch (IOException e) {
            out.println("ERRO: Não foi possível ler o arquivo de disciplinas.");
        }
    }

    private static void carregarEstudantes(ListaEstudantes lista, PrintWriter out) {
        out.println(">> CARREGANDO ESTUDANTES...");
        try (BufferedReader br = new BufferedReader(new FileReader(ESTUDANTE_CSV))) {
            br.readLine(); // Pula o cabecalho
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",", 2);
                if (partes.length == 2) {
                    try {
                        int id = Integer.parseInt(partes[0].trim());
                        String nome = partes[1].trim();
                        lista.adicionarEstudante(new Estudante(nome, id));
                    } catch (NumberFormatException e) {
                        out.println("AVISO: ID de estudante inválido ignorado na linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            out.println("ERRO: Não foi possível ler o arquivo de estudantes.");
        }
    }

    private static void carregarMatriculas(HistoricoNotas historicoNotas, PrintWriter out) {
        out.println(">> CARREGANDO MATRÍCULAS...");
        try (BufferedReader br = new BufferedReader(new FileReader(MATRICULAS_CSV))) {
            br.readLine(); // Pula o cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 3) {
                    try {
                        int idEstudante = Integer.parseInt(partes[0].trim());
                        String codigoDisciplina = partes[1].trim();
                        double nota = Double.parseDouble(partes[2].trim());

                        historicoNotas.adicionarMatricula(idEstudante, codigoDisciplina, nota);
                    } catch (NumberFormatException e) {
                        out.println("AVISO: Dados de matrícula inválidos ignorados na linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            out.println("ERRO: Não foi possível ler o arquivo de matrículas.");
        }
        out.println(">> Carga de dados concluída.\n");
    }

    // ====================================================================
    // MÉTODOS AUXILIARES DE EXIBIÇÃO (RELATÓRIOS)
    // ====================================================================

    private static void exibirDisciplinas(CadastroDisciplinas cadastro, PrintWriter out) {
        out.println("\n== PASSO 3: Disciplinas (em ordem de inserção) ==");
        for (Disciplinas d : cadastro.obterTodasDisciplinas()) {
            out.println(d);
        }
    }

    private static void exibirDuplicatas(Set<Disciplinas> duplicatas, PrintWriter out) {
        out.println("\n== PASSO 5c: Duplicatas detectadas (Parte B - Extra) ==");
        if (duplicatas.isEmpty()) {
            out.println("(nenhuma duplicata encontrada nos dados)");
        } else {
            for (Disciplinas d : duplicatas) {
                out.println("Duplicata ignorada | Código: " + d.getCodigo() + ", Nome Tentado: " + d.getNome());
            }
        }
    }

    private static void exibirEstudantesOrdenados(ListaEstudantes lista, PrintWriter out) {
        out.println("\n== PASSO 2: Estudantes Ordenados por Nome (Parte A) ==");
        for (Estudante e : lista.obterEstudantesOrdenadosPorNome()) {
            out.println("ID: " + e.getId() + " | Nome: " + e.getNome());
        }
    }

    private static void exibirMediasAlunos(ListaEstudantes listaEstudantes, HistoricoNotas historico, PrintWriter out) {
        out.println("\n== PASSO 4: Médias e Matrículas por Estudante ==");
        for (Estudante e : listaEstudantes.obterTodosEstudantes()) { // Assume-se um método 'obterTodosEstudantes' não ordenado
            double media = historico.mediaEstudante(e.getId());
            List<Matriculas> matriculas = historico.obterMatricula(e.getId());

            out.printf("ID: %d | Nome: %s | Média: %.2f%n", e.getId(), e.getNome(), media);

            if (matriculas != null && !matriculas.isEmpty()) {
                out.println("   Matrículas:");
                for (Matriculas m : matriculas) {
                    out.printf("     -> %s (Nota: %.2f)%n", m.getCodigoDisciplina(), m.getNota());
                }
            }
        }
    }

    private static void exibirAlunosAcimaDaMedia(ListaEstudantes listaEstudantes, HistoricoNotas historico, double limite, PrintWriter out) {
        out.printf("\n== PASSO 5a: Alunos com Média Final >= %.2f ==%n", limite);
        boolean encontrado = false;

        // Filtra os IDs dos estudantes no histórico
        for (Integer id : historico.obterTodosIds()) { // Assume-se um método 'obterTodosIds' no HistoricoNotas
            double media = historico.mediaEstudante(id);
            if (media >= limite) {
                Estudante e = listaEstudantes.buscarPorId(id); // Assume-se um método 'buscarPorId' no ListaEstudantes
                if (e != null) {
                    out.printf("  [APROVADO] ID: %d | Nome: %s | Média: %.2f%n", e.getId(), e.getNome(), media);
                    encontrado = true;
                }
            }
        }
        if (!encontrado) {
            out.println("(nenhum estudante encontrado)");
        }
    }

    private static void exibirMediasDisciplinas(CadastroDisciplinas cadastro, HistoricoNotas historico, PrintWriter out) {
        out.println("\n== PASSO 5b: Disciplinas com Média Final < 6.0 ==");
        boolean encontrado = false;

        for (Disciplinas d : cadastro.obterTodasDisciplinas()) {
            double media = historico.mediaDisciplina(d.getCodigo());
            if (media < 6.0 && media > 0) { // Média > 0 para ignorar disciplinas sem matrículas
                out.printf("  [BAIXA] Código: %s | Nome: %s | Média: %.2f%n", d.getCodigo(), d.getNome(), media);
                encontrado = true;
            }
        }
    }
}