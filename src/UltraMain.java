import java.util.Scanner;

public class UltraMain {
    Scanner entrada = new Scanner(System.in);
    String manoGeneral;
    String nombre;
    String ganadorRonda[] = new String[3];
    int puntosTrucoEnJuego;
    int puntosEnvidoEnJuego;
    String ganadorEnvido;
    boolean mazo = false;
    String ganador;

    public UltraMain(String manoGeneral, String nombre) {
        this.manoGeneral = manoGeneral;
        this.nombre = nombre;
    }

    public void partida() {

        //repartida + tanto
        Repartidor repartidor = new Repartidor();
        repartidor.repartoTotal();

        //player
        String cartaPlayer1 = repartidor.getCartaPlayer1(), cartaPlayer2 = repartidor.getCartaPlayer2(), cartaPlayer3 = repartidor.getCartaPlayer3();
        int valorCartaPlayer1 = repartidor.getCartaPlayerValor1(), valorCartaPlayer2 = repartidor.getCartaPlayerValor2(), valorCartaPlayer3 = repartidor.getCartaPlayerValor3();
        int tantoCartaPlayer1 = repartidor.getTantoCartaPlayer1(), tantoCartaPlayer2 = repartidor.getTantoCartaPlayer2(), tantoCartaPlayer3 = repartidor.getTantoCartaPlayer3();
        int envidoPlayer = repartidor.getEnvidoTotalPlayer();
        //cpu
        String cartaCpu1 = repartidor.getCartaCpu1(), cartaCpu2 = repartidor.getCartaCpu2(), cartaCpu3 = repartidor.getCartaCpu3();
        int valorCartaCpu1 = repartidor.getCartaCpuValor1(), valorCartaCpu2 = repartidor.getCartaCpuValor2(), valorCartaCpu3 = repartidor.getCartaCpuValor3();
        int tantoCartaCpu1 = repartidor.getTantoCartaCpu1(), tantoCartaCpu2 = repartidor.getTantoCartaCpu2(), tantoCartaCpu3 = repartidor.getTantoCartaCpu3();
        int envidoCpu = repartidor.getEnvidoTotalCpu();

        GanadorEnvido ganadorDelEnvido = new GanadorEnvido(envidoCpu, envidoPlayer, manoGeneral);
        String ganadorEnvido = ganadorDelEnvido.quienGano();


        //juego
        String mano = manoGeneral;
        boolean trucoCantado = false;
        Hilo hilo = new Hilo(valorCartaPlayer1, valorCartaPlayer2, valorCartaPlayer3, cartaPlayer1, cartaPlayer2, cartaPlayer3);
        String borrado = "";
        String respuestaPlayer = "";
        String respuestaCpu = "";
        int ronda = 1;
        int puntosActualesCpu = 0;
        int puntoActualesPlayer = 0;
        boolean envidoTerminado = false;
        int primeraCartaTantoPlayer = 0;
        int valorRespuestaPlayer = 0;
        int valorRespuestaCpu = 0;
        boolean repiteCantoCpu = false;
        boolean repiteCantoPlayer = false;
        String eleccionCpu1 = "";
        String eleccionCpu2 = "";
        boolean terminaHiloTruco;
        String quienTieneElCanto = null;
        String preguntadorTruco;

        String texto = "Elige entre: " + cartaPlayer1 + " (1), " + cartaPlayer2 + " (2), " + cartaPlayer3 + " (3), Truco (4), Envido (5), o Mazo (6)";
        String textoPieGeneral = "Tienes: un " + cartaPlayer1 + ", un " + cartaPlayer2 + " y un " + cartaPlayer3;
        String instancia = "";
        do {
            System.out.println("[Empieza la ronda " + ronda + "]");
            if (mano.equals("Cpu")) {
                if(ronda == 1) {
                    System.out.println(textoPieGeneral);
                }
                do {
                    HiloCpu hiloCpu = new HiloCpu(cartaPlayer1, cartaPlayer2, cartaPlayer3, valorCartaPlayer1, valorCartaPlayer2, valorCartaPlayer3, cartaCpu1, cartaCpu2, cartaCpu3, valorCartaCpu1, valorCartaCpu2, valorCartaCpu3, ronda, respuestaPlayer, envidoCpu, mano, envidoTerminado, eleccionCpu1, eleccionCpu2, instancia, quienTieneElCanto, trucoCantado);
                    hiloCpu.analizadorDeRespuestaPlayer();
                    hiloCpu.cerebroCpu();
                    respuestaCpu = hiloCpu.getRespuestaCpu();
                    valorRespuestaCpu = hiloCpu.getValorRespuestaCpu();
                    eleccionCpu1 = hiloCpu.getEleccionCpu1();
                    eleccionCpu2 = hiloCpu.getEleccionCpu2();

                    if (respuestaCpu != null && (respuestaCpu.equals("Envido") || respuestaCpu.equals("Real Envido") || respuestaCpu.equals("Falta Envido") && envidoTerminado != false)) {
                        ConteoPuntosEnvido cpe = new ConteoPuntosEnvido(instancia, primeraCartaTantoPlayer, puntoActualesPlayer, puntosActualesCpu, ganadorEnvido, envidoCpu, envidoTerminado, "Cpu", respuestaPlayer, respuestaCpu, cartaPlayer1, cartaPlayer2, cartaPlayer3, tantoCartaPlayer1, tantoCartaPlayer2, tantoCartaPlayer3, nombre, envidoPlayer);
                        cpe.hiloEnvido();
                        repiteCantoCpu = true;
                        envidoTerminado = cpe.isEnvidoTerminado();
                        puntosEnvidoEnJuego = cpe.getPuntosEnJuego();
                        ganadorEnvido = cpe.getGanadorEnvido();
                    } else if (respuestaCpu != null && (respuestaCpu.equals("Truco") || respuestaCpu.equals("Retruco") || respuestaCpu.equals("Vale Cuatro"))) {
                        repiteCantoCpu = true;
                        instancia = respuestaCpu;
                        preguntadorTruco = "Cpu";
                        trucoCantado = true;
                        do {
                            HiloTruco ht = new HiloTruco(instancia, preguntadorTruco);
                            ht.analizadorDePreguntas();
                            ht.respondeCpu();
                            ht.respondePlayer();
                            ht.analizadorRespuestas();
                            instancia = ht.getInstancia();
                            terminaHiloTruco = ht.isFinHilo();
                            puntosTrucoEnJuego = ht.getPuntosEnJuegoTruco();
                            mazo = ht.isMazo();
                            ganadorRonda[ronda - 1] = ht.getGanadorRonda();
                            ganador = ht.getGanadorRonda();
                            quienTieneElCanto = ht.getQuienTieneElCanto();
                            preguntadorTruco = ht.getPreguntadorTruco();

                        } while (!terminaHiloTruco);
                    } else if (respuestaCpu != null && respuestaCpu.equals("Mazo")) {
                        ganador = "Player";
                        repiteCantoCpu = false;
                        mazo = true;
                    } else {
                        repiteCantoCpu = false;
                    }
                } while (repiteCantoCpu && !mazo);

                do {
                    if (!mazo) {
                        texto = texto.replace(borrado, "");
                        if (ronda == 2) {
                            texto = texto.replace(" Envido (5),", "");
                        }
                        if (quienTieneElCanto != null && quienTieneElCanto.equals("Cpu")) {
                            texto = texto.replace("Truco (4), ", ". ");
                            texto = texto.replace("Retruco (4), ", ". ");
                            texto = texto.replace("Vale Truco (4), ", ". ");
                        } else {
                            if (instancia.equals("")) {
                                texto = texto.replace(". ", "Truco (4), ");
                            } else if (instancia.equals("Truco")) {
                                texto = texto.replace(". ", "Retruco (4), ");
                                texto = texto.replace("Truco (4), ", "Retruco (4), ");
                            } else if (instancia.equals("Retruco")) {
                                texto = texto.replace(". ", "Vale Cuatro (4), ");
                                texto = texto.replace("Truco (4), ", "Vale Cuatro (4), ");
                                texto = texto.replace("Retruco (4), ", "Vale Cuatro (4), ");
                            }
                        }
                        hilo.hiloPlayer(cartaPlayer1, cartaPlayer2, cartaPlayer3, ronda, texto, trucoCantado);
                        hilo.respuestaAValor();
                        borrado = hilo.getBorrarTexto();
                        respuestaPlayer = hilo.getRespuestaPlayer();
                        valorRespuestaPlayer = hilo.getValorRespuesta();

                        if (respuestaPlayer != null && (respuestaPlayer.equals("Envido") || respuestaPlayer.equals("Real Envido") || respuestaPlayer.equals("Falta Envido"))) {
                            ConteoPuntosEnvido cpe = new ConteoPuntosEnvido(instancia, primeraCartaTantoPlayer, puntoActualesPlayer, puntosActualesCpu, ganadorEnvido, envidoCpu, envidoTerminado, "Player", respuestaPlayer, respuestaCpu, cartaPlayer1, cartaPlayer2, cartaPlayer3, tantoCartaPlayer1, tantoCartaPlayer2, tantoCartaPlayer3, nombre, envidoPlayer);
                            cpe.hiloEnvido();
                            repiteCantoPlayer = true;
                            envidoTerminado = cpe.isEnvidoTerminado();
                            puntosEnvidoEnJuego = cpe.getPuntosEnJuego();
                            ganadorEnvido = cpe.getGanadorEnvido();
                        } else if (respuestaPlayer != null && (respuestaPlayer.equals("Truco") || respuestaPlayer.equals("Retruco") || respuestaPlayer.equals("Vale Cuatro"))) {
                            repiteCantoPlayer = true;
                            instancia = respuestaPlayer;
                            preguntadorTruco = "Player";
                            trucoCantado = true;
                            do {
                                HiloTruco ht = new HiloTruco(instancia, preguntadorTruco);
                                ht.analizadorDePreguntas();
                                ht.respondeCpu();
                                ht.respondePlayer();
                                ht.analizadorRespuestas();
                                instancia = ht.getInstancia();
                                terminaHiloTruco = ht.isFinHilo();
                                puntosTrucoEnJuego = ht.getPuntosEnJuegoTruco();
                                mazo = ht.isMazo();
                                ganadorRonda[ronda - 1] = ht.getGanadorRonda();
                                ganador = ht.getGanadorRonda();
                                quienTieneElCanto = ht.getQuienTieneElCanto();
                                preguntadorTruco = ht.getPreguntadorTruco();
                            } while (!terminaHiloTruco);
                        } else if (respuestaPlayer != null && respuestaPlayer.equals("Mazo")) {
                            ganador = "Cpu";
                            repiteCantoPlayer = false;
                            mazo = true;
                        } else {
                            repiteCantoPlayer = false;
                        }
                    }
                } while (repiteCantoPlayer && !mazo);

            } else { //player es mano
                do {
                    texto = texto.replace(borrado, "");
                    if (ronda == 2) {
                        texto = texto.replace(" Envido (5),", "");
                    }
                    if (quienTieneElCanto != null && quienTieneElCanto.equals("Cpu")) {
                        texto = texto.replace("Truco (4), ", ". ");
                        texto = texto.replace("Retruco (4), ", ". ");
                        texto = texto.replace("Vale Truco (4), ", ". ");
                    } else {
                        if (instancia.equals("")) {
                            texto = texto.replace(". ", "Truco (4), ");
                        } else if (instancia.equals("Truco")) {
                            texto = texto.replace(". ", "Retruco (4), ");
                            texto = texto.replace("Truco (4), ", "Retruco (4), ");
                        } else if (instancia.equals("Retruco")) {
                            texto = texto.replace(". ", "Vale Cuatro (4), ");
                            texto = texto.replace("Truco (4), ", "Vale Cuatro (4), ");
                            texto = texto.replace("Retruco (4), ", "Vale Cuatro (4), ");
                        }
                    }
                    hilo.hiloPlayer(cartaPlayer1, cartaPlayer2, cartaPlayer3, ronda, texto, trucoCantado);
                    hilo.respuestaAValor();
                    borrado = hilo.getBorrarTexto();
                    respuestaPlayer = hilo.getRespuestaPlayer();
                    valorRespuestaPlayer = hilo.getValorRespuesta();

                    if (respuestaPlayer != null && (respuestaPlayer.equals("Envido") || respuestaPlayer.equals("Real Envido") || respuestaPlayer.equals("Falta Envido"))) {
                        ConteoPuntosEnvido cpe = new ConteoPuntosEnvido(instancia, primeraCartaTantoPlayer, puntoActualesPlayer, puntosActualesCpu, ganadorEnvido, envidoCpu, envidoTerminado, "Player", respuestaPlayer, respuestaCpu, cartaPlayer1, cartaPlayer2, cartaPlayer3, tantoCartaPlayer1, tantoCartaPlayer2, tantoCartaPlayer3, nombre, envidoPlayer);
                        cpe.hiloEnvido();
                        repiteCantoPlayer = true;
                        envidoTerminado = cpe.isEnvidoTerminado();
                        puntosEnvidoEnJuego = cpe.getPuntosEnJuego();
                        ganadorEnvido = cpe.getGanadorEnvido();
                    } else if (respuestaPlayer != null && (respuestaPlayer.equals("Truco") || respuestaPlayer.equals("Retruco") || respuestaPlayer.equals("Vale Cuatro"))) {
                        repiteCantoPlayer = true;
                        instancia = respuestaPlayer;
                        preguntadorTruco = "Player";
                        trucoCantado = true;
                        do {
                            HiloTruco ht = new HiloTruco(instancia, preguntadorTruco);
                            ht.analizadorDePreguntas();
                            ht.respondeCpu();
                            ht.respondePlayer();
                            ht.analizadorRespuestas();
                            instancia = ht.getInstancia();
                            terminaHiloTruco = ht.isFinHilo();
                            puntosTrucoEnJuego = ht.getPuntosEnJuegoTruco();
                            mazo = ht.isMazo();
                            ganadorRonda[ronda - 1] = ht.getGanadorRonda();
                            ganador = ht.getGanadorRonda();
                            quienTieneElCanto = ht.getQuienTieneElCanto();
                            preguntadorTruco = ht.getPreguntadorTruco();
                        } while (!terminaHiloTruco);

                    } else if (respuestaPlayer != null && respuestaPlayer.equals("Mazo")) {
                        ganador = "Cpu";
                        repiteCantoPlayer = false;
                        mazo = true;
                    } else {
                        repiteCantoPlayer = false;
                    }
                } while (repiteCantoPlayer && !mazo);
                do {
                    if (!mazo) {
                        HiloCpu hiloCpu = new HiloCpu(cartaPlayer1, cartaPlayer2, cartaPlayer3, valorCartaPlayer1, valorCartaPlayer2, valorCartaPlayer3, cartaCpu1, cartaCpu2, cartaCpu3, valorCartaCpu1, valorCartaCpu2, valorCartaCpu3, ronda, respuestaPlayer, envidoCpu, mano, envidoTerminado, eleccionCpu1, eleccionCpu2, instancia, quienTieneElCanto, trucoCantado);
                        hiloCpu.analizadorDeRespuestaPlayer();
                        hiloCpu.cerebroCpu();
                        respuestaCpu = hiloCpu.getRespuestaCpu();
                        valorRespuestaCpu = hiloCpu.getValorRespuestaCpu();
                        eleccionCpu1 = hiloCpu.getEleccionCpu1();
                        eleccionCpu2 = hiloCpu.getEleccionCpu2();

                        if (respuestaCpu != null && (respuestaCpu.equals("Envido") || respuestaCpu.equals("Real Envido") || respuestaCpu.equals("Falta Envido") && envidoTerminado != false)) {
                            ConteoPuntosEnvido cpe = new ConteoPuntosEnvido(instancia, primeraCartaTantoPlayer, puntoActualesPlayer, puntosActualesCpu, ganadorEnvido, envidoCpu, envidoTerminado, "Cpu", respuestaPlayer, respuestaCpu, cartaPlayer1, cartaPlayer2, cartaPlayer3, tantoCartaPlayer1, tantoCartaPlayer2, tantoCartaPlayer3, nombre, envidoPlayer);
                            cpe.hiloEnvido();
                            repiteCantoCpu = true;
                            envidoTerminado = cpe.isEnvidoTerminado();
                            puntosEnvidoEnJuego = cpe.getPuntosEnJuego();
                            ganadorEnvido = cpe.getGanadorEnvido();

                        } else if (respuestaCpu != null && (respuestaCpu.equals("Truco") || respuestaCpu.equals("Retruco") || respuestaCpu.equals("Vale Cuatro"))) {
                            repiteCantoCpu = true;
                            instancia = respuestaCpu;
                            preguntadorTruco = "Cpu";
                            trucoCantado = true;
                            do {
                                HiloTruco ht = new HiloTruco(instancia, preguntadorTruco);
                                ht.analizadorDePreguntas();
                                ht.respondeCpu();
                                ht.respondePlayer();
                                ht.analizadorRespuestas();
                                instancia = ht.getInstancia();
                                terminaHiloTruco = ht.isFinHilo();
                                puntosTrucoEnJuego = ht.getPuntosEnJuegoTruco();
                                mazo = ht.isMazo();
                                ganadorRonda[ronda - 1] = ht.getGanadorRonda();
                                ganador = ht.getGanadorRonda();
                                quienTieneElCanto = ht.getQuienTieneElCanto();
                                preguntadorTruco = ht.getPreguntadorTruco();

                            } while (!terminaHiloTruco);
                        } else if (respuestaCpu != null && respuestaCpu.equals("Mazo")) {
                            ganador = "Player";
                            repiteCantoCpu = false;
                            mazo = true;
                        } else {
                            repiteCantoCpu = false;
                        }
                    }
                } while (repiteCantoCpu && !mazo);
            }
            ComparacionRespuestas cp = new ComparacionRespuestas(valorRespuestaPlayer, valorRespuestaCpu, manoGeneral);
            cp.comparador();
            mano = cp.getMano();
            if (ganadorRonda[ronda - 1] == null) {
                ganadorRonda[ronda - 1] = cp.getGanador();
            }
            if(ganador == null) {
                System.out.println("El ganador de la ronda " + ronda + " es: " + ganadorRonda[ronda - 1]);
            }
            if(ronda == 2) { //si el ganador de la primera y segunda ronda son el mismo...
                if ((ganadorRonda[0].equals(ganadorRonda[1])) || (ganadorRonda[0].equals("Empate") && !ganadorRonda[1].equals("Empate"))) {
                    ganador = ganadorRonda[1];
                    break;
                }
            }

            ronda++;

            if ((respuestaPlayer != null && respuestaPlayer.equals("Mazo") || respuestaCpu != null && respuestaCpu.equals("Mazo"))) {
                mazo = true;
            }


        } while (mazo == false && ronda < 4);
    }
    int puntosPlayer, puntosCpu;
    int contadorVictoriasPlayer = 0, contadorVictoriasCpu = 0;
    public void contadorPuntos(){
        if(ganador != null && ganador.equals("Cpu")){
            puntosCpu = puntosTrucoEnJuego;
        } else if(ganador != null && ganador.equals("Player")){
            puntosPlayer = puntosTrucoEnJuego;
        } else {
            for (int i = 0; i < 3; i++) {
                if (ganadorRonda[i].equals("Cpu")) {
                    contadorVictoriasCpu++;
                } else if (ganadorRonda[i].equals("Player")) {
                    contadorVictoriasPlayer++;
                } else {
                    if (manoGeneral.equals("Player")) {
                        contadorVictoriasPlayer++;
                    } else {
                        contadorVictoriasCpu++;
                    }
                }
            }
            if (contadorVictoriasCpu > contadorVictoriasPlayer) {
                puntosCpu = puntosTrucoEnJuego;
            } else {
                puntosPlayer = puntosTrucoEnJuego;
            }
        }
        if(ganadorEnvido != null && ganadorEnvido.equals("Player")){
            puntosPlayer+= puntosEnvidoEnJuego;
        } else if (ganadorEnvido != null && ganadorEnvido.equals("Cpu")) {
            puntosCpu+= puntosEnvidoEnJuego;
        }
    }

    public int getPuntosPlayer() {
        return puntosPlayer;
    }

    public int getPuntosCpu() {
        return puntosCpu;
    }
}
