package ui.file;

/**
 * Created by mathias on 28/02/17.
 */
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class TableSelectionListener implements ListSelectionListener {

    private int rowCount;

    private FileBrowserFrame frame;

    private JTable table;

    public TableSelectionListener(FileBrowserFrame frame, JTable table) {
        this.frame = frame;
        this.table = table;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }


    public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            ListSelectionModel lsm =
                    (ListSelectionModel) event.getSource();
            int row = lsm.getMinSelectionIndex();

            if ((row >= 0) && (row < rowCount)) {
                row = table.convertRowIndexToModel(row);
                FileNode fileNode = (FileNode) table.getModel()
                        .getValueAt(row, 10);
                frame.updateFileDetail(fileNode);
                frame.setDesktopButtonFileNode(fileNode);
            }
        }
    }

}