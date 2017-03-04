package ui.file;

/**
 * Created by mathias on 28/02/17.
 */
import java.io.File;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileSelectionListener implements TreeSelectionListener {

    private FileBrowserFrame frame;

    private FileBrowserModel model;

    public FileSelectionListener(FileBrowserFrame frame,
                                 FileBrowserModel model) {
        this.frame = frame;
        this.model = model;
    }


    public void valueChanged(TreeSelectionEvent event) {
        DefaultMutableTreeNode node =
                (DefaultMutableTreeNode)
                        event.getPath().getLastPathComponent();
        FileNode fileNode = (FileNode) node.getUserObject();

        AddNodes addNodes = new AddNodes(model, node);
        new Thread(addNodes).start();

        File file = fileNode.getFile();
        frame.updateFileDetail(fileNode);
        frame.setDesktopButtonFileNode(fileNode);
        if (file.isDirectory()) {
            frame.setDefaultTableModel(node);
        } else {
            frame.clearDefaultTableModel();
        }
    }

}