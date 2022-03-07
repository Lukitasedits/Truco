import java.util.Arrays;
import java.util.Random;

public class PropiedadesCarta {
    String carta;
//    String paloCarta;
    int numeroCarta;
    int cartaValor;
    String[] arrayPalos = {"espada", "oro", "copa", "basto"};
    String matrizCartas[][] = new String[4][10];
    String arrayCartas[] = new String[40];

    public void ordenCartas() {
        String palo = "";
        int numero = 1;
        int filas = 4;
        int columnas = 10;
        int contador = 1;

        int h = 0;
        for (int i = 0; i < filas; i++) { //relleno de matriz "matrizCartas"
            for (int j = 0; j < columnas; j++) {
                matrizCartas[i][j] = numero + " de " + arrayPalos[i];
                numero++;
                if (numero == 8) {
                    numero = 10;
                }
                if (numero > 12) {
                    numero = 1;
                }
                arrayCartas[h] = matrizCartas[i][j];

                h++;
            }
        }
        int arrayOrden[] = {1,9,5,37,33,29,3,23,19,15,14,10,6,38,34,30,4,24,20,16,13,11,7,39,35,31,27,25,21,17,2,12, 8,40,36,32,28,26,22,18};
        String aux;

        int aux2 = 0;
        for(int i = 0; i < 40-1; i++){ //Orden arrayCartas
            for(int j = 0; j < 40-1; j++){
                if(arrayOrden[j] > arrayOrden[j+1]){
                    aux = arrayCartas[j];
                    arrayCartas[j] = arrayCartas[j+1];
                    arrayCartas[j+1] = aux;
                    aux2 = arrayOrden[j];
                    arrayOrden[j] = arrayOrden[j+1];
                    arrayOrden[j+1] = aux2;
                }
            }
        }
    }




     public void reparto(){
        Random randomObj = new Random();
        int posicionCarta = randomObj.nextInt(40); //numero aleatorio entre 0 y 39
        numeroCarta = posicionCarta + 1; //el numero de la carta va de un orden de 1 a 40
        carta = arrayCartas[posicionCarta]; //almaceno en "carta" la carta que corresponde

        if(numeroCarta <= 4){ // 1-2-3-4
            cartaValor = numeroCarta;
        } else if(numeroCarta >= 5 && numeroCarta <= 8){ //5-6-7-8
            cartaValor = 5;
        } else if (numeroCarta >= 9 && numeroCarta <= 12){ //9-10-11-12
            cartaValor = 6;
        } else if(numeroCarta == 13 || numeroCarta == 14){ //13 - 14
            cartaValor = 7;
        } else if(numeroCarta >= 15 && numeroCarta <= 18){ //15-16-17-18
            cartaValor = 8;
        } else if(numeroCarta >= 19 && numeroCarta <= 22){ //19-20-21-22
            cartaValor = 9;
        } else if(numeroCarta >= 23 && numeroCarta <= 26){ //23-24-25-26
            cartaValor = 10;
        } else if(numeroCarta == 27 || numeroCarta == 28){ //27-28
            cartaValor = 11;
        } else if(numeroCarta >= 29 && numeroCarta <= 32){ //29-30-31-32
            cartaValor = 12;
        } else if(numeroCarta >= 33 && numeroCarta <= 36){ //33-34-35-36
            cartaValor = 13;
        } else if(numeroCarta >= 37 && numeroCarta <= 40){ //37-38-39-40
            cartaValor = 14;
        }
    }


    public String palo(){
        String paloCarta = carta.substring(carta.lastIndexOf(" ")+1);
        return paloCarta;
    }

    public String getCarta() { //retorna el nombre de la carta
        return carta;
    }

    public int getCartaValor() { //retorna el valor de la carta
        return cartaValor;
    }
}
