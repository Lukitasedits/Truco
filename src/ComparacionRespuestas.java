public class ComparacionRespuestas {
    int valorRespuestaPlayer;
    int valorRespuestaCpu;
    String mano;
    String manoGeneral;
    String ganadorRonda;

    public ComparacionRespuestas(int valorRespuestaPlayer, int valorRespuestaCpu, String manoGeneral) {
        this.valorRespuestaPlayer = valorRespuestaPlayer;
        this.valorRespuestaCpu = valorRespuestaCpu;
        this.manoGeneral = manoGeneral;
    }

    public void comparador(){
        if(valorRespuestaCpu > valorRespuestaPlayer){
            mano = "Player";
            ganadorRonda = "Player";
        } else if (valorRespuestaCpu < valorRespuestaPlayer){
            mano = "Cpu";
            ganadorRonda = "Cpu";
        } else {
            mano = manoGeneral;
            ganadorRonda = "Empate";

        }
    }

    public String getMano() {
        return mano;
    }

    public String getGanador() {
        return ganadorRonda;
    }
}
