import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Stryker on 04/12/2016.
 */
public class PionColorLines extends Pion{
    Couleur couleur = Couleur.randomCouleur();

    enum Couleur{
        ROUGE,
        VERT,
        BLEU;

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


    public static void main (String[] args){
        PionColorLines a = new PionColorLines();
        a.afficher();
        PionColorLines b = new PionColorLines();
        b.afficher();
        PionColorLines c = new PionColorLines();
        c.afficher();
    }

}
