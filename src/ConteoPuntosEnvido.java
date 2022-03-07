public class ConteoPuntosEnvido {
    Envido  envido = new Envido();
    String instancia;
    int primeraCartaTantoPlayer;
    int puntoActualesPlayer;
    int puntosActualesCpu;
    String ganadorEnvido;
    int envidoCpu;
    boolean envidoTerminado;
    String cantadorEnvido;
    int puntosEnJuego;
    String respuestaCpu;
    String respuestaPlayer;
    String cartaPlayer1, cartaPlayer2, cartaPlayer3;
    int tantoCartaPlayer1, tantoCartaPlayer2, tantoCartaPlayer3;
    String nombre;
    int envidoPlayer;

    public ConteoPuntosEnvido(String instancia, int primeraCartaTantoPlayer, int puntoActualesPlayer, int puntosActualesCpu, String ganadorEnvido, int envidoCpu, boolean envidoTerminado, String cantadorEnvido, String respuestaPlayer, String respuestaCpu, String cartaPlayer1, String cartaPlayer2, String cartaPlayer3, int tantoCartaPlayer1, int tantoCartaPlayer2, int tantoCartaPlayer3, String nombre, int envidoPlayer) {
        this.instancia = instancia;
        this.primeraCartaTantoPlayer = primeraCartaTantoPlayer;
        this.puntoActualesPlayer = puntoActualesPlayer;
        this.puntosActualesCpu = puntosActualesCpu;
        this.ganadorEnvido = ganadorEnvido;
        this.envidoCpu = envidoCpu;
        this.envidoTerminado = envidoTerminado;
        this.cantadorEnvido = cantadorEnvido;
        this.respuestaPlayer = respuestaPlayer;
        this.respuestaCpu = respuestaCpu;
        this.cartaPlayer1 = cartaPlayer1;
        this.cartaPlayer2 = cartaPlayer2;
        this.cartaPlayer3 = cartaPlayer3;
        this.tantoCartaPlayer1 = tantoCartaPlayer1;
        this.tantoCartaPlayer2 = tantoCartaPlayer2;
        this.tantoCartaPlayer3 = tantoCartaPlayer3;
        this.nombre = nombre;
        this.envidoPlayer = envidoPlayer;
    }

    public void setPrimeraCartaTantoPlayer(){
        if(respuestaPlayer.equals(cartaPlayer1)){
            primeraCartaTantoPlayer = tantoCartaPlayer1;
        } else if(respuestaPlayer.equals(cartaPlayer2)){
            primeraCartaTantoPlayer = tantoCartaPlayer2;
        } else {
            primeraCartaTantoPlayer = tantoCartaPlayer3;
        }
    }

    public void hiloEnvido(){
         if(cantadorEnvido.equals("Player")){
            envido.preguntadorInicialPlayer();
        } else if (cantadorEnvido.equals("Cpu")) {
             envido.PreguntadorCpu(respuestaCpu);
         }
        do {
            envido.analizadorDePreguntas(instancia);
            envido.respuestaJugador();
            envido.setRespuestaCpu(envidoCpu, primeraCartaTantoPlayer);
            envido.sumadorDePuntos(puntoActualesPlayer, puntosActualesCpu, ganadorEnvido);
            instancia = envido.getPreguntaStr();
            envidoTerminado = envido.isEnvidoTerminado();
        } while(!envidoTerminado);
        puntosEnJuego = envido.getPuntosEnvidoEnJuego();
        if(!(envido.getGanadorEnvidoNoQuerido()).equals("")){
            ganadorEnvido = envido.getGanadorEnvidoNoQuerido();
        } else {
            if (cantadorEnvido.equals("Player") && ganadorEnvido.equals("Cpu")) {
                System.out.println(nombre + ": Tengo " + envidoPlayer + "!");
                System.out.println("Cpu: " + envidoCpu + " son mejores");
            } else if (cantadorEnvido.equals("Player") && ganadorEnvido.equals("Player")) {
                System.out.println(nombre + ": Tengo " + envidoPlayer + "! ");
                System.out.println("Cpu: Son buenas");
            } else if (cantadorEnvido.equals("Cpu") && ganadorEnvido.equals("Player")) {
                System.out.println("Cpu: Tengo " + envidoCpu + "! ");
                System.out.println(nombre + ": " + envidoPlayer + " son mejores");
            } else if (cantadorEnvido.equals("Cpu") && ganadorEnvido.equals("Cpu")) {
                System.out.println("Cpu: Tengo " + envidoCpu + "! ");
                System.out.println(nombre + ": Son buenas");
            }
            if(ganadorEnvido.equals("Player")) {
                System.out.println(nombre + " se lleva " + envido.getPuntosEnvidoEnJuego() + " pts.");
            } else {
                System.out.println("Cpu se lleva " + envido.getPuntosEnvidoEnJuego() + " pts.");
            }
        }
    }

    public boolean isEnvidoTerminado() {
        return envidoTerminado;
    }

    public int getPuntosEnJuego() {
        return puntosEnJuego;
    }

    public String getGanadorEnvido() {
        return ganadorEnvido;
    }
}
