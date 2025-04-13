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
        new CityPlanningApp().run(new LoadGridFromFileIO());
        
        
    }
    
    public void run(LoadGridFromFileIO gridFileReader) {
        try {
            List<List<GridSquare>> grid = gridFileReader.readContents(LoadGridFromFileIO.GRID_FILE);
            displayGrid(grid);
            showMenu(grid);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public void displayGrid(List<List<GridSquare>> grid) {
        for (List<GridSquare> row : grid) {
            for (GridSquare square : row) {
                System.out.print("+-------------+");
            }
        
            System.out.println("");

            for (GridSquare square : row) {

                if(square != null) {
                    String terrain = square.getTerrain();
                    System.out.printf("| %-11s |",terrain);
                } else {
                    System.out.print("|    [null]|   ");
                }
            }
            System.out.println("");

            for (GridSquare square : row) {

                if(square != null && !square.getZoningRules().isEmpty()) {
                    Map<String, String> zoningRules = square.getZoningRules();
                    StringBuilder zoningSummary = new StringBuilder();
                    
                    if(zoningRules.containsKey("heritage")){
                        zoningSummary.append("H").append(zoningRules.get("heritage").substring(0,1)).append(" ");
                    }
                    if(zoningRules.containsKey("flood-risk")){
                        zoningSummary.append("F").append(zoningRules.get("flood-risk")).append(" ");
                    }
                    if(zoningRules.containsKey("height-limit")){
                        zoningSummary.append("L").append(zoningRules.get("height-limit")).append(" ");
                    }
                    if(zoningRules.containsKey("contamination")){
                        zoningSummary.append("C");
                    }
                    System.out.printf("| %-11s |", zoningSummary.toString());
                        

                } else {
                    System.out.print("|             |");
                }
                

            }
            System.out.println();
            
            for (GridSquare square : row) {
                System.out.print("+-------------+");
            }
            System.out.println();
        }        

    }
    
    public static void showMenu(List<List<GridSquare>> grid) {
        
        System.out.println("\nCity Planning App");
        Scanner sc = new Scanner(System.in);
        BuildCity buildCity = new BuildCity();
        boolean done = false;
        while(!done){
            System.out.println(
            "\nMenu \n(a)Build Structure \n(b)Build City \n(c)Configure \n(d)Quit"
            );
            System.out.print("\nChoose an option : ");
            String opt = sc.nextLine();
            switch(opt) {
                case "a":
                    BuildStructure buildStructure = new BuildStructure();
                    buildStructure.BuildStructureFromGrid(grid);
                    break;

                case "b":
                    
                    if(buildCity.getStrategy() == null) {
                        buildCity.setStrategy(new RandomStrategy(grid));
                    }
                    buildCity.build();
                    break;

                case "c":
                    System.out.println(
                    "\nConfigure \n(a)Uniform \n(b)Random \n(c)Central"
                    );
                    System.out.print("\nChoose an option: ");
                    String option = sc.nextLine();
                    switch (option) {
                        case "a":
                            
                            System.out.print("Number of floors: ");
                            int floors = Integer.parseInt(sc.nextLine());

                            System.out.print("Foundation type (slab/stilts): ");
                            String foundation = sc.nextLine();

                            System.out.print("Material (wood/stone/brick/concrete): ");
                            String material = sc.nextLine();
                            
                            buildCity.setStrategy(new UniformStrategy(grid, material, foundation, floors));
                            break;
                        case "b":
                            buildCity.setStrategy(new RandomStrategy(grid));
                            break;
                        case "c":
                            buildCity.setStrategy(new CentralStrategy(grid));
                            break;
                        default:
                            System.out.printf("Invalid Configuration '%s'.\n", option);
                            
                    }
                    break;

                case "d":
                    done = true;
                    break;

                default:
                    System.out.printf("Unknown option '%s'.\n", opt);
            }

        }
        
    }
}
