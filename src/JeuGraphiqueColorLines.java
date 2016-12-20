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

    public JeuGraphiqueColorLines(PlateauColorLines colorLines) {
        super(colorLines);
        putThreeColors();
    }
    String game;

    public void putThreeColors() {
        int cmp = 0;
        while (cmp != 3) {
            int a = new Random().nextInt(p.longueur);
            int b = new Random().nextInt(p.largeur);
            while (!p.cases[a][b].isEmpty()) {
                a = new Random().nextInt(p.longueur);
                b = new Random().nextInt(p.largeur);
            }
            p.cases[a][b].pion = new PionColorLines();
            try {
                Image img = null;
                if(p.cases[a][b].pion.toString().equals("rouge")) {
                    img = ImageIO.read(getClass().getResource("PionRouge.png"));
                }else if(p.cases[a][b].pion.toString().equals("vert")){
                    img = ImageIO.read(getClass().getResource("PionVert.png"));
                }else if(p.cases[a][b].pion.toString().equals("bleu")){
                    img = ImageIO.read(getClass().getResource("PionBleu.png"));
                }else{
                    img = ImageIO.read(getClass().getResource("PionArcEnCiel.png"));
                }
                this.p.cases[a][b].setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            cmp++;
        }
    }


    @Override
    public void maj(int x, int y) {
        p.cases[x][y].fabrique(p.cases[tabsave[0]][tabsave[1]].pion.toString());
        p.cases[tabsave[0]][tabsave[1]].pion = null;
        p.cases[tabsave[0]][tabsave[1]].setIcon(null);
        try{
            Image img = null;
            if(p.cases[x][y].pion.toString().equals("rouge")) {
                img = ImageIO.read(getClass().getResource("PionRouge.png"));
            }else if(p.cases[x][y].pion.toString().equals("vert")){
                img = ImageIO.read(getClass().getResource("PionVert.png"));
            }else if(p.cases[x][y].pion.toString().equals("bleu")){
                img = ImageIO.read(getClass().getResource("PionBleu.png"));
            }else{
                img = ImageIO.read(getClass().getResource("PionArcEnCiel.png"));
            }
            p.cases[x][y].setIcon(new ImageIcon(img));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        v.repaint();
        putThreeColors();
    }

    @Override
    public void jouer(String s) {
        switch(s){
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

    }
}
