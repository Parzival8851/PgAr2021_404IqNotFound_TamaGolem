import it.unibs.fp.mylib.InputDati;


public class Main
{
    public static final String MSG_SCELTA = "scegli il numero degli elementi\ntra 3 e 5(inclusi) se vuoi che il livello della partta sia facile\ntra 6 e 8(inclusi) se vuoi che il livello della partita sia intermedio\n tra 9 e 10(inclusi se vuoi che il livello della partita sia difficile\nnon inserire altri valori perchè potrebbero compromettere le meccaniche del gioco";
    private static final String PREMI = "Premi per continuare";
    private static final String MSG_BENVENUTO = "Benvenuto all'arena per effetteuare nuovi scontri e migliorare le tue capacità per governare gli elementi";
    private static final String MSG_ERROREN = "il numero inserito non è consentito perchè potrebbe compromettere le funzionalità del gioco";


 //USO 5 PER LE PROVE, ATTENZIONE AI COMMENTI
    public static final int N=/*InputDati.leggiIntero(MSG_SCELTA, 3, 10);*/5; // scrivere metodo per prendere n e verificare che sia comreso tra 3 e 10
    public static final int G=0;  // scrivere formula per numero di golem
    public static final int V=10; // impostata a 10
    public static final int P=0;
    public static final int S=0;


    public static void main(String[] args){
        //System.out.println(MSG_BENVENUTO);
       // InputDati.leggiStringa(PREMI);
        // serve per capire la scelta del numero di elementi
        // e controllare che il numero inserito sia valido



        // il numero di pietre
        int P = ((N + 1)/3)+1;
        //il numero di di tamagolem
        int G = ((N- 1)*(N - 2) / (2 * P));
        int S = ((2 * G * P) / N)* N;





    }


}
