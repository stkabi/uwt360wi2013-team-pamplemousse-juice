/*
 * TCSS360 Winter 2013
 * Team Pamplemouse_Juice
 * Talal SADAK
 * Weaving application
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

import data.DataProvider;
import entities.Entry;
import entities.User;

/**
 * Entries screen class.
 * 
 * Sets up the entries screen ui
 * 
 * @author Talal Sadak
 * 
 */
public class EntriesScreen extends BaseScreen implements ActionListener {

	/** Unique serial ID */
	private static final long serialVersionUID = 3747690407033623572L;

	/** the current user */
	private User u;

	/** the data provider */
	private DataProvider dp;

	/** the entry list */
	private ArrayList<Entry> entry_list;

	/** the entries text fields */
	private LiteTextField one, two, three;

	/** the entries text fields array */
	private LiteTextField[] entries_arr = { one, two, three };

	/** the entries text fields text */
	private String[] entries_txt = { " Classic ", " Hipster ", " Ancient " };

	/** the buttons of this screen */
	private LiteButton logout, remove, add, user;

	/** the buttons array */
	private LiteButton[] button_arr = { logout, remove, add };

	/** the buttons text array */
	private String[] button_txt = { "Logout", "Remove", "Add" };

	/** the button colors */
	private Color[] button_color = { LiteButton.RED, LiteButton.BLUE,
			LiteButton.GREEN };

	/** the check boxes of the categories */
	private JCheckBox ckbx1, ckbx2, ckbx3;

	/** the check boxes array */
	private JCheckBox[] ckbx_items = { ckbx1, ckbx2, ckbx3 };

	/** the check boxes text array */
	private String[] ckbx_txt_arr = { "Classic", "Hipster", "Ancient" };

	/** the check box group container */
	private ButtonGroup chekBoxGrp = new ButtonGroup();

	/** the initial text entry */
	private String entryTxt = "Empty";

	/** the category ID text */
	private String categoryID = "";

	/**
	 * Constructor for the class.
	 * 
	 * @param application
	 *            the application
	 */
	public EntriesScreen(final App application) {
		super(application);

		u = application.getLoggedInUser();
		dp = application.getDataProvider();
		entry_list = dp.getEntriesByUserId(u.getID());

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Container titleContainer = new Container();
		titleContainer
				.setLayout(new BoxLayout(titleContainer, BoxLayout.X_AXIS));

		Container buttonContainer = new Container();
		buttonContainer.setLayout(new BoxLayout(buttonContainer,
				BoxLayout.X_AXIS));

		this.setPreferredSize(new Dimension(300, 400));

		JLabel title = new JLabel("User Entries", null, JLabel.CENTER);
		titleContainer.add(title);
		titleContainer.add(Box.createRigidArea(new Dimension(60, 30)));
		user = new LiteButton("User: " + u.getName());
		user.setBorder(new EmptyBorder(8, 8, 8, 8));
		user.setForeground(new Color(170, 170, 170));
		user.setBackground(Color.WHITE);
		user.setContentAreaFilled(false);
		user.setBorderPainted(false);
		user.setFocusable(false);
		user.addActionListener(this);
		titleContainer.add(user);

		title.setAlignmentX(Component.LEFT_ALIGNMENT);
		title.setForeground(new Color(13, 102, 255));
		title.setFont(title.getFont().deriveFont(Font.PLAIN, 25));

		for (int i = 0; i < button_arr.length; i++) {
			button_arr[i] = new LiteButton(button_txt[i]);
			button_arr[i].addActionListener(this);
			button_arr[i].setBackground(button_color[i]);
			button_arr[i].setEnabled(false);
			buttonContainer.add(button_arr[i]);
			if (i == 0) {
				buttonContainer.add(new Box.Filler(null, null, null));
			}
			if (i == 1) {
				buttonContainer.add(Box.createRigidArea(new Dimension(10, 0)));
			}
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
			entriesContainer.setLayout(new BoxLayout(entriesContainer,
					BoxLayout.LINE_AXIS));
			if (entry_list.size() - 1 > i) {
				entryTxt = entry_list.get(i).getOtherDetails();
			}
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

	/**
	 * Handles the event actions for the buttons.
	 * 
	 * @param the_event
	 *            the action event
	 */
	@Override
	public void actionPerformed(final ActionEvent the_event) {
		Object event_object = the_event.getSource();
		int i = 0;

		// get the selected check box index
		for (JCheckBox c : ckbx_items) {
			if (c.isSelected()) {
				System.out.println("CheckBox selected: " + i);
				categoryID = ckbx_txt_arr[i];
				break;
			}
			i++;
		}

		// if (!entry_list.isEmpty()) {
		// enable the remove button if an entry is present
		button_arr[1].setEnabled(true);
		// }
		if (entry_list.size() < 3) {
			// enable the add button if not all entries are filled
			button_arr[2].setEnabled(true);
		}

		// Handle the logout button
		if (event_object.equals(button_arr[0])) {
			application.loginScreen = null;
			application.showLogin();
		}
		// Handle the remove button
		if (event_object.equals(button_arr[1])) {
			// TODO: fix issue with entry list being empty
			ArrayList<Entry> entryList = dp
					.getEntriesByUserId(u.getID());

			Entry entry = entryList.get(i);
			// entry_list.remove(i);
			System.out.println("Entry list size: " + entry_list.size());
			dp.removeItem(entry);

		}
		// Handle the add button
		if (event_object.equals(button_arr[2])) {
			application.changeScreen(new SubmissionScreen(application, this,
					categoryID, i));
		}
		// Handle click on the user (editing)
		if (event_object.equals(user)) {
			application.changeScreen(new EditRegScreen(application, this));
		}

	}
}
