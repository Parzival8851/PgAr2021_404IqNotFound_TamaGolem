import it.unibs.fp.mylib.InputDati;



public class Main
{
    public static final String MSG_SCELTA = "scegli il numero degli elementi\ntra 3 e 5(inclusi) se vuoi che il livello della partita sia facile\ntra 6 e 8(inclusi) se vuoi che il livello della partita sia intermedio\ntra 9 e 10(inclusi) se vuoi che il livello della partita sia difficile\nnon inserire altri valori perchè potrebbero compromettere le meccaniche del gioco";
    private static final String MSG_BENVENUTO = "Benvenuto all'arena per effetteuare nuovi scontri e migliorare le tue capacità per governare gli elementi";
    public static final String CIAOCIAO = "Grazie di aver giocato al nostro gioco\nClaudia, Matteo e Matteo\nP.S. Se hai vinto alla prima, bel nome ;)\n\n";

    // sono pubbliche, ma le usiamo come costanti
    public static int N=0;
    public static int P=0;
    public static int G=0;
    public static int S=0;

    public static final int V=10; // impostata a 10

    public static final String ERR_NOME_G = "Giocatori con nomi identici, reinserire nome del secondo giocatore: ";
    public static final String RIGIOCARE = "\n\nVuoi rigiocare?";
    public static final String MSG_INIZIALE = "\nL'arena consiste in uno scontro tra due giocatori e i loro Tamagolem, \nogni golem avrà delle pietre che scagliera' contro l'altro \nfino alla distruzione dell'uno o dell'altro.\n\nOra andremo a creare i nostri campioni!";
    public static final String PRIMO_GIOCATORE = "PRIMO GIOCATORE! Presentati:";
    public static final String EQUILIBRIO = "Ed ecco il delicatissimo(!!!) equlibrio di questo mondo\n";
    public static final String GODS = "\n\n\n\nMATTEO E CLAUDIA VINCONO DI DEFAULT PERCHE' SONO I DEI CHE CREANO L'EQULIBRIO, SPIACE :)\n";
    public static final String SECONDO_GIOCATORE = "ORA TOCCA AL SECONDO GIOCATORE! Presentati:";
    public static final String DIVINITA1="Matteo";
    public static final String DIVINITA2="Claudia";
    private static Giocatore[] gamer=null;
    private static Equilibrio eq=null;
    private static Scontro s=null;
    private static Pietra[] sassi;

    public static void main(String[] args) throws InterruptedException {
        do
        {
            // serve per capire la scelta del numero di elementi
            // e controllare che il numero inserito sia valido

            N=InputDati.leggiIntero(MSG_BENVENUTO+"\n"+MSG_SCELTA, 3, 10);
            P=Math.round((N + 1)/3)+1;
            G=Math.round(((N- 1)*(N - 2)) / (2 * P));
            S=Math.round(2*G*P/N) *N;

            setPietre();
            System.out.println(MSG_INIZIALE);
            eq=new Equilibrio();
            gamer=new Giocatore[2];
            // creo i due giocatori
            System.out.println(PRIMO_GIOCATORE);
            gamer[0]=new Giocatore();
            System.out.println(SECONDO_GIOCATORE);
            gamer[1]=new Giocatore();
            controlloNome();

            //sfizio personale :D
            if(gamer[0].getNome().equals(DIVINITA1) || gamer[1].getNome().equals(DIVINITA1) || gamer[0].getNome().equals(DIVINITA2) || gamer[1].getNome().equals(DIVINITA2))
            {
                System.out.println(GODS);
            }
            else
            {
                // inizio la battaglia
                s= new Scontro(gamer[0],gamer[1], eq); // preparo i golem
                s.round(); // battaglia vera e propria
                s.isWinner();
            }


            System.out.println(EQUILIBRIO);
            eq.stampaEquilibrio();


        }while(InputDati.yesOrNo(RIGIOCARE));


        System.out.println(CIAOCIAO);

    }

    /**
     * se i due nomi sono uguali faccio reimmettere
     */
    private static void controlloNome()
    {
        while (gamer[1].getNome().equalsIgnoreCase(gamer[0].getNome()))
        {
            gamer[1].setNome(InputDati.leggiStringaNonVuota(ERR_NOME_G));
        }
    }

    private static void setPietre()
    {
        sassi=new Pietra[N];

        for (int i = 0; i < N; i++)
        {
            sassi[i]=new Pietra(i);
        }
    }

    public static Pietra getSasso(int i)
    {
        return sassi[i];
    }


}