import java.util.Random;

/**
 * Created by Stryker on 04/12/2016.
 */
public class PlateauColorLines extends Plateau{
    protected Joueur j1;
    public PlateauColorLines(int dimension, Joueur1ColorLines j1) {
        super(dimension);
        cases = new CaseColorLines[dimension][dimension];
        this.j1 = j1;
    }

    protected void initializeThePlate() {
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                this.cases[i][j] = new CaseColorLines(i, j);
            }
        }
    }

    @Override
    public int[] robot() {
        return new int[0];
    }

    public void deplacementDuPion(){}


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
            cmp++;
        }
    }

    public PlateauColorLines(int dimension) {
        super(dimension);
    }

    @Override
    protected void faire(int a, int b, boolean c) {

    }

    @Override
    public void afficherScore() {

    }

    @Override
    public Case[][] getCases() {
        return this.cases;
    }

    @Override
    public void jouer(String s) {

    }

    public static void main (String[] args) {
        Plateau a = new PlateauColorLines(5, new Joueur1ColorLines());
        while (!a.isFull()) {
            a.afficher();
            ((PlateauColorLines) a).putThreeColors();

        }
    }
}
