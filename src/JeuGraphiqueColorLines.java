import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by merat on 15/12/16.
 */
public class JeuGraphiqueColorLines extends JeuGraphique {
    Joueur joue = p.getJoueur("");

    public JeuGraphiqueColorLines(PlateauColorLines colorLines) {
        super(colorLines);
        putThreeColors();
        v.setScore("  " + "Score" + " " + joue.getPseudo() + ":  " + joue.getScore());
    }

    String game;

    public void putThreeColors() {
        int cmp = 0;
        while (cmp != 3) {
            if (!p.isFull()) {
                int a = new Random().nextInt(p.longueur);
                int b = new Random().nextInt(p.largeur);
                while (!p.cases[a][b].isEmpty()) {
                    a = new Random().nextInt(p.longueur);
                    b = new Random().nextInt(p.largeur);
                }
                p.cases[a][b].pion = new PionColorLines();
                try {
                    this.p.cases[a][b].setIcon(new ImageIcon(fabriqueimg(p.cases[a][b].pion)));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                cmp++;
            } else {
                cmp = 3;
                endGame();
            }
        }
    }

    public void putThreeParticularColors(String s) {
        int cmp = 0;
        while (cmp != 3) {
            if (!p.isFull()) {
                int a = new Random().nextInt(p.longueur);
                int b = new Random().nextInt(p.largeur);
                while (!p.cases[a][b].isEmpty()) {
                    a = new Random().nextInt(p.longueur);
                    b = new Random().nextInt(p.largeur);
                }
                p.cases[a][b].pion = new PionColorLines();
                try {
                    this.p.cases[a][b].setIcon(new ImageIcon(fabriqueimg(new PionColorLines(s))));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                cmp++;
            } else {
                cmp = 3;
                endGame();
            }
        }
    }

    @Override
    public void maj(int x, int y) {
        if (p.cases[x][y].isEmpty() && !p.cases[tabsave[0]][tabsave[1]].isEmpty()) {
            p.cases[x][y].fabrique(p.cases[tabsave[0]][tabsave[1]].pion.toString());
            p.cases[tabsave[0]][tabsave[1]].pion = null;
            p.cases[tabsave[0]][tabsave[1]].setIcon(null);
            try {
                p.cases[x][y].setIcon(new ImageIcon(fabriqueimg(p.cases[x][y].pion)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            p.alignement(p.cases[x][y]);
            v.setScore("  " + "Score" + " " + joue.getPseudo() + ":  " + joue.getScore());
            v.repaint();
            if (game.equals("variante") && joue.score % 10 == 0 && joue.score != 0) {
                JFrame frame = new JFrame("Bonus");
                String[] choice = {"Red", "Green", "Blue", "ArcEnCiel","Refuse"};
                String res = (String) JOptionPane.showInputDialog(frame, "You get the ability to choose the color of the new pawn the robot's gonna give you in exchange of a fourth of your points ! \n Choose Wisely !", "Choices", JOptionPane.QUESTION_MESSAGE, null, choice, choice[0]);
                if (res.equals("Red") || res.equals("Green") || res.equals("Blue") || res.equals("ArcEnCiel")) {
                    putThreeParticularColors(res.toLowerCase());
                    joue.score = joue.score / 4;
                } else {
                    putThreeColors();
                }
            } else {
                putThreeColors();
            }
            v.setMessage("The piece has been dropped");
            turncounter++;
        } else {
            v.setMessage("Select a new pawn and put it elsewhere");
            turncounter = 0;
        }
    }


    public Image fabriqueimg(Pion a) {
        try {
            Image img = null;
            if (a.toString().equals("rouge")) {
                img = ImageIO.read(getClass().getResource("PionRouge.png"));
            } else if (a.toString().equals("vert")) {
                img = ImageIO.read(getClass().getResource("PionVert.png"));
            } else if (a.toString().equals("bleu")) {
                img = ImageIO.read(getClass().getResource("PionBleu.png"));
            } else {
                img = ImageIO.read(getClass().getResource("PionArcEnCiel.png"));
            }
            return img;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public void jouer(String s) {
        this.game = s;
    }

    @Override
    public void turnShow() {

    }

    @Override
    public void showScore() {
        JFrame frame = new JFrame();
        String j1 = "Score de " + joue.getPseudo() + ": " + joue.getScore();
        JOptionPane.showMessageDialog(frame, j1);
    }
}
