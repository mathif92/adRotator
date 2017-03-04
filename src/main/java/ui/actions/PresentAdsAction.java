package ui.actions;

import ui.frames.AdsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by mathias on 28/02/17.
 */
public class PresentAdsAction implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        final AdsFrame adsFrame = new AdsFrame();
        adsFrame.setVisible(true);

        KeyAdapter keyListener = new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    adsFrame.dispose();
                }
            }
        };

        adsFrame.addKeyListener(keyListener);
    }
}
