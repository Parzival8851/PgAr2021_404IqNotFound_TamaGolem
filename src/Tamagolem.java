import java.util.*;

public class Tamagolem
{
    private Deque<Pietra> pietre;
    private int vita;


    /**
     * controllo che il tamagolem abbia hp
     * @return ritorno falso se il golem è morto
     */
    private boolean isAlive()
    {
        if(vita<=0)
            return false;
        return true;
    }

    public Tamagolem(){//costruttore
        pietre = new ArrayDeque<Pietra>();
        vita = Main.V;

    }

    private void creaDequePietre(){
    //richiamare sceltaPietre
    }

    private void sceltaPietre(Deque<Pietra> sassi){//aggiungo le pietre scelte a una deque di pietre
        for (Pietra x: sassi) {
            //completare
            pietre.addFirst(x);
        }
    }

    public int getVita(){
        return vita;
    }

    /**
     * Viene selezionata la prima pietra inserita nella deque e poi viene spostata in
     * ultima posizione nel caso il tamagolem dovesse rimanere in vita
     * @return la pietra lanciata
     */
    private Pietra scagliaPietre(){
       Pietra attuale = pietre.getFirst();//salco in una variabile temporanea la pietra da ritornare
       pietre.addLast(pietre.getFirst());//sposto la pietra in fondo
       pietre.removeFirst();//la rimuovo dalla posizione iniziale
       return attuale;
    }

    public void danno(int danno){
        vita-= danno;
    }



    /**
     * il golem è morto, reinizzializzo la vita, pulisco le pietre per poterle
     * scegliere di nuovo e aggiorno roundMax
     */
    private void respawn()
    {
        this.vita=Main.V;
        pietre.clear();
        creaDequePietre();
    }
}
