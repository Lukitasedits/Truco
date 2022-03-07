import java.util.Random;
import java.util.Scanner;

public class Envido {
    public static int random50(){
        Random randomObj = new Random();
        int random = randomObj.nextInt(2);
        return random;
    }
    public static int random75(){
        Random randomObj = new Random();
        int random = randomObj.nextInt(4);
        return random;
    }
    Scanner entrada = new Scanner(System.in);
    int preguntaEnvido; // 1: Envido, 2: Real Envido, 3: Falta Envido
    int puntosEnvidoEnJuego = 0;
    String preguntador = "";
    boolean querido = true;
    boolean envidoTerminado;
    String preguntaTexto = "Elige entre: Envido (1), Real Envido (2) o Falta Envido (3)";
    int respuestaPlayerInt;
    boolean noQuerido;
    boolean subida = false;
    String cuantoSubio = "";
    String responde;
    String preguntaStr;
    String ganadorEnvidoNoQuerido = "";

    public void PreguntadorCpu(String respuestaCpu){
        preguntador = "Cpu";
        preguntaStr = respuestaCpu;
        if(respuestaCpu.equals("Envido")){
            preguntaEnvido = 1;
        } else if (respuestaCpu.equals("Real Envido")){
            preguntaEnvido = 2;
        } else if (respuestaCpu.equals("Falta Envido")){
            preguntaEnvido = 3;
        }
    }
    public void preguntadorInicialPlayer() {
        System.out.println(preguntaTexto);
        int eleccionPlayer = entrada.nextInt();
        preguntador = "Player";
        switch (eleccionPlayer) {
            case 1:
                preguntaEnvido = 1; //envido
                break;
            case 2:
                preguntaEnvido = 2; //real envido
                break;
            case 3:
                preguntaEnvido = 3; //falta envido
                break;
        }
    }

    public void analizadorDePreguntas(String instancia) {
        if (preguntador.equals("Player")) { //¿Quien preguntó?
            responde = "Cpu";
        } else { //preguntador = "Cpu"
            responde = "Player";
        }

        switch (preguntaEnvido) { //¿Qué preguntó?
            case 0: preguntaStr = instancia;
            break;
            case 1:
                preguntaStr = "Envido";
                break;
            case 2:
                preguntaStr = "Real Envido";
                break;
            case 3:
                preguntaStr = "Falta Envido";
        }
        preguntaEnvido = 0;
    }

    public void respuestaJugador() {
        if (responde.equals("Player")) {
            if (preguntaStr.equals("Envido")) {
                System.out.println("Envido (1), Real Envido (2), Falta Envido (3), quiero (4) o no quiero (5)");
                respuestaPlayerInt = entrada.nextInt();
                switch (respuestaPlayerInt) {
                    case 1:
                        cuantoSubio = "Envido";
                        subida = true;
                        break;
                    case 2:
                        cuantoSubio = "Real Envido";
                        subida = true;
                        break;
                    case 3:
                        cuantoSubio = "Falta Envido";
                        subida = true;
                        break;
                    case 4:
                        break;
                    case 5:
                        querido = false;
                }
            } else if (preguntaStr.equals("Real Envido")) {
                System.out.println("Falta Envido (1), quiero (2) o no quiero (3)");
                respuestaPlayerInt = entrada.nextInt();
                switch (respuestaPlayerInt) {
                    case 1:
                        cuantoSubio = "Falta Envido";
                        subida = true;
                        break;
                    case 2:
                        break;
                    case 3:
                        querido = false;
                }
            } else if (preguntaStr.equals("Falta Envido")) {
                System.out.println("quiero (2) o no quiero (3)");
                respuestaPlayerInt = entrada.nextInt();
                switch (respuestaPlayerInt) {
                    case 1:
                        break;
                    case 2:
                        querido = false;
                }
            }
            preguntador = "Player";
        }
    }

    public void setRespuestaCpu(int tantoCpu, int tantoCartaTirada) {
        int probabilidadRespuesta = (tantoCpu - (tantoCartaTirada / 2)) * 100 / 33;
        if (responde.equals("Cpu")) {
            if (preguntaStr.equals("Envido")) {
                if (probabilidadRespuesta > 40 && probabilidadRespuesta <= 60) { //quiero pero tal vez no
                    switch(random50()){
                        case 0: System.out.println("Cpu: Quiero!");
                                break;
                        case 1: System.out.println("Cpu: No quiero!");
                            querido = false;
                    }

                } else if (probabilidadRespuesta > 60 && probabilidadRespuesta <= 70){ //envido-envido
                    switch(random50()){
                        case 0: System.out.println("Cpu: Quiero!");
                        break;
                        case 1: //envido
                            System.out.println("Cpu: Envido!");
                            cuantoSubio = "Envido";
                            subida = true;
                    }
                } else if (probabilidadRespuesta > 70 && probabilidadRespuesta <= 90) {
                    System.out.println("Cpu: Real Envido!");
                    cuantoSubio = "Real Envido";
                    subida = true;
                } else if (probabilidadRespuesta > 90 && probabilidadRespuesta <= 100) {
                    System.out.println("Cpu: Falta Envido!");
                    cuantoSubio = "Falta Envido";
                    subida = true;
                } else {
                    System.out.println("Cpu: No quiero!");
                    querido = false;
                }

            } else if (preguntaStr.equals("Real Envido")) {
                if (probabilidadRespuesta > 70 && probabilidadRespuesta <= 90) {
                    System.out.println("Cpu: Quiero!");
                } else if (probabilidadRespuesta > 90 && probabilidadRespuesta <= 100) {
                    System.out.println("Cpu: Falta Envido!");
                    cuantoSubio = "Falta Envido";
                    subida = true;
                } else {
                    System.out.println("Cpu: No quiero!");
                    querido = false;
                }
            } else if (preguntaStr.equals("Falta Envido")) {
                if (probabilidadRespuesta > 90 && probabilidadRespuesta <= 100) {
                    System.out.println("Cpu: Quiero!");
                } else {
                    System.out.println("Cpu: No quiero!");
                    querido = false;
                }
            }
            preguntador = "Cpu";
        }
    }

    public boolean isSubida() {
        return subida;
    }

    boolean faltaEnvido;
    public void sumadorDePuntos(int puntosTotalesPlayer, int puntosTotalesCpu, String ganadorEnvido){
        if(querido) {
            if (preguntaStr.equals("Envido")) {
                puntosEnvidoEnJuego += 2;
            } else if (preguntaStr.equals("Real Envido")) {
                puntosEnvidoEnJuego += 3;
            } else if (preguntaStr.equals("Falta Envido")) {
                if (ganadorEnvido.equals("Player")) {
                    puntosEnvidoEnJuego = 30 - puntosTotalesCpu;
                } else {
                    puntosEnvidoEnJuego = 30 - puntosTotalesPlayer;
                }
            }
        } else {
            if(responde.equals("Player"))
            ganadorEnvidoNoQuerido = "Cpu";
            else{
                ganadorEnvidoNoQuerido = "Player";
            }
            puntosEnvidoEnJuego++;
            envidoTerminado = true;
        }
        if (subida == true) {
            preguntaStr = cuantoSubio;
        } else if (subida == false){
            envidoTerminado = true;
        }
            subida = false;
    }

    public String getGanadorEnvidoNoQuerido() {
        return ganadorEnvidoNoQuerido;
    }

    public String getPreguntaStr() {
        return preguntaStr;
    }

    public boolean isEnvidoTerminado() {
        return envidoTerminado;
    }

    public int getPuntosEnvidoEnJuego() {
        return puntosEnvidoEnJuego;
    }
}
