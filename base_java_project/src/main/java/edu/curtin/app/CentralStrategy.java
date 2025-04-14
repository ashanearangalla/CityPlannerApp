/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

import java.util.List;

/**
 *
 * @author Ashane
 */
public class CentralStrategy implements BuildCityStrategy{
    List<List<GridSquare>> grid;
    private int totalStructures;
    private double totalCost;
    
    public CentralStrategy(List<List<GridSquare>> grid) {
        this.grid = grid;
    }

    @Override
    public void totalNumberOfStructures() {
        BuildStructure builder = new BuildStructure();
        
        int height = grid.size();
        int width = grid.get(0).size();
        
        double centerX = (height -1)/ 2.0;
        double centerY = (width -1) /2.0;
        
        for(int i =0; i< height; i++) {
            List<GridSquare> row = grid.get(i);
            for(int j =0; j<width; j++) {
                GridSquare square = row.get(j);
                
                double distance = Math.sqrt(Math.pow(i - centerX, 2) + Math.pow(j - centerY, 2)) ;
                int nFloors = (int) Math.round( 1 + 20 / (distance +1));
                
                String material;
                if(distance <= 2) {
                    material = "concrete";
                }
                else if(2 < distance && distance <= 4) {
                    material = "brick";
                }
                else if(4 < distance && distance <= 6) {
                    material = "stone";
                }
                else {
                    material = "wood";
                }
                
                String foundation = "slab";
                Structure structure = builder.buildStructureOnGridSquare(square, nFloors, foundation, material);
                
                if (!structure.isBuildable()) {
                    square.setBuildable(false);
                    
                } else {
                    square.setBuildable(true);
                    square.setFloors(nFloors);
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
