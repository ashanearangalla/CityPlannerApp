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
public class Util {
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
    
    
    public void displayBuiltCity(List<List<GridSquare>> grid) {
        
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
}
