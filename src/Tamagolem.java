import java.util.Collection;
import java.util.Queue;
import java.util.Iterator;
import java.util.ArrayList;

public class Tamagolem
{
    private Queue<Pietra> pietre;
    private int vita;

    /**
     * controllo che il tamagolem abbia hp
     * @return ritorno falso se il golem è morto
     */
    private boolean isAlive(){
        if(vita==0)
            return false;
        return true;
    }

    Tamagolem(){//costruttore
        pietre = new Queue<Pietra>();
        vita = Main.VITA_TAMAGOLEM;

    }

    private void sceltaPietre(ArrayList<Pietra> sassi){//aggiungo le pietre scelte a una queue di pietre
        for (ArrayList<Pietra> x: sassi) {
            pietre.add(x);
        }
    }

    public int getVita{
        return vita;
    }

    private Pietra scagliaPietre(){
        //da pensare beneeeeeee
    }

    public void getDanno(int danno){
        vita-= danno;
    }


}
