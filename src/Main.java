import Classes.Testing;

public class Main {

    public static void main(String[] args) {

        int instancesCount = 30;
        int instancePointsCount = 10;
        String folder = "/home/adil/Bureau/CPA/GeoAlgo/Projet/WelzlFinal/src/Varoumas_benchmark/samples";

        try{
            instancesCount = Integer.parseInt(args[0]);
            instancePointsCount = Integer.parseInt(args[1]);
            folder = args[2];
        }
        catch (Exception ex){
            System.out.println("Une erreur est survenue dans le passage des arguments en ligne de commande.");
            System.out.println("Veuillez respecter le schema suivant javac Main.java $1 $2 $3");
            System.out.println("$1 : Entier correspondant au nombre d'instances de test (max 1664).");
            System.out.println("$2 : Entier correspondant au nombre de points par instances (max 255).");
            System.out.println("$3 : Chemin absolu du dossier contenant les instances de test.");
        }

        Testing testing = new Testing(folder, instancesCount, instancePointsCount);
        testing.Start();




        

        
        
        



         

















        

        // Create a JFrame to display the coordinate system and points
//        JFrame frame = new JFrame("Point Drawer");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1000, 1000);
//        frame.setLocationRelativeTo(null);

        // Create a PointDrawer object and add it to the JFrame
        //Display pointDrawer = new Display(points, circle);
        //frame.add(pointDrawer);

        // Display the JFrame
        //frame.setVisible(true);
    }


}