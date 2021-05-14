import it.unibs.fp.mylib.EstrazioniCasuali;


import java.util.*;

public class Scontro
{
    public static final String PIETRE_UGUALI = "Le pietre scelte sono tutte uguali: Il giocatore ";
    public static final String NEW_PIETRE = " reimmette le sue pietre ";
    public static final String NUTRIRE = "Ora entrambi i giocatori nutriranno il loro golem con " + Main.P + " pietre per iniziare lo scontro\n\n";
    public static final String SCELTA_PIETRE = "!  Tocca a te scegliere le pietre:\n";
    public static final String OVERKILL = "MOSSA OVERKILL!\n";
    public static final int WAITIME = 2000;
    public static final String INTERAZIONE = "Nell'arena esiste un delicato equilibrio tra gli elementi: alcuni sono forti rispetto ad altri, ma soccombono contro altri ancora\nPer questo motivo i nostri golem lanciano le loro pietre sperando che \n la loro interazione faccia del danno all'altro e non a loro stessi\n\n";
    public static final String GOLEM = "Inizialmente i golem hanno " + Main.V + " Punti Vita e tramite lo scontro posso o fare danno o subirne\nOgni giocatore ha inizialmente "+Main.G+" Tamagolem\n";
    public static final String CICLO = "Le pietre che ogni giocatore sceglierà verranno scagliate nello stesso ordine fino a quando un giocatore non avrà ucciso tutti i golem dell'altro, diventando così\nVINCITORE!!\nCHE LA BATTAGLIA ABBIA INIZIO!!\n";
    public static final String SPAZI = "\n\n\n\n";
    private Giocatore[] player=new Giocatore[2];
    private Equilibrio equi;

    private static final String PRESS="\nPremere un tasto per continuare:";
    private static final String VINCE= "Vince ";
    private static final String GOLEMDI= ", il golem di ";
    private static final String GOLEMDI2= "Il golem di ";
    private static final String DANNO= " subisce un danno pari a ";
    private static final String LANCIA= " lancia una pietra di ";
    private static final String ELIMINATO="Viene killato il golem di ";
    private static final String RIMANGONO=" rimangono ";
    private static final String GOLEMSQUADRA=" golem in squadra ";
    private static final String GOLEMPLAYER="Al golem del giocatore ";
    private static final String PS=" punti vita";
    private static final String UGUALI="Le pietre dei due golem sono uguali";
    private static final String A = "A ";
    private static final String GG="Ottima battaglia!\n\n";
    private static final String GIOCATORE="Il giocatore ";
    private static final String HAVINTO=" ha vinto";
    private static final String HA=" ha ";

    /**
     * creo il terreno di gioco e comunico le regole
     * @param g1 giocatore 1
     * @param g2 giocatore 2
     * @param e equilibrio per le interazioni
     */
    public Scontro(Giocatore g1, Giocatore g2, Equilibrio e)
    {
        this.player[0] = g1;
        this.player[1] = g2;
        this.equi = e;
        System.out.println(INTERAZIONE);
        System.out.println(GOLEM);
        System.out.println(CICLO);
        premiPerContinuare();
    }

    /**
     * metodo che gestisce ciò che avviene per attuare uno scontro
     * quindi  nutro i golem con le pietre (scelte in un altro metodo),
     * gestisce i danni che provocano  le pietre,
     * permette di usare i golem dopo la morte del precedente
     */
    public void round() throws InterruptedException {
        // salvo i golem in uso per non doverli scrivere ogni volta
        Tamagolem t1 = player[0].getGolem();
        Tamagolem t2 = player[1].getGolem();

        //scegliere le pietre del golem
        System.out.println(NUTRIRE);
        System.out.println(SPAZI +player[0].getNome()+ SCELTA_PIETRE);
        t1.sceltaPietre();
        System.out.println(SPAZI+player[1].getNome()+ SCELTA_PIETRE);
        t2.sceltaPietre();

        // vita iniziale
        System.out.println(SPAZI+GOLEMDI2+player[0].getNome()+HA+t1.getVita()+PS);
        System.out.println(GOLEMDI2+player[1].getNome()+HA+t2.getVita()+PS+"\n");

        // vado avanti quando ho ancora dei golem con cui giocare
        do
        {
            // controllo che le P pietre non siano tutte uguali E nello stessomordine per evitare un ciclo infinito
            while(!controlloPietre(t1,t2))
            {
                int caso = EstrazioniCasuali.estraiIntero(0,1);
                System.out.println(PIETRE_UGUALI +player[caso].getNome()+ NEW_PIETRE);
                if(caso==0) t1.sceltaPietre();
                else t2.sceltaPietre();
            }

            // comunico cosa sto lanciando
            System.out.println("\n" + player[0].getNome() + LANCIA + t1.getPietra().getTipo() + ", " +
                    player[1].getNome() + LANCIA + t2.getPietra().getTipo());

            // salvo indice delle pietre
            int p1 = t1.getPietra().getIndice();
            int p2 = t2.getPietra().getIndice();

            int forza = equi.getForza(p1, p2);
            if(Math.abs(forza)==Main.V) System.out.println(OVERKILL);

            if (forza > 0) //vince la pietra p1 -> danno al golem 2
            {
                t2.danno(forza); // aggiorno la vita con il danno preso e comunico
                System.out.println(VINCE + player[0].getNome() + GOLEMDI + player[1].getNome() + DANNO + forza);

                if (t2.getVita() <= 0) // se G2 è morto, respawn, aggiorno il numero di golem usati e il giocatore sceglie di nuovo le pietre e comunico
                {

                    System.out.println(ELIMINATO + player[1].getNome());

                    player[1].aggiornaRound();
                    System.out.println(A +player[1].getNome() + RIMANGONO + player[1].roundRimanenti() + GOLEMSQUADRA);
                    System.out.println(A + player[0].getNome() + RIMANGONO + player[0].roundRimanenti() + GOLEMSQUADRA);

                    if(controlloGolem(1))
                        t2.respawn();



                } else // altrimenti comunico la vita rimanente di G2
                    System.out.println(GOLEMPLAYER + player[1].getNome() + RIMANGONO + t2.getVita() + PS);

                // vita rimanente di G1
                System.out.println(GOLEMPLAYER + player[0].getNome() + RIMANGONO + t1.getVita() + PS);

            }
            else if (forza == 0) // interazione tra gli stessi elementi
            {
                System.out.println(UGUALI);
            }
            else // vince la pietra p2 -> danno al golem 1
            {
                t1.danno(-forza);
                System.out.println(VINCE + player[1].getNome() + GOLEMDI + player[0].getNome() + DANNO + (-forza));

                if (t1.getVita() <= 0) // se G1 è morto, respawn, aggiorno il numero di golem usati e il giocatore sceglie di nuovo le pietre e comunico
                {

                    System.out.println(ELIMINATO + player[0].getNome());

                    player[0].aggiornaRound();
                    System.out.println(A + player[0].getNome() + RIMANGONO + player[0].roundRimanenti() + GOLEMSQUADRA);
                    System.out.println(A + player[1].getNome() + RIMANGONO + player[1].roundRimanenti() + GOLEMSQUADRA);

                    if(controlloGolem(0))
                        t1.respawn();


                }
                else // altrimenti comunico la vita rimanente di G1
                    System.out.println(GOLEMPLAYER + player[0].getNome() + RIMANGONO + t1.getVita() + PS);

                // vita rimanente di G2
                System.out.println(GOLEMPLAYER + player[1].getNome() + RIMANGONO + t2.getVita() + PS);
            }

            // aggiorno le pietre per passare a quella dopo nella coda
            t1.aggiornaPietra();
            t2.aggiornaPietra();

            // interazione di 3s per dare tempo di leggere
            Thread.sleep(WAITIME);
            premiPerContinuare();
        }while (controlloGolem(0) && controlloGolem(1));
    }


    /**
     * Controllo che la partita non sia finita e comunico il vincitore
     *
     */
    public void isWinner()
    {
        if(!controlloGolem(0))
        {
            System.out.println(GIOCATORE+player[1].getNome()+HAVINTO);

        }
        if(!controlloGolem(1))
        {
            System.out.println(GIOCATORE+ player[0].getNome() +HAVINTO);
        }
        System.out.println(GG);
    }




    /**
     * <h7>Siccome non abbiamo un array di golem controlliamo di non sforare il numero di golem</h7>
     * @param index indice del giocatore che controllo
     * @return true nel caso ho ancora round da effettuare, else altrimenti
     */
    private boolean controlloGolem(int index)
    {
        if(player[index].getRoundMax()>Main.G)
        {
            return false;
        }
        return true;//ritorno vero se il numero max di tamagolem non è superato
    }



    /**
     * controllo che tutte e tre le pietre non siano uguali perchè in quel caso si crea uno scontro infinito,
     * dato che nessun golem perderebbe vita
     * @param t1 tamagolem del primo giocatore
     * @param t2 tamagolem del secondo giocatore
     * @return false se devo fare reinserire le pietre, true se vanno bene
     */
    private boolean controlloPietre(Tamagolem t1, Tamagolem t2)
    {
        int controllo=0;
        for (int i = 0; i < Main.P; i++)
        {
            // se le pietre sono nello stesso ordine e il nome che le descrive è uguale le faccio reinserire
            // solo se sono TUTTE uguali, una diversa mi basta
            if (t1.getPietra().getTipo().equalsIgnoreCase(t2.getPietra().getTipo()))
                controllo++;
            t1.aggiornaPietra();
            t2.aggiornaPietra();
        }

        if (controllo==Main.P) return false; // ritorno false se le devo fare reinserire
        return true;
    }

    /**
     * <h7>Aspetto finche l'utente non decide di continuare</h7>
     */
    private void premiPerContinuare(){
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\r\n");

        System.out.println(PRESS);
        String s = input.nextLine();
    }
}
