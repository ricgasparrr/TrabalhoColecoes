/**
 * Classe auxiliar para armazenar somente as variáveis idEstudante e média em uma lista.
 * Implementado o construtor e os getters.
 */

public class EstudanteMedia {
    private final int idEstudante;
    private final double media;

    EstudanteMedia(int idEstudante, double media){
        this.idEstudante=idEstudante;
        this.media=media;
    }

    public int getIdEstudante() {
        return idEstudante;
    }

    public double getMedia() {
        return media;
    }
}
