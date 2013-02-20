package ui;

import javax.swing.JPanel;

public class BaseScreen extends JPanel {
    private static final long serialVersionUID = 8504668632569400294L;

    public App application;

    public BaseScreen(App application) {
        this.application = application;
    }
}
