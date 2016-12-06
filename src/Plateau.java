/**
 * Created by Stryker on 28/11/2016.
 */
public abstract class Plateau {
    protected int longueur;
    protected int largeur;
    protected Case[][] cases;
    protected Joueur blanc;
    protected Joueur noir;

    //Retrait d'élément superflue mettre this.blanc = blanc pour après le déclarer new ne sert à rien
    public Plateau(int longueur, int largeur, Joueur1Gomoku jb, Joueur2Gomoku jn) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.blanc = jb;
        this.noir = jn;
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
        System.out.print("  X ");
        for (int i = 0; i < cases.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Y");
        for (int i = 0; i < cases.length; i++) {
            System.out.print(cmp + "   ");
            cmp++;
            for (int j = 0; j < cases[i].length; j++) {
                cases[i][j].afficher();
            }
            System.out.println();
        }
    }

    public boolean isFull() {
        for (int i = 0; i < cases.length; i++) {
            for (Case c : cases[i]) {
                if (c.isEmpty()) return false;
            }
        }
        return true;
    }

}
