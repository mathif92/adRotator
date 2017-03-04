package ui;

import ui.actions.EditAdsAction;
import ui.actions.PresentAdsAction;

import javax.swing.*;

/**
 * Created by mathias on 28/02/17.
 */
public class MainMenu extends JFrame {
    private JButton start;
    private JButton edit;
    private JPanel panel;

    public MainMenu() {
        createUIComponents();
    }

    private void createUIComponents() {
        panel = new JPanel();
        setupButtons();
        addButtonsToPanel();
        this.add(panel);
    }

    private void setupButtons() {
        start = new JButton();
        edit = new JButton();
        start.setText("Presentar Publicidades");
        edit.setText("Editar Publicidades");
        start.addActionListener(new PresentAdsAction());
        edit.addActionListener(new EditAdsAction());
    }

    private void addButtonsToPanel() {
        start.setSize(200, 100);
        edit.setSize(200, 100);
        panel.add(start);
        panel.add(edit);
    }
}
