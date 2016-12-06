import java.util.Random;

/**
 * Created by Stryker on 04/12/2016.
 */
public class PlateauColorLines extends Plateau{

    public PlateauColorLines(int longueur, int largeur, Joueur1Gomoku jb, Joueur2Gomoku jn) {
        super(longueur, largeur, jb, jn);
        cases = new CaseColorLines[longueur][largeur];
        initializeThePlate();
    }

    private void initializeThePlate() {
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                this.cases[i][j] = new CaseColorLines(i, j);
            }
        }
    }

    public boolean isEmpty() {
        for (int i = 0; i < cases.length; i++) {
            for (Case c : cases[i]) {
                if (!c.isEmpty()) return false;
            }
        }
        return true;
    }

    public boolean isFull() {
        for (int i = 0; i < cases.length; i++) {
            for (Case c : cases[i]) {
                if (c.isEmpty()) return false;
            }
        }
        return true;
    }




    public void putThreeColors(){
        int cmp = 0;
        while(cmp != 3){
            int a = new Random().nextInt(longueur);
            int b = new Random().nextInt(largeur);
            while(!cases[a][b].isEmpty()){
                a = new Random().nextInt(longueur);
                b = new Random().nextInt(largeur);
            }
            ((CaseColorLines)cases[a][b]).setPion(new PionColorLines());
            this.afficher();
            cmp++;
        }
    }

    public static void main (String[] args){
        Plateau a = new PlateauColorLines(5,5,null,null);
        a.afficher();
        ((PlateauColorLines)a).putThreeColors();
    }
}
