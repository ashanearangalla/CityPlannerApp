/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author Ashane
 */
public class BuildStructure {
     private static final Logger logger = Logger.getLogger(BuildStructure.class.getName());

    public void buildStructurePrompt(List<List<GridSquare>> grid) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n\nBuild Structure\n");
        int row, col, floors;
        String foundation, material;
        // while loops are used to get the expected input
        while(true) {
            System.out.print("Enter row: ");
            String input = scanner.nextLine();
            
            try {
                row = Integer.parseInt(input);
                if(row > 0 && row <= grid.size()) {
                    break;
                } else {
                    System.out.println("\nError: row must be between 1 to " + (grid.size()) + ")!\n");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("\nError: Please enter a valid Integer!\n");
            }
        }
        
        while(true) {
            System.out.print("Enter column: ");
            String input = scanner.nextLine();
            
            try {
                col = Integer.parseInt(input);
                if(col > 0 && col <= grid.get(0).size()) {
                    break;
                } else {
                    System.out.println("\nError: column must be between 1 to " + (grid.get(0).size()) + ")!\n");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("\nError: Please enter a valid Integer!\n");
            }
        }

        while(true) {
            System.out.print("Number of floors: ");
            String input = scanner.nextLine();
            
            try {
                floors = Integer.parseInt(input);
                if(floors > 0 && floors<= 100) {
                    break;
                } else {
                    System.out.println("\nError: floors must be between 1 to 100\n");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("\nError: Please enter a valid Integer\n");
            }
        }

        
        while(true) {
            System.out.print("\nFoundation type \n(a)slab \n(b)stilts \nChoose option: ");
            String input = scanner.nextLine();
            switch(input) {
                case "a":
                    foundation = "slab";
                    break;
                case "b":
                    foundation = "stilts";
                    break;
                default:
                    System.out.println("\nInvalid foundation type. Please choose a or b!\n");
                    continue;
            }
            break;
        }

        while(true) {
            System.out.print("\nMaterial type \n(a)wood \n(b)stone \n(c)brick \n(d)concrete \nChoose option: ");
            String input = scanner.nextLine();
            switch(input) {
                case "a":
                    material = "wood";
                    break;
                case "b":
                    material = "stone";
                    break;
                case "c":
                    material= "brick";
                    break;
                case "d":
                    material= "concrete";
                    break;
                default:
                    System.out.println("\nInvalid material type. Please choose a ,b ,c or d!\n");
                    continue;
            }
            break;
        }

        // row-1 and col-1 is used because the rows, and columns we are getting from the user are starting from 1
        GridSquare square = grid.get(row-1).get(col-1);
        Structure structure = buildStructureOnGridSquare(square, floors, foundation, material);

        if (!structure.isBuildable()) {
            System.out.println("\nCannot build: " + structure.getReason()+"\n");
        } else {
            System.out.printf("\nStructure can be built. Total cost: $%,.2f\n", structure.getCost());
        }
    }

    public Structure buildStructureOnGridSquare(GridSquare square, int floors, String foundation, String material) {
        try {
            // creation of base structure
            Structure structure = new BaseStructure(floors, foundation, material);

            String terrain = square.getTerrain();
            Map<String, String> zoning = square.getZoningRules();

            // the base structure is wrapped accoring to terrin and zoning rules
            if ("swampy".equals(terrain)) {
                structure = new SwampDecorator(structure, floors, material, foundation);
            }

            if ("rocky".equals(terrain)) {
                structure = new RockyDecorator(structure);
            }

            if ("true".equals(zoning.get("contamination"))) {
                structure = new ContaminatedDecorator(structure);
            }

            if (zoning.containsKey("heritage")) {
                structure = new HeritageDecorator(structure, material, zoning.get("heritage"));
            }

            if (zoning.containsKey("flood-risk")) {
                structure = new FloodRiskDecorator(structure, Integer.parseInt(zoning.get("flood-risk")), floors);
            }

            if (zoning.containsKey("height-limit")) {
                structure = new HeightLimitDecorator(structure, floors, Integer.parseInt(zoning.get("height-limit")));
            }
            logger.info("Structure is wrapped with decoraotrs");
            return structure;

        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        } catch (NullPointerException ne) {
            System.out.println("Error: " + ne.getMessage());
            return null;
        }
    }
}
