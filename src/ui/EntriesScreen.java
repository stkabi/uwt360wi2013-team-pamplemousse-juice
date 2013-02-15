
package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class EntriesScreen extends BaseScreen {

    private static final long serialVersionUID = 3747690407033623572L;

    private LiteTextField one, two, three;
    private LiteTextField[] entries_arr = { one, two, three };
    private String[] entries_txt = { " 1. ", " 2. ", " 3. " };
    private LiteButton logout, remove, add;
    private LiteButton[] button_arr = { logout, remove, add };
    private String[] button_txt = { "Logout", "Remove", "Add" };
    private Color[] button_color = { LiteButton.RED, LiteButton.BLUE, LiteButton.GREEN };
    private JCheckBox ckbx1, ckbx2, ckbx3;
    private JCheckBox[] ckbx_items = { ckbx1, ckbx2, ckbx3 };
    private ButtonGroup chekBoxGrp = new ButtonGroup();
    private String entryTxt = "empty";

    public EntriesScreen(final App application) {
	super(application);
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	Container titleContainer = new Container();
	titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.X_AXIS));

	Container buttonContainer = new Container();
	buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));

	this.setPreferredSize(new Dimension(300, 400));
	Border padding = new EmptyBorder(8, 8, 8, 8);
	padding = new CompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
		padding);

	JLabel title = new JLabel("User Entries", null, JLabel.CENTER);
	titleContainer.add(title);
	titleContainer.add(new Box.Filler(null, null, null));
	title.setAlignmentX(Component.LEFT_ALIGNMENT);
	title.setForeground(new Color(13, 102, 255));
	title.setFont(title.getFont().deriveFont(Font.PLAIN, 25));

	for (int i = 0; i < button_arr.length; i++) {
	    button_arr[i] = new LiteButton(button_txt[i]);
	    button_arr[i].addKeyListener(inputChangeListener);
	    button_arr[i].setBackground(button_color[i]);
	    button_arr[i].setEnabled(false);
	    buttonContainer.add(button_arr[i]);
	    if (i == 0) buttonContainer.add(new Box.Filler(null, null, null));
	    if (i == 1) buttonContainer.add(Box.createRigidArea(new Dimension(10, 0)));
	}
	button_arr[0].setEnabled(true);
	button_arr[0].addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		// TODO: disable session's token authentication here
		application.loginScreen = null;
		application.showLogin();

	    }
	});
	this.setBackground(Color.white);
	this.setBorder(new EmptyBorder(10, 10, 10, 10));

	this.add(Box.createVerticalStrut(10));
	this.add(titleContainer);
	this.add(Box.createRigidArea(new Dimension(100, 30)));
	this.add(Box.createVerticalStrut(10));

	for (int i = 0; i < entries_arr.length; i++) {
	    Container entriesContainer = new Container();
	    entriesContainer.setLayout(new BoxLayout(entriesContainer, BoxLayout.LINE_AXIS));
	    // if (userEntries.get(i) != null) entryTxt = "Entered";
	    entries_arr[i] = new LiteTextField(entryTxt);
	    ckbx_items[i] = new JCheckBox();
	    ckbx_items[i].setSelected(false);
	    ckbx_items[i].setEnabled(true);
	    ckbx_items[i].setFocusable(false);
	    ckbx_items[i].setBackground(getBackground());
	    ckbx_items[i].addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

		    performValidation(); // TODO: enable, or disable buttons
		}
	    });
	    chekBoxGrp.add(ckbx_items[i]);
	    entries_arr[i].setEditable(false);
	    entries_arr[i].setBackground(getBackground());
	    entries_arr[i].setFocusable(false);
	    entries_arr[i].setBorder(new TitledBorder(BorderFactory
		    .createTitledBorder(entries_txt[i])));
	    entriesContainer.add(ckbx_items[i]);
	    entriesContainer.add(Box.createRigidArea(new Dimension(10, 0)));
	    entriesContainer.add(entries_arr[i]);
	    entriesContainer.add(Box.createVerticalGlue());
	    this.add(entriesContainer);
	}
	this.add(Box.createRigidArea(new Dimension(100, 100)));
	this.add(Box.createVerticalStrut(60));
	this.add(buttonContainer);
    }

    private void performValidation() {
	// TODO: Check if the user has an entry before enabling / disabling the add/remove buttons
	;
    }

    /** Enable/disable the button depending on the user data validity */
    private KeyListener inputChangeListener = new KeyListener() {

	@Override
	public void keyPressed(KeyEvent e) {
	    performValidation();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    performValidation();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	    performValidation();
	}
    };

}
