
//import it.unibs.fp.mylib.EstrazioniCasuali;


public class Pietra {

    private String tipo;
    private int indice;

    public String getTipo(){
        return tipo;
    }

    /**
     * sceglie l'elemento
     */
    public Pietra(int i)
    {
        setTipo(i);
    }

    public int getIndice() {
        return indice;
    }



    public void setTipo(int i)
    {
        indice=i;

        switch (i)
        {
            case 0:
                tipo = "fuoco";
                break;
            case 1:
                tipo = "acqua";
                break;
            case 2:
                tipo = "terra";
                break;
            case 3:
                tipo = "aria";
                break;
            case 4:
                tipo ="ghiaccio";
                break;
            case 5:
                tipo = "nebbia";
                break;
            case 6:
                 tipo = "fulmine";
                 break;
            case 7:
                 tipo = "sabbia";
                break;
            case 8:
                tipo = "luce";
                break;
            case 9:
                tipo = "tenebre";
                break;
        }

    }

}



