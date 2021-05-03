import it.unibs.fp.mylib.EstrazioniCasuali;

public class Equilibrio
{
    static int [][] matrice = null;
    static int somma=0;

    /**
     * Passo N e creo una matrice che riempio con delle regole con numeri pseudo-causali
     * @param N difficoltà del gioco, numero di elementi presenti
     * @param V vita del Tamagole, serve per definire sup(W)=V
     */
    public Equilibrio(int N, int V)
    {
        this.matrice= new int[N][N]; // inizializzo la dimensione della matrice ad N

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                matrice[i][j]=0;
                // inizializzo tutta la matrice a 0 per poter contare i posti che non ho ancora calcolato
                // le interazioni di di due elementi uguali sono nulle (matrice[i][i]=0; è costante)




        for (int riga = 0; riga < N; riga++) // calcolo i valori per ogni riga
        {
            aggiornaSomma(riga, N);

            for (int colonna = riga+1; colonna < N; colonna++) // calcolo i valori di ogni cella da sinistra a partire dal primo posto dopo la diagonale
            {
                if(colonna==1 && riga==0) // sup(W)=V
                    matrice[riga][colonna]=V;
                else // tutte le altre celle (nella prima riga parto dall'indice 2)
                {

                }

                matrice[colonna][riga]=-matrice[riga][colonna]; // matrice simmetrica
            }
        }







    }

    /**
     * sommo tutti gli elementi della riga per non sforare con i calcoli delle celle sup(W)=V
     * @param riga
     * @param N
     */
    static private void aggiornaSomma(int riga, int N)
    {
        somma=0;
        for (int i = 0; i < N; i++)
            somma+=matrice[riga][i];
    }




    /**
     * calcolo quanti posti nella riga non ho ancora calcolato
     * @param riga indice della riga per capire quanti posti devo calcolare
     * @return
     */
    private int postiRimanenti(int riga, int N)
    {
        int conta=0; // contatore di posti non calcolati

        for (int i = 0; i < N; i++)
            if (matrice[riga][i]==0) conta++;

        return conta--; // tolgo il posto matrice[i][i] che è sempre 0
    }

    /**
     * calcolo il primo posto da sinistra che non ho ancora calcolato esclusa la diagonale
     * @param matrice
     * @param riga
     * @param N
     * @return
     */
    /*
    private int primoPostoVuoto(int matrice[][], int riga, int N)
    {
        for (int i = 0; i < N; i++)
            if (matrice[riga][i]==0 && riga!=i)
                return i; // ritorno il primo indice di cella vuota non diagonale

        return -1;
        // se tutte le celle sono riempite ritorno errore
        // (non lo uso in quanto calcolo l'ultima cella quando postirimanenti=2)
    }
    */

}
