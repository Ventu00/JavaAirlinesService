public class Administrador {
    private  String  usuario;
    private String contrasenya;

    public Administrador(String usuario, String contrasenya) {
        this.usuario = usuario;
        this.contrasenya = contrasenya;
    }
//getters y setters
public String getUsuario() {
    return usuario;
}

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }    //metodes
    /**
     * Verifica las credenciales de inicio de sesión del administrador.
     *
     * Compara el nombre de usuario y la contraseña proporcionados con las credenciales del administrador.
     * Si coinciden, se establece el valor de la variable 'sortir' en true, indicando que se puede salir del
     * bucle principal del programa.
     *
     * @param nomUsuari El nombre de usuario proporcionado.
     * @param contrasenya La contraseña proporcionada.
     * @param sortir El valor actual de la variable 'sortir'.
     * @return true si las credenciales son válidas y se puede salir del bucle, false en caso contrario.
     */
    public boolean loginAdmin(String nomUsuari, String contrasenya, boolean sortir) {
        if (this.usuario.equals(nomUsuari) && this.contrasenya.equals(contrasenya)) {
            sortir = true;
        }
        return sortir;
    }

    }

