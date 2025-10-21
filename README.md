# 📚 Sistema de Gestão Acadêmica 

## 👤 Integrantes do Grupo

- **Yuri Douglas Cavalcante Veloso Soares** (Estudante de Engenharia de Software)
- **Ricardo Cronemberger Cruz Ruben Pereira** (Estudante de Engenharia de Software)

---

## 🎯 Descrição do Projeto 

Este projeto simula um sistema básico de gestão acadêmica. Seu objetivo é processar dados de estudantes, disciplinas e notas a partir de três arquivos CSV (`disciplinas.csv`, `estudantes.csv`, `matriculas.csv`).

O programa orquestra o carregamento desses dados, garante a consistência das informações utilizando diferentes estruturas de Coleções Java, e gera um relatório final (`relatorio_final.txt`) com diversas consultas e estatísticas, como listas ordenadas, detecção de duplicatas e cálculo de médias por aluno e disciplina.

---

## 💡 Justificativa das Escolhas de Coleções e Implementações

A escolha das estruturas de dados foi fundamental para cumprir os requisitos de desempenho, unicidade e ordenação do sistema:

| Interface | Implementação | Classe | Justificativa |
| :--- | :--- | :--- | :--- |
| **`Set`** | `LinkedHashSet` | `CadastroDisciplinas` | **Unicidade e Ordem:** Garante que cada disciplina seja única pelo seu código (graças a `equals()` e `hashCode()` baseados no código). O `LinkedHashSet` foi escolhido especificamente para **manter a ordem de inserção** das disciplinas (requisito do Passo 3). |
| **`List`** | `ArrayList` | `ListaEstudantes` | **Ordenação:** Permite o armazenamento sequencial dos estudantes. A ordenação é garantida pela implementação da interface `Comparable` na classe `Estudante` (ordenando por nome), que é utilizada pelo `Collections.sort()`. |
| **`Map`** | `HashMap` | `HistoricoNotas` | **Associação Chave/Valor:** Essencial para mapear o **ID do Estudante (Chave)** ao seu respectivo **Histórico de Matrículas (Valor: List<Matricula>)**. Permite busca rápida de notas (`O(1)`) pelo ID. |

---

## 💻 Como Executar o Programa e Gerar o Arquivo de Saída

1.  **Pré-requisitos:** Certifique-se de ter o **JDK (Java Development Kit)** instalado.
2.  **Organização dos Arquivos:** Coloque os três arquivos de entrada (`disciplinas.csv`, `estudantes.csv`, `matriculas.csv`) na **pasta raiz (diretório de execução)** do projeto, junto com a pasta `src`.
3.  **Execução:** Compile e execute a classe `Main.java` através do seu IDE ou terminal. O programa irá processar os CSVs.
4.  **Saída:** O relatório final será gerado no mesmo diretório dos CSVs, no arquivo chamado **`relatorio_final.txt`**.

---

## 🚧 Comentário sobre Desafios Encontrados

O principal desafio encontrado na implementação e integração foi a garantia do contrato de igualdade e ordenação da API Collections:

1.  **Contrato `equals`/`hashCode`:** Garantir que a unicidade da `Disciplina` fosse definida **apenas** pelo `codigo`, exigindo a implementação precisa dos métodos e do construtor na classe `Disciplina`.
2.  **I/O e Estrutura de Projeto:** Lidar com exceções de I/O (`IOException`) e garantir que os arquivos CSV fossem lidos corretamente pelo `BufferedReader`, o que exigiu que a organização dos arquivos de entrada estivesse perfeita no diretório de execução.
3.  **Integração do `Map`:** O desafio em `HistoricoNotas` foi calcular médias por disciplina, o que exigiu iterar sobre os **valores** do `Map` (listas de matrículas) e filtrar as notas pelo código da disciplina.

---