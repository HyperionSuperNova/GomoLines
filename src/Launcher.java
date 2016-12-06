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
        userChoiceOfGame();
    }


    private void userChoiceOfGame() {
        Scanner sc = new Scanner(System.in);
        int z = sc.nextInt();
        sc = new Scanner(System.in);
        System.out.println("Voulez-vous jouer avec un robot?");
        String res = sc.nextLine();
        Plateau p = null;
        if (res.equals("oui")) {
            System.out.print("Pseudo joueur: ");
            res = sc.nextLine();
            p = new PlateauGomoku(z, new JoueurGomoku(res, "blanc", z), new JoueurGomoku("Robot", "noir", z));
            p.initializeThePlate();
            p.afficher();
            p.jouer("robot");
        }else if (res.equals("non")) {
            System.out.print("Pseudo premier joueur :");
            res = sc.nextLine();
            JoueurGomoku a = new JoueurGomoku(res,"blanc",z);
            System.out.print("Pseudo deuxième joueur: ");
            res = sc.nextLine();
            JoueurGomoku b = new JoueurGomoku(res,"noir",z);
            p = new PlateauGomoku(z,a,b);
            p.initializeThePlate();
            p.jouer("humain");

        }
    }
    public static void main(String [] args) {
        Launcher test = new Launcher();
    }
}
