//import it.unibs.fp.mylib.InputDati;

import it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;

public class Giocatore {
    private String nome;
    private Tamagolem golem;
    private int roundMax=1;

    public Giocatore(String nome){
        golem = new Tamagolem();
        this.nome=nome;
    }

    private void scegliNome(){
        this.nome= InputDati.leggiStringa("Inserisci il nome del player");
    }

    public String getNome(){
        return nome;
    }

    public Tamagolem getGolem() {
        return golem;
    }

    public int getRoundMax() {
        return roundMax;
    }
}
