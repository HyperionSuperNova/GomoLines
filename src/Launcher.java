import java.util.Scanner;

/**
 * Created by Stryker on 17/11/16.
 */
public class Launcher {

    public Launcher() {
        userExperience();
    }

    private void userExperience(){
        System.out.println("Bienvenue!");
        System.out.println("Veuillez entrer la dimension du plateau: (Un entier supérieur ou égal à 5)");
        int dimension = new Scanner(System.in).nextInt();
        System.out.println("Which Game do you want to play ?");
        System.out.println("a) I want to Play Gomoku !");
        System.out.println("b) I want to play ColorLines !");
        if(new Scanner(System.in).nextLine().equals("a")){
            GomokuLaunch(dimension);
        }else{
            ColorLinesLaunch(dimension);
        }
    }

    public void GomokuLaunch(int dimension){
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez-vous jouer avec un robot?");
        String res = sc.nextLine().toLowerCase();
        Plateau p = null;
        if (res.equals("oui")) {
            System.out.print("Pseudo joueur: ");
            res = sc.nextLine();
            p = new PlateauGomoku(dimension, new JoueurGomoku(res, "blanc", dimension), new JoueurGomoku("Robot", "noir", dimension));
            p.initializeThePlate();
            p.afficher();
            p.jouer("robot");
        }else if (res.equals("non")) {
            System.out.print("Pseudo premier joueur :");
            res = sc.nextLine();
            JoueurGomoku a = new JoueurGomoku(res,"blanc",dimension);
            System.out.print("Pseudo deuxième joueur: ");
            res = sc.nextLine();
            JoueurGomoku b = new JoueurGomoku(res,"noir",dimension);
            p = new PlateauGomoku(dimension,a,b);
            p.initializeThePlate();
            p.jouer("humain");
        }
    }

    public void ColorLinesLaunch(int dimension){
        System.out.println("lel");
        Plateau p = new PlateauColorLines(dimension, new JoueurColorLines("Nabil"));
        p.initializeThePlate();
        while(!p.isFull()) p.jouer("classique");
    }

    public static void main(String [] args) {
        Launcher test = new Launcher();
    }
}
