import it.unibs.fp.mylib.EstrazioniCasuali;

import java.util.ArrayList;

import java.util.Collections;


public class Equilibrio
{
    private static int[][] matrice = null; // matrice per l'equilibrio
    private static int sommaR = 0; // somma della riga
    private static int sommaC = 0; // somma della colonna



    /**
     * calculo l'equilibrio su una matrice antisimmetrica con diagonale nulla
     */
    public Equilibrio()
    {
        // inizializzo a 0 tutte le celle
        matrice = new int[Main.N][Main.N];

        for (int i = 0; i < Main.N; i++)
        {
            for (int j = 0; j < Main.N; j++)
            {
                matrice[i][j]=0;
            }
        }

        inizializzazioneCellaV(); // sup(W)=V

        for (int riga = 0; riga < Main.N; riga++) // righe
        {
            aggiornaSommaR(riga); // aggiorno sommaR della riga su cui lavoro

            // colonne, parto da riga+1 per lavorare sui valori della triangolare superiore,
            // diagonale di 0, triangolare inferiore simmetrica e negativa rispetto a sopra
            // -> aJK = - aKJ e aXX=0
            for (int colonna=riga+1; colonna<Main.N; colonna++)
            {
                if(matrice[riga][colonna]==0) // calcolo solo delle celle vuote, perché una è inizializzata a V (e -V la sua simmetrica)
                {
                    if (colonna == (Main.N - 1)) // sono all'ultima cella
                    {
                        matrice[riga][colonna] = -sommaR; // per avere equilibrio l'ultima casella è l'opposto della somma di tutte le altre
                    }
                    else
                    {
                        aggiornaSommaC(colonna); // aggiorno sommaC per la cella su cui calcolo il valore per l'equilibrio

                        // a questo punto io ho la cella che possiede sia la somma orizzontale che verticale per la sua posizione
                        // calcolo i valori possibili di intersezione tra quelli possibili R\C per non sforare V, vita massima del tamagolem

                        matrice[riga][colonna] = calcolaCella(); // calcolo cella
                    }

                    matrice[colonna][riga] = -matrice[riga][colonna]; // set opposta
                    sommaR += matrice[riga][colonna]; // aggiorno la somma della riga
                }
            }
        }



    }



    /**
     * all'inizio della riga aggiorno la sua somma
     * @param riga la riga su cui sto sommando
     */
    private static void aggiornaSommaR(int riga)
    {
        sommaR=0;
        for (int i = 0; i < Main.N; i++)
            sommaR+=matrice[riga][i];
    }

    /**
     * all'inizio della colonna ( che a questo punto è singola cella) aggiorno la sua somma
     * @param colonna la colonna su cui sto lavorando
     */
    private void aggiornaSommaC(int colonna)
    {
        sommaC=0;
        for (int i = 0; i < Main.N; i++)
            sommaC+=matrice[i][colonna];
    }

    /**
     * inizializzo una cella a caso non sulla diagonale che conterrà V per avere sup(W)=V
     */
    private void inizializzazioneCellaV() // evito l'ultima riga e colonna per non generare casi scomodi di somma finale
    {
        int riga=EstrazioniCasuali.estraiIntero(0, Main.N-2); // estraggo l'indice di riga

        ArrayList<Integer> temp =new ArrayList<>(); // creo un arraylist temporaneo con tutti gli indici tranne riga per evitare la diagonale
        for (int i=0; i<(Main.N-1); i++)
            if (i!=riga) temp.add(i);

        Collections.shuffle(temp); // mix degi possibili indici
        int colonna = temp.get(0); // prendo il primo

        matrice[riga][colonna]=Main.V; // detto una cella a caso a V e -V la sua gemella
        matrice[colonna][riga]=-Main.V; // per avere sup(W)=V
    }

    /**
     * calcolo la cella come intersezione delle possibilità tra righe e colonne per stare dentro V nelle due somme
     * @return valore della cella da immettere in matrice
     */
    private int calcolaCella()
    {

        ArrayList<Integer> tempR; // lista delle possibilità sulle righe
        ArrayList<Integer> tempC; // e sulle colonne

        // calcolo i valori possibili per riga e colonna
        tempR = calcoloValoriPossibiliCella(sommaR); // riga
        tempC = calcoloValoriPossibiliCella(sommaC); // colonna

        tempR.retainAll(tempC); // tengo solo i valori che sono possibili sia per righe che per colonne per evitare di "rompere la matrice"
        Collections.shuffle(tempR); // mix per randomizzare

        return tempR.get(0); // ritorno il primo elemento
    }

    /**
     * calcolo i valori possibili per non sforare V data Somma
     * @param sum uso questo parametro per capire se calcolo su righe o su colonne per riutilizzare il metodo
     * @return valori possibili nella cella data la somma R\C
     */
    private ArrayList<Integer> calcoloValoriPossibiliCella(int sum)
    {
        ArrayList<Integer> temp = new ArrayList<>(); // lista temporanea dei valori che calcolo

        switch (Math.abs(sum))
        {
            case 0: // aggiungo da 1 a V e da -1 a -V, non ho preferenze per averlo negativo o positivo
                for (int i = 1; i < Main.V; i++)
                {
                    temp.add(i);
                    temp.add(-i);
                }
                break;

            case Main.V: // somma è il massimo possibile, aggiungo i valori opposti da |1| a |V-1| per abbassarla (o alzarla)
                for (int i = 1; i < (Main.V+1); i++)
                {
                    int valore = -i*Integer.signum(sum);
                    temp.add(valore);
                }
                break;

            default: // la somma è compresa tra 0 e |V|, aggiungo con segno opposto da |1| a |S-1| e con stesso segno da |1| a |V-S|
                for (int i = 1; i < Math.abs(sum); i++) // aggiungo con segno opposto da |1| a |S-1| -> torno a (stesso segno) 1
                {
                    int valore = -i*Integer.signum(sum);
                    temp.add(valore);
                }

                for (int i = 1; i < (Main.V-Math.abs(sum)+1); i++) // aggiungo con stesso segno da |1| a |V-S| -> arrivo a (stesso segno) V
                {
                    int valore = i*Integer.signum(sum);
                    temp.add(valore);
                }

                for(int i = (Math.abs(sum)+1); i<(Main.V+1); i++) // aggiungo con segno opposto da |S+1| a |V| -> arrivo a (segno opposto) V stando al massimo a V
                {
                    int valore = -i*Integer.signum(sum);
                    temp.add(valore);
                }
                break;
        }

        return temp;
    }

    /**
     * prendo la cella dello scontro tra i due elementi
     * @param riga x
     * @param colonna y
     * @return forza dell'interazione
     */
    public int getForza(int riga, int colonna)
    {
        return matrice[riga][colonna];
    }

    /**
     * stampo per l'utente l'intera matrice
     */
    public void stampaEquilibrio()
    {
        for (int i = 0; i < Main.N; i++)
        {
            String temp1=Integer.toString(matrice[i][0]);
            if (temp1.length()==1) System.out.print("  "+matrice[i][0]); // 4 spazi con singola cifra (6)
            else if(temp1.length()==2) System.out.print(" "+matrice[i][0]); // 3 spazi con doppia cifra o negativo (-6 o 10)
            else System.out.print(matrice[i][0]); // 2 spazi con tripla cifra (-10)

            for (int j = 1; j < Main.N; j++)
            {

                String temp;
                temp = Integer.toString(matrice[i][j]);

                if (temp.length()==1) System.out.print( "    "+matrice[i][j]); // 4 spazi con singola cifra (6)
                else if(temp.length()==2) System.out.print("   "+matrice[i][j]); // 3 spazi con doppia cifra o negativo (-6 o 10)
                else System.out.print("  "+matrice[i][j]); // 2 spazi con tripla cifra (-10)
            }
            System.out.println();

        }

    }


}
