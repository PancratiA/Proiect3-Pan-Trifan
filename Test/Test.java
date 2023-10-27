package Test;
import java.util.ArrayList;

public class Test {
    private ArrayList<Intrebare> intrebari;

    public Test() {
        intrebari = new ArrayList<>();
    }

    public void addIntrebare(Intrebare intrebare) {
        intrebari.add(intrebare);
    }

    public ArrayList<Intrebare> getIntrebari() {
        return intrebari;
    }
}

