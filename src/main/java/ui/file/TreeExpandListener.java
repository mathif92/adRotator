package ui.file;

/**
 * Created by mathias on 28/02/17.
 */
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;


public class TreeExpandListener implements TreeWillExpandListener {

    private FileBrowserModel model;

    public TreeExpandListener(FileBrowserModel model) {
        this.model = model;
    }


    public void treeWillCollapse(TreeExpansionEvent event)
            throws ExpandVetoException {
    }


    public void treeWillExpand(TreeExpansionEvent event)
            throws ExpandVetoException {
        TreePath path = event.getPath();
        DefaultMutableTreeNode node =
                (DefaultMutableTreeNode) path.getLastPathComponent();

        AddNodes addNodes = new AddNodes(model, node);
        new Thread(addNodes).start();
    }

}