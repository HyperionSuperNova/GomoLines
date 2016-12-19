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

    public JeuGraphiqueGomoku(PlateauGomoku gomoku) {
        super(gomoku);
    }

    String game;

    public void jouer(String s) {
        this.game = s;
    }

    public void alignement(Plateau.Case cas, Joueur j){
        if(p.alignement(cas)){
            j.setScore(1);
        }
        //v.score = new JLabel("Score J1:" + p.getJoueur("blanc").getScore() + " Score J2:" + p.getJoueur("noire").getScore());
    }


    public void maj(int x, int y) {
        //this.p.cases[x][y].setBackground(Color.RED);
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
        v.repaint();
        if (p.isFull()) {
            System.out.println("yolo");
        } else if(this.game.equals("robot")) {
            majr();
        }else{
            maj2();
        }
    }

    private void maj2() {
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
        v.repaint();
    }


    /*public void setIcon(int x,int y){
        p.cases[x][y].setBackground(Color.CYAN);
        p.cases[x][y].repaint();
        this.repaint();
    }*/

}
