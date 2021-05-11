import it.unibs.fp.mylib.InputDati;

import java.util.*;

public class Tamagolem
{
    private static final String CARICAPIETRE="Devi caricare le pietre nel tuo golem, verranno scagliate nell'ordine di caricamento";

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
        pietre = new ArrayDeque<>();
        vita = Main.V;

    }

    private void creaDequePietre(){


        //richiamare sceltaPietre
    }

    public void sceltaPietre() // aggiungo le pietre scelte a una deque di pietre
    {
        System.out.println(CARICAPIETRE);// stringa: devi caricare le pietre nel tuo golem, verranno scagliate nell'ordine di caricamento


        // arraylist di N elementi con S\N valore che mi indica uante pietre di quell'elemento ho ancora nella scorta comune
        ArrayList<Integer> pietreTemp = new ArrayList<>();
        for (int i = 0; i < Main.N; i++)
            pietreTemp.add(Main.S/Main.N);

        // pietre da caricare
        for (int i = 0; i < Main.P; i++)
        {
            // elementi da cui posso scegliere per ogni pietra
            for (int j = 0; j < Main.N; j++)
                System.out.println("Puoi scegliere ancora "+ pietreTemp.get(j) + " di " + Main.getSasso(j).getTipo());

            // stringa scegli pietra
            int scelta=InputDati.leggiIntero("scegliele l'elemento della pietra che carico", 0, 9);
            // setto nella coda la pietra scelta, dal basso così avrò la prima pietra che sceglie in alto
            pietre.addLast(Main.getSasso(scelta));
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
    private Pietra scagliaPietre()
    {
       Pietra attuale = pietre.getFirst();//salvo in una variabile temporanea la pietra da ritornare
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

    public Pietra getPietra()
    {
        return pietre.getFirst();
    }
}
