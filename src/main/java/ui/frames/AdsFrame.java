package ui.frames;

import ui.thread.AdRotatorThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by mathias on 28/02/17.
 */
public class AdsFrame extends JFrame {

    JPanel panel;

    public AdsFrame() {
        panel = new JPanel();
        getContentPane().add(panel);
        AdRotatorThread adRotatorThread = new AdRotatorThread(panel, "/home/mathias/AdRotator");
        adRotatorThread.run();

        panel.setVisible(true);
        getContentPane().setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
        pack();
        setResizable(false);

        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                Point p = new Point(0, 0);
                SwingUtilities.convertPointToScreen(p, getContentPane());
                Point l = getLocation();
                l.x -= p.x;
                l.y -= p.y;
                setLocation(l);
            }
        });

    }

}
