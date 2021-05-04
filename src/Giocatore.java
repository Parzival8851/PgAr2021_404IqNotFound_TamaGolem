//import it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;

public class Giocatore {
    private String nome;
    private ArrayList<Tamagolem> squadra;

    public Giocatore(String nome){
        squadra = new ArrayList<Tamagolem>();
        this.nome=nome;
    }

    private void scegliNome(){
        //this.nome= InputDati.leggiStringa("Inserisci il nome del player");
    }

    public String getNome(){
        return nome;
    }

    public int numGolem(){
            return squadra.size();
        }

    public void addGolem(Tamagolem golem){
        squadra.add(golem);
    }

    public void eliminaGolem(Tamagolem golem){
        squadra.remove(golem);
    }


}
