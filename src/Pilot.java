import java.time.LocalDate;

public class Pilot {
    private  String nom;
    private  String cognoms;
    private  String dni;
    private  String telefon;
    private  String codiPilot;
    private  LocalDate dataNaixement;

    public Pilot(){}
    public Pilot(String nom, String cognoms, String dni, String telefon, String codiPilot, LocalDate dataNaixement) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.dni = dni;
        this.telefon = telefon;
        this.codiPilot = codiPilot;
        this.dataNaixement = dataNaixement;
    }
    // Getters i setters per a cada atribut
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getCodiPilot() {
        return codiPilot;
    }

    public void setCodiPilot(String codiPilot) {
        this.codiPilot = codiPilot;
    }

    public LocalDate getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(LocalDate dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    /**
     * Muestra los datos de un piloto por consola.
     *
     * Imprime por consola el nombre, apellidos, DNI, teléfono, código de piloto y fecha de nacimiento
     * del piloto.
     */
    public void mostrardadespilots() {
        System.out.println("Nom: " + nom);
        System.out.println("Cognoms: " + cognoms);
        System.out.println("DNI: " + dni);
        System.out.println("Telèfon: " + telefon);
        System.out.println("Codi Pilot: " + codiPilot);
        System.out.println("Data Naixement: " + dataNaixement);
        System.out.println("---------------------------");
    }

}
