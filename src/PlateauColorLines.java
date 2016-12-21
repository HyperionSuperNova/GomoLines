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

    public void putThreeColors() {
        int cmp = 0;
        while (cmp != 3) {
            int a = new Random().nextInt(longueur);
            int b = new Random().nextInt(largeur);
            while (!cases[a][b].isEmpty()) {
                a = new Random().nextInt(longueur);
                b = new Random().nextInt(largeur);
            }
            cases[a][b].pion = new PionColorLines();
            cmp++;
        }
    }

    public void launch() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel pion souhaitez vous déplacer ?");
        String[] t = sc.nextLine().split(",");
        int a = Integer.parseInt(t[0]);
        int b = Integer.parseInt(t[1]);
        if (cases[a][b].pion == null) {
            System.out.println("Impossible de déplacer ce pion! La case est vide!");
            launch();
        } else {
            System.out.println("Ou souhaitez vous déplacer le pion ?");
            String[] z = new Scanner(System.in).nextLine().split(",");
            faire(Integer.parseInt(z[0]), Integer.parseInt(z[1]), cases[a][b]);

        }
    }

    protected void faire(int x, int y, Case c) {
        if (x >= 0 && x <= cases.length && y >= 0 && y < cases.length) {
            for (int i = 0; i < cases.length; i++) {
                for (int j = 0; j < cases[i].length; j++) {
                    if (x == i && y == j) {
                        if (cases[i][j].isEmpty()) {
                            switch (c.getPion().toString()) {
                                case "rouge":
                                    cases[i][j].fabrique("rouge");
                                    break;
                                case "vert":
                                    cases[i][j].fabrique("vert");
                                    break;
                                case "bleu":
                                    cases[i][j].fabrique("bleu");
                                    break;
                                case "arcenciel":
                                    cases[i][j].fabrique("arcenciel");
                                    break;
                            }
                            c.setPion(null);
                            alignement(cases[i][j]);
                        } else {
                            System.out.println("Coup interdit!");
                            System.out.println("La case n'est pas vide!");
                            afficher();
                            System.out.println("Veuillez rentrer à nouveau des valeurs: ");
                            String s = new Scanner(System.in).nextLine();
                            String[] t = s.split(",");
                            faire(Integer.parseInt(t[0]), Integer.parseInt(t[1]), c);
                        }
                    }
                }
            }
        } else {
            System.out.println("Valeurs incorrectes!");
            afficher();
            System.out.println("Veuillez entrer à nouveau des valeurs:");
            String s = new Scanner(System.in).nextLine();
            String[] t = s.split(",");
            faire(Integer.parseInt(t[0]), Integer.parseInt(t[1]), c);
        }
    }

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
    public Case[][] getCases() {
        return this.cases;
    }

    @Override
    public Joueur getJoueur(String j) {
        return this.j1;
    }
}
