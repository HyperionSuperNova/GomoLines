/**
 * Created by Stryker on 28/11/2016.
 */
public abstract class Plateau {
    protected int longueur;
    protected int largeur;
    protected Case[][] cases;

    //Constructeur de Plateau est appelé par les constructeur de PlateauColorLines et PlateauGomoku
    //Représente les propriétés communes aux plateau des deux jeux.
    public Plateau(int dimension) {
        this.longueur = dimension;
        this.largeur = dimension;
    }

    //Vérifie si le plateau est vide ou non (en verifiant si il y'a un pion dans une case)
    public boolean isEmpty() {
        for (int i = 0; i < cases.length; i++) {
            for (Case c : cases[i]) {
                if (!c.isEmpty()) return false;
            }
        }
        return true;
    }

    //Affiche le plateau et ces Pions
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
        System.out.print("x/y ");
        for (int i = 0; i < cases.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    //Vérifie si le plateau est plein ou non sert de condition d'ârret parfois
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
