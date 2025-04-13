/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Ashane
 */
public class BuildCity {
    private BuildCityStrategy strategy;
    
    public void build () {
        getStrategy().totalNumberOfStructures();
        getStrategy().totalCost();
        List<List<GridSquare>> grid = getStrategy().structuresCanBeBuilt();
        displayGrid(grid);
        
    }
    
    
    public void displayGrid(List<List<GridSquare>> grid) {
        for (List<GridSquare> row : grid) {
            for (GridSquare square : row) {
                System.out.print("+-------------+");
            }
        
            System.out.println("");

            for (GridSquare square : row) {

                if(square != null) {
                    boolean buildable = square.getBuildable();
                    if(buildable) {
                        System.out.printf("|  Buildable  |",buildable);
                    } else {
                        System.out.printf("| UnBuildable |",buildable);
                    }
                    
                } else {
                    System.out.print("|    [null]   |");
                }
            }
            System.out.println("");

            for (GridSquare square : row) {

                if(square.getBuildable()) {
                    
                    StringBuilder zoningSummary = new StringBuilder();
                    
                    if(!(square.getMaterial() == null)){
                        zoningSummary.append("M:").append(square.getMaterial().substring(0,1)).append(" ");
                    }
                    if(square.getFloors() != 0){
                        zoningSummary.append("F:").append(square.getFloors()).append(" ");
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

    /**
     * @return the strategy
     */
    public BuildCityStrategy getStrategy() {
        return strategy;
    }

    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(BuildCityStrategy strategy) {
        this.strategy = strategy;
    }
    
    
}
