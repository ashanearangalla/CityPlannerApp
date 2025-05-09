/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Ashane
 */

// Build the same structure in all units
public class UniformStrategy implements BuildCityStrategy{
    private static final Logger logger = Logger.getLogger(UniformStrategy.class.getName());
    private String material;
    private String foundation;
    List<List<GridSquare>> grid;
    private int floors;
    private int totalStructures;
    private double totalCost;
    
    public UniformStrategy(List<List<GridSquare>> grid, String material, String foundation, int floors) {
        this.material = material;
        this.foundation = foundation;
        this.floors = floors;
        this.grid = grid;
        this.totalStructures = 0;
        this.totalCost = 0.00;
        logger.info("Uniform Strategy is added");
    }

    @Override
    public void totalNumberOfStructures() {
        BuildStructure buildStructure= new BuildStructure();
        
        for(List<GridSquare> row : grid) {
            for(GridSquare square: row) {
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
