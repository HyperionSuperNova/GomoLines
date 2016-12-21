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
        public boolean passage; // cette variable va nous servir dans les alignements pour vérifier
                                // que l'on est bien passé par la lors de la vérification des alignements

        public Case(int x, int y){
            this.x = x;
            this.y = y;
            this.pion = null;
            this.passage = false;
        }

        public Case(int x, int y, Pion p){
            this(x,y);
            this.pion = p;
        }


        public void fabrique(String s) {
            this.pion = Pion.factory(s);
        }

        public void setPion(Pion a){
            this.pion = a;
        }

        // teste si le pion courant est le même que le pion passé en argument
        public boolean isItEquals(Pion a){
            return pion.toString().equals(a.toString());
        }

        public boolean isEmpty(){
            return pion == null;
        }

        public void setPassage(boolean b){
            this.passage = b;
        }
    }
    public Case[][] getCases(){
        return cases;
    }
    public int getLongueur(){
        return longueur;
    }
    public int getLargeur(){
        return largeur;
    }
    public abstract Joueur getJoueur(String j);
    public abstract boolean alignement(Case cas);
}
