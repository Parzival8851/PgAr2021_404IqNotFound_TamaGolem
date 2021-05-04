//import it.unibs.fp.mylib.EstrazioniCasuali;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Equilibrio
{
    static int [][] matrice = null;
    static int somma=0;
    private static final Random rand = new Random();

    public static void main(String[] args) {
        Equilibrio prova = new Equilibrio(5, 10);
    }

    public static int estraiIntero(int min, int max) {
        int range = max + 1 - min;
        int casual = rand.nextInt(range);
        return casual + min;
    }

    /**
     * Passo N e creo una matrice che riempio con delle regole con numeri pseudo-causali
     * @param N difficoltà del gioco, numero di elementi presenti
     * @param V vita del Tamagolem, serve per definire sup(W)=V
     */
    public Equilibrio(int N, int V)
    {
        this.matrice= new int[N][N]; // inizializzo la dimensione della matrice ad N

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                matrice[i][j]=0;
                // inizializzo tutta la matrice a 0 per poter contare i posti che non ho ancora calcolato
                // le interazioni di di due elementi uguali sono nulle (matrice[i][i]=0; è costante)



        // righe
        for (int riga = 0; riga < N; riga++) // calcolo i valori per ogni riga
        {
            aggiornaSomma(riga, N);

            // celle
            for (int colonna = riga+1; colonna < N; colonna++) // calcolo i valori di ogni cella da sinistra a partire dal primo posto dopo la diagonale
            {
                if(colonna==1 && riga==0) // sup(W)=V
                    matrice[riga][colonna]=V;
                else // tutte le altre celle (nella prima riga parto dall'indice 2)
                {
                    /*if(colonna<(N-2)) // eseguo questo fino alla secondultima cella della riga
                    {
                        if (Math.abs(somma)>V) // ho superato la V quindi creo un random controllato per non sforare sup(W)=V
                            matrice[riga][colonna]=-estraiIntero(somma-V, V-1)*Integer.signum(somma);
                            // creo un intero casuale tra somma-V e V-1 così nel caso peggiore(somma-V) la somma torna =V
                            // visto che questa cella avrà segno opposto alla somma dato dal - iniziale
                            // moltiplicato per il segno della somma
                        else // somma <= V
                            matrice[riga][colonna]=estraiIntero(1,V-1)*estraiSegno();
                            // estraggo casualmente con segno causale fino a V-1 per avere un unico ingresso che killa il golem

                    }
                    else if(colonna==(N-1)) // penultima cella  */
                    if(colonna<(N-1))
                    {
                        /*if (Math.abs(somma)>V)
                        {
                            matrice[riga][colonna]=estraiIntero(Math.abs(somma-V), V-1);
                            if (somma<0) matrice[riga][colonna]=-matrice[riga][colonna]; // ho superato la V quindi creo un random controllato per non sforare sup(W)=V
                        }

                        else
                        {*/
                            ArrayList<Integer> temp = new ArrayList<Integer>(); // arraylist temporaneo dei valori da cui posso estrarre

                            for (int i = 1; i < Math.abs(somma); i++)
                                temp.add(-i);
                            // possibili valori da estrarre negativi: da -1 a -(somma-1)
                            // in modo da non fare 0 la somma aggiornata

                            for (int i = Math.abs(somma+1); i <= V; i++)
                                temp.add(i-somma);
                            // possibili valori da estrarre positivi: da somma+1-somma a V-somma
                            // in modo da non fare 0 la somma aggiornata

                            Collections.shuffle(temp); // shuffle della lista da cui prendere i valori
                            matrice[riga][colonna]=temp.get(0); // prendo un elemento a caso tra i possibili
                       // }



                    }
                    else matrice[riga][colonna]=-somma; // l'ultimo elemento è l'opposto della somma della riga per raggiungere l'equilibrio
                }

                matrice[colonna][riga]=-matrice[riga][colonna]; // matrice simmetrica
                somma+=matrice[riga][colonna]; // aggiorno la somma con l'elemento appena inserito
            }


        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matrice[i][j] + "   ");

            }
            System.out.println();
        }







    }

    /**
     * ritorna in modo casuale segno positivo o negativo
     * @return
     */
    private int estraiSegno()
    {
        if(estraiIntero(0,1)==0) return 1;
        else return -1;
    }

    /**
     * sommo tutti gli elementi della riga per non sforare con i calcoli delle celle sup(W)=V
     * @param riga
     * @param N
     */
    static private void aggiornaSomma(int riga, int N)
    {
        somma=0;
        for (int i = 0; i < riga; i++) // tengo conto dei valori già calcolati fino alla diagonale esclusa perché 0
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
