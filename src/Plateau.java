import javax.swing.*;

/**
 * Created by Stryker on 28/11/2016.
 */
public abstract class Plateau {
    protected int longueur;
    protected int largeur;
    protected Case[][] cases;

    //Constructeur de Plateau est appelé par les constructeurs de PlateauColorLines et PlateauGomoku
    //Représente les propriétés communes au plateau des deux jeux.
    public Plateau(int dimension) {
        this.longueur = dimension;
        this.largeur = dimension;
    }

    //Vérifie si le plateau est vide ou non (en vérifiant si il y'a un pion dans une case)
    public boolean isEmpty() {
        for (int i = 0; i < cases.length; i++) {
            for (Case c : cases[i]) {
                if (!c.isEmpty()) return false;
            }
        }
        return true;
    }

    //Affiche le plateau et ses Pions
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

    //Vérifie si le plateau est plein ou non. Sert de condition d'ârret parfois
    public boolean isFull() {
        for (int i = 0; i < cases.length; i++) {
            for (Case c : cases[i]) {
                if (c.isEmpty()) return false;
            }
        }
        return true;
    }

    public void initializeThePlate() {
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                this.cases[i][j] = new Case(i, j);
            }
        }
    }

    class Case extends JButton {
        public int x;
        public int y;
        public Pion pion;

        public Case(int x, int y){
            this.x = x;
            this.y = y;
            this.pion = null;
        }

        public Case(int x, int y, Pion p){
            this(x,y);
            this.pion = p;
        }

        public void afficher(){
            if(!isEmpty()) pion.afficher();
            else System.out.print("- ");
        }

        public int getCoorX(){
            return this.x;
        }

        public int getCoorY(){
            return this.y;
        }

        public Pion getPion() {
            return pion;
        }

        public void fabrique(String s) {
            this.pion = Pion.factory(s);
        }

        public void setPion(Pion a){
            this.pion = a;
        }

        public boolean isItEquals(Pion a){
            return pion.toString().equals(a.toString());
        }

        public boolean isEmpty(){
            return pion == null;
        }
    }
    public abstract Case[][] getCases();
    public int getLongueur(){
        return longueur;
    }
    public int getLargeur(){
        return largeur;
    }
    public abstract Joueur getJoueur(String j);
    public abstract boolean alignement(Case cas);
}
