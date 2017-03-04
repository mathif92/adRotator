package ui.actions;

import ui.file.FileBrowserFrame;
import ui.file.FileBrowserModel;
import ui.frames.EditAdsFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by mathias on 28/02/17.
 */
public class EditAdsAction implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        final FileBrowserFrame editAdsFrame = new FileBrowserFrame(new FileBrowserModel());
    }
}
