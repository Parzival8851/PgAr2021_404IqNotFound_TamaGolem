import it.unibs.fp.mylib.InputDati;


public class Main
{
    public static final String MSG_SCELTA = "scegli il numero degli elementi\ntra 3 e 5(inclusi) se vuoi che il livello della partita sia facile\ntra 6 e 8(inclusi) se vuoi che il livello della partita sia intermedio\ntra 9 e 10(inclusi) se vuoi che il livello della partita sia difficile\nnon inserire altri valori perchè potrebbero compromettere le meccaniche del gioco";
    private static final String MSG_BENVENUTO = "Benvenuto all'arena per effetteuare nuovi scontri e migliorare le tue capacità per governare gli elementi";

    public static final int N=InputDati.leggiIntero(MSG_BENVENUTO+"\n"+MSG_SCELTA, 3, 10);
    public static final int P=Math.round((N + 1)/3)+1;
    public static final int G=Math.round(((N- 1)*(N - 2)) / (2 * P));
    public static final int V=10; // impostata a 10
    public static final int S=Math.round(2*G*P/N) *N;
    public static final String ERR_NOME_G = "Giocatori con nomi identici, reinserire nome del secondo giocatore: ";
    public static final String RIGIOCARE = "Vuoi rigiocare?";
    public static final String MSG_INIZIALE = "L'arena consiste in uno scontro tra due giocatori e i loro Tamagolem, ogni golem avrà delle pietre che scagliera' contro l'altro fino alla distruzione dell'uno o dell'altro.\n\n Ora andremo a creare i nostri campioni!";
    public static final String PRIMO_GIOCATORE = "PRIMO GIOCATORE! Presentati:";
    private static Giocatore[] gamer=new Giocatore[2];
    private static Equilibrio eq=new Equilibrio();
    private static Scontro s;
    private static Pietra[] sassi;

    public static void main(String[] args)
    {
        do
        {
            // serve per capire la scelta del numero di elementi
            // e controllare che il numero inserito sia valido

            setPietre();
            System.out.println(MSG_INIZIALE);

            // creo i due giocatori
            System.out.println(PRIMO_GIOCATORE);
            gamer[0]=new Giocatore();
            System.out.println("ORA TOCCA AL SECONDO GIOCATORE! Presentati:");
            gamer[1]=new Giocatore();
            controlloNome();

            // inizio la battaglia
            s= new Scontro(gamer[0],gamer[1], eq); // preparo i golem
            s.round(); // battaglia vera e propria
            s.isWinner();

            eq.stampaEquilibrio();


        }while(InputDati.yesOrNo(RIGIOCARE));

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

