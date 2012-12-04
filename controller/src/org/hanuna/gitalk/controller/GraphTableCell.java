package org.hanuna.gitalk.controller;

import org.hanuna.gitalk.printmodel.PrintCellRow;

/**
 * @author erokhins
 */
public class GraphTableCell {
    public static final int HEIGHT_CELL = 22;
    public static final int WIDTH_NODE = 15;
    public static final int CIRCLE_RADIUS = 5;
    public static final int SELECT_CIRCLE_RADIUS = 6;
    public static final float THICK_LINE = 2.5f;
    public static final float SELECT_THICK_LINE = 3.3f;

    private final PrintCellRow row;
    private final String text;

    public GraphTableCell(PrintCellRow row, String text) {
        this.row = row;
        this.text = text;
    }

    public PrintCellRow getRow() {
        return row;
    }

    public String getText() {
        return text;
    }
}