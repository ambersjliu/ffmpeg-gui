package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A panel containing a label and a button.
 */
class LabelButtonPanel extends JPanel {
    LabelButtonPanel(JLabel label, JButton button) {
        this.add(label);
        this.add(button);
    }
}

