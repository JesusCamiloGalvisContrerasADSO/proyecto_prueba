
package servicios;

public class Respuesta {
    private boolean existe;
    private String error;

    public Respuesta(boolean existe, String error) {
        this.existe = existe;
        this.error = error;
    }

    public boolean isExiste() {
        return existe;
    }

    public String getError() {
        return error;
    }
}
