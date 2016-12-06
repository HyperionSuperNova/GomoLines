import java.util.Scanner;

/**
 * Created by merat on 17/11/16.
 */
public class PlateauGomoku extends Plateau {


    public PlateauGomoku(int longueur, int largeur, Joueur1Gomoku j1, Joueur2Gomoku j2) {
        super(longueur, largeur,j1,j2);
        cases = new CaseGomoku[longueur][largeur];
        initializeThePlate();
    }

    public int getLongueur() {
        return longueur;
    }

    public int getLargeur() {
        return largeur;
    }


    private void initializeThePlate() {
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                this.cases[i][j] = new CaseGomoku(i, j);
            }
        }
    }

    //Factorisation du code les deux boucles for dans un constructeur c'est pas hyper propre.


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

    public void setCase(CaseGomoku c) {  // peut etre a mettre dans case
        for (int i = 0; i < cases.length; i++) {
            for (Case b : cases[i]) {
                if (c.getX() == b.getX() && c.getY() == b.getY()) b = c;
            }
        }
    }

    public CaseGomoku[][] getCases() {
        return (CaseGomoku[][]) cases;
    }

    /*public void afficher() {
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
    }*/

    public boolean alignementVertical(Case c) {          //verifie alignement vertical
        int cmp = 1;
        for (int j = c.getY() + 1; j < cases[c.getX()].length; j++) {
            if (!cases[c.getX()][j].isEmpty() && cases[c.getX()][j].isItEquals(c.pion)) {
                cmp++;
            } else if (cmp < 5) return false;
            else return true;
        }
        return (cmp == 5);
    }

    public boolean alignementHorizontal(Case c) {          //verifie alignement horizontal
        int cmp = 1;
        for (int i = c.getX() + 1; i < cases.length; i++) {
            if (!cases[i][c.getY()].isEmpty() && cases[i][c.getY()].isItEquals(c.pion)) {
                cmp++;
            } else if (cmp < 5) return false;
            else return true;
        }
        return (cmp == 5);
    }

    public boolean alignementDiagonalAvantDroite(Case c) {           //verifie alignement Avant droite
        int cmp = 1;
        int x = c.getX();
        int y = c.getY();
        while (x > 0 && x < cases.length - 1 && y > 0 && y < cases.length - 1) {
            if (!cases[x + 1][y + 1].isEmpty() && cases[x + 1][y + 1].isItEquals(c.pion)) {
                cmp++;
                x += 1;
                y += 1;
            } else if (cmp < 5) return false;
            else return true;
        }
        return (cmp == 5);
    }

    public boolean alignementDiagonalAvantGauche(Case c) {               //verifie alignement Avant gauche
        int cmp = 1;
        int x = c.getX();
        int y = c.getY();
        while (x > 0 && x < cases.length - 1 && y > 0 && y < cases.length - 1) {
            if (!cases[x - 1][y + 1].isEmpty() && cases[x - 1][y + 1].isItEquals(c.pion)) {
                cmp++;
                x -= 1;
                y += 1;
            } else if (cmp < 5) return false;
            else return true;
        }
        return (cmp == 5);
    }

    public boolean alignementDiagonalArriereDroite(Case c) {              //verifie alignement Arriere droite
        int cmp = 1;
        int x = c.getX();
        int y = c.getY();
        while (x > 0 && x < cases.length - 1 && y > 0 && y < cases.length - 1) {
            if (!cases[x + 1][y - 1].isEmpty() && cases[x + 1][y - 1].isItEquals(c.pion)) {
                cmp++;
                x += 1;
                y -= 1;
            } else if (cmp < 5) return false;
            else return true;
        }
        return (cmp == 5);
    }

    public boolean alignementDiagonalArriereGauche(Case c) {             //verifie alignement Arriere gauche
        int cmp = 1;
        int x = c.getX();
        int y = c.getY();
        while (x > 0 && x < cases.length - 1 && y > 0 && y < cases.length - 1) {
            if (!cases[x - 1][y - 1].isEmpty() && cases[x - 1][y - 1].isItEquals(c.pion)) {
                cmp++;
                x -= 1;
                y -= 1;
            } else if (cmp < 5) return false;
            else return true;
        }
        return (cmp == 5);
    }

    public boolean alignement(Case c) {          // vérifie alignement peu importe le type d'alignement
        return (alignementVertical(c) || alignementHorizontal(c) || alignementDiagonalArriereDroite(c) ||
                alignementDiagonalArriereGauche(c) || alignementDiagonalAvantDroite(c) || alignementDiagonalAvantGauche(c));

    }

    public boolean isValid(Case c) {
        return c.isEmpty();

    }

    public void faire(int x, int y, boolean joueur) {
        Scanner sc = new Scanner(System.in);
        if (x >= 0 && x <= cases.length && y >= 0 && y < cases.length) {
            for (int i = 0; i < cases.length; i++) {
                for (int j = 0; j < cases[i].length; j++) {
                    if (x == i && y == j) {
                        if (isValid(cases[i][j])) {
                            if (joueur) {
                                ((CaseGomoku)cases[i][j]).setPionGomoku(true);
                                //blanc.setPions();
                                if (alignement(cases[i][j])) blanc.setScore(1);
                            } else {
                                ((CaseGomoku)cases[i][j]).setPionGomoku(false);
                                //noir.setPions();
                                if (alignement(cases[i][j])) noir.setScore(1);
                            }
                        } else {
                            System.out.println("Coup interdit!");
                            System.out.println("La case n'est pas vide!");
                            afficher();
                            System.out.println("Veuillez rentrer à nouveau des valeurs: ");
                            String s = sc.nextLine();
                            String[] t = s.split(",");
                            faire(Integer.parseInt(t[1]), Integer.parseInt(t[0]), joueur);
                        }
                    }
                }
            }
        } else {
            System.out.println("Valeurs incorrectes!");
            afficher();
            System.out.println("Veuillez entrer à nouveau des valeurs:");
            String s = sc.nextLine();
            String[] t = s.split(",");
            faire(Integer.parseInt(t[1]), Integer.parseInt(t[0]), joueur);
        }
    }

    public void afficherScore() {
        System.out.println("Score de " + blanc.getPseudo() + ": " + blanc.getScore());
        System.out.println("Score de " + noir.getPseudo() + ": " + noir.getScore());
        if (noir.getScore() > blanc.getScore()) System.out.println(noir.getPseudo() + " a gagné!");
        else if (noir.getScore() < blanc.getScore()) System.out.println(blanc.getPseudo() + " a gagné!");
        else System.out.println("Match nul!");
    }
}
