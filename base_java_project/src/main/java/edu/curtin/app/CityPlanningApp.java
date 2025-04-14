package edu.curtin.app;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Entry point into the application. To chane the package, and/or the name of this class, make
 * sure to update the 'minClass = ...' line in build.gradle.
 */
public class CityPlanningApp
{
    public static void main(String[] args)
    {
        if(args.length != 1) {
            System.err.println("Provide grid data file");
            System.exit(1);
        }
        String filename = args[0];
        new CityPlanningApp().run(new LoadGridFromFileIO(filename));
    }
    
    public void run(LoadGridFromFileIO gridFileReader) {
        Util util = new Util();
        try {
            System.out.println("\nCity Planning App\n");
            List<List<GridSquare>> grid = gridFileReader.readContents();
            util.displayGrid(grid);
            showMenu(grid);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    
    public static void showMenu(List<List<GridSquare>> grid) {
        
       
        Scanner sc = new Scanner(System.in);
        Configure configure = new Configure();
        
        boolean done = false;
        while(!done){
            System.out.println(
            "\n\nMenu \n\n(a)Build Structure \n(b)Build City \n(c)Configure \n(d)Quit"
            );
            System.out.print("\nChoose an option : ");
            String opt = sc.nextLine();
            switch(opt) {
                case "a":
                    BuildStructure buildStructure = new BuildStructure();
                    buildStructure.buildStructurePrompt(grid);
                    break;

                case "b":
                    configure.randomConfiguration(grid);
                    break;

                case "c":
                    configure.configurationPrompt(grid);
                    break;

                case "d":
                    done = true;
                    break;

                default:
                    System.out.printf("Unknown option '%s'\n", opt);
            }

        }
        
    }
}
