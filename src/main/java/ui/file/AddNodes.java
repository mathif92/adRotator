package ui.file;

/**
 * Created by mathias on 28/02/17.
 */

import javax.swing.tree.DefaultMutableTreeNode;

public class AddNodes implements Runnable {

    private DefaultMutableTreeNode node;

    private FileBrowserModel model;

    public AddNodes(FileBrowserModel model, DefaultMutableTreeNode node) {
        this.model = model;
        this.node = node;
    }


    public void run() {
        FileNode fileNode = (FileNode) node.getUserObject();
        if (fileNode.isGenerateGrandchildren()) {
            model.addGrandchildNodes(node);
            fileNode.setGenerateGrandchildren(false);
        }
    }

}
