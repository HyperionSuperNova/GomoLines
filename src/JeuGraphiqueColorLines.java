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

    @Override
    public void maj(int x, int y) {
        if(p.cases[x][y].isEmpty() && !p.cases[tabsave[0]][tabsave[1]].isEmpty()) {
            p.cases[x][y].fabrique(p.cases[tabsave[0]][tabsave[1]].pion.toString());
            p.cases[tabsave[0]][tabsave[1]].pion = null;
            p.cases[tabsave[0]][tabsave[1]].setIcon(null);
            try {
                p.cases[x][y].setIcon(new ImageIcon(fabriqueimg(p.cases[x][y].pion)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println(p.cases[x][y] == null);
            p.alignement(p.cases[x][y]);
            v.setScore("  " + "Score" + " " + joue.getPseudo() + ":  " + joue.getScore());
            v.repaint();
            putThreeColors();
            v.setMessage("The piece has been dropped");
            turncounter++;
        }else{
            v.setMessage("Select a new pawn and put it elsewhere");
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

    public void pawnEraser() {
        for (int i = 0; i < p.cases.length; i++) {
            for (int j = 0; j < p.cases[i].length; j++) {
                if (p.cases[i][j].pion == null) {
                    p.cases[i][j].setIcon(null);
                }
            }
        }
    }


    @Override
    public void jouer(String s) {
        switch (s) {
            case "classique":
                this.game = s;
                break;
        }
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
