/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 *
 * @author Ashane
 */

// Build the city by randomly seclecting materials, foundatin and flloors
public class RandomStrategy implements BuildCityStrategy{
    private static final Logger logger = Logger.getLogger(RandomStrategy.class.getName());
    private List<List<GridSquare>> grid;
    private String[] materials = {"concrete", "brick", "stone", "wood"};
    private String[] foundations ={"slab", "stilts"};
    private int totalStructures;
    private double totalCost;
    
    public RandomStrategy(List<List<GridSquare>> grid) {
        this.grid = grid;
        this.totalStructures = 0;
        this.totalCost = 0.00;
        logger.info("Random Strategy is applied");
    }

    @Override
    public void totalNumberOfStructures() {

        Random rand = new Random();
        BuildStructure buildStructure= new BuildStructure();
    
        for (List<GridSquare> row : grid) {
            for (GridSquare square : row) {
                int floors = rand.nextInt(50) + 1;
                String material = materials[rand.nextInt(materials.length)];
                String foundation = foundations[rand.nextInt(foundations.length)];
                
                Structure structure = buildStructure.buildStructureOnGridSquare(square, floors, foundation, material);
                
                if (!structure.isBuildable()) {
                    square.setBuildable(false);
                    
                } else {
                    square.setBuildable(true);
                    square.setFloors(floors);
                    square.setMaterial(material);
                    totalCost += structure.getCost();
                    totalStructures++;
                    
                }
                
            }
            
        }
        System.out.printf("\nTotal structures can be built: %d", totalStructures);
          
    }

    @Override
    public void totalCost() {
        System.out.printf("\nTotal cost: $%,.2f\n\n", totalCost);
      
    }

    @Override
    public List<List<GridSquare>> structuresCanBeBuilt() {
        return grid;
    }
    
}
