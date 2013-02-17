
package ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.*;

import data.DataProvider;

import entities.Entry;
import entities.User;

public class EntriesScreen extends BaseScreen implements ActionListener {

    private static final long serialVersionUID = 3747690407033623572L;
    private User current_user;
    private DataProvider user_data;

    private ArrayList<Entry> entry_list = new ArrayList<Entry>();
    private LiteTextField one, two, three;
    private LiteTextField[] entries_arr = { one, two, three };
    private String[] entries_txt = { " 1. ", " 2. ", " 3. " };
    private LiteButton logout, remove, add, user;
    private LiteButton[] button_arr = { logout, remove, add };
    private String[] button_txt = { "Logout", "Remove", "Add" };
    private Color[] button_color = { LiteButton.RED, LiteButton.BLUE, LiteButton.GREEN };
    private JCheckBox ckbx1, ckbx2, ckbx3;
    private JCheckBox[] ckbx_items = { ckbx1, ckbx2, ckbx3 };
    private String[] ckbx_txt_arr = { "1", "2", "3" };
    private ButtonGroup chekBoxGrp = new ButtonGroup();
    private String entryTxt = "empty";
    private String categoryID = "";

    public EntriesScreen(final App application) {
	super(application);

	current_user = application.getLoggedInUser();
	user_data = application.getDataProvider();
	// entry_list = user_data.getEntriesByUserId(current_user.id);

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
	titleContainer.add(Box.createRigidArea(new Dimension(60, 30)));
	user = new LiteButton("User: " + application.getLoggedInUser());
	user.setBorder(new EmptyBorder(8, 8, 8, 8));
	user.setForeground(new Color(170, 170, 170));
	user.setBackground(Color.WHITE);
	user.setContentAreaFilled(false);
	user.setBorderPainted(false);
	user.setFocusable(false);
	user.addActionListener(this);
	titleContainer.add(user);
	titleContainer.add(new JSeparator(JSeparator.HORIZONTAL));
	title.setAlignmentX(Component.LEFT_ALIGNMENT);
	title.setForeground(new Color(13, 102, 255));
	title.setFont(title.getFont().deriveFont(Font.PLAIN, 25));

	for (int i = 0; i < button_arr.length; i++) {
	    button_arr[i] = new LiteButton(button_txt[i]);
	    button_arr[i].addActionListener(this);
	    button_arr[i].setBackground(button_color[i]);
	    button_arr[i].setEnabled(false);
	    buttonContainer.add(button_arr[i]);
	    if (i == 0) buttonContainer.add(new Box.Filler(null, null, null));
	    if (i == 1) buttonContainer.add(Box.createRigidArea(new Dimension(10, 0)));
	}
	button_arr[0].setEnabled(true);
	this.setBackground(Color.white);
	this.setBorder(new EmptyBorder(10, 10, 10, 10));

	this.add(Box.createVerticalStrut(10));
	this.add(titleContainer);
	this.add(new JSeparator(JSeparator.HORIZONTAL), -1);
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
	    ckbx_items[i].setFocusable(true);
	    ckbx_items[i].setBackground(getBackground());
	    ckbx_items[i].addActionListener(this);
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

    @Override
    public void actionPerformed(final ActionEvent the_event) {
	Object event_object = the_event.getSource();

	// Handle the logout button
	if (event_object.equals(button_arr[0])) {
	    application.loginScreen = null;
	    application.showLogin();
	}
	// Handle the remove button
	if (event_object.equals(button_arr[1])) {
	    // TODO: handle removing an entry
	}
	// Handle the add button
	if (event_object.equals(button_arr[2])) {
	    application.changeScreen(new SubmissionScreen(application, this, categoryID));
	}
	if (event_object.equals(user)) {

	    application.changeScreen(new EditRegScreen(application, this));
	}
	// Handle the checkbox item
	for (int i = 0; i < ckbx_items.length; i++) {
	    if (event_object.equals(ckbx_items[i])) {
		// TODO: verify if entry exists or not and capture the
		// location of the checkbox to
		// make modifications to the data
		categoryID = ckbx_txt_arr[i];
		button_arr[1].setEnabled(true);
		button_arr[2].setEnabled(true);
	    }
	}

    }

}
