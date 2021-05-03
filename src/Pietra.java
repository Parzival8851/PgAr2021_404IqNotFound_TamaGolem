import java.util.Random;

public class Pietra {

    private char tipo;

    public char getTipo{
        return tipo;
    }


    /**
     * NON SO SE FUNZIONA MA DOVREBBE SERVIRE PER CREARE UN NUMERO A CASO NON SO SE FUNGE COME SRAND è DA PROVARE
     * E' PRESO D INTERNET
     */
    public static int randInt{
        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        //
        // In particular, do NOT do 'Random rand = new Random()' here or you
        // will get not very good / not very random results.
        Random rand;
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((10 - 1) + 1) + 1;

        return randomNum;
    }

    /**
     * sceglie l'elemento
     */
    public sceglieElementi(char tipo) {

        switch (randomNum) {
            case 1:
                Elementi tipo = Elementi.fuoco;
                break;
            case 2:
                Elementi tipo = Elementi.acqua;
                break;
            case 3:
                Elementi tipo = Elementi.terra;
                break;
            case 4:
                Elementi tipo = Elementi.aria;
                break;
            case 5:
                Elementi tipo = Elementi.ghiaccio;
                break;
            case 6:
                Elementi tipo = Elementi.nebbia;
                break;
            case 7:
                Elementi tipo = Elementi.fulmine;
            case 8:
                Elementi tipo = Elementi.sabbia;
                break;
            case 9:
                Elementi tipo = Elementi.luce;
                break;
            case 10:
                Elementi tipo = Elementi.tenebre;
                break;
        }
        return tipo;
    }

}



