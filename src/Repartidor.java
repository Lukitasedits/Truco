public class Repartidor {
    String cartaPlayer[] = new String[3];
    int cartaPlayerValor[] = new int[3];
    String cartaPlayerPalo[] = new String[3];
    String cartaCpu[] = new String[3];
    int cartaCpuValor[] = new int[3];
    String cartaCpuPalo[] = new String[3];
    int envidoTotalPlayer = 0;
    int envidoTotalCpu = 0;
    int tantoCartaPlayer[] = new int[3];
    int tantoCartaCpu[] = new int[3];

    public void repartoTotal() {
        //reparto Player
        PropiedadesCarta[] cartasPlayer = new PropiedadesCarta[3];
        do {
            for (int i = 0; i < 3; i++) {
                cartasPlayer[i] = new PropiedadesCarta();
                cartasPlayer[i].ordenCartas();
                cartasPlayer[i].reparto();
                cartaPlayer[i] = cartasPlayer[i].getCarta();
                cartaPlayerPalo[i] = cartasPlayer[i].palo();
                cartaPlayerValor[i] = cartasPlayer[i].getCartaValor();
            }
        } while(cartaPlayer[0].equals(cartaPlayer[1]) || cartaPlayer[1].equals(cartaPlayer[2]) || cartaPlayer[2].equals(cartaPlayer[0]));

        //reparto Cpu
        PropiedadesCarta[] cartasCpu = new PropiedadesCarta[3];
        do {
            for (int i = 0; i < 3; i++) {
                cartasCpu[i] = new PropiedadesCarta();
                cartasCpu[i].ordenCartas();
                cartasCpu[i].reparto();
                cartaCpu[i] = cartasCpu[i].getCarta();
                cartaCpuPalo[i] = cartasCpu[i].palo();
                cartaCpuValor[i] = cartasCpu[i].getCartaValor();
            }
        } while(cartaCpu[0].equals(cartaCpu[1]) || cartaCpu[1].equals(cartaCpu[2]) || cartaCpu[2].equals(cartaCpu[0]) ||
                cartaCpu[0].equals(cartaPlayer[0]) || cartaCpu[0].equals(cartaPlayer[1]) || cartaCpu[0].equals(cartaPlayer[2]) ||
                cartaCpu[1].equals(cartaPlayer[0]) || cartaCpu[1].equals(cartaPlayer[1]) || cartaCpu[1].equals(cartaPlayer[2]) ||
                cartaCpu[2].equals(cartaPlayer[0]) || cartaCpu[2].equals(cartaPlayer[1]) || cartaCpu[2].equals(cartaPlayer[2]));

        Tanto tantoPlayer = new Tanto(cartaPlayer[0], cartaPlayer[1], cartaPlayer[2], cartaPlayerPalo[0], cartaPlayerPalo[1], cartaPlayerPalo[2]);
        tantoPlayer.valorTanto();
        envidoTotalPlayer = tantoPlayer.tantoTotal();
        tantoCartaPlayer[0] = tantoPlayer.getTantoCarta1();
        tantoCartaPlayer[1] = tantoPlayer.getTantoCarta2();
        tantoCartaPlayer[2] = tantoPlayer.getTantoCarta3();
        Tanto tantoCpu = new Tanto(cartaCpu[0], cartaCpu[1], cartaCpu[2], cartaCpuPalo[0], cartaCpuPalo[1], cartaCpuPalo[2]);
        tantoCpu.valorTanto();
        tantoCartaCpu[0] = tantoCpu.getTantoCarta1();
        tantoCartaCpu[1] = tantoCpu.getTantoCarta2();
        tantoCartaCpu[2] = tantoCpu.getTantoCarta3();
        envidoTotalCpu = tantoCpu.tantoTotal();

    }

    public String getCartaPlayer1(){
        return cartaPlayer[0];
    }
    public String getCartaPlayer2(){
        return cartaPlayer[1];
    }
    public String getCartaPlayer3(){
        return cartaPlayer[2];
    }

    public int getCartaPlayerValor1(){
        return cartaPlayerValor[0];
    }
    public int getCartaPlayerValor2(){
        return cartaPlayerValor[1];
    }
    public int getCartaPlayerValor3(){
        return cartaPlayerValor[2];
    }

    public String getCartaCpu1(){
        return cartaCpu[0];
    }
    public String getCartaCpu2(){
        return cartaCpu[1];
    }
    public String getCartaCpu3(){
        return cartaCpu[2];
    }

    public int getCartaCpuValor1(){
        return cartaCpuValor[0];
    }
    public int getCartaCpuValor2(){
        return cartaCpuValor[1];
    }
    public int getCartaCpuValor3(){
        return cartaCpuValor[2];
    }

    public int getTantoCartaPlayer1(){
        return tantoCartaPlayer[0];
    }
    public int getTantoCartaPlayer2(){
        return tantoCartaPlayer[1];
    }
    public int getTantoCartaPlayer3(){
        return tantoCartaPlayer[2];
    }

    public int getTantoCartaCpu1(){
        return tantoCartaCpu[0];
    }
    public int getTantoCartaCpu2(){
        return tantoCartaCpu[1];
    }
    public int getTantoCartaCpu3(){
        return tantoCartaCpu[2];
    }


    public int getEnvidoTotalPlayer() {
        return envidoTotalPlayer;
    }

    public int getEnvidoTotalCpu() {
        return envidoTotalCpu;
    }

}
