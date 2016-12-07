/**
 * Created by Stryker on 28/11/2016.
 */
public abstract class Plateau {
    protected int longueur;
    protected int largeur;
    protected Case[][] cases;

    //Retrait d'élément superflue mettre this.blanc = blanc pour après le déclarer new ne sert à rien
    public Plateau(int dimension) {
        this.longueur = dimension;
        this.largeur = dimension;
        //initializeThePlate();
    }

    public boolean isEmpty() {
        for (int i = 0; i < cases.length; i++) {
            for (Case c : cases[i]) {
                if (!c.isEmpty()) return false;
            }
        }
        return true;
    }

    public void afficher() {
        int cmp = 0;               //affiche le plateau
        System.out.println();
        for (int i = 0; i < cases.length; i++) {
            System.out.print(cmp + "   ");
            cmp++;
            for (int j = 0; j < cases[i].length; j++) {
                cases[i][j].afficher();
            }
            System.out.println();
        }
        System.out.println();
        System.out.print("y/x ");
        for (int i = 0; i < cases.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public boolean isFull() {
        for (int i = 0; i < cases.length; i++) {
            for (Case c : cases[i]) {
                if (c.isEmpty()) return false;
            }
        }
        return true;
    }


    public boolean isValid(Case c) {
        return c.isEmpty();
    }

    public int getLongueur(){return this.longueur;}
    public int getLargeur(){return this.largeur;}
    protected abstract void faire(int a, int b, boolean c);
    public abstract void afficherScore();
    protected abstract void initializeThePlate();
    public abstract Case[][] getCases();
    public abstract void jouer(String s);
    public abstract int[] robot();

}
