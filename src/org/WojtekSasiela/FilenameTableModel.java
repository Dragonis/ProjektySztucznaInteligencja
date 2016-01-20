package org.WojtekSasiela;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Created by Dragonis on 20.01.2016.
 */
public class FilenameTableModel extends AbstractTableModel {
    private String[] columnNames = {"id", "filename", "zz"};
    private ArrayList<String> rowData = new ArrayList<String>();

    public FilenameTableModel()
    {

        rowData.add("test");
        rowData.add("test2");
        rowData.add("test2");

    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return rowData.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int rowIndex, int colIndex) {

        switch (colIndex) {
            case 0:
                return 5;
            case 1:
                return 6;
            case 2:

                return 6;
        }
        return null;
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}