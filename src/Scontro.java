public class Scontro {
    private Giocatore[] player;
    private int vincitore;
    private Equilibrio equi;

    private static final String NUMTAMA = "Numero di Tamagolem esaurito";

    private void round(Tamagolem[] golem){

        Tamagolem t1 = player[0].getGolem();
        Tamagolem t2 = player[1].getGolem();

        //Controllo sul numero di golem disponibili
        controlloGolem(1);
        controlloGolem(2);

        while(t1.getVita()>0 || t2.getVita()>0){
            int p1 = 0;
            int p2 = 0;
        }
    }

    /**
     * Controllo che la partita non sia finita, ritorno -1 se è cosi,
     * 0 se vince il player1, 1 se vince il player2
     * @return
     */
    private int isWinner(){
        if(!controlloGolem(0)) {
            System.out.println("Il giocatore 1 ha vinto");
            return 0;
        }
        if(!controlloGolem(1)) {
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
    private boolean controlloGolem(int index){
        if(player[index].getRoundMax()>Main.G){
            System.out.println(NUMTAMA);
            return false;
        }
        return true;//ritorno vero se il numero max di tamagolem non è superato
    }
}
