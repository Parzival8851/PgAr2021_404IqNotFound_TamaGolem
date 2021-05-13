import it.unibs.fp.mylib.InputDati;

import java.util.*;

public class Tamagolem
{
    private static final String CARICAPIETRE="Devi caricare le pietre nel tuo golem, verranno scagliate nell'ordine di caricamento\n";

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


    public void sceltaPietre() // aggiungo le pietre scelte a una deque di pietre
    {
        System.out.println(CARICAPIETRE);// stringa: devi caricare le pietre nel tuo golem, verranno scagliate nell'ordine di caricamento
        pietre.clear();

        // arraylist di N elementi con S\N valore che mi indica uante pietre di quell'elemento ho ancora nella scorta comune
        ArrayList<Integer> pietreTemp = new ArrayList<>();
        for (int i = 0; i < Main.N; i++)
            pietreTemp.add(Main.S/Main.N);

        // pietre da caricare
        for (int i = 0; i < Main.P; i++)
        {
            System.out.println("Pietra N°: "+(i+1)+" di "+Main.P);

            // elementi da cui posso scegliere per ogni pietra
            for (int j = 0; j < Main.N; j++)
                if (pietreTemp.get(j)>0) // mostro solo gli elementi in cui ho ancora pietre
                    System.out.println("Puoi scegliere ancora "+ pietreTemp.get(j) + " pietre di " + Main.getSasso(j).getTipo()+" ("+(Main.getSasso(j).getIndice())+")");


            // stringa scegli pietra
            int scelta=InputDati.leggiIntero("\nScegliere l'elemento della pietra che carico inserendo il numero tra parantesi", 0, Main.N-1);
            // setto nella coda la pietra scelta, dal basso così avrò la prima pietra che sceglie in alto
            pietre.addLast(Main.getSasso(scelta));
            // tolgo una pietra dall'elenco che posso scegliere
            pietreTemp.set(scelta, pietreTemp.get(scelta)-1);
        }

    }

    public int getVita(){
        return vita;
    }

    /**
     * Viene selezionata la prima pietra inserita nella deque e poi viene spostata in
     * ultima posizione nel caso il tamagolem dovesse rimanere in vita
     *
     */
    public void aggiornaPietra()
    {
       pietre.addLast(pietre.getFirst());//sposto la pietra in fondo
       pietre.removeFirst();//la rimuovo dalla posizione iniziale
    }

    public void danno(int danno){
        vita-= danno;
    }



    /**
     * il golem è morto, reinizzializzo la vita, pulisco le pietre per poterle
     * scegliere di nuovo e aggiorno roundMax
     */
    public void respawn()
    {
        this.vita=Main.V;
        pietre.clear();
        sceltaPietre();
    }

    public Pietra getPietra()
    {
        return pietre.getFirst();
    }
}
