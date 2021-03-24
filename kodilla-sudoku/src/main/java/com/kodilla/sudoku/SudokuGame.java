package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuGame {
    public static final int EMPTY = 0;
    private List<List<SudokuElement>> sudokuBoard;
    private int subArrayRowOffset;
    private int subArrayColumnOffset;

    public void printArray() {
        List<SudokuElement> row;
        System.out.println("\n  r \\ c    1   2   3   4   5   6   7   8   9           Sudoku Game help:\n                                                       ......................................");
        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            row = sudokuBoard.get(rowIndex);
            System.out.print("  " + (rowIndex + 1) + "      |");
            for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
                if (row.get(columnIndex).getValue() == EMPTY) {
                    System.out.print("   |");
                } else {
                    System.out.print(" " + row.get(columnIndex).getValue() + " |");
                }
                if (columnIndex == 8) {
                    switch (rowIndex) {
                        case 0 -> System.out.print("            r - row (1..9)");
                        case 1 -> System.out.print("            c - column (1..9)");
                        case 2 -> System.out.print("            v - value (1..9 or 0 to empty)");
                        case 3 -> System.out.print("          type 'rcv,rcv,...,' to insert values");
                        case 4 -> System.out.print("          type 'exit' to finish");
                        case 5 -> System.out.print("          type 'sudoku' to find a solution");
                        case 6 -> System.out.print("          type 'new game' to reset");
                        case 7 -> System.out.print("         .......................................");
                        case 8 -> System.out.print("                                      good luck!");
                    }
                }
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }
    //??
    private void addToAffectedElements(int currentValue, int row, int column) {
        for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            sudokuBoard.get(row).get(columnIndex).addToAvailableValues(currentValue);
        }
        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            sudokuBoard.get(rowIndex).get(column).addToAvailableValues(currentValue);
        }
        for (int columnIndex = subArrayColumnOffset; columnIndex < subArrayColumnOffset + 3; columnIndex++) {
            for (int rowIndex = subArrayRowOffset; rowIndex < subArrayRowOffset + 3; rowIndex++) {
                sudokuBoard.get(rowIndex).get(columnIndex).addToAvailableValues(currentValue);
            }
        }
    }

    private void updateAffectedElements(int currentValue, int insertValue, int row, int column) {
        subArrayRowOffset = row / 3 * 3;
        subArrayColumnOffset = column / 3 * 3;

        if (currentValue != 0) {
            addToAffectedElements(currentValue, row, column);
        }
        for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            sudokuBoard.get(row).get(columnIndex).removeFromAvailableValues(insertValue);
        }
        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            sudokuBoard.get(rowIndex).get(column).removeFromAvailableValues(insertValue);
        }
        for (int columnIndex = subArrayColumnOffset; columnIndex < subArrayColumnOffset + 3; columnIndex++) {
            for (int rowIndex = subArrayRowOffset; rowIndex < subArrayRowOffset + 3; rowIndex++) {
                sudokuBoard.get(rowIndex).get(columnIndex).removeFromAvailableValues(insertValue);
            }
        }
    }

    public List<List<SudokuElement>> fillSudokuArray(String command) {
        int row, column, currentValue, insertValue;

        try {
            for (int stringIndex = 0; stringIndex < command.length() / 4; stringIndex++) {
                row = Character.getNumericValue(command.charAt(stringIndex * 4)) - 1;
                column = Character.getNumericValue(command.charAt((stringIndex * 4) + 1)) - 1;
                currentValue = sudokuBoard.get(row).get(column).getValue();
                insertValue = Character.getNumericValue(command.charAt((stringIndex * 4) + 2));

                if (currentValue == insertValue) {
                    System.out.println("The value " + insertValue + " is already there at row " + (row + 1) + ", column " + (column + 1));
                } else {
                    if (sudokuBoard.get(row).get(column).setValue(insertValue)) {
                        updateAffectedElements(currentValue, insertValue, row, column);
                    } else {
                        System.out.println("Illegal to insert " + insertValue + " to row " + (row + 1) + ", column " + (column + 1) + " due to an other existing value. Please try something else..");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sudokuBoard;
    }

    public List<List<SudokuElement>> createNewGame() {

        sudokuBoard = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            List<SudokuElement> rowList = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
                rowList.add(new SudokuElement(EMPTY));
            }
            sudokuBoard.add(rowList);
        }
        return sudokuBoard;

    }
}
