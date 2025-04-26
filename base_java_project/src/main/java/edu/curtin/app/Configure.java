/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ashane
 */
public class Configure {

    BuildCity buildCity = new BuildCity();

    // prompt to get user input
    public void configurationPrompt(List<List<GridSquare>> grid) {
        Scanner sc = new Scanner(System.in);
        int floors;
        String foundation, material;

        System.out.println(
                "\n\nConfigure \n\n(a)Uniform \n(b)Random \n(c)Central \n(d)Back"
        );
        System.out.print("\nChoose an option: ");
        String option = sc.nextLine();
        switch (option) {
            case "a":
                System.out.println("\n\nUniform Approach\n");
                while (true) {
                    System.out.print("Number of floors: ");
                    String input = sc.nextLine();

                    try {
                        floors = Integer.parseInt(input);
                        if (floors > 0 && floors <= 100) {
                            break;
                        } else {
                            System.out.println("\nError: floors must be between 1 to 100\n");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("\nError: Please enter a valid Integer\n");
                    }
                }

                while (true) {
                    System.out.print("\nFoundation type \n(a)slab \n(b)stilts \nChoose option: ");
                    String input = sc.nextLine();
                    switch (input) {
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

                while (true) {
                    System.out.print("\nMaterial type \n(a)wood \n(b)stone \n(c)brick \n(d)concrete \nChoose option: ");
                    String input = sc.nextLine();
                    switch (input) {
                        case "a":
                            material = "wood";
                            break;
                        case "b":
                            material = "stone";
                            break;
                        case "c":
                            material = "brick";
                            break;
                        case "d":
                            material = "concrete";
                            break;
                        default:
                            System.out.println("\nInvalid material type. Please choose a ,b ,c or d!\n");
                            continue;
                    }
                    break;
                }

                buildCity.setStrategy(new UniformStrategy(grid, material, foundation, floors));
                System.out.println("\nUniform Approach Applied!");

                break;
            case "b":
                buildCity.setStrategy(new RandomStrategy(grid));
                System.out.println("\nRandom Approach Applied!");
                break;
            case "c":
                buildCity.setStrategy(new CentralStrategy(grid));
                System.out.println("\nCentral Approach Applied!");
                break;
            case "d":
                break;
            default:
                System.out.printf("Invalid Configuration '%s'\n", option);
                break;

        }
    }

    // if no configuration is set random configuration is used
    public void randomConfiguration(List<List<GridSquare>> grid) {
        if (buildCity.getStrategy() == null) {
            buildCity.setStrategy(new RandomStrategy(grid));
        }
        buildCity.build();
    }

}
