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
                if (c.x == b.x && c.y == b.y) b = c;
            }
        }
    }

    public Case[][] getCases() {
        return cases;
    }

    public boolean alignementHorizontal(Case c) {                                                                   //verifie alignement horizontal
        int cmp = 0;                                                                                                //  _____________________         //  |___|___|___|___|___|
        int x = c.x;                                                                                                //  |___|___|___|___|___|
        int y = c.y;                                                                                                //  |---|---|---|-> |___|
        Case curseur;                                                                                               //  |___|___|___|___|___|
        int j = 0;                                                                                                  //  |___|___|___|___|___|
        int cmpF = 0;
        boolean b = false;
        while(j < cases[x].length){
            curseur = cases[x][j];
            if(curseur.x == c.x && curseur.y == c.y) b = true;
            if(curseur.isEmpty() || !(curseur.pion.toString().equals(c.pion.toString()))){
                b = false;
                cmp = 0;
            }
            if(!curseur.isEmpty() && c.pion.toString().equals(curseur.pion.toString())){
                cases[x][j].setPassage(true);
                cmp ++;
            }else cmp = 0;
            if(cmp >= 5 && b){
                int i = curseur.y + 1 - cmp;
                System.out.println("i: "+i+" curseur.y: "+curseur.y+" curseur.x: "+curseur.x);
                while (cmp >= 0 && i < cases[x].length){
                    curseur = cases[x][i];
                    if(curseur.x == c.x && curseur.y == c.y) {
                        if (i+1 < cases[x].length) c.setPassage(true);
                    }
                    if(curseur.passage) cmpF++;
                    cmp--;
                    i++;
                }
                return (cmpF >= 5 && c.passage);
            }
            j++;
        }
        return (cmp == 0 && cmpF >= 5 && c.passage);
    }

    public boolean alignementVertical(Case c) {                                                                     //verifie alignement vertical
        int cmp =0;                                                                                                 //  _____________________
        boolean b = false;                                                                                          //  |___|___|___| | |___|
        int x = c.x;                                                                                       //  |___|___|___| | |___|
        int y = c.y;                                                                                       //  |___|___|___| | |___|
        Case curseur = cases[0][y];                                                                                 //  |___|___|___| v |___|
        int i = 0;                                                                                                  //  |___|___|___|___|___|
        int cmpF = 0;
        while(i < cases[x].length){
            curseur = cases[i][y];
            if(curseur.x == c.x && curseur.y == c.y) b = true;
            if(curseur.pion == null || !(curseur.pion.toString().equals(c.pion.toString()))){
                b = false;
                cmp = 0;
            }
            if(!curseur.isEmpty() && c.pion.toString().equals(curseur.pion.toString())){
                cases[i][y].setPassage(true);
                cmp ++;
            }
            if(cmp >= 5 && b){
                int j = curseur.x + 1 - cmp;
                while (cmp >= 0 && j < cases[x].length){
                    curseur = cases[j][y];
                    if(curseur.x == c.x && curseur.y == c.y){
                        c.setPassage(true);
                    }
                    if(curseur.passage) cmpF++;
                    cmp--;
                    j++;
                }
                return (cmpF >= 5 && c.passage);
            }
            i++;
        }
        return (cmp >= 5 && c.passage);
    }

    public boolean alignementDiagonal1(Case c) {                                                                     // verifie alignement diagonal
        int x = c.x;                                                                                       //  _____________________
        int y = c.y;                                                                                       //  | \ |___|___|___|___|
        int cmp = 0;                                                                                                //  |___| \ |___|___|___|
        boolean b = false;                                                                                          //  |___|___| \ |___|___|
        Case curseur = null;                                                                                        //  |___|___|___|_| |___|
        int cmpF = 0;
        while (x >= 0 && y >= 0) {                                                                                    //  |___|___|___|___|___|
            curseur = cases[x][y];
            x -= 1;
            y -= 1;
        }
        int i = curseur.x;
        int j = curseur.y;
        while (i < cases.length && j < cases.length) {
            curseur = cases[i][j];
            if (curseur.x == c.x && curseur.y == c.y) b = true;
            if (curseur.pion == null || !(curseur.pion.toString().equals(c.pion.toString()))) {
                b = false;
                cmp = 0;
            }
            if (!curseur.isEmpty() && c.pion.toString().equals(curseur.pion.toString())) {
                cases[i][j].setPassage(true);
                cmp++;
            }else cmp = 0;
            if (cmp >= 5 && b) {
                int ii = curseur.x + 1 - cmp;
                int jj = curseur.y + 1 - cmp;
                while (cmp >= 0 && ii < cases.length && jj < cases.length) {
                    curseur = cases[ii][jj];
                    if (curseur.x == c.x && curseur.y == c.y) {
                        c.setPassage(true);
                    }
                    if (curseur.passage) cmpF++;
                    cmp--;
                    ii++;
                    jj++;
                }
                return (cmpF >= 5 && c.passage);
            }
                i++;
                j++;
        }
        return (cmpF >= 5 && c.passage);
    }

    public boolean alignementDiagonal2(Case c){                                                                     // verifie alignement autre diagonal
        int x = c.x;                                                                                       //  _____________________
        int y = c.y;                                                                                       //  |___|___|___|___| / |
        int cmp = 0;                                                                                                //  |___|___|___| / |___|
        boolean b = false;                                                                                          //  |___|___| / |___|___|
        Case curseur = null;                                                                                        //  |___| L |___|___|___|
       int cmpF = 0;
        while(x < cases.length && y >= 0){                                                                          //  |___|___|___|___|___|
            curseur = cases[x][y];
            x += 1;
            y -= 1;
        }
        int i = curseur.x;
        int j = curseur.y;
        while( i >= 0 && j < cases.length){
            curseur = cases[i][j];
            if(curseur.x == c.x && curseur.y == c.y) b = true;
            if(curseur.pion == null || !(curseur.pion.toString().equals(c.pion.toString()))){
                b = false;
                cmp = 0;
            }
            if(!curseur.isEmpty() && c.pion.toString().equals(curseur.pion.toString())){
                cases[i][j].setPassage(true);
                cmp ++;
            }else cmp = 0;
            if (cmp >= 5 && b) {
                int ii = curseur.x - 1 + cmp;
                int jj = curseur.y + 1 - cmp;
                while (cmp >= 0 && ii < cases.length && jj < cases.length) {
                    curseur = cases[ii][jj];
                    if (curseur.x == c.x && curseur.y == c.y) {
                        c.setPassage(true);
                    }
                    if (curseur.passage) cmpF++;
                    cmp--;
                    ii--;
                    jj++;
                }
                return (cmpF >= 5 && c.passage);
            }
            i--;
            j++;
        }
        return (cmpF >= 5 && c.passage);
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
