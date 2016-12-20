import java.util.Scanner;

/**
 * Created by merat on 17/11/16.
 */
public class PlateauGomoku extends Plateau {
    protected Joueur blanc;
    protected Joueur noir;

    public PlateauGomoku(int dimension, JoueurGomoku j1, JoueurGomoku j2) {
        super(dimension);
        cases = new Case[dimension][dimension];
        this.blanc = j1;
        this.noir = j2;
        this.initializeThePlate();
    }


    @Override
    public boolean alignement(Case c) {          // vérifie alignement peu importe le type d'alignement
        return (alignementVertical(c) || alignementHorizontal(c) || alignementDiagonal2(c) || alignementDiagonal1(c));
    }

    public void setCase(Case c) {  // peut etre a mettre dans case
        for (int i = 0; i < cases.length; i++) {
            for (Case b : cases[i]) {
                if (c.getCoorX() == b.getCoorX() && c.getCoorY() == b.getCoorY()) b = c;
            }
        }
    }

    public Case[][] getCases() {
        return cases;
    }

    public boolean alignementHorizontal(Case c) {                                                                   //verifie alignement horizontal
        int cmp = 0;                                                                                                 //  _____________________
        boolean b = false;                                                                                          //  |___|___|___|___|___|
        int x = c.getCoorX();                                                                                           //  |___|___|___|___|___|
        int y = c.getCoorY();                                                                                           //  |---|---|---|-> |___|
        Case curseur;                                                                                               //  |___|___|___|___|___|
        int j = 0;                                                                                                  //  |___|___|___|___|___|
        while(j < cases[x].length){
            curseur = cases[x][j];
            if(curseur.getCoorX() == c.getCoorX() && curseur.getCoorY() == c.getCoorY()) b = true;
            if(curseur.pion == null || !(curseur.pion.toString().equals(c.pion.toString()))) cmp = 0;
            if(!curseur.isEmpty() && c.pion.toString().equals(curseur.pion.toString())) cmp ++;
            if(cmp >= 5) return true;
            j++;
        }
        return (cmp >= 5 && b);
    }

    public boolean alignementVertical(Case c) {                                                                     //verifie alignement vertical
        int cmp =0;                                                                                                 //  _____________________
        boolean b = false;                                                                                          //  |___|___|___| | |___|
        int x = c.getCoorX();                                                                                           //  |___|___|___| | |___|
        int y = c.getCoorY();                                                                                           //  |___|___|___| | |___|
        Case curseur = cases[0][y];                                                                                 //  |___|___|___| v |___|
        int i = 0;                                                                                                  //  |___|___|___|___|___|
        while(i < cases[x].length){
            curseur = cases[i][y];
            if(curseur.getCoorX() == c.getCoorX() && curseur.getCoorY() == c.getCoorY()) b = true;
            if(curseur.pion == null || !(curseur.pion.toString().equals(c.pion.toString()))) cmp = 0;
            if(!curseur.isEmpty() && c.pion.toString().equals(curseur.pion.toString())) cmp ++;
            if(cmp >= 5) return true;
            i++;
        }
        return (cmp >= 5 && b);
    }

    public boolean alignementDiagonal1(Case c){                                                                     // verifie alignement diagonal
        int x = c.getCoorX();                                                                                           //  _____________________
        int y = c.getCoorY();                                                                                           //  | \ |___|___|___|___|
        int cmp = 0;                                                                                                //  |___| \ |___|___|___|
        boolean b = false;                                                                                          //  |___|___| \ |___|___|
        Case curseur = null;                                                                                        //  |___|___|___|_| |___|
        while(x >= 0 && y >= 0){                                                                                    //  |___|___|___|___|___|
            curseur = cases[x][y];
            x -= 1;
            y -= 1;
        }
        int i = curseur.getCoorX();
        int j = curseur.getCoorY();
        while( i < cases.length && j < cases.length){
            curseur = cases[i][j];
            if(curseur.getX() == c.getX() && curseur.getY() == c.getY()) b = true;
            if(curseur.pion == null || !(curseur.pion.toString().equals(c.pion.toString()))) cmp = 0;
            if(!curseur.isEmpty() && c.pion.toString().equals(curseur.pion.toString())) cmp ++;
            if(cmp >= 5) return true;
            i++;
            j++;
        }
        return (cmp >= 5 && b);
    }

    public boolean alignementDiagonal2(Case c){                                                                     // verifie alignement autre diagonal
        int x = c.getCoorX();                                                                                           //  _____________________
        int y = c.getCoorY();                                                                                           //  |___|___|___|___| / |
        int cmp = 0;                                                                                                //  |___|___|___| / |___|
        boolean b = false;                                                                                          //  |___|___| / |___|___|
        Case curseur = null;                                                                                        //  |___| L |___|___|___|
        while(x < cases.length && y >= 0){                                                                          //  |___|___|___|___|___|
            curseur = cases[x][y];
            x += 1;
            y -= 1;
        }
        int i = curseur.getCoorX();
        int j = curseur.getCoorY();
        while( i >= 0 && j < cases.length){
            curseur = cases[i][j];
            if(curseur.getCoorX() == c.getCoorX() && curseur.getCoorY() == c.getCoorY()) b = true;
            if(curseur.pion == null || !(curseur.pion.toString().equals(c.pion.toString()))) cmp = 0;
            if(!curseur.isEmpty() && c.pion.toString().equals(curseur.pion.toString())) cmp ++;
            if(cmp >= 5) return true;
            i--;
            j++;
        }
        return (cmp >= 5 && b);
    }


    public void afficherScore() {
        System.out.println("Score de " + blanc.getPseudo() + ": " + blanc.getScore());
        System.out.println("Score de " + noir.getPseudo() + ": " + noir.getScore());
        if (noir.getScore() > blanc.getScore()) System.out.println(noir.getPseudo() + " a gagné!");
        else if (noir.getScore() < blanc.getScore()) System.out.println(blanc.getPseudo() + " a gagné!");
        else System.out.println("Match nul!");
    }


    @Override
    public Joueur getJoueur(String j) {
        Joueur z = null;
        switch(j){
            case "blanc":
                z = this.blanc;
                break;
            case "noir":
                z = this.noir;
                break;
        }
        return z;
    }
}
