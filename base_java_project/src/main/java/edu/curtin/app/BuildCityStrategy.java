/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.curtin.app;

import java.util.List;

/**
 *
 * @author Ashane
 */
public interface BuildCityStrategy {
    
    public void totalNumberOfStructures();
   
    public void totalCost();
    
    public List<List<GridSquare>> structuresCanBeBuilt();
    
}
