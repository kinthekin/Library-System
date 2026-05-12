/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarysystem;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class BookTableModel extends AbstractTableModel {
    
        private static int OBJECT_COL = -1;
	private static final int TITLE_COL = 0;
	private static final int ISBN_COL = 1;
	private static final int AUTHOR_COL = 2;
	private static final int PUBLISHER_COL = 3;
        private static final int ID_COL = 4;
        
        private String[] columnNames = { "Title", "ISBN", "Author", "Publisher", "Id"};
	private List<Book> books;
        
        public BookTableModel(List<Book> mybooks) {
		books = mybooks;
	}
        
        @Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Book tempBook = books.get(row);

		switch (col) {
		case TITLE_COL:
                    return tempBook.getTitle();
		case ISBN_COL:
                    return tempBook.getISBN();
                case AUTHOR_COL:
                    return tempBook.getAuthor();
                case PUBLISHER_COL:
                    return tempBook.getPublisher();
                case ID_COL:
                   return tempBook.getID();
		default:
			return tempBook;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

    /**
     * @return the OBJECT_COL
     */
    public static int getOBJECT_COL() {
        return OBJECT_COL;
    }

    /**
     * @param aOBJECT_COL the OBJECT_COL to set
     */
    public static void setOBJECT_COL(int aOBJECT_COL) {
        OBJECT_COL = aOBJECT_COL;
    }

    /**
     * @return the ID_COL
     */
    public static int getID_COL() {
        return ID_COL;
    }

}


    

