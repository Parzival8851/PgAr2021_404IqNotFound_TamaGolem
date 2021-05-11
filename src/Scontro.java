public class Scontro
{
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


        while(t1.getVita()>0 && t2.getVita()>0 && controlloGolem(1) && controlloGolem(2))
        {
            //scegliere le pietre del golem
            t1.sceltaPietre();
            t2.sceltaPietre();

            System.out.println("\n" + player[0].getNome() + LANCIA + t1.getPietra().getTipo()+ "," +
                    player[1].getNome() + LANCIA + t2.getPietra().getTipo());

            int p1=t1.getPietra().getIndice();
            int p2=t2.getPietra().getIndice();

            if(equi.matrice[p1][p2]>0)
            {//vince la pietra p1
                t2.danno(equi.matrice[p1][p2]);
                System.out.println(VINCE + player[0].getNome() + GOLEMDI + player[1].getNome() +
                        DANNO + equi.matrice[p1][p2]);
                if(t2.getVita()<=0)
                {
                    player[1].eliminaGolem();
                    System.out.println(ELIMINATO + player[2].getNome());
                    if(player[1].numGolem()>0)
                    {
                        player[1].setGolemAttivo();
                        System.out.println(player[1].getNome()+ RIMANGONO+ player[1].numGolem + GOLEMSQUADRA);

                    }//else vince la partita il player 1
                }else System.out.println(GOLEMPLAYER+ player[1].getNome()+ RIMANGONO+ t2.getVita()+PS);

            }
            else if(equi.getForza(p1,p2)==0)
            {
                System.out.println(UGUALI);
            }
            else // picchio il primo
            {
                t1.danno(equi.matrice[p2][p1]);
                System.out.println(VINCE + player[1].getNome() + GOLEMDI + player[0].getNome() +
                        DANNO + equi.matrice[p2][p1]);
                if(player[0].numGolem()>0)
                {
                    player[0].setGolemAttivo();
                    System.out.println(player[0].getNome()+ RIMANGONO+ player[0].numGolem + GOLEMSQUADRA);

                }//else vince la partita il player 2
            }
            System.out.println(GOLEMPLAYER+ player[0].getNome()+ RIMANGONO+ t1.getVita()+PS);

        }
    }


    /**
     * Controllo che la partita non sia finita, ritorno -1 se è cosi,
     * 0 se vince il player1, 1 se vince il player2
     * @return
     */
    private int isWinner()
    {
        if(!controlloGolem(0))
        {
            System.out.println("Il giocatore 1 ha vinto");
            return 0;
        }
        if(!controlloGolem(1))
        {
            System.out.println("Il giocatore 2 ha vinto");
            return 1;
        }
        return -1;
    }

    public void scontro(Equilibrio equilibrio){
        this.equi=equilibrio;
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

    private int numPietra(String pietra)
    {
        for(int i=0; i<elementi.lenght;i++)
        {
            if(elementi[i].equals(pietra))
                return i;
            return -1;
        }
    }
}
