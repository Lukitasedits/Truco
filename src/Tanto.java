public class Tanto {
    String carta1, carta2, carta3;
    String palo1, palo2, palo3;
    Integer tantoCarta1, tantoCarta2, tantoCarta3;

    public Tanto(String carta1, String carta2, String carta3, String palo1, String palo2, String palo3) {
        this.carta1 = carta1;
        this.carta2 = carta2;
        this.carta3 = carta3;
        this.palo1 = palo1;
        this.palo2 = palo2;
        this.palo3 = palo3;
    }

    public void valorTanto() {
        tantoCarta1 = Integer.valueOf(carta1.substring(0, (carta1.indexOf(" "))));
        tantoCarta2 = Integer.valueOf(carta2.substring(0, (carta2.indexOf(" "))));
        tantoCarta3 = Integer.valueOf(carta3.substring(0, (carta3.indexOf(" "))));
        if (tantoCarta1 >= 10 && tantoCarta1 <= 12) {
            tantoCarta1 = 0;
        }
        if (tantoCarta2 >= 10 && tantoCarta2 <= 12) {
            tantoCarta2 = 0;
        }
        if (tantoCarta3 >= 10 && tantoCarta3 <= 12) {
            tantoCarta3 = 0;
        }
    }

    public int tantoTotal(){
        int envido = 0;
        if(palo1.equals(palo2) && palo1.equals(palo3)){ //si las tres cartas son del mismo palo
            if (tantoCarta1 <= tantoCarta2 && tantoCarta1 <= tantoCarta3){
                envido = tantoCarta2 + tantoCarta3 + 20;
            }
            else if (tantoCarta2 <= tantoCarta1 && tantoCarta2 <= tantoCarta3){
                envido = tantoCarta1 + tantoCarta3 + 20;
            } else {
                envido = tantoCarta1 + tantoCarta2 + 20;
            }
        } else { //si no..
            if(palo1.equals(palo2)){
                envido = tantoCarta1 + tantoCarta2 + 20;
            }
            if(palo1.equals(palo3)){
                envido = tantoCarta1 + tantoCarta3 + 20;
            }
            if(palo2.equals(palo3)){
                envido = tantoCarta2 + tantoCarta3 + 20;
            }
        }
        return envido;
    }

    public Integer getTantoCarta1() {
        return tantoCarta1;
    }

    public Integer getTantoCarta2() {
        return tantoCarta2;
    }

    public Integer getTantoCarta3() {
        return tantoCarta3;
    }
}
