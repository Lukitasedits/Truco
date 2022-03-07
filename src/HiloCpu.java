public class HiloCpu {
    String cartaPlayer1, cartaPlayer2, cartaPlayer3;
    int valorCartaPlayer1, valorCartaPlayer2, valorCartaPlayer3;
    String cartaCpu1, cartaCpu2, cartaCpu3;
    int valorCartaCpu1, valorCartaCpu2, valorCartaCpu3;
    int ronda;
    String respuestaPlayer;
    int valorRespuestaPlayer;
    String eleccionCpu1, eleccionCpu2;
    int tantoCpu;
    String mano;
    String respuestaCpu = "";
    int valorRespuestaCpu;
    boolean envidoCantado;
    String instancia = "";
    String quienTieneElCanto;
    boolean trucoCantado;

    public HiloCpu(String cartaPlayer1, String cartaPlayer2, String cartaPlayer3, int valorCartaPlayer1, int valorCartaPlayer2, int valorCartaPlayer3, String cartaCpu1, String cartaCpu2, String cartaCpu3, int valorCartaCpu1, int valorCartaCpu2, int valorCartaCpu3, int ronda, String respuestaPlayer, int tantoCpu, String mano,  boolean envidoCantado, String eleccionCpu1, String eleccionCpu2, String instancia, String quienTieneElCanto, boolean trucoCantado) {
        this.cartaPlayer1 = cartaPlayer1;
        this.cartaPlayer2 = cartaPlayer2;
        this.cartaPlayer3 = cartaPlayer3;
        this.valorCartaPlayer1 = valorCartaPlayer1;
        this.valorCartaPlayer2 = valorCartaPlayer2;
        this.valorCartaPlayer3 = valorCartaPlayer3;
        this.cartaCpu1 = cartaCpu1;
        this.cartaCpu2 = cartaCpu2;
        this.cartaCpu3 = cartaCpu3;
        this.valorCartaCpu1 = valorCartaCpu1;
        this.valorCartaCpu2 = valorCartaCpu2;
        this.valorCartaCpu3 = valorCartaCpu3;
        this.ronda = ronda;
        this.respuestaPlayer = respuestaPlayer;
        this.tantoCpu = tantoCpu;
        this.mano = mano;
        this.envidoCantado = envidoCantado;
        this.eleccionCpu1 = eleccionCpu1;
        this.eleccionCpu2 = eleccionCpu2;
        this.instancia = instancia;
        this.quienTieneElCanto = quienTieneElCanto;
        this.trucoCantado = trucoCantado;
    }

    public void analizadorDeRespuestaPlayer(){
        if(respuestaPlayer.equals(cartaPlayer1)){
            valorRespuestaPlayer = valorCartaPlayer1;
        } else if(respuestaPlayer.equals(cartaPlayer2)){
            valorRespuestaPlayer = valorCartaPlayer2;
        } else if(respuestaPlayer.equals(cartaPlayer3)){
            valorRespuestaPlayer = valorCartaPlayer3;
        }
        if(quienTieneElCanto == null || quienTieneElCanto.equals("Cpu")) {
            CpuTruco ct = new CpuTruco();
            ct.formulaTrucoCpu();
            boolean trucoCpu = ct.isCantoTrucoCpu();
            if (trucoCpu) {
                if (instancia.equals("")) {
                    respuestaCpu = "Truco";
                } else if (instancia.equals("Truco")) {
                    respuestaCpu = "Retruco";
                } else if (instancia.equals("Retruco")) {
                    respuestaCpu = "Vale Cuatro";
                }
            }
        }
    }



    public void cerebroCpu() {
        if(!respuestaCpu.equals("Truco") && !respuestaCpu.equals("Retruco") && !respuestaCpu.equals("Vale Cuatro")) {
            if (ronda == 1 && !envidoCantado && !trucoCantado) {
                DecisionEnvidoCpu dec = new DecisionEnvidoCpu(tantoCpu);
                dec.setPreguntadorInicialCpu();
                respuestaCpu = dec.getEleccionEnvidoCpu();
                if(!respuestaCpu.isEmpty()) {
                    System.out.println("Cpu: " + respuestaCpu + "!");
                }

            }
            if (respuestaCpu.isEmpty()){
                DecisionesCpu dc = new DecisionesCpu(cartaCpu1, cartaCpu2, cartaCpu3, valorCartaCpu1, valorCartaCpu2, valorCartaCpu3, ronda, valorRespuestaPlayer);
                dc.setEleccion1(eleccionCpu1);
                dc.setEleccion2(eleccionCpu2);
                dc.llenadoArrays();
                dc.ordenamientoArrays();
                if (ronda == 1) {
                    if (mano.equals("Cpu")) {
                        dc.ronda1Mano();
                    } else {
                        dc.ronda1Pie();
                    }
                    eleccionCpu1 = dc.getEleccion1();
                    respuestaCpu = dc.getEleccion1();
                    valorRespuestaCpu = dc.getValorEleccion1();
                } else if (ronda == 2) {
                    if (mano.equals("Cpu")) {
                        dc.ronda2Mano();
                    } else {
                        dc.ronda2Pie();
                    }
                    respuestaCpu = dc.getEleccion2();
                    valorRespuestaCpu = dc.getValorEleccion2();
                    eleccionCpu2 = dc.getEleccion2();
                } else {
                    if (mano.equals("Cpu")) {
                        dc.ronda3Mano();
                    } else {
                        dc.ronda3Pie();
                    }
                    respuestaCpu = dc.getEleccion3();
                    valorRespuestaCpu = dc.getValorEleccion3();
                }
                if(respuestaCpu.equals("Mazo")){
                    System.out.println("Cpu: Me voy al mazo");
                } else {
                    System.out.println("Cpu tiró un " + respuestaCpu);
                }
            }
        } else {
            System.out.println("Cpu: " + respuestaCpu + "!");
        }
    }

    public String getEleccionCpu1() {
        return eleccionCpu1;
    }

    public String getEleccionCpu2() {
        return eleccionCpu2;
    }

    public String getRespuestaCpu() {
        return respuestaCpu;
    }

    public int getValorRespuestaCpu() {
        return valorRespuestaCpu;
    }

}
