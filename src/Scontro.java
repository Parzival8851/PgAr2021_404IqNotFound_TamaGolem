import it.unibs.fp.mylib.EstrazioniCasuali;

public class Scontro
{
    public static final String PIETRE_UGUALI = "Le pietre scelte sono tutte uguali: Il giocatore ";
    public static final String NEW_PIETRE = "reimmette le sue pietre";
    private Giocatore[] player=new Giocatore[2];
    private int vincitore=0;
    private Equilibrio equi=null;

    private static final String NUMTAMA = "Numero di Tamagolem esaurito ";
    private static final String VINCE= "Vince ";
    private static final String GOLEMDI= ", il golem di ";
    private static final String DANNO= "subisce un danno pari a ";
    private static final String LANCIA= "lancia una pietra di ";
    private static final String ELIMINATO="Vieni eliminato il golem di ";
    private static final String RIMANGONO=" rimangono";
    private static final String GOLEMSQUADRA="golem in squadra";
    private static final String GOLEMPLAYER="Al golem del giocatore";
    private static final String PS="punti vita";
    private static final String UGUALI="Le pietre dei due golem sono uguali";


    /**
     * creo il terreno di gioco
     * @param g1 giocatore 1
     * @param g2 giocatore 2
     * @param e equilibrio per le interazioni
     */
    public Scontro(Giocatore g1, Giocatore g2, Equilibrio e)
    {
        this.player[0] = g1;
        this.player[1] = g2;
        this.equi = e;
    }

    /**
     *
     */
    public void round()
    {
        // salvo i golem in uso per non doverli scrivere ogni volta
        Tamagolem t1 = player[0].getGolem();
        Tamagolem t2 = player[1].getGolem();

        //scegliere le pietre del golem
        t1.sceltaPietre();
        t2.sceltaPietre();

        // vado avanti quando (entrambi i golem hanno ancora vita E) ho ancora dei golem con cui giocare
        while (/*t1.getVita()>0 && t2.getVita()>0 &&*/ controlloGolem(0) && controlloGolem(1))
        {
            while(!controlloPietre(t1,t2))
            {
                int caso = EstrazioniCasuali.estraiIntero(0,1);
                System.out.println(PIETRE_UGUALI +player[caso].getNome()+ NEW_PIETRE);
                if(caso==0) t1.sceltaPietre();
                else t2.sceltaPietre();
            }

            System.out.println("\n" + player[0].getNome() + LANCIA + t1.getPietra().getTipo() + "," +
                    player[1].getNome() + LANCIA + t2.getPietra().getTipo());


            int p1 = t1.getPietra().getIndice();
            int p2 = t2.getPietra().getIndice();
            t1.aggiornaPietra();
            t2.aggiornaPietra();

            int forza = equi.getForza(p1, p2);

            if (forza > 0) //vince la pietra p1 -> danno al golem 2
            {
                t2.danno(forza); // aggiorno la vita con il danno preso e comunico
                System.out.println(VINCE + player[0].getNome() + GOLEMDI + player[1].getNome() + DANNO + forza);

                if (t2.getVita() <= 0) // se G2 è morto, respawn, aggiorno il numero di golem usati e il giocatore sceglie di nuovo le pietre e comunico
                {

                    System.out.println(ELIMINATO + player[1].getNome());
                    t2.respawn();
                    player[1].aggiornaRound();
                    System.out.println(player[1].getNome() + RIMANGONO + player[1].roundRimanenti() + GOLEMSQUADRA);

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
                    t1.respawn();
                    player[0].aggiornaRound();
                    System.out.println(player[0].getNome() + RIMANGONO + player[0].roundRimanenti() + GOLEMSQUADRA);

                }
                else // altrimenti comunico la vita rimanente di G1
                    System.out.println(GOLEMPLAYER + player[0].getNome() + RIMANGONO + t1.getVita() + PS);

                // vita rimanente di G2
                System.out.println(GOLEMPLAYER + player[1].getNome() + RIMANGONO + t2.getVita() + PS);
            }
        }
    }


    /**
     * Controllo che la partita non sia finita, ritorno -1 se è cosi,
     * 0 se vince il player1, 1 se vince il player2
     * @return
     */
    public void isWinner()
    {
        if(!controlloGolem(0))
        {
            System.out.println("Il giocatore 1 ha vinto");

        }
        if(!controlloGolem(1))
        {
            System.out.println("Il giocatore 2 ha vinto");
        }
    }



    /**
     * Siccome non abbiamo un array di golem controlliamo di non sforare il numero di golem
     * @param index
     * @return
     */
    private boolean controlloGolem(int index)
    {
        if(player[index].getRoundMax()>Main.G)
        {
            System.out.println(NUMTAMA);
            return false;
        }
        return true;//ritorno vero se il numero max di tamagolem non è superato
    }

    public Giocatore getPlayer1()
    {
        return player[0];
    }

    public Giocatore getPlayer2()
    {
        return player[1];
    }

    private boolean controlloPietre(Tamagolem t1, Tamagolem t2)
    {
        int controllo=0;
        for (int i = 0; i < Main.P; i++)
        {
            if (t1.getPietra().getTipo().equalsIgnoreCase(t2.getPietra().getTipo()))
                controllo++;
            t1.aggiornaPietra();
            t2.aggiornaPietra();
        }

        if (controllo==Main.P) return false;
        return true;
    }


}
