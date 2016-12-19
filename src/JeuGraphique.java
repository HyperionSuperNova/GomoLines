import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by merat on 15/12/16.
 */
public abstract class JeuGraphique extends JFrame{
    Plateau p;
    Vue v;
    Controleur c;

    public JeuGraphique(Plateau p){
        this.p = p;
        v = new Vue();
    }

    public Plateau getPlateau(){
        return p;
    }

    public Vue getVue(){
        return v;
    }

    class Vue extends JFrame{
        private  JPanel gui = new JPanel(new BorderLayout(0,0));
        //private final Plateau.Case[][] cases = new Plateau.Case[p.getLongueur()][p.getLongueur()];
        private JPanel plateau;
        private final JLabel message = new JLabel("Game is ready to play!");

        public Vue(){
            gui.setBorder(new EmptyBorder(5, 5, 5, 5));
            JToolBar tools = new JToolBar();
            tools.setFloatable(false);
            tools.add(new JButton("Gomoku")); // TODO - add functionality!
            tools.add(new JButton("ColorLines")); // TODO - add functionality!
            tools.add(message);
            tools.setVisible(true);
            gui.add(tools, BorderLayout.PAGE_START);

            plateau = new JPanel(new GridLayout(0, p.getLongueur()));
            plateau.setSize(p.getLongueur()*64,p.getLargeur()*64);
            gui.add(plateau);
            for(int i = 0; i < p.getCases().length; i++){
                for(int j = 0; j < p.getCases()[i].length; j++){
                    p.getCases()[i][j] = p.new Case(i,j,null);
                    p.getCases()[i][j].addMouseListener(c);
                }
            }

            // create the chess board squares
            Insets buttonMargin = new Insets(0,0,0,0);
            for (int i = 0; i < p.getCases().length; i++) {
                for (int j = 0; j < p.getCases()[i].length; j++) {
                    Plateau.Case b = p.new Case(i,j);
                    b.setMargin(buttonMargin);
                    // our chess pieces are 64x64 px in size, so we'll
                    // 'fill this in' using a transparent icon..
                    ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                    b.setIcon(icon);
                    if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                        b.setBackground(Color.WHITE);
                    } else {
                        b.setBackground(Color.BLACK);
                    }
                    p.getCases()[j][i] = b;
                }
            }
            // fill the black non-pawn piece row
            for (int i = 0; i < p.getLongueur(); i++) {
                for (int j = 0; j < p.getLongueur(); j++) {
                            plateau.add(p.getCases()[j][i]);
                }
            }
            gui.setSize(plateau.getWidth(), plateau.getHeight());
        }
        public final JComponent getGui() {
            return gui;
        }
    }

     abstract class Controleur implements MouseInputListener{
    }
}
