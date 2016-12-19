import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

/**
 * Created by merat on 15/12/16.
 */
public class JeuGraphiqueColorLines extends JeuGraphique {

    public JeuGraphiqueColorLines(PlateauColorLines colorLines) {
        super(colorLines);
        c = new ControleurColorLines();
    }

    class ControleurColorLines extends Controleur implements MouseInputListener{
        @Override
        public void mouseClicked(MouseEvent e) {

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
