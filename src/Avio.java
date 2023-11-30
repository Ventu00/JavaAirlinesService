import java.time.LocalDate;

public class Avio {
    private  String nomAvio;
    private  LocalDate dataCreacio;
    private  int capacitatCombustible;
    private  int numPlacesTotal;

    public Avio(String nomAvio, LocalDate dataCreacio, int capacitatCombustible, int numPlacesTotal) {
        this.nomAvio = nomAvio;
        this.dataCreacio = dataCreacio;
        this.capacitatCombustible = capacitatCombustible;
        this.numPlacesTotal = numPlacesTotal;
    }
//getters i seters
    public String getNomAvio() {
        return nomAvio;
    }

    public void setNomAvio(String nomAvio) {
        this.nomAvio = nomAvio;
    }

    public LocalDate getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(LocalDate dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public int getCapacitatCombustible() {
        return capacitatCombustible;
    }

    public void setCapacitatCombustible(int capacitatCombustible) {
        this.capacitatCombustible = capacitatCombustible;
    }

    public int getNumPlacesTotal() {
        return numPlacesTotal;
    }

    public void setNumPlacesTotal(int numPlacesTotal) {
        this.numPlacesTotal = numPlacesTotal;
    }
    //metodes
    /**
     * Muestra por consola los datos del avión.
     *
     * Imprime por consola el nombre del avión, la fecha de creación,
     * la capacidad de combustible y el número total de plazas.
     */
    public void mostrarDadesAvio() {
        System.out.println("Nom de l'avió: " + nomAvio);
        System.out.println("Data de creació: " + dataCreacio);
        System.out.println("Capacitat de combustible: " + capacitatCombustible + " litres");
        System.out.println("Nombre de places total: " + numPlacesTotal);
        System.out.println("---------------------------");
    }
}
