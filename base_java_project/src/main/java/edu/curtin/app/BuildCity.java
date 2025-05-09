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
    Util util = new Util();
    
    // build method which is responsible for building city using the previously set strategy
    public void build () {
        System.out.println("\n\nBuild City");
        getStrategy().totalNumberOfStructures();
        getStrategy().totalCost();
        List<List<GridSquare>> grid = getStrategy().structuresCanBeBuilt();
        util.displayBuiltCity(grid);
        
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
