public class Disciplinas {
    private String codigo;
    private String nome;


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        //verifica se a disciplina ja está registrada na memória
        if (this == o) return true;

        //se for nulo, então não são iguais. ai registra na memória
        if (o == null || getClass() != o.getClass())
            return false;


        Disciplinas that = (Disciplinas) o;

        return this.codigo.equals(that.codigo);
    }
      @Override
      public int hashCode() {



          return codigo.hashCode();
        }
        @Override
    public String toString(){
        return codigo + " - " + nome;
    }
}
