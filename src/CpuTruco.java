import java.util.Random;

public class CpuTruco {
    boolean cantoTrucoCpu;
    public void formulaTrucoCpu(){
        Random randomObj = new Random();
        int randomInt = randomObj.nextInt(3);
        switch (randomInt){
            case 0: cantoTrucoCpu = false;
            case 1: cantoTrucoCpu = false;
                    break;
            case 2: cantoTrucoCpu = true;
        }
    }

    public boolean isCantoTrucoCpu() {
        return cantoTrucoCpu;
    }
}
