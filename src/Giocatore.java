//import it.unibs.fp.mylib.InputDati;

import it.unibs.fp.mylib.InputDati;


public class Giocatore
{
    public static final String NOME_GIOCATORE = "Inserisci il nome del player: ";
    private String nome;
    private Tamagolem golem;
    private int roundMax=1;

    public Giocatore()
    {
        golem = new Tamagolem();
        scegliNome();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private void scegliNome()
    {
        this.nome= InputDati.leggiStringa(NOME_GIOCATORE);
    }

    public String getNome(){
        return nome;
    }

    public Tamagolem getGolem() {
        return golem; }

    public int getRoundMax() {
        return roundMax;
    }

    public void aggiornaRound()
    {
        roundMax++;
    }

    public int roundRimanenti()
    {
        return Main.G-roundMax+1;
    }
}
