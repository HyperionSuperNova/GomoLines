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
        c = new ControleurGomoku();
    }

    public void setIcont(int x,int y){
        ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
        p.cases[x][y].setBackground(Color.CYAN);
        this.repaint();
    }

    class ControleurGomoku extends Controleur implements MouseInputListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            int a = e.getX();
            int b = e.getY();
            setIcont(a,b);
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
