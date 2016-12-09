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
    }

    @Override
    public void jouer(String s) {
        switch (s) {
            case "robot":
                while (!this.isFull()) {
                    String[] t1 = this.blanc.jouer();
                    this.faire(Integer.parseInt(t1[0]), Integer.parseInt(t1[1]), true);
                    int [] rob = this.robot();
                    this.faire(rob[0], rob[1], false);
                    this.afficher();
                }
                break;
            case "humain":
                this.afficher();
                while (!this.isFull()) {
                    String[] t1 = this.blanc.jouer();
                    this.faire(Integer.parseInt(t1[0]), Integer.parseInt(t1[1]), true);
                    this.afficher();
                    String[] t2 = this.noir.jouer();
                    this.faire(Integer.parseInt(t2[0]), Integer.parseInt(t2[1]), false);
                    this.afficher();
                }
                break;
        }
        this.afficherScore();
    }

    //Factorisation du code les deux boucles for dans un constructeur c'est pas hyper propre.

    public int[] robot(){
        int a = (int)(Math.random()*this.getLongueur());
        int b = (int)(Math.random()*this.getLargeur());
        while(!this.getCases()[a][b].isEmpty()){
            a = (int)(Math.random()*this.getLongueur());
            b = (int)(Math.random()*this.getLongueur());
        }
        System.out.println("Coup " + this.noir.getPseudo() + ": "+a+","+b);
        return new int[]{a,b};
    }




    public void setCase(Case c) {  // peut etre a mettre dans case
        for (int i = 0; i < cases.length; i++) {
            for (Case b : cases[i]) {
                if (c.getX() == b.getX() && c.getY() == b.getY()) b = c;
            }
        }
    }

    public Case[][] getCases() {
        return cases;
    }

    public boolean alignementHorizontal(Case c) {                                                                   //verifie alignement horizontal
        int cmp =0;                                                                                                 //  _____________________
        boolean b = false;                                                                                          //  |___|___|___|___|___|
        int x = c.getX();                                                                                           //  |___|___|___|___|___|
        int y = c.getY();                                                                                           //  |---|---|---|-> |___|
        Case curseur = cases[x][0];                                                                                 //  |___|___|___|___|___|
        int j = 0;                                                                                                  //  |___|___|___|___|___|
        while(j < cases[x].length){
            curseur = cases[x][j];
            if(curseur.getX() == c.getX() && curseur.getY() == c.getY()) b = true;
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
        int x = c.getX();                                                                                           //  |___|___|___| | |___|
        int y = c.getY();                                                                                           //  |___|___|___| | |___|
        Case curseur = cases[0][y];                                                                                 //  |___|___|___| v |___|
        int i = 0;                                                                                                  //  |___|___|___|___|___|
        while(i < cases[x].length){
            curseur = cases[i][y];
            if(curseur.getX() == c.getX() && curseur.getY() == c.getY()) b = true;
            if(curseur.pion == null || !(curseur.pion.toString().equals(c.pion.toString()))) cmp = 0;
            if(!curseur.isEmpty() && c.pion.toString().equals(curseur.pion.toString())) cmp ++;
            if(cmp >= 5) return true;
            i++;
        }
        return (cmp >= 5 && b);
    }

    public boolean alignementDiagonal1(Case c){                                                                     // verifie alignement diagonal
        int x = c.getX();                                                                                           //  _____________________
        int y = c.getY();                                                                                           //  | \ |___|___|___|___|
        int cmp = 0;                                                                                                //  |___| \ |___|___|___|
        boolean b = false;                                                                                          //  |___|___| \ |___|___|
        Case curseur = null;                                                                                        //  |___|___|___|_| |___|
        while(x >= 0 && y >= 0){                                                                                    //  |___|___|___|___|___|
            curseur = cases[x][y];
            x -= 1;
            y -= 1;
        }
        int i = curseur.getX();
        int j = curseur.getY();
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
        int x = c.getX();                                                                                           //  _____________________
        int y = c.getY();                                                                                           //  |___|___|___|___| / |
        int cmp = 0;                                                                                                //  |___|___|___| / |___|
        boolean b = false;                                                                                          //  |___|___| / |___|___|
        Case curseur = null;                                                                                        //  |___| L |___|___|___|
        while(x < cases.length && y >= 0){                                                                          //  |___|___|___|___|___|
            curseur = cases[x][y];
            x += 1;
            y -= 1;
        }
        int i = curseur.getX();
        int j = curseur.getY();
        while( i >= 0 && j < cases.length){
            curseur = cases[i][j];
            if(curseur.getX() == c.getX() && curseur.getY() == c.getY()) b = true;
            if(curseur.pion == null || !(curseur.pion.toString().equals(c.pion.toString()))) cmp = 0;
            if(!curseur.isEmpty() && c.pion.toString().equals(curseur.pion.toString())) cmp ++;
            if(cmp >= 5) return true;
            i--;
            j++;
        }
        return (cmp >= 5 && b);
    }

    public boolean alignement(Case c) {          // vérifie alignement peu importe le type d'alignement
        return (alignementVertical(c) || alignementHorizontal(c) || alignementDiagonal2(c) || alignementDiagonal1(c));

    }

    public void faire(int x, int y, boolean joueur) {
        Scanner sc = new Scanner(System.in);
        if (x >= 0 && x <= cases.length && y >= 0 && y < cases.length) {
            for (int i = 0; i < cases.length; i++) {
                for (int j = 0; j < cases[i].length; j++) {
                    if (x == i && y == j) {
                        if (cases[i][j].isEmpty()) {
                            if (joueur) {
                                cases[i][j].fabrique("blanc");
                                if (alignement(cases[i][j])) blanc.setScore(1);
                            } else {
                                cases[i][j].fabrique("noir");
                                if (alignement(cases[i][j])) noir.setScore(1);
                            }
                            System.out.println("Score blanc: "+blanc.getScore());
                            System.out.println("Score noir: "+noir.getScore());
                        } else {
                            System.out.println("Coup interdit!");
                            System.out.println("La case n'est pas vide!");
                            afficher();
                            System.out.println("Veuillez rentrer à nouveau des valeurs: ");
                            String s = sc.nextLine();
                            String[] t = s.split(",");
                            faire(Integer.parseInt(t[0]), Integer.parseInt(t[1]), joueur);
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
