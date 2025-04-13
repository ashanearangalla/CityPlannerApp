/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Ashane
 */
public class BuildStructure {

    public void BuildStructureFromGrid(List<List<GridSquare>> grid) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter row: ");
        int row = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter column: ");
        int col = Integer.parseInt(scanner.nextLine());

        System.out.print("Number of floors: ");
        int floors = Integer.parseInt(scanner.nextLine());

        System.out.print("Foundation type (slab/stilts): ");
        String foundation = scanner.nextLine();

        System.out.print("Material (wood/stone/brick/concrete): ");
        String material = scanner.nextLine();

        GridSquare square = grid.get(row).get(col);

        Structure structure = BuildStructureOnGridSquare(square, floors, foundation, material);

        if (!structure.isBuildable()) {
            System.out.println("Cannot build: " + structure.getReason());
        } else {
            System.out.printf("Structure can be built. Total cost: $%,.2f\n", structure.getCost());
        }
    }

    public Structure BuildStructureOnGridSquare(GridSquare square, int floors, String foundation, String material) {
        try {

            Structure structure = new BaseStructure(floors, foundation, material);

            String terrain = square.getTerrain();
            Map<String, String> zoning = square.getZoningRules();

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

            return structure;

        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
