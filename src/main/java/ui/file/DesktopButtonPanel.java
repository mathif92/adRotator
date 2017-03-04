package ui.file;

/**
 * Created by mathias on 28/02/17.
 */
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DesktopButtonPanel {

    private FileNode fileNode;

    private JPanel panel;

    public DesktopButtonPanel() {
        createPartControl();
    }

    private void createPartControl() {
        panel = new JPanel();

        JButton openButton = new JButton("Open");
        openButton.addActionListener(new OpenListener());
        panel.add(openButton);
    }

    public void setFileNode(FileNode fileNode) {
        this.fileNode = fileNode;
    }

    public JPanel getPanel() {
        return panel;
    }

    public class OpenListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            openFile(fileNode);
        }

        private void openFile(FileNode fileNode) {
            if (fileNode.getFile().isFile()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.OPEN)) {
                        try {
                            desktop.open(fileNode.getFile());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

}