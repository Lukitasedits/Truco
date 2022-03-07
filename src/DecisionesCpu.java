import java.util.Arrays;

public class DecisionesCpu {
    String carta1, carta2, carta3;
    int valorCarta1, valorCarta2, valorCarta3;
    int ronda;
    String[] arrayCartasCpu;
    int[] arrayValores;
    String eleccionCpu/*, mano*/;
    String eleccion1, eleccion2, eleccion3 = "";
    int valorEleccion1, valorEleccion2, valorEleccion3 ;
    int valorEleccionPlayer;
    boolean primeraValeOro;


    public DecisionesCpu(String carta1, String carta2, String carta3, int valorCarta1, int valorCarta2, int valorCarta3, int ronda, int valorEleccionPlayer/*, String mano*/) {
        this.carta1 = carta1;
        this.carta2 = carta2;
        this.carta3 = carta3;
        this.valorCarta1 = valorCarta1;
        this.valorCarta2 = valorCarta2;
        this.valorCarta3 = valorCarta3;
        this.ronda = ronda;
        this.valorEleccionPlayer = valorEleccionPlayer;
//        this.mano = mano;
    }

    public void setEleccion1(String eleccion1) {
        this.eleccion1 = eleccion1;
    }
    public void setEleccion2(String eleccion2) {
        this.eleccion2 = eleccion2;
    }
    public void setEleccion3(String eleccion3) {
        this.eleccion3 = eleccion3;
    }

    public void llenadoArrays() {
        switch (ronda) {
            case 1:
                arrayCartasCpu = new String[]{carta1, carta2, carta3};
                arrayValores = new int[]{valorCarta1, valorCarta2, valorCarta3};
                break;
            case 2:
                if (eleccion1.equals(carta1)) {
                    arrayCartasCpu = new String[]{carta2, carta3};
                    arrayValores = new int[]{valorCarta2, valorCarta3};
                } else if (eleccion1.equals(carta2)) {
                    arrayCartasCpu = new String[]{carta1, carta3};
                    arrayValores = new int[]{valorCarta1, valorCarta3};
                } else if (eleccion1.equals(carta3)) {
                    arrayCartasCpu = new String[]{carta1, carta2};
                    arrayValores = new int[]{valorCarta1, valorCarta2};
                }
                break;
            case 3:
                if (eleccion1.equals(carta1) && eleccion2.equals(carta2) || eleccion1.equals(carta2) && eleccion2.equals(carta1)) {
                    arrayCartasCpu = new String[]{carta3};
                    arrayValores = new int[]{valorCarta3};
                } else if (eleccion1.equals(carta1) && eleccion2.equals(carta3) || eleccion1.equals(carta3) && eleccion2.equals(carta1)) {
                    arrayCartasCpu = new String[]{carta2};
                    arrayValores = new int[]{valorCarta2};
                } else if (eleccion1.equals(carta2) && eleccion2.equals(carta3) || eleccion1.equals(carta3) && eleccion2.equals(carta2)) {
                    arrayCartasCpu = new String[]{carta1};
                    arrayValores = new int[]{valorCarta1};
                }
        }
    }

    public void ordenamientoArrays(){
        if(ronda == 1) {
            String aux;
            int aux2 = 0;
            for (int i = 0; i < arrayValores.length - 1; i++) {
                for (int j = 0; j < arrayValores.length - 1; j++) {
                    if (arrayValores[j] > arrayValores[j + 1]) {
                        aux = arrayCartasCpu[j];
                        arrayCartasCpu[j] = arrayCartasCpu[j + 1];
                        arrayCartasCpu[j + 1] = aux;
                        aux2 = arrayValores[j];
                        arrayValores[j] = arrayValores[j + 1];
                        arrayValores[j + 1] = aux2;
                    }
                }
            }
        } else if(ronda == 2){
            int aux;
            String aux2;
            if(arrayValores[0] > arrayValores[1]){
                aux = arrayValores[0];
                arrayValores[0] = arrayValores[1];
                arrayValores[1] = aux;
                aux2 = arrayCartasCpu[0];
                arrayCartasCpu[0] = arrayCartasCpu[1];
                arrayCartasCpu[1] = aux2;

            }
        }
    }

    @Override
    public String toString() {
        return "DecisionesCpu{" +
                "arrayCartasCpu=" + Arrays.toString(arrayCartasCpu) +
                ", arrayValores=" + Arrays.toString(arrayValores) +
                '}';
    }

    public void ronda1Mano(){
        for(int i = 0; i < 3; i++){
            if(arrayValores[i] == 5 || arrayValores[i] == 4){
                setEleccion1(arrayCartasCpu[i]);
                valorEleccion1 = arrayValores[i];
            }
            if(valorEleccion1 != 5 && valorEleccion1 != 4){
                setEleccion1(arrayCartasCpu[i]);
                valorEleccion1 = arrayValores[i];
            }
        }
    }

    public void ronda1Pie(){
        for(int i = 0; i < 3; i++){
            if((arrayValores[0] <= 4 || arrayValores[1] <= 4 || arrayValores[2] <= 4) && arrayValores[i] == valorEleccionPlayer){
                setEleccion1(arrayCartasCpu[i]);
                valorEleccion1 = arrayValores[i];
                break;
            } else {
                if(valorEleccionPlayer > arrayValores[i]){
                    setEleccion1(arrayCartasCpu[i]);
                    valorEleccion1 = arrayValores[i];
                } else if(arrayValores[0] >= valorEleccionPlayer && arrayValores[1] >= valorEleccionPlayer && arrayValores[2] >= valorEleccionPlayer){
                    setEleccion1(arrayCartasCpu[i]);
                    valorEleccion1 = arrayValores[i];
                }
            }
        }
    }

    public void ronda2Mano(){
        primeraValeOro = true;
        for(int i = 0; i < 2; i++){
            eleccion2 = arrayCartasCpu[i];
            valorEleccion2 = arrayValores[i];
        }
    }

    public void ronda2Pie(){
        for(int i = 0; i < 2 ; i++) {
            if (arrayValores[i] < valorEleccionPlayer){
                setEleccion2(arrayCartasCpu[i]);
                valorEleccion2 = arrayValores[i];
            } else if(arrayValores[0] >= valorEleccionPlayer && arrayValores[1] >= valorEleccionPlayer) {
                setEleccion2("Mazo");
            }
        }
    }

    public void ronda3Mano(){
        setEleccion3(arrayCartasCpu[0]);
        valorEleccion3 = arrayValores[0];
    }

    public void ronda3Pie(){
        if(valorEleccionPlayer > arrayValores[0] || valorEleccionPlayer == arrayValores[0] && primeraValeOro){
            setEleccion3(arrayCartasCpu[0]);
            valorEleccion3 = arrayValores[0];
        } else {
            setEleccion3("Mazo");
        }
    }


    public String getEleccion1() {
        return eleccion1;
    }

    public String getEleccion2() {
        return eleccion2;
    }

    public String getEleccion3() {
        return eleccion3;
    }

    public int getValorEleccion1() {
        return valorEleccion1;
    }

    public int getValorEleccion2() {
        return valorEleccion2;
    }

    public int getValorEleccion3() {
        return valorEleccion3;
    }
}
