import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Stryker on 04/12/2016.
 */
public class PionColorLines extends Pion{
    Couleur couleur;

    public PionColorLines(){
        this.couleur = Couleur.randomCouleur();
    }

    public PionColorLines(String s){
        switch(s){
            case "rouge":
                this.couleur = Couleur.ROUGE;
                break;
            case "bleu":
                this.couleur = Couleur.BLEU;
                break;
            case "vert":
                this.couleur = Couleur.VERT;
                break;
            case "arcenciel":
                this.couleur = Couleur.ARCENCIEL;
                break;
            default:
                this.couleur = Couleur.randomCouleur();
                break;
        }
    }

    enum Couleur{
        ROUGE,
        VERT,
        BLEU,
        ARCENCIEL;

        private static List<Couleur> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static Couleur randomCouleur()  {
            return VALUES.get(new Random().nextInt(SIZE));
        }
    }

    @Override
    public void afficher() {
        if(this.couleur.name().equals("ROUGE")){
            System.out.print("R ");
        }else if(this.couleur.name().equals("VERT")){
            System.out.print("V ");
        }else{
            System.out.print("B ");
        }
    }

    public String toString(){
        return this.couleur.name();
    }
}
