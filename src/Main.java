import java.time.LocalDate;import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;import java.util.Scanner;import java.util.InputMismatchException;
/**
 * Clase principal que contiene el punto de entrada del programa.
 *
 * La clase `Main` es la clase principal del programa. Esta clase se encarga de inicializar las listas de vuelos, aviones, pilotos y
 * clientes, y luego invoca al método `Aerolinea` para comenzar la ejecución del programa.
 */
public class Main {
    private static ArrayList<Vol> vols = new ArrayList<>();
    private static ArrayList<Avio> avions = new ArrayList<>();
    private static ArrayList<Pilot> pilots = new ArrayList<>();
    private static ArrayList<Client> clients = new ArrayList<>();

    /**
     * Punto de entrada del programa.
     *
     * El método `main` es el punto de entrada del programa. Aquí se inicializan las listas de vuelos,
     * aviones, pilotos y clientes, y luego se invoca al método `Aerolinea` para comenzar la ejecución
     * del programa.
     *
     * @param args Los argumentos de línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {Aerolinea();}
    private static void objetosExistentes() {
        //vols
        Vol vol1 =new Vol("ABC", "España", "Japo", 5, LocalTime.of(22, 40, 30), "Juan", "ASD3", 40, 5);
        Vol vol2 = new Vol("XYZ", "Francia", "Alemania", 10, LocalTime.of(15, 20, 0), "Pedro", "QWE6", 60, 8);
        vols.add(vol1);
        vols.add(vol2);
        //Pilots
        Pilot piloto1 = new Pilot("Juan", "Pérez", "12345678A", "555-123456", "P001", LocalDate.of(1985, 5, 15));
        Pilot piloto2 = new Pilot("Maria", "García", "87654321B", "555-654321", "P002", LocalDate.of(1990, 10, 30));
        pilots.add(piloto1);
        pilots.add(piloto2);
        //Avio
        Avio avio1 = new Avio("AvioDelta", LocalDate.of(1968, 2, 9), 216528, 660);
        Avio avio2 = new Avio("AvioAlfa", LocalDate.of(2000, 1, 8), 716528, 960);
        avions.add(avio1);
        avions.add(avio2);
    }
    /**
     * Método principal de la aerolínea.
     *
     * El método `Aerolinea` es el punto de entrada del programa principal de la aerolínea. Se encarga de
     * inicializar los objetos existentes (vuelos, aviones, pilotos, clientes), y luego entra en un bucle
     * que permite a los usuarios elegir entre usar la funcionalidad de administrador o la funcionalidad
     * de cliente. El bucle se ejecuta hasta que el usuario decide salir del programa.
     */
    private static void Aerolinea() {
        objetosExistentes();
        boolean sortir = false;
        while (!sortir) {
            int opcio = 0;
            opcio = menuElegirUsuario(opcio);
            switch (opcio) {
                case 1 -> usarAdministrador(sortir);
                case 2 -> usarClient(sortir);
                default -> pordefectoSwitch();
            }
        }
    }

    private static void usarClient(boolean sortir) {
        int opcioClient = 0;
        while (!sortir) {
           opcioClient=menuOpcionsClient(opcioClient);
            switch (opcioClient) {
                case 1 -> visualitzarDadesVol();
                case 2 -> comprar(opcioClient);
                case 3 -> transaccio(vols, opcioClient);
                case 4 -> sortir=sortirClient(sortir);
                default -> pordefectoSwitch();
            }
        }
    }
    private static void pordefectoSwitch() {
        System.out.println("Opció no vàlida");
    }
    private static boolean sortirClient(boolean sortir) {
        System.out.println("Fins aviat!");
        sortir = true;
    return sortir;}
    private static void comprar(int opcioClient) {
        visualitzarVolsClient();
        transaccio(vols, opcioClient);
    }
    private static String transaccio(ArrayList<Vol> vols, int opcioClient) {
        int preu = 100;
        int numPassatgers = 0;
        String codigoVuelo = introducirVuelo();
        Vol volSeleccionado = buscarSeleccionar(vols, codigoVuelo);
            numPassatgers = introducePasajeros();
            if (opcioClient == 3) {
                validacioDeId();
                procesAnular(numPassatgers, volSeleccionado, preu,codigoVuelo);
            } else if (opcioClient == 2) {
                procesComprar(numPassatgers, volSeleccionado, preu,codigoVuelo);
            }
        return codigoVuelo;
    }
    private static void validacioDeId() {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        boolean idValid = false;

        while (!idValid) {
            System.out.println("Introdueix el teu numero de id: ");
            try {
                id = scanner.nextInt();
                if (existeixClient(id, clients)) {
                    idValid = true;
                } else {
                    System.out.println("El client amb l'ID " + id + " no existeix.");
                }
            } catch (InputMismatchException e) {
                errorPasajero();
            }
        }
    }

    private static void errorPasajero() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Error: Has d'introduir un número enter.");
        scanner.next();

    }


    private static boolean existeixClient(int id, ArrayList<Client> clients) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private static String introducirVuelo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el código del vuelo: ");
        return scanner.next();
    }
    private static int introducePasajeros() {
        Scanner scanner = new Scanner(System.in);
        int pasajeros = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Introduce el número de pasajeros: ");
                pasajeros = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Introduce un número entero adecuado.");
            }
        }
        return pasajeros;
    }

    private static void procesAnular(int numPassatgers, Vol volSeleccionado, int preu, String codigoVuelo) {
        if (volSeleccionado.getPlacesDisponibles() >= numPassatgers) {
            calcularPreuTotal(numPassatgers, preu, volSeleccionado.getPercentatgeDevolucio());
            boolean volAnulat = demanarConfirmacioAnulacio();
            if (volAnulat) {
                anularBitllets(numPassatgers, volSeleccionado);
            } else {
                System.out.println("anulació cancel·lada.");
            }
        } else {
            errorAnular(volSeleccionado);
        }
    }

    private static void errorAnular(Vol volSeleccionado) {
        System.out.println("ERROR: El número de bitllets comprats es menor al número que es vol anul·lar.");
        System.out.println("Places disponibles en aquest vol: " + volSeleccionado.getPlacesDisponibles());
    }

    /**
     * Calcula el precio total de la compra de billetes, teniendo en cuenta el número de pasajeros,
     * el precio unitario y el porcentaje de devolución.
     *
     * @param numPassatgers        El número de pasajeros para los que se está calculando el precio total.
     * @param preu                 El precio unitario de los billetes.
     * @param percentatgeDevolucio El porcentaje de devolución a aplicar.
     * @return                     El precio total de la compra.
     */
    private static double calcularPreuTotal(int numPassatgers, int preu, double percentatgeDevolucio) {
        double devolucio = preu * percentatgeDevolucio / 100;
        double preuTotal = preu * numPassatgers - devolucio;
        System.out.println("El preu total és: " + preuTotal);
        return preuTotal;
    }

    /**
     * Solicita la confirmación de anulación de los billetes al usuario.
     *
     * @return true si el usuario confirma la anulación, false en caso contrario.
     */
    private static boolean demanarConfirmacioAnulacio() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vols anular els bitllets? (S/N): ");
        String resposta = scanner.next();
        return resposta.equalsIgnoreCase("S");
    }

    /**
     * Anula los billetes para el número de pasajeros especificado en el vuelo seleccionado.
     * Incrementa el número de plazas disponibles en el vuelo y actualiza el ArrayList correspondiente.
     *
     * @param numPassatgers     El número de pasajeros cuyos billetes se van a anular.
     * @param volSeleccionado   El vuelo seleccionado en el que se van a anular los billetes.
     */
    private static void anularBitllets(int numPassatgers, Vol volSeleccionado) {
        // incrementem el nombre de places disponibles del vuelo seleccionado y actualizamos l'ArrayList
        volSeleccionado.setPlacesDisponibles(volSeleccionado.getPlacesDisponibles() + numPassatgers);
        System.out.println("Bitllets anulats correctament!");
    }

    /**
     * Busca y selecciona un objeto Vol en una lista de vols basándose en el código de vuelo.
     *
     * @param vols           La lista de vols en la que buscar.
     * @param codigoVuelo    El código de vuelo a buscar.
     * @return               El objeto Vol encontrado o null si no se encuentra.
     */
    private static Vol buscarSeleccionar(ArrayList<Vol> vols, String codigoVuelo) {
        Vol volSeleccionado = null;
        boolean trobat = false;
        int i = 0;
        while (!trobat && i < vols.size()) {
            Vol vol = vols.get(i);
            if (vol.getCodi().equals(codigoVuelo)) {
                volSeleccionado = vol;
                trobat = true;
            }
            i++;
        }
        if (!trobat) {
            sinoEstatrobat(codigoVuelo);
        }
        return volSeleccionado;
    }


    private static void sinoEstatrobat(String codigoVuelo) {
        System.out.println("No se ha encontrado ningún vuelo con el código " + codigoVuelo + ".");
        introducirCodigoVuelo(codigoVuelo);
    }
    private static String introducirCodigoVuelo(String codigoVuelo) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el código de vuelo: ");
        codigoVuelo = scanner.next();
    return codigoVuelo;}

    /**
     * Procesa la compra de billetes para un vuelo seleccionado.
     *
     * @param numPassatgers   El número de pasajeros para los que se desea comprar los billetes.
     * @param volSeleccionado El objeto Vol seleccionado para la compra.
     * @param preu            El precio unitario de los billetes.
     * @param codigoVuelo     El código de vuelo asociado a la compra.
     */
    private static void procesComprar(int numPassatgers, Vol volSeleccionado, int preu, String codigoVuelo) {
        if (volSeleccionado.getPlacesDisponibles() < numPassatgers) {
            mostrarErrorComprar(volSeleccionado, numPassatgers, codigoVuelo);
        }
        double preuTotal = calcularPreuTotal(numPassatgers, preu);
        mostrarPreuTotal(preuTotal);

        if (confirmarCompra()) {
            comprarBitllets(numPassatgers, volSeleccionado);
            insertarId();
        } else {
            System.out.println("Compra cancel·lada.");
        }
    }


    private static void insertarId() {
        System.out.println("Bitllets comprats correctament!");
        Client nouclient = new Client();
        clients.add(nouclient);
        System.out.println("El teu id de Client es: "+ nouclient.getId());

    }

    private static void mostrarErrorComprar(Vol volSeleccionado, int numPassatgers, String codigoVuelo) {
        System.out.println("No hi ha places suficients per a " + numPassatgers + " passatgers en el vol " + codigoVuelo + ".");
        System.out.println("Places disponibles en aquest vol: " + volSeleccionado.getPlacesDisponibles());
    }
    private static double calcularPreuTotal(int numPassatgers, int preu) {
        return preu * numPassatgers;
    }
    /**
     * Muestra el precio total de la compra de billetes.
     *
     * @param preuTotal El precio total de la compra.
     */
    private static void mostrarPreuTotal(double preuTotal) {
        System.out.println("El preu total és: " + preuTotal);
    }

    /**
     * Solicita la confirmación de compra de los billetes al usuario.
     *
     * @return true si el usuario confirma la compra, false en caso contrario.
     */
    private static boolean confirmarCompra() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vols comprar els bitllets? (S/N): ");
        String resposta = scanner.next();
        return resposta.equalsIgnoreCase("S");
    }

    /**
     * Compra los billetes para el número de pasajeros especificado en el vuelo seleccionado.
     * Reduce el número de plazas disponibles en el vuelo.
     *
     * @param numPassatgers     El número de pasajeros para los que se están comprando los billetes.
     * @param volSeleccionado   El vuelo seleccionado en el que se van a comprar los billetes.
     */
    private static void comprarBitllets(int numPassatgers, Vol volSeleccionado) {
        volSeleccionado.setPlacesDisponibles(volSeleccionado.getPlacesDisponibles() - numPassatgers);
    }

    /**
     * Controla el uso de la funcionalidad de administrador.
     *
     * @param sortir Indica si se debe salir de la funcionalidad de administrador.
     * @return true si se debe salir de la funcionalidad de administrador, false en caso contrario.
     */
    private static boolean usarAdministrador(boolean sortir) {
        sortir = validarAdmin(sortir);
        while (!sortir) {
            int opcionAdmin = menuOpcionsAdmin();
            sortir = opcionesAdmin(opcionAdmin, sortir);
        }
        return sortir;
    }

    /**
     * Controla las diferentes opciones disponibles para el administrador.
     *
     * @param opcioAdmin La opción seleccionada por el administrador.
     * @param sortir     Indica si se debe salir de la funcionalidad de administrador.
     * @return           true si se debe salir de la funcionalidad de administrador, false en caso contrario.
     */
    private static boolean opcionesAdmin(int opcioAdmin, boolean sortir) {
        switch (opcioAdmin) {
            case 1 -> crearVol();
            case 2 -> visualitzarDadesVol();
            case 3 -> modificarDadesVol();
            case 4 -> esborrarVol();
            case 5 -> visualitzarDadesAvio();
            case 6 -> visualitzarPilot();
            case 7 -> sortir = true;
            default -> System.out.println("Opció no vàlida");
        }
        return sortir;
    }

    /**
     * Valida las credenciales de administrador introducidas por el usuario.
     *
     * @param sortir        Indica si se debe salir de la funcionalidad de administrador.
     * @return              true si las credenciales son correctas y se puede acceder a la funcionalidad de administrador, false en caso contrario.
     */
    private static boolean validarAdmin(boolean sortir) {
        Administrador admin = new Administrador("admin","admin");
        String nomUsuari = introduirUsuari();
        String contrasenya = introduirContrasenya();
        sortir = usuarioCorrectoOIncorrecto(sortir, nomUsuari, contrasenya, admin);
        return sortir;
    }


    /**
     * Solicita al usuario que introduzca la contraseña.
     *
     * @return La contraseña introducida por el usuario.
     */
    private static String introduirContrasenya() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix la contrasenya:");
        String contrasenya = scan.nextLine();
        return contrasenya;
    }

    /**
     * Solicita al usuario que introduzca el nombre de usuario.
     *
     * @return El nombre de usuario introducido por el usuario.
     */
    private static String introduirUsuari() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix el nom d'usuari:");
        String nomUsuari = scan.nextLine();
        return nomUsuari;
    }
    /**
     * Verifica si las credenciales de usuario son correctas o incorrectas y actualiza la variable sortir en consecuencia.
     *
     * @param sortir        Indica si se debe salir de la funcionalidad de administrador.
     * @param nomUsuari     El nombre de usuario introducido por el usuario.
     * @param contrasenya   La contraseña introducida por el usuario.
     * @param admin         El objeto Administrador para realizar la verificación.
     * @return              true si las credenciales son incorrectas y se debe salir de la funcionalidad de administrador, false si las credenciales son correctas.
     */
    private static boolean usuarioCorrectoOIncorrecto(boolean sortir, String nomUsuari, String contrasenya, Administrador admin) {
        if (admin.loginAdmin(nomUsuari, contrasenya, sortir)) {
            System.out.println("Benvingut, administrador!");
            sortir = false;
        } else {
            System.out.println("Usuari o contrasenya incorrectes.");
            sortir = true;
        }
        return sortir;
    }

    /**
     * Muestra el menú de opciones para el cliente y solicita al usuario que elija una opción.
     *
     * @param opcioClient La opción seleccionada por el cliente.
     * @return            La opción seleccionada por el cliente después de ser validada.
     */
    private static int menuOpcionsClient(int opcioClient) {
        opcioClient = 0;
        while (opcioClient <= 1 && opcioClient >= 4); {
            System.out.println("Elegeix una opció: ");
            System.out.println("1. Visualitzar informació del vol");
            System.out.println("2. Comprar bitllets");
            System.out.println("3. Anul·lar bitllets");
            System.out.println("4. Sortir");
            opcioClient = validaropcioninsertadaDeCliente(opcioClient);
        }
        return opcioClient;
    }

    private static int validaropcioninsertadaDeCliente(int opcioClient) {
        Scanner scan = new Scanner(System.in);
        try {
            opcioClient = scan.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Error: La opcio ha de ser un número enter.");

        }
    return opcioClient;}

    /**
     * Muestra el menú de opciones para el administrador y solicita al usuario que elija una opción.
     *
     * @return La opción seleccionada por el administrador después de ser validada.
     */
    private static int menuOpcionsAdmin() {
        int opcioAdmin = 0;
        do {
            System.out.println("Elegeix una opció:");
            System.out.println("1. Crear vol");
            System.out.println("2. Visualitzar dades vol");
            System.out.println("3. Modificar dades vol");
            System.out.println("4. Esborrar vol");
            System.out.println("5. Mostra els avions");
            System.out.println("6. Mostra els pilots");
            System.out.println("7. Sortir");
            opcioAdmin = validaropcioninsertada(opcioAdmin);
        } while (opcioAdmin <= 1 && opcioAdmin >= 7);
        return opcioAdmin;
    }


    private static int validaropcioninsertada(int opcioAdmin) {
        Scanner scan = new Scanner(System.in);
        try {
            opcioAdmin = scan.nextInt();
        }catch (InputMismatchException e) {
            System.out.println("Error: La opcio ha de ser un número enter.");
        }
    return opcioAdmin;}

    private static int menuElegirUsuario(int opcio) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Escananull una opció:");
        System.out.println("1. Administrador");
        System.out.println("2. Client");
        try {
            opcio = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: La opcio ha de ser un número enter.");
        }
    return opcio;}
    private static void visualitzarVolsClient() {
        for (int i = 0; i < vols.size(); i++) {
            vols.get(i).visualitzarDades();
        }
    }
    private static void visualitzarDadesAvio() {
        for(int i=0; i<avions.size(); i++) {
                avions.get(i).mostrarDadesAvio();
        }
    }
    private static void visualitzarDadesVol() {
        String codivol = validarValorCodi();
        boolean voltrobat = false;
        voltrobat=busquedaAvio(codivol,voltrobat);
        siNoestaAlaLlista(voltrobat);
    }

    private static void siNoestaAlaLlista(boolean voltrobat) {
        if(!voltrobat) {
            System.out.println("EL vol no està a la llista.");
        }
    }

    private static String validarValorCodi() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix el codi de vol: ");
        String codivol = scanner.next();
 return codivol;}
    private static boolean busquedaAvio(String codivol, boolean voltrobat) {
        for(int i=0; i<vols.size(); i++) {
            if(codivol.equals(vols.get(i).getCodi())) {
                voltrobat = true;
                vols.get(i).visualitzarDades();
            }
        }return voltrobat;
    }
    /**
     * Elimina un vuelo de la lista de vuelos disponibles según el código proporcionado por el usuario.
     *
     * Solicita al usuario que introduzca el código del vuelo que desea eliminar.
     * Recorre la lista de vuelos y elimina el vuelo que coincida con el código proporcionado.
     */
    private static void esborrarVol() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdueix el codi del vol que vols eliminar: ");
        String elimiarVolCodi = scanner.next();
        for (int i = 0; i < vols.size(); i++) {
            if (elimiarVolCodi.equals(vols.get(i).getCodi())) {
                vols.remove(i);
            }
        }
    }

    /**
     * Modifica los datos de un vuelo existente según el código proporcionado por el usuario.
     *
     * Solicita al usuario que introduzca el código del vuelo que desea modificar.
     * Recorre la lista de vuelos y, si encuentra un vuelo con el código proporcionado, permite al usuario modificar los datos del vuelo.
     * Los datos que se pueden modificar incluyen: origen, destino, duración, hora de salida, piloto, avión, número de plazas y porcentaje de devolución.
     * Muestra un mensaje de éxito si se encuentra y modifica el vuelo, o un mensaje de error si no se encuentra el vuelo.
     */
    private static void modificarDadesVol() {
        String codi = null;
        int durada;
        codi = nomVolaModificar(codi);
        boolean encontrado = false;
        for (int i = 0; i < vols.size(); i++) {
            if (codi.equals(vols.get(i).getCodi())) {
                modificarOrigen(i);
                modificarDesti(i);
                durada = modificarDurada(i);
                modificarSortida(i, durada);
                modificarPilot(i);
                modificarAvio(i);
                modificarPlaces(i);
                modificarDevolucio(i);
                System.out.println("Vol modificat correctament");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Vol no trobat");
        }
    }


    /**
     * Modifica el porcentaje de devolución de un vuelo existente en la posición especificada.
     *
     * Solicita al usuario que introduzca el nuevo porcentaje de devolución.
     * Intenta leer un número decimal desde la entrada del usuario.
     * Si se introduce un valor válido, se establece el porcentaje de devolución del vuelo en la posición especificada.
     * Si se produce un error de entrada, se muestra un mensaje de error y se descarta la entrada incorrecta.
     * El proceso se repite hasta que se proporcione una entrada válida.
     *
     * @param i La posición del vuelo en la lista de vuelos.
     */
    private static void modificarDevolucio(int i) {
        Scanner scan = new Scanner(System.in);
        boolean validInput = false;
        double percentatgeDevolucio = 0.0;
        do {
            try {
                System.out.println("Introdueix el percentatge de devolució:");
                percentatgeDevolucio = scan.nextDouble();
                vols.get(i).setPercentatgeDevolucio(percentatgeDevolucio);
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: S'ha d'introduir un número decimal.");
                scan.nextLine();
            }
        } while (!validInput);
    }


    /**
     * Modifica el número de plazas disponibles de un vuelo existente en la posición especificada.
     *
     * Solicita al usuario que introduzca el nuevo número de plazas disponibles.
     * Intenta leer un número entero desde la entrada del usuario.
     * Si se introduce un valor válido, se establece el número de plazas disponibles del vuelo en la posición especificada.
     * Si se produce un error de entrada, se muestra un mensaje de error y se descarta la entrada incorrecta.
     * El proceso se repite hasta que se proporcione una entrada válida.
     *
     * @param i La posición del vuelo en la lista de vuelos.
     */
    private static void modificarPlaces(int i) {
        Scanner scan = new Scanner(System.in);
        boolean validInput = false;
        int placesDisponibles = 0;
        do {
            try {
                System.out.println("Introdueix el número de places disponibles:");
                placesDisponibles = scan.nextInt();
                vols.get(i).setPlacesDisponibles(placesDisponibles);
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: S'ha d'introduir un número enter.");
                scan.nextLine();
            }
        } while (!validInput);
    }
    private static void modificarAvio(int i) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Escull Avio:");
        visualitzarDadesAvio();
        String nomAvio = escollirAvions();
        vols.get(i).setAvio(nomAvio);
        scan.nextLine();
    }

    private static void modificarPilot(int i) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Escull pilot:");
        visualitzarPilot();
        String nomPilot = escollirPilots();
        vols.get(i).setPilot(nomPilot);
        scan.nextLine();
    }

    /**
     * Modifica la hora de salida de un vuelo existente en la posición especificada.
     *
     * Solicita al usuario que introduzca la nueva hora de salida en el formato "hh:mm".
     * Intenta analizar la cadena de entrada y convertirla en un objeto LocalTime.
     * Si se introduce un valor válido, se establece la hora de salida del vuelo en la posición especificada.
     * Además, se calcula y se establece la hora de llegada del vuelo utilizando la duración y la hora de salida.
     * Si se produce un error de formato en la entrada, se muestra un mensaje de error y se descarta la entrada incorrecta.
     * El proceso se repite hasta que se proporcione una entrada válida.
     *
     * @param i La posición del vuelo en la lista de vuelos.
     * @param durada La duración del vuelo en minutos.
     */
    private static void modificarSortida(int i, int durada) {
        Scanner scan = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Introdueix l'hora de sortida del vol (format: hh:mm):");
                String horaSortida = scan.next();
                LocalTime sortida = LocalTime.parse(horaSortida);
                vols.get(i).setHoraSortida(sortida);
                String.valueOf(vols.get(i).calcularHoraArribada(durada, sortida));
                validInput = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: La opcio ha de ser en format hh:mm.");
                scan.nextLine(); // consumir entrada incorrecta
            }
        }
    }

    /**
     * Modifica la duración de un vuelo existente en la posición especificada.
     *
     * Solicita al usuario que introduzca la nueva duración del vuelo en minutos.
     * Intenta analizar el entero proporcionado como entrada y establece la duración del vuelo en la posición especificada.
     * Si se produce un error de formato en la entrada, se muestra un mensaje de error y se descarta la entrada incorrecta.
     * El proceso se repite hasta que se proporcione una entrada válida.
     *
     * @param i La posición del vuelo en la lista de vuelos.
     * @return La duración del vuelo modificada en minutos.
     */
    private static int modificarDurada(int i) {
        Scanner scan = new Scanner(System.in);
        int durada = 0;
        boolean inputCorrecto = false;
        while (!inputCorrecto) {
            try {
                System.out.println("Introdueix la durada del vol (minuts):");
                durada = scan.nextInt();
                vols.get(i).setDurada(durada);
                scan.nextLine();
                inputCorrecto = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: La opcio ha de ser un número enter (en minuts).");
                scan.nextLine();
            }
        }
        return durada;
    }

    private static void modificarDesti(int i) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix el destí del vol:");
        String desti = scan.next();
        vols.get(i).setDesti(desti);
    }

    private static void modificarOrigen(int i) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix l'origen del vol:");
        String origen = scan.next();
        vols.get(i).setOrigen(origen);
    }

    private static String nomVolaModificar(String codi) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix el codi del vol que vols modificar:");
         codi = scan.next();
return codi;    }

    /**
     * Crea un nuevo objeto de tipo `Vol` y lo agrega a la lista de vuelos.
     *
     * Solicita al usuario que ingrese los datos necesarios para crear un nuevo vuelo, como el código, origen, destino, duración, etc.
     * Luego, utiliza los datos ingresados para crear un nuevo objeto `Vol`.
     * Agrega el nuevo vuelo a la lista de vuelos existente.
     * Finalmente, muestra un mensaje indicando que el vuelo se ha creado correctamente.
     *
     * @return El nuevo objeto `Vol` creado.
     */
    private static Vol crearVol() {
        Scanner scan = new Scanner(System.in);
        String codi = crearCodi();
        String origen = crearOrigen();
        String desti = crearDesti();
        int durada = crearDurada();
        LocalTime sortida = crearSortida();
        LocalTime horaArribada = Vol.calcularHoraArribada(durada, sortida);
        String nomPilot = crearPilot();
        String nomAvio = crearAvio();
        int placesDisponibles = crearPlaces();
        double percentatgeDevolucio = crearDevolucio();
        Vol nouvol = new Vol(codi, origen, desti, durada, sortida, nomPilot, nomAvio, placesDisponibles, percentatgeDevolucio);
        vols.add(nouvol);
        System.out.println("Vol creat correctament");
        return nouvol;
    }

    private static double crearDevolucio() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix el percentatge de devolució:");
        double percentatgeDevolucio = 0.0;
        try {
            percentatgeDevolucio = scan.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Error: La opcio ha de ser double.");
        }
    return percentatgeDevolucio;}

    private static int crearPlaces() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix el número de places disponibles:");
        int placesDisponibles = 0;
        try {
            placesDisponibles = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: La opcio ha de ser enter.");
        }
    return placesDisponibles;}

    private static String crearAvio() {
        System.out.println("Escull Avio:");
        visualitzarDadesAvio();
        String nomAvio = escollirAvions();
    return nomAvio;}

    private static String crearPilot() {
        System.out.println("Escull pilot:");
        visualitzarPilot();
        String nomPilot = escollirPilots();
    return nomPilot;}
    private static LocalTime crearSortida() {
        Scanner scan = new Scanner(System.in);
        LocalTime sortida = null;
        while (sortida == null) {
            try {
                System.out.println("Introdueix l'hora de sortida del vol (format: hh:mm):");
                sortida = LocalTime.parse(scan.next());
            } catch (DateTimeParseException e) {
                System.out.println("Error: La opcio ha de ser en format hh:mm.");
            }
        }
        return sortida;
    }


    private static int crearDurada() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix la durada del vol (minuts):");
        int durada = 0;
        try {
            durada = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: La opcio ha de ser un número enter (en minuts).");
        }
    return durada;}

    private static String crearDesti() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix el desti del vol:");
        String desti = scan.next();
        return desti;}
    private static String crearOrigen() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix l'origen del vol:");
        String origen = scan.next();
   return origen; }

    private static String crearCodi() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix el codi del vol:");
        String codi = scan.next();
    return codi;}
    private static String escollirAvions() {
        String nomAvio=escriuNomAvio();
        nomAvio=buscarMeterAvio(nomAvio);
        return nomAvio;
    }
    /**
     * Busca y valida el nombre de un avión en la lista de aviones disponibles.
     *
     * Solicita al usuario que ingrese el nombre de un avión.
     * Verifica si el avión está incluido en la lista de aviones disponibles.
     * Si el avión no se encuentra en la lista, solicita al usuario que ingrese un nuevo nombre de avión.
     * Continúa solicitando un nuevo nombre de avión hasta que se ingrese uno válido que esté incluido en la lista.
     * Devuelve el nombre del avión válido encontrado.
     *
     * @param nomAvio El nombre del avión a buscar y validar.
     * @return El nombre del avión válido encontrado.
     */
    private static String buscarMeterAvio(String nomAvio) {
        boolean encontrado = false;
        while (!encontrado) {
            encontrado = incluyendoAvio(encontrado, nomAvio);
            if (!encontrado) {
                nomAvio = sinEncontrarAvio(nomAvio);
            }
        }
        return nomAvio;
    }
    private static String sinEncontrarAvio(String nomAvio) {
        System.out.println("El avio no esta a la llista");
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix el nom del avio:");
        nomAvio = scan.nextLine();
        return nomAvio;
    }
    /**
     * Verifica si el nombre de un avión está incluido en la lista de aviones disponibles.
     *
     * Recorre la lista de aviones disponibles y compara el nombre de cada avión con el nombre proporcionado.
     * Si se encuentra un avión con el mismo nombre, se muestra un mensaje indicando que el avión ha sido incluido correctamente.
     * Se actualiza el valor de la variable "encontrado" a true.
     * Se interrumpe el bucle de búsqueda utilizando la instrucción "break".
     *
     * @param encontrado El estado actual de la búsqueda del avión.
     * @param nomAvio El nombre del avión a buscar.
     * @return true si se encuentra el avión, false en caso contrario.
     */
    private static boolean incluyendoAvio(boolean encontrado, String nomAvio) {
        for (Avio avio : avions) {
            if (avio.getNomAvio().equals(nomAvio)) {
                System.out.println("Avio inclos correctament");
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }
    private static String escriuNomAvio() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Escriu el nom del Avio que vols seleccionar:");
        String nomAvio = scan.next();
   return nomAvio; }

    public static String escollirPilots() {
        String nomPilot=escriuNomPilot();
       nomPilot= buscarMeterPilot(nomPilot);
        return nomPilot;
    }

    /**
     * Busca y verifica si el nombre de un piloto está incluido en la lista de pilotos disponibles.
     *
     * Realiza una búsqueda en la lista de pilotos disponibles para encontrar un piloto con el nombre proporcionado.
     * Si se encuentra un piloto con el mismo nombre, se muestra un mensaje indicando que el piloto ha sido incluido correctamente.
     * Si no se encuentra el piloto, se solicita al usuario que ingrese un nuevo nombre de piloto.
     * La búsqueda y verificación se repiten hasta que se encuentre un piloto o se proporcione un nombre válido.
     *
     * @param nomPilot El nombre del piloto a buscar o ingresar.
     * @return El nombre del piloto encontrado o ingresado.
     */
    private static String buscarMeterPilot(String nomPilot) {
        boolean encontrado = false;
        while (!encontrado) {
            encontrado = incluyendoPilot(encontrado, nomPilot);
            if (!encontrado) {
                nomPilot = sinEncontrar(nomPilot);
            }
        }
        return nomPilot;
    }

    /**
     * Verifica si el nombre de un piloto está incluido en la lista de pilotos disponibles.
     *
     * Recorre la lista de pilotos disponibles y compara el nombre de cada piloto con el nombre proporcionado.
     * Si se encuentra un piloto con el mismo nombre, se muestra un mensaje indicando que el piloto ha sido incluido correctamente.
     * Se actualiza el valor de la variable "encontrado" a true.
     * Se interrumpe el bucle de búsqueda utilizando la instrucción "break".
     *
     * @param encontrado El estado actual de la búsqueda del piloto.
     * @param nomPilot El nombre del piloto a buscar.
     * @return true si se encuentra el piloto, false en caso contrario.
     */
    private static boolean incluyendoPilot(boolean encontrado, String nomPilot) {
        for (Pilot pilot : pilots) {
            if (pilot.getNom().equals(nomPilot)) {
                System.out.println("Pilot inclos correctament");
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    private static String sinEncontrar(String nomPilot) {
        System.out.println("El pilot no esta a la llista");
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix el nom del pilot:");
        nomPilot = scan.nextLine();
    return nomPilot; }


    private static String escriuNomPilot() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Escriu el nom del pilot que vols seleccionar:");
        String nomPilot = scan.next();
return nomPilot;    }

    private static void visualitzarPilot() {
        for(int i=0; i<pilots.size(); i++) {
            pilots.get(i).mostrardadespilots();
        }
    }
}