public class DecisionEnvidoCpu {
    String eleccionEnvidoCpu = "";
    int tantoCpu;

    public DecisionEnvidoCpu(int tantoCpu) {
        this.tantoCpu = tantoCpu;
    }


    public void setPreguntadorInicialCpu() {

        if (tantoCpu == 0) {
            switch (Envido.random75()) {
                case 3:
                    eleccionEnvidoCpu = "Real Envido";
            }
        } else if (tantoCpu >= 20 && tantoCpu <= 24) {
            switch (Envido.random75()) {
                case 3:
                    eleccionEnvidoCpu = "Envido";
            }
        } else if (tantoCpu > 24 && tantoCpu <= 27) {
            switch (Envido.random50()) {
                case 1:
                    eleccionEnvidoCpu = "Envido";
            }
        } else if (tantoCpu > 27 && tantoCpu <= 30) {
            switch (Envido.random75()) {
                case 0:
                case 1:
                    eleccionEnvidoCpu = "Envido";
                    break;
                case 2:
                case 3:
                    eleccionEnvidoCpu = "Real Envido";
            }
        } else if (tantoCpu > 30) {
            switch (Envido.random75()) {
                case 1:
                case 2:
                    eleccionEnvidoCpu = "Envido";
                case 3:
                    eleccionEnvidoCpu = "Falta Envido";
            }
        }

    }

    public String getEleccionEnvidoCpu() {
        return eleccionEnvidoCpu;
    }
}

