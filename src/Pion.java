/**
 * Created by Stryker on 04/12/2016.
 */
public abstract class Pion {

    public static Pion factory(String s){
        switch(s){
            case "rouge":
            case "bleu":
            case "vert":
            case "arcenciel":
                return new PionColorLines(s);
            case "noir":
            case "blanc":
                return new PionGomoku(s);
        }
        return null;
    }

    public abstract void afficher();
    public abstract String toString();
}
