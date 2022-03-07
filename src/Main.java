import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    //entrada información player
        Scanner entrada = new Scanner(System.in);
        System.out.print("Dime tu nombre: ");
        String nombre = entrada.nextLine();
        String manoGeneral;
        int puntosTotalesPlayer = 0;
        int puntosTotalesCpu = 0;

        String arrayManoGeneral[] = {"Player", "Cpu"};
        do{
            for(int i = 0; i < 2; i++) {
                manoGeneral = arrayManoGeneral[i];
                UltraMain ultraMain = new UltraMain(manoGeneral, nombre);
                ultraMain.partida();
                ultraMain.contadorPuntos();
                puntosTotalesCpu += ultraMain.getPuntosCpu();
                puntosTotalesPlayer += ultraMain.getPuntosPlayer();
                System.out.println("========== Puntos Totales ===========");
                System.out.println("          " + nombre + ": " + puntosTotalesPlayer + " puntos");
                System.out.println("           " + "Cpu: " + puntosTotalesCpu + " puntos");
                System.out.println("=====================================");

                if(puntosTotalesCpu == 30 && puntosTotalesPlayer == 30){
                    break;
                }
            }
        } while(puntosTotalesCpu < 30 && puntosTotalesPlayer < 30);

        if(puntosTotalesCpu >= 30){
            System.out.println("EL GANADOR ES CPU!!!");
        } else {
            System.out.println("GANASTE!! FELICIDADES " + nombre.toUpperCase() + "!!!");
        }



    }
}
