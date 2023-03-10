package Unidad3.Gimansio;

/**
 * ENTREGA UT3
 *
 * @author - (aquí pon tu nombre)
 * <p>
 * <p>
 * Un gimnasio oferta clases de yoga, pilates y spinning
 * en sus diferentes salas. En cada sala se realiza una actividad de la misma duración
 * que se repite a lo largo del día un nº de veces. Puede haber salas
 * con la misma actividad pero de duración diferentes (ej. en la sala 1
 * se hace spinning en clases de 40 minutos y en la sala 3 se
 * hace también spinning pero de duración 70 minutos).
 * <p>
 * El precio de las clases depende de su duración.
 * El precio base es 3.0€ y se añade a este precio 0.20€ por cada período completo de 15 minutos.
 * <p>
 * <p>
 * La primera clase comienza siempre a las 10.30 (a.m) y la última no puede acabar después
 * de las 8.30 (p.m). En todas las salas entre clase y clase (incluida la última)
 * hay un descanso de 10 minutos
 * <p>
 * El gimnasio quiere mostrar al usuario el nº de clases que se dan en cada sala con su precio
 * y además quiere efectuar ciertos cálculos dependiendo del nº de personas que se han inscrito
 * a las actividades de las diferentes salas
 */
public class Gimnasio {
    private final int HORA_PRIMERA_CLASE = 10;
    private final int MINUTOS_PRIMERA_CLASE = 30;
    private final int HORA_ULTIMA_CLASE = 20;
    private final int MINUTOS_ULTIMA_CLASE = 30;
    private final int DESCANSO = 10;
    private final double PRECIO_BASE = 3.0;
    private final double PRECIO_15MINUTOS = 0.20;
    private final char YOGA = 'Y';
    private final char PILATES = 'P';
    private final char SPINNING = 'S';
    private String nombreGimnasio;
    private int inscritosYoga;
    private int inscritosPilates;
    private int inscritosSpinning;
    private double totalAcumulado;
    private int salaMaximoSpinning;
    private int inscritosMaximoSpinning;


    /**
     * Constructor  -
     * Recibe un único parámetro, el nombre del gimnasio
     * e inicializa el resto de atributos adecuadamente
     */
    public Gimnasio(String nombreGimnasio) {
        this.nombreGimnasio = nombreGimnasio;
        this.inscritosYoga = 0;
        this.inscritosPilates = 0;
        this.inscritosSpinning = 0;
        this.totalAcumulado = 0;
        this.salaMaximoSpinning = 0;
        this.inscritosMaximoSpinning = 0;


    }

    /**
     * Accesor para el nombre del gimnasio
     */
    public String getNombre() {
        return this.nombreGimnasio;
    }

    /**
     * Mutador para el nombre del gimnasio
     */
    public void setNombre(String nuevoNombreGimnasio) {
        this.nombreGimnasio = nuevoNombreGimnasio;
    }

    /**
     * accesor para el importe total acumulado
     * entre todos los inscritos en el gimnasio
     */
    public double getImporteTotal() {
        return totalAcumulado;
    }

    /**
     * Este método recibe 4 parámetros:
     * - sala: el nº de sala donde se hace la actividad
     * - tipo: el tipo de actividad, un carácter 'Y' yoga, 'P' pilates 'S' spinning
     * - horas y minutos : duración de la actividad en nº horas y minutos
     * - inscritos: el nº de personas inscritas en esa actividad en la sala
     * <p>
     * Por ej, tarificarClaseEnSala(4, 'P', 1, 5, 15) significa que en la sala 4 se hace
     * pilates, las clases duran 1 hora y  5 minutos y se han inscrito en esta sala 15
     * <p>
     * A partir de esta información el método debe calcular:
     * - total inscritos por tipo de actividad (independientemente de la sala)
     * - la sala y el máximo nº de inscritos en spinning en esa sala
     * <p>
     * Utiliza   una sentencia switch  para analizar el tipo de actividad
     * <p>
     * También el método calculará y mostrará posteriormente la siguiente información:
     * - el precio de la clase en la sala (basándose en su duración). En el ejemplo anterior
     * la clase de pilates duraba 1 hora y 5 minutos . Como el total de minutos
     * de duración es 65 su precio será: 3 + 0,20 *  4 = 3,8 ya que son 4 los períodos completos de
     * 15 minutos que hay
     * - nº de veces que la clase se ofertará en la sala (dependerá de su duración. No olvidar que entre clase
     * y clase siempre hay un descanso)
     * - la hora de finalización de la última clase (hora y minutos) - !!Ver resultados de ejecución!!
     * - el método además irá registrando el importe total que lleva acumulado el gimnasio entre todas
     * las personas inscritas
     */
    public void tarificarClaseEnSala(int sala, char tipo, int horas, int minutos, int inscritos) {

        System.out.println("Sala numero: " + sala);

        //Analizar tipo de actividad
        //Apuntar inscritos por cada actividad
        switch (tipo) {
            case YOGA:
                inscritosYoga += inscritos;
                System.out.println("Actividad: YOGA");
                break;
            case PILATES:
                inscritosPilates += inscritos;
                System.out.println("Actividad: PILATES");
                break;
            case SPINNING:
                inscritosSpinning += inscritos;
                System.out.println("Actividad: SPINNING");
                if (inscritos > inscritosMaximoSpinning) {
                    salaMaximoSpinning = sala;
                    inscritosMaximoSpinning = inscritos;
                }
                break;
        }


        // Calcular el precio de cada clase = 3€ base + (horas y minutos)/15
        // Solo se cuentan si pasan 15 minutos completos

        int minutosClase = horas * 60 + minutos;
        double precioClase = PRECIO_BASE + (PRECIO_15MINUTOS * (minutosClase / 15));
        totalAcumulado += precioClase * inscritos;

        //Número de veces que se oferta la clase en un dia

        int minsClasesTotales = (HORA_ULTIMA_CLASE * 60 + MINUTOS_ULTIMA_CLASE) - (HORA_PRIMERA_CLASE * 60 + MINUTOS_PRIMERA_CLASE);
        int numClases = minsClasesTotales / (minutosClase + DESCANSO);



        //Unidad3.Hora a la que acaba la ultima clase
        int horaUltimaClase = HORA_PRIMERA_CLASE + (horas * numClases);
        int minutosUltimaClase = MINUTOS_PRIMERA_CLASE + (minutos * numClases) + (DESCANSO * numClases);
        while (minutosUltimaClase >= 60) {
            horaUltimaClase++;
            minutosUltimaClase -= 60;
        }


        System.out.println("Longitud: " + minutosClase + " minutos");
        System.out.println("Descanso: " + DESCANSO + " minutos");
        System.out.println("Precio de la clase: " + precioClase + " euros");
        System.out.println("Clase ofertada en la sala: " + numClases + " veces al dia");
        System.out.println("La ultima clase termina a las: " + horaUltimaClase + " y " + minutosUltimaClase + " minutos");
        System.out.println("Total inscritos en la sala: " + inscritos + " inscritos");


    }

    /**
     * nº sala en la que hay más inscritos para spinning
     */
    public int getSala() {
        return salaMaximoSpinning;
    }

    /**
     * Devuelve el nombre de la actividad con más inscritos independientemente de la sala
     */
    public String getActividad() {
        if (inscritosYoga > inscritosPilates && inscritosYoga > inscritosSpinning) {
            return "Yoga";
        } else if (inscritosPilates > inscritosYoga && inscritosPilates > inscritosSpinning) {
            return "Pilates";
        } else if (inscritosSpinning > inscritosYoga && inscritosSpinning > inscritosPilates) {
            return "Spinning";
        } else {
            if (inscritosYoga == inscritosPilates) {
                return "Yoga y pilates tienen el número de inscritos";
            }
            if (inscritosYoga == inscritosSpinning) {
                return "Yoga y spinning tienen el mismo número de inscritos";
            }
            return "Pilates y spinning tienen el mismo número de inscritos";
        }

    }

}
