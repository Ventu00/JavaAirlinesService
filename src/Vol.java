import java.time.LocalTime;
import java.util.ArrayList;

public class Vol extends ArrayList<Vol> {
    private  String codi;
    private  String origen;
    private  String desti;
    private  int durada;
    private  LocalTime horaSortida;
    private  LocalTime horaArribada;
    private  String pilot;
    private  String avio;
    private  int placesDisponibles;
    private double percentatgeDevolucio;


    public Vol(String codi, String origen, String desti, int durada, LocalTime horaSortida, String pilot, String avio, int placesDisponibles, double percentatgeDevolucio) {
        this.codi = codi;
        this.origen = origen;
        this.desti = desti;
        this.durada = durada;
        this.horaSortida = horaSortida;
        this.horaArribada = calcularHoraArribada(durada,horaSortida);
        this.pilot = pilot;
        this.avio = avio;
        this.placesDisponibles = placesDisponibles;
        this.percentatgeDevolucio = percentatgeDevolucio;
    }
    // Getters i setters per a les dades de la classe
    public String getCodi() {
        return codi;
    }
    public void setCodi(String codi) {
        this.codi = codi;
    }
    public String getOrigen() {
        return origen;
    }
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    public String getDesti() {
        return desti;
    }
    public void setDesti(String desti) {
        this.desti = desti;
    }
    public int getDurada() {
        return durada;
    }
    public void setDurada(int durada) {
        this.durada = durada;
        calcularHoraArribada(durada,horaSortida);
    }
    public LocalTime getHoraSortida() {
        return horaSortida;
    }
    public void setHoraSortida(LocalTime horaSortida) {
        this.horaSortida = LocalTime.from(horaSortida);
        calcularHoraArribada(durada,horaSortida);
    }
    public String getPilot() {
        return pilot;
    }
    public void setPilot(String pilot) {
        this.pilot = pilot;
    }
    public String getAvio() {
        return avio;
    }
    public void setAvio(String avio) {
        this.avio = avio;
    }

    public int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public int setPlacesDisponibles(int placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
        return placesDisponibles;
    }

    public double getPercentatgeDevolucio() {
        return percentatgeDevolucio;
    }

    public void setPercentatgeDevolucio(double percentatgeDevolucio) {
        this.percentatgeDevolucio = percentatgeDevolucio;
    }
    //funcions
    /**
     * Muestra por consola los datos de un vuelo.
     *
     * Muestra en la consola los diferentes atributos de un vuelo, como el código, origen, destino,
     * duración, hora de salida, hora de llegada, piloto, avión y las plazas disponibles.
     *
     * Esta función no devuelve ningún valor, simplemente imprime los datos por consola.
     */
    public void visualitzarDades() {
        System.out.println("Codi: " + codi);
        System.out.println("Origen: " + origen);
        System.out.println("Destí: " + desti);
        System.out.println("Durada: " + durada);
        System.out.println("Hora de sortida: " + horaSortida);
        System.out.println("Hora d'arribada: " + horaArribada);
        System.out.println("Pilot: " + pilot);
        System.out.println("Avió: " + avio);
        System.out.println("Places disponibles: " + placesDisponibles);
        System.out.println("----------------------------------");
    }

    /**
     * Calcula la hora de llegada de un vuelo dado su duración y hora de salida.
     *
     * Dada la duración del vuelo en minutos y la hora de salida, se calcula la hora de llegada sumando
     * la duración a la hora de salida. El resultado se devuelve como un objeto LocalTime.
     *
     * @param durada La duración del vuelo en minutos.
     * @param sortida La hora de salida del vuelo.
     * @return La hora de llegada del vuelo calculada a partir de la duración y la hora de salida.
     */
    public static LocalTime calcularHoraArribada(int durada, LocalTime sortida) {
        LocalTime horaArribada = sortida.plusMinutes(durada);
        horaArribada = LocalTime.parse(horaArribada.toString());
        return horaArribada;
    }

    }


