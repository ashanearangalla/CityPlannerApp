/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ashane
 */
public class LoadGridFromFileIO {
    String filename;

    public LoadGridFromFileIO(String filename) {
        this.filename = filename;
    }
    

    public List<List<GridSquare>> readContents() throws FileParseException,
            IOException {
        int numRows, numCols;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String firstLine = reader.readLine().strip();
            String[] dimensions = firstLine.split(",");
            if(dimensions.length != 2) {
                throw new FileParseException("Invalid dimensions format. Expected format: 'rows, cols'");
            }
            try {
                numRows = Integer.parseInt(dimensions[0].strip());
                numCols = Integer.parseInt(dimensions[1].strip());
                if(numRows < 1 || numCols < 1) {
                    throw new FileParseException("Dimensions must be positive integers");
                }
                
            } catch (NumberFormatException e) {
                throw new FileParseException("Non-integer value in grid dimensions");
            }
            
            List<List<GridSquare>> grid = new ArrayList<>();

            for (int row = 0; row < numRows; row++) {
                List<GridSquare> rowList = new ArrayList<>();
                
                for (int colNo = 0; colNo < numCols; colNo++) {
                    String line = reader.readLine();
                    line = line.strip();
                    if (line != null) {
                        String parts[] = line.split(",");
                        if (parts.length < 1) {
                            throw new FileParseException("Line is Empty");
                        }

                        if(!List.of("flat", "rocky", "swampy").contains(parts[0])){
                            throw new FileParseException("Invalid terrain type: "+ parts[0]);
                        }
                        GridSquare gridSquare = new GridSquare(parts[0]);

                        if (parts.length > 1) {
                            for (int rule = 1; rule < parts.length; rule++) {
                                String zoningRule = parts[rule].strip();
                                String key = zoningRule.contains("=") ? zoningRule.split("=")[0] : zoningRule;
                                
                                if(!List.of("flood-risk", "heritage", "height-limit", "contamination").contains(key)){
                                    throw new FileParseException("Unknown zoning rule: "+ zoningRule);
                                }

                                if (zoningRule.contains("flood-risk")) {
                                    try {
                                        int floodRiskValue = Math.round(Float.parseFloat(zoningRule.split("=")[1]));
                                        if(!(floodRiskValue > 0 && floodRiskValue <100)) {
                                            throw new FileParseException("Flood risk out of range");
                                        }
                                        gridSquare.addZoningRule("flood-risk",String.valueOf(floodRiskValue));
                                        
                                    } catch(NumberFormatException e) {
                                        throw new FileParseException("Invalid flood risk value");
                                    }
                                    
                                }
                                if (zoningRule.contains("heritage")) {
                                    String heritage = zoningRule.split("=")[1];
                                    if(!List.of("wood", "stone", "brick").contains(heritage)){
                                        throw new FileParseException("Invalid heritage: "+ heritage);
                                    }
                                    gridSquare.addZoningRule("heritage", heritage);
                                }
                                if (zoningRule.contains("height-limit")) {

                                    try {
                                        int heightLimit = Integer.parseInt(zoningRule.split("=")[1]);
                                        if(heightLimit < 1) {
                                            throw new FileParseException("Height limit must be greater than 0");
                                        }
                                        gridSquare.addZoningRule("height-limit", String.valueOf(heightLimit));
                                    } catch(NumberFormatException e) {
                                        throw new FileParseException("Invalid Height limit value");
                                    }
                                    
                                }
                                if (zoningRule.contains("contamination")) {
                                    
                                    if(!zoningRule.equals("contamination")){
                                        throw new FileParseException("contamination cannot contain a value");
                                    }
                                    gridSquare.addZoningRule("contamination", "true");
                                }

                            }

                        }
                        rowList.add(gridSquare);
                    } else {
                        throw new FileParseException("Missing records. Expected more grid squares.");
                    }

                }
                grid.add(rowList);
            }
            return grid;
        }

    }

}
