import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Stryker on 04/12/2016.
 */
public class PlateauColorLines extends Plateau {
    protected Joueur j1;

    public PlateauColorLines(int dimension, JoueurColorLines j1) {
        super(dimension);
        cases = new Case[dimension][dimension];
        this.j1 = j1;
    }

    public PlateauColorLines(int dimension) {
        super(dimension);
        initializeThePlate();
    }

    // vérifie si un alignement vertical s'est créé en bougeant le pion de la case passée en argument
    public boolean alignementVertical(Case c) {
        int cmp = 0;
        int x = c.x;
        int y = c.y;
        boolean b = false;
        boolean croisement = false;
        Case curseur;
        int curs = 0;
        String couleur = cases[x][y].pion.toString();
        int i = 0;
        int arc = 0;
        while (i < this.longueur) {
            curseur = cases[i][y];
            if (curseur.pion == null) {
                cmp = 0;
            } else {
                if (!curseur.pion.toString().equals("arcenciel") && !croisement) {
                    couleur = curseur.pion.toString();
                    croisement = true;
                }
                if(curseur.pion.toString().equals("arcenciel")){
                    arc++;
                }
                if (couleur.equals(curseur.pion.toString())  || curseur.pion.toString().equals("arcenciel")) {
                    cmp++;
                    curs = curseur.x;
                } else {
                    croisement = false;
                    cmp = arc;
                    arc = 0;
                    i--;
                }
            }
            if (cmp >= 5) {
                j1.setScore(cmp % 4);
                b = true;
                suppressionDesPionsVertical(i + (1 - cmp), curs, y, c);
            }
            i++;
        }
        return (b);
    }

    // vérifie si un alignement horizontal s'est créé en bougeant le pion de la case passée en argument
    public boolean alignementHorizontal(Case c) {
        int cmp = 0;
        int x = c.x;
        int y = c.y;
        int arc = 0;
        boolean b = false;
        boolean croisement = false;
        Case curseur;
        int curs = 0; // j'ai laissé curs car lorsque je fais curs+i ca me fait une grosse erreur de sa mére la pute comme tu dis si bien.
        int i = 0;
        String couleur = cases[x][y].pion.toString();
        while (i < this.largeur) {
            curseur = cases[x][i];
            if (curseur.pion == null) {
                cmp = 0;
            } else {
                if (!curseur.pion.toString().equals("arcenciel") && !croisement) {
                    couleur = curseur.pion.toString();
                    croisement = true;
                }
                if(curseur.pion.toString().equals("arcenciel")){
                    arc++;
                }
                if (couleur.equals(curseur.pion.toString())  || curseur.pion.toString().equals("arcenciel")) {
                    cmp++;
                    curs = curseur.y;
                } else {
                    croisement = false;
                    cmp = arc;
                    arc = 0;
                    i--;
                }
            }
            if (cmp >= 5) {
                j1.setScore(cmp % 4);
                b = true;
                suppressionDesPionsHorizontal(i + (1 - cmp), curs, x, c);
            }
            i++;
        }
        return (b);
    }

    // vérifie si un alignement diagonal (en partant de la case 0,0; haut gauche et allant jusqu'à la cases n,n; bas droit)
    // s'est créé en bougeant le pion de la case passée en argument
    public boolean alignementDiagonal1(Case c) {
        int x = c.x;
        int y = c.y;
        int cmp = 0;
        boolean b = false;
        boolean croisement = false;
        int arc = 0;
        String couleur = cases[x][y].pion.toString();
        Case curseur = null;
        int fin1 = 0;
        int fin2 = 0;
        while (x >= 0 && y >= 0) {
            curseur = cases[x][y];
            x -= 1;
            y -= 1;
        }
        x = curseur.x;
        y = curseur.y;
        int deb1 = curseur.x;
        int deb2 = curseur.y;
        while (x < cases.length && y < cases.length) {
            curseur = cases[x][y];
            if (curseur.pion == null) {
                cmp = 0;
            } else {
                if (!curseur.pion.toString().equals("arcenciel") && !croisement) {
                    couleur = curseur.pion.toString();
                    croisement = true;
                }
                if(curseur.pion.toString().equals("arcenciel")){
                    arc++;
                }
                if (couleur.equals(curseur.pion.toString()) || curseur.pion.toString().equals("arcenciel")) {
                    cmp++;
                    fin1 = curseur.x;
                    fin2 = curseur.y;
                } else {
                    croisement = false;
                    cmp = arc;
                    deb1 = curseur.x-arc;
                    deb2 = curseur.y-arc;
                    arc = 0;
                    x--;
                    y--;
                }
            }
            if (cmp >= 5) {
                j1.setScore(cmp % 4);
                b = true;
                suppressionDesPionsDiagonaux1(deb1, deb2, fin1, fin2, c);
            }
            x++;
            y++;
        }
        return (b);
    }

    // vérifie si un alignement diagonal (en partant de la case n,0; bas gauche et allant jusqu'à la cases 0,n; haut droit)
    // s'est créé en bougeant le pion de la case passée en argument
    public boolean alignementDiagonal2(Case c) {
        int x = c.x;
        int y = c.y;
        int cmp = 0;
        boolean croisement = false;
        boolean b = false;
        int arc = 0;
        String couleur = cases[x][y].pion.toString();
        Case curseur = null;
        int fin2 = 0;
        while (x < cases.length && y >= 0) {
            curseur = cases[x][y];
            x += 1;
            y -= 1;
        }
        x = curseur.x;
        y = curseur.y;
        int deb1 = curseur.x;
        int deb2 = curseur.y;
        while (x >= 0 && y < cases.length) {
            curseur = cases[x][y];
            if (curseur.pion == null) {
                cmp = 0;
            } else {
                if (!curseur.pion.toString().equals("arcenciel") && !croisement) {
                    couleur = curseur.pion.toString();
                    croisement = true;
                }
                if(curseur.pion.toString().equals("arcenciel")){
                    arc++;
                }
                if (couleur.equals(curseur.pion.toString()) || curseur.pion.toString().equals("arcenciel")) {
                    cmp++;
                    fin2 = curseur.y;
                } else {
                    croisement = false;
                    cmp = arc;
                    deb1 = curseur.x+arc;
                    deb2 = curseur.y-arc;
                    arc =0;
                    x++;
                    y--;
                }
            }
            if (cmp >= 5) {
                j1.setScore(cmp % 4);
                b = true;
                suppressionDesPionsDiagonaux2(deb1, deb2, fin2, c);
            }
            x--;
            y++;
        }
        return (b);
    }

    // teste si un des alignements a été réalisé. Si oui, ajoute un/des point(s) sinon ne fait rien
    public boolean alignement(Case cas) {
        boolean a = alignementVertical(cas);
        boolean b = alignementHorizontal(cas);
        boolean c = alignementDiagonal1(cas);
        boolean d = alignementDiagonal2(cas);
        if (a && b || a && c || a && d || b && c || b && d || c && d) {
            j1.setScore(2);
        }

        if (a && b && c || a && b && d || b && c && d || a && c && d) {
            j1.setScore(3);
        }
        if (a && b && c && d) {
            j1.setScore(4);
        }

        if (a || b || c || d) {
            cas.pion = null;
            cas.setIcon(null);
        }
        return a||b||c||d;
    }

    //si alignement Vertical il y a, alors on supprime les pions formant l'alignement
    public void suppressionDesPionsVertical(int debut, int fin, int y, Case c) {
        int i = debut;
        while (i < fin) {
            if (cases[i][y] != c) {
                cases[i][y].pion = null;
                cases[i][y].setIcon(null);
            }
            i++;
        }
    }

    //si alignement horizontal il y a, alors on supprime les pions formant l'alignement
    public void suppressionDesPionsHorizontal(int debut, int fin, int x, Case c) {
        int i = debut;
        while (i <= fin) {
            if (cases[x][i] != c) {
                cases[x][i].pion = null;
                cases[x][i].setIcon(null);
            }
            i++;
        }
    }

    //si alignement diagonal1 il y a, alors on supprime les pions formant l'alignement
    public void suppressionDesPionsDiagonaux1(int deb1, int deb2, int fin1, int fin2, Case c) {
        while (deb1 < fin1 && deb2 < fin2) {
            if (cases[deb1][deb2] != c) {
                cases[deb1][deb2].pion = null;
                cases[deb1][deb2].setIcon(null);
            }
            deb1++;
            deb2++;
        }
    }

    //si alignement diagonal2 il y a, alors on supprime les pions formant l'alignement
    public void suppressionDesPionsDiagonaux2(int deb1, int deb2, int fin2, Case c) {
        while (deb1 >= 0 && deb2 < fin2) {
            if (cases[deb1][deb2] != c) {
                cases[deb1][deb2].pion = null;
                cases[deb1][deb2].setIcon(null);
            }
            deb1--;
            deb2++;
        }
    }
    @Override
    public Joueur getJoueur(String j) {
        return this.j1;
    }
}
