import java.util.Scanner;

public class Hilo {
    int valorCarta1, valorCarta2, valorCarta3;
    String carta1, carta2, carta3;
    String borrarTexto;
    String respuestaPlayer;
    Scanner entrada = new Scanner(System.in);
    int valorRespuesta;
    String quienTieneElCanto;
    boolean trucoCantado;

    public Hilo(int valorCarta1, int valorCarta2, int valorCarta3, String carta1, String carta2, String carta3) {
        this.valorCarta1 = valorCarta1;
        this.valorCarta2 = valorCarta2;
        this.valorCarta3 = valorCarta3;
        this.carta1 = carta1;
        this.carta2 = carta2;
        this.carta3 = carta3;
        this.quienTieneElCanto = quienTieneElCanto;
    }

    public void hiloPlayer(String carta1, String carta2, String carta3, int ronda, String texto, boolean trucoCantado) {

        System.out.println(texto);
        int decisionPlayer = entrada.nextInt();
        if (decisionPlayer == 1 && texto.contains(carta1)) {
            borrarTexto = carta1 + " (1), ";
            respuestaPlayer = carta1;
        } else if (decisionPlayer == 2 && texto.contains(carta2)) {
            borrarTexto = carta2 + " (2), ";
            respuestaPlayer = carta2;
        } else if (decisionPlayer == 3 && texto.contains(carta3)) {
            borrarTexto = carta3 + " (3), ";
            respuestaPlayer = carta3;
        } else if (decisionPlayer == 4 && texto.contains("Truco")) {
            borrarTexto = "";
            respuestaPlayer = "Truco";
        } else if (decisionPlayer == 4 && texto.contains("Retruco")) {
            borrarTexto = "";
            respuestaPlayer = "Retruco";
        } else if (decisionPlayer == 4 && texto.contains("Vale Cuatro")){
            borrarTexto = "";
            respuestaPlayer = "Vale Cuatro";
        }else if (decisionPlayer == 5 && texto.contains("Envido") && ronda == 1 && trucoCantado == false) {
            borrarTexto = " Envido (5),";
            respuestaPlayer = "Envido";
        } else if (decisionPlayer == 6) {
           respuestaPlayer = "Mazo";
        }

    }

    public void respuestaAValor(){
        if(respuestaPlayer.equals(carta1)){
            valorRespuesta = valorCarta1;
        } else if (respuestaPlayer.equals(carta2)){
            valorRespuesta = valorCarta2;
        } else if( respuestaPlayer.equals(carta3)){
            valorRespuesta = valorCarta3;
        }
    }

    public int getValorRespuesta() {
        return valorRespuesta;
    }

    public String getBorrarTexto() {
        return borrarTexto;
    }

    public String getRespuestaPlayer() {
        return respuestaPlayer;
    }

}
