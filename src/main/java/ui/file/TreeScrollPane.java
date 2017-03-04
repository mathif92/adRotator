package ui.file;

/**
 * Created by mathias on 28/02/17.
 */
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTree;


public class TreeScrollPane {

    private FileBrowserFrame frame;

    private FileBrowserModel model;

    private JScrollPane scrollPane;

    private JTree tree;

    public TreeScrollPane(FileBrowserFrame frame,
                          FileBrowserModel model) {
        this.frame = frame;
        this.model = model;
        createPartControl();
    }

    private void createPartControl() {
        tree = new JTree(model.createTreeModel());
        tree.addTreeSelectionListener(
                new FileSelectionListener(frame, model));
        tree.addTreeWillExpandListener(
                new TreeExpandListener(model));
        tree.expandRow(1);
        tree.setRootVisible(false);
        tree.setCellRenderer(new FileTreeCellRenderer(model));
        tree.setShowsRootHandles(true);

        scrollPane = new JScrollPane(tree);

        Dimension preferredSize = scrollPane.getPreferredSize();
        Dimension widePreferred = new Dimension(
                300, (int) preferredSize.getHeight());
        scrollPane.setPreferredSize( widePreferred );
    }

    public JTree getTree() {
        return tree;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

}