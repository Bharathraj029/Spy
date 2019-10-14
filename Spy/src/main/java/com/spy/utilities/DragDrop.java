package com.spy.utilities;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

public class DragDrop {

	public static void moveUpwards(JTable table)
	{
	    moveRowBy(-1,table);
	}

	public static void moveDownwards(JTable table)
	{
	    moveRowBy(1,table);
	}

	private static void moveRowBy(int by,JTable table)
	{
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    int[] rows = table.getSelectedRows();
	    int destination = rows[0] + by;
	    int rowCount = model.getRowCount();

	    if (destination < 0 || destination >= rowCount)
	    {
	        return;
	    }

	    model.moveRow(rows[0], rows[rows.length - 1], destination);
	    table.setRowSelectionInterval(rows[0] + by, rows[rows.length - 1] + by);
	}
	
}
