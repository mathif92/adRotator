package ui.file;

/**
 * Created by mathias on 28/02/17.
 */
import java.io.File;
import java.util.Enumeration;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class FileBrowserModel {

    private FileSystemView fileSystemView;

    public FileBrowserModel() {
        this.fileSystemView = FileSystemView.getFileSystemView();
    }

    public DefaultTreeModel createTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();

        for (File file : fileSystemView.getRoots()) {
            root.add(new DefaultMutableTreeNode(new FileNode(file)));
        }

        addChildNodes(root);
        addGrandchildNodes(root);

        return new DefaultTreeModel(root);
    }

    public void addGrandchildNodes(DefaultMutableTreeNode root) {
        Enumeration<?> enumeration = root.children();
        while (enumeration.hasMoreElements()) {
            DefaultMutableTreeNode node =
                    (DefaultMutableTreeNode) enumeration.nextElement();
            addChildNodes(node);
        }
    }

    private void addChildNodes(DefaultMutableTreeNode root) {
        Enumeration<?> enumeration = root.children();
        while (enumeration.hasMoreElements()) {
            DefaultMutableTreeNode node =
                    (DefaultMutableTreeNode) enumeration.nextElement();
            FileNode fileNode = (FileNode) node.getUserObject();
            File file = fileNode.getFile();
            if (file.isDirectory()) {
                try {
                    for (File child : file.listFiles()) {
                        node.add(new DefaultMutableTreeNode(
                                new FileNode(child)));
                    }
                } catch(NullPointerException ex) {
                    System.out.println("No permissions to list the contents of this directory");
                }
            }
        }
    }

    public FileSystemView getFileSystemView() {
        return fileSystemView;
    }

    public Icon getFileIcon(File file) {
        return fileSystemView.getSystemIcon(file);
    }

    public String getFileText(File file) {
        return fileSystemView.getSystemDisplayName(file);
    }

}