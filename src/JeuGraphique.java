import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by merat on 15/12/16.
 */
public abstract class JeuGraphique {
    protected static int turncounter = 0;
    protected static int[] tabsave = new int[2];
    Plateau p;
    Vue v;
    Controleur c;

    public JeuGraphique(Plateau p) {
        this.p = p;
        this.v = new Vue();
        this.c = new Controleur();
    }

    public Plateau getPlateau() {
        return p;
    }

    public void endGame() {
        try {
            showScore();
            v.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public abstract void maj(int x, int y);

    public abstract void jouer(String s);

    public abstract void turnShow();

    public abstract void showScore();

    public Vue getVue() {
        return v;
    }

    class Vue extends JFrame {
        private JPanel gui = new JPanel(new BorderLayout(0, 0));
        private JPanel plateau;
        private JLabel message = new JLabel("Click where you want to take your pawn");
        private JLabel score = new JLabel(" Score J1 " + 0);
        JToolBar tools = new JToolBar();

        public Vue() {
            gui.setBorder(new EmptyBorder(5, 5, 5, 5));
            tools.setFloatable(false);
            tools.add(message);
            tools.setVisible(true);
            gui.add(tools, BorderLayout.PAGE_START);
            tools.add(score);
            plateau = new JPanel(new GridLayout(p.getLargeur(), p.getLongueur()));
            plateau.setSize(p.getLongueur() * 64, p.getLargeur() * 64);
            gui.setSize(plateau.getWidth(), plateau.getHeight());
            gui.add(plateau);
            Insets buttonMargin = new Insets(0, 0, 0, 0);
            for (int i = 0; i < p.getCases().length; i++) {
                for (int j = 0; j < p.getCases()[i].length; j++) {
                    p.cases[i][j] = p.new Case(i, j, null);
                    p.cases[i][j].setMargin(buttonMargin);
                    p.cases[i][j].setPreferredSize(new Dimension(40, 40));
                    if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                        p.cases[i][j].setBackground(Color.WHITE);
                    } else {
                        p.cases[i][j].setBackground(Color.BLACK);
                    }
                    p.cases[i][j].addMouseListener(new Controleur());
                    plateau.add(p.cases[i][j]);
                }
            }

            this.add(gui);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setLocationByPlatform(true);
            this.pack();
            this.setMinimumSize(this.getSize());
            this.setMaximumSize(new Dimension(1024, 768));
            this.setVisible(true);
        }

        public final JComponent getGui() {
            return gui;
        }

        public void setScore(String a) {
            score.setText(a);
        }

        public void setMessage(String a) {
            message.setText(a);
        }
    }

    class Controleur implements ActionListener, MouseInputListener, MouseMotionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < p.cases.length; i++) {
                for (int j = 0; j < p.cases[i].length; j++) {
                    if (e.getSource() == p.cases[i][j] && p instanceof PlateauGomoku) {
                        maj(i, j);
                        turnShow();
                    } else if (e.getSource() == p.cases[i][j] && p instanceof PlateauColorLines) {
                        if (turncounter % 2 == 0 && !p.cases[i][j].isEmpty()) {
                            tabsave[0] = i;
                            tabsave[1] = j;
                            v.setMessage("Click where you want to drop it");
                            turncounter++;
                        } else {
                            maj(i, j);
                            v.setMessage("Click where you want to take your pawn");
                        }
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}
