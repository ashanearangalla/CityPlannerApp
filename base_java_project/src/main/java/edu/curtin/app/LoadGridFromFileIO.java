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

    public static final String GRID_FILE = "grid-1";

    public List<List<GridSquare>> readContents(String filename) throws FileParseException,
            IOException {
        int numRows, numCols;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String firstLine = reader.readLine().strip();
            String[] dimensions = firstLine.split(",");
            numRows = Integer.parseInt(dimensions[0].strip());
            numCols = Integer.parseInt(dimensions[1].strip());

            List<List<GridSquare>> grid = new ArrayList<>();

            
            for (int row = 0; row < numRows; row++) {
                List<GridSquare> rowList = new ArrayList<>();
                

                for (int colNo = 0; colNo < numCols; colNo++) {
                    String line = reader.readLine();
                    line = line.strip();
                    if (!line.isEmpty()) {
                        String parts[] = line.split(",");
                        if (parts.length < 1) {
                            throw new FileParseException("Vehicle services file format error");
                        }

                        GridSquare gridSquare = new GridSquare(parts[0]);

                        if (parts.length > 1) {
                            for (int rule = 1; rule < parts.length; rule++) {
                                String zoningRule = parts[rule].strip();

                                if (zoningRule.contains("flood-risk")) {
                                    int floodRiskValue = Math.round(Float.parseFloat(zoningRule.split("=")[1]));
                                    gridSquare.addZoningRule("flood-risk",String.valueOf(floodRiskValue));
                                }
                                if (zoningRule.contains("heritage")) {
                                    String heritage = zoningRule.split("=")[1];
                                    gridSquare.addZoningRule("heritage", heritage);
                                }
                                if (zoningRule.contains("height-limit")) {

                                    String heightLimit = zoningRule.split("=")[1];
                                    gridSquare.addZoningRule("height-limit", heightLimit);
                                }
                                if (zoningRule.contains("contamination")) {

                                    gridSquare.addZoningRule("contamination", "true");
                                }

                            }

                        }
                        rowList.add(gridSquare);
                    }

                }
                grid.add(rowList);
            }
            return grid;
        }

    }

}
