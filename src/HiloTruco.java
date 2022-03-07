import java.util.Random;
import java.util.Scanner;

public class HiloTruco {
    Scanner entrada = new Scanner(System.in);
    String instancia;
    String preguntadorTruco;
    String responde;
    boolean querido = true;
    String respuesta;
    boolean subida;
    String ganadorRonda;
    int puntosEnJuegoTruco;
    boolean mazo;
    boolean finHilo = true;
    String quienTieneElCanto;

    public HiloTruco(String instancia, String preguntadorTruco) {
        this.instancia = instancia;
        this.preguntadorTruco = preguntadorTruco;
    }

    public void analizadorDePreguntas() {
        if (preguntadorTruco.equals("Player")) {
            responde = "Cpu";
        } else { //preguntador = cpu
            responde = "Player";
        }
    }

    public void respondeCpu() {
        if(responde.equals("Cpu")){
            Random randomObj = new Random();
            int randomInt = randomObj.nextInt(3);
            switch (randomInt) {
             case 0:
                 System.out.println("Cpu: Quiero!");
                 break;
             case 1:
                 System.out.println("Cpu: No Quiero!");
                 querido = false;
                 break;
             case 2: //subida
                 if (instancia.equals("Truco")){
                     System.out.println("Cpu: Quiero Retruco!");
                     subida = true;
                 } else if (instancia.equals("Retruco")) {
                     System.out.println("Cpu: Quiero Vale Cuatro!");
                         subida = true;
                 } else { //vale cuatro
                     int randomInt2 = randomObj.nextInt(2);
                     switch (randomInt2) {
                         case 0:
                         System.out.println("Cpu: Quiero!");
                         break;
                         case 1: System.out.println("Cpu: No Quiero!");
                         querido = false;
                        }
                    }
            }
        }
    }

    public void respondePlayer(){
        if(responde.equals("Player")) {
            int respuestaIntPlayer = 0;
            if (instancia.equals("Truco")) {
                System.out.println("Quiero (1), No Quiero (2), o Retruco (3)");
                respuestaIntPlayer = entrada.nextInt();
            } else if (instancia.equals("Retruco")) {
                System.out.println("Quiero (1), No quiero (2), o Vale Cuatro (3)");
                respuestaIntPlayer = entrada.nextInt();
            } else if (instancia.equals("Vale Cuatro")) {
                System.out.println("Quiero (1) o No Quiero (2)");
                respuestaIntPlayer = entrada.nextInt();
            }
            switch (respuestaIntPlayer) {
                case 2: //no quiero
                    querido = false;
                    break;
                case 3: //subida
                    subida = true;
            }
        }
    }

    public void analizadorRespuestas(){
        if(instancia.equals("Truco")){
            if(querido == false){
                mazo = true;
                puntosEnJuegoTruco = 1;
                ganadorRonda = preguntadorTruco;
            } else {
                if(subida == true){
                    instancia = "Retruco";
                    finHilo = false;
                } else {
                    puntosEnJuegoTruco = 2;
                }
            }
        } else if(instancia.equals("Retruco")){
            if(querido == false){
                mazo = true;
                puntosEnJuegoTruco = 2;
                ganadorRonda = preguntadorTruco;
            } else {
                if(subida){
                    instancia = "Vale Cuatro";
                    finHilo = false;
                } else {
                    puntosEnJuegoTruco = 3;
                }
            }
        } else if(instancia.equals("Vale Cuatro")){
            if(querido == false){
                mazo = true;
                puntosEnJuegoTruco = 3;
                ganadorRonda = preguntadorTruco;
            } else {
                puntosEnJuegoTruco = 4;
            }
        }
        if(!finHilo){
            preguntadorTruco = responde;
        } else {
            quienTieneElCanto = responde;
        }
    }

    public String getPreguntadorTruco() {
        return preguntadorTruco;
    }

    public String getInstancia() {
        return instancia;
    }

    public String getGanadorRonda() {
        return ganadorRonda;
    }

    public int getPuntosEnJuegoTruco() {
        return puntosEnJuegoTruco;
    }

    public String getQuienTieneElCanto() {
        return quienTieneElCanto;
    }

    public boolean isMazo() {
        return mazo;
    }

    public boolean isFinHilo() {
        return finHilo;
    }
}
