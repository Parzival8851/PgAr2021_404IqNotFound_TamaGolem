import it.unibs.fp.mylib.InputDati;


public class Main
{
    public static final String MSG_SCELTA = "scegli il numero degli elementi\ntra 3 e 5(inclusi) se vuoi che il livello della partta sia facile\ntra 6 e 8(inclusi) se vuoi che il livello della partita sia intermedio\ntra 9 e 10(inclusi) se vuoi che il livello della partita sia difficile\nnon inserire altri valori perchè potrebbero compromettere le meccaniche del gioco";
    private static final String PREMI = "Premi per continuare";
    private static final String MSG_BENVENUTO = "Benvenuto all'arena per effetteuare nuovi scontri e migliorare le tue capacità per governare gli elementi";
    private static final String MSG_ERRORE = "il numero inserito non è consentito perchè potrebbe compromettere le funzionalità del gioco";

    public static final int N=InputDati.leggiIntero(MSG_SCELTA, 3, 10);
    public static final int P=Math.round((N + 1)/3)+1;
    public static final int G=Math.round(((N- 1)*(N - 2)) / (2 * P));
    public static final int V=10; // impostata a 10
    public static final int S=Math.round(2*G*P/N) *N;
    public static final String ERR_NOME_G = "Giocatori con nomi identici, reinserire il secondo nome: ";
    public static final String RIGIOCARE = "Vuoi rigiocare?";
    private static Giocatore gamer[]=new Giocatore[2];
    private static Equilibrio eq=new Equilibrio();
    private static Scontro s=null;
    private static Pietra sassi[]=null;

    public static void main(String[] args)
    {
        do
        {
            inizio();
            // serve per capire la scelta del numero di elementi
            // e controllare che il numero inserito sia valido

            // creo i due giocatori
            gamer[0]=new Giocatore();
            gamer[1]=new Giocatore();
            controlloNome();

            // inizio la battaglia
            s= new Scontro(gamer[0],gamer[1], eq); // preparo i golem
            s.round(); // battaglia vera e propria
            s.isWinner();


        }while(InputDati.yesOrNo(RIGIOCARE));




        // chiedo se si vuole rigiocare




    }

    /**
     * se i due nomi sono uguali faccio reimmettere
     */
    private static void controlloNome() {
        while (gamer[1].getNome().equalsIgnoreCase(gamer[0].getNome()))
        {
            gamer[1].setNome(InputDati.leggiStringaNonVuota(ERR_NOME_G));
        }
    }

    private static void inizio()
    {
        System.out.println(MSG_BENVENUTO);
        InputDati.leggiStringa(PREMI);
    }


    private void setPietre()
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

