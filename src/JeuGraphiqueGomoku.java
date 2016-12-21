import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by merat on 15/12/16.
 */
public class JeuGraphiqueGomoku extends JeuGraphique {
    Joueur a = p.getJoueur("blanc");
    Joueur b = p.getJoueur("noir");
    public JeuGraphiqueGomoku(PlateauGomoku gomoku) {
        super(gomoku);
        turnShow();
        v.setScore("  " + "Score" + " " + a.getPseudo() + ":  " + a.getScore() + " " + "Score " + b.getPseudo() + ": " + b.getScore());
    }
    String game;
    private static int turn = 0;
    public void jouer(String s) {
        this.game = s;
    }

    public void alignement(Plateau.Case cas, Joueur j){
        if(p.alignement(cas)){
            j.setScore(1);
        }
    }

    @Override
    public void turnShow(){
        if(turn % 2 == 0) v.setMessage("Tour de: " + p.getJoueur("blanc").getPseudo());
        else v.setMessage("Tour de: " + p.getJoueur("noir").getPseudo());
    }

    @Override
    public void showScore(){
        JFrame frame = new JFrame();
        String j1 = "Score de " + a.getPseudo() + ": " + a.getScore();
        String j2 = "Score de " + b.getPseudo() + ": " + b.getScore();
        String winner = "";
        if (b.getScore() > a.getScore()) winner = b.getPseudo() + " a gagné!";
        else if (a.getScore() == b.getScore()) winner = "Match nul";
        else winner = a.getPseudo() + " a gagné";
        JOptionPane.showMessageDialog(frame, j1 + "\n\n" + j2 + "\n\n" + winner);
    }

    @Override
    public void maj(int x, int y) {
        if (this.turn % 2 != 0 && !this.game.equals("robot")) {
                maj2(x, y);
            } else if (this.game.equals("robot") && this.turn % 2 != 0) {
                majr();
            } else {
                maj1(x, y);
            }

            if (p.isFull()) {
                System.out.println("yolo");
            } else if (this.game.equals("robot")) {
                majr();
            }
            v.setScore("  " + "Score" + " " + a.getPseudo() + ":  " + a.getScore() + " " + "Score " + b.getPseudo() + ": " + b.getScore());
            if(p.isFull()) endGame();
    }

    private void maj1(int x, int y){
        if (p.cases[x][y].isEmpty()) {
            try {
                Image img = ImageIO.read(getClass().getResource("PionBlanc.png"));
                this.p.cases[x][y].setIcon(new ImageIcon(img));

            } catch (Exception ex) {
                System.out.println(ex);
            }

            this.p.cases[x][y].fabrique("blanc");
            this.alignement(p.cases[x][y],p.getJoueur("blanc"));
        }
        turn++;
        v.repaint();
    }

    private void maj2(int x,int y) {
        if (p.cases[x][y].isEmpty()) {
            try {
                Image img = ImageIO.read(getClass().getResource("PionNoir.png"));
                this.p.cases[x][y].setIcon(new ImageIcon(img));

            } catch (Exception ex) {
                System.out.println(ex);
            }

            this.p.cases[x][y].fabrique("noir");
            this.alignement(p.cases[x][y],p.getJoueur("noir"));
            turn++;
        }

    }


    private void majr() {
        int a = (int) (Math.random() * p.longueur);
        int b = (int) (Math.random() * p.largeur);
        while (!this.p.cases[a][b].isEmpty()) {
            a = (int) (Math.random() * p.longueur);
            b = (int) (Math.random() * p.largeur);
        }
        try {
            Image img = ImageIO.read(getClass().getResource("PionNoir.png"));
            this.p.cases[a][b].setIcon(new ImageIcon(img));
            this.p.cases[a][b].fabrique("noir");
            this.alignement(p.cases[a][b],p.getJoueur("noir"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        turn++;
        v.repaint();
    }
}
