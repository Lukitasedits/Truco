public class GanadorEnvido {
    int tantoCpu;
    int tantoPlayer;
    String mano;

    public GanadorEnvido(int tantoCpu, int tantoPlayer, String mano) {
        this.tantoCpu = tantoCpu;
        this.tantoPlayer = tantoPlayer;
        this.mano = mano;
    }

    public String quienGano(){
        String ganador = mano;
        if(tantoCpu > tantoPlayer) {
            ganador = "Cpu";
        } else if (tantoCpu < tantoPlayer){
            ganador = "Player";
        }
        return ganador;
    }
}
