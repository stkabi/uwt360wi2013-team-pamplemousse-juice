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
 * Sets up the entries screen UI
 * 
 * @author Talal Sadak
 */
public class EntriesScreen extends BaseScreen implements ActionListener {

	/** Unique serial ID */
	private static final long serialVersionUID = 3747690407033623572L;

	/** Contest rules */
	private static final String Rules = "\n Read rules before adding an entry"
			+ " \n\n\nYour reading of these rules constitutes an agreement to share,"
			+ " release and use your details by the organizer of this competition.\nAn"
			+ " entry must be a woven article.\nContestant registration information"
			+ " is secured so that only trusted individuals can view it.\nContestants"
			+ " may not have more than one entry in the same category.\nContestants may"
			+ " not have more than three entries in the contest.\nContestants  may not"
			+ " judge a category they have an entry in.\nContestant's articles must be"
			+ " tagged with an ID card. Contestants must register before submitting a"
			+ " contest entry.\nContestants must register before the cutoff date, two"
			+ " weeks before the event.\nContestants must sign a release. Each entry"
			+ " must be from a single, registered contestant.\nEntries must be usable"
			+ " for the categories of products.\nEntries must follow the list of eligible"
			+ " fiber materials Entries must include a representation of the weaving draft"
			+ " for the article.\nJudges cannot be Contestants.\nNo more than one event"
			+ " can be held at a time.\nRegistration and entry submission are two"
			+ " separate events.\nThe computerized aspects of the contest should"
			+ " not detract from the fun of the even.\nA particular user can carry"
			+ " out only those tasks related to their role.\nFor each event there"
			+ " is only one user who is the Organizer. A User becomes an Attendee"
			+ " only by registering. \nA User can have only one role for a given"
			+ " event.\nAn Attendee only becomes a Contestant by entering.\n\nPlease"
			+ " hit add to enter\n";

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
	private LiteButton logout, remove, add, user_button;

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

	/** the rules text area */
	private JTextPane rules_area = new JTextPane();

	/** the scroll pane container */
	private JScrollPane scroll_pane = new JScrollPane(rules_area);

	/** the initial text entry */
	private String entryTxt = "Empty";

	/** the category ID text */
	private String categoryID = "";

	/** rules area text font */
	final Font font = new Font("Plain", Font.PLAIN, 12);

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
		this.setPreferredSize(new Dimension(300, 400));
		this.setBackground(Color.white);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(Box.createVerticalStrut(10));
		this.add(setupTitleContainer());
		this.add(new JSeparator(JSeparator.HORIZONTAL), -1);
		this.add(Box.createRigidArea(new Dimension(100, 30)));
		this.add(Box.createVerticalStrut(10));
		setupEntriesContainer();
		this.add(Box.createRigidArea(new Dimension(300, 30)));
		this.add(setupRulesComponent());
		this.add(Box.createVerticalStrut(60));
		this.add(setupButtonsContainer());
	}

	private Component setupRulesComponent() {
		Container labelContainer = new Container();
		labelContainer.setLayout(new BoxLayout(labelContainer,
				BoxLayout.LINE_AXIS));
		Container rulesContainer = new Container();
		rulesContainer
				.setLayout(new BoxLayout(rulesContainer, BoxLayout.Y_AXIS));
		rules_area.setPreferredSize(rules_area.getMinimumSize());
		rules_area.setEditable(false);
		Border padding = new EmptyBorder(8, 8, 8, 8);
		padding = new CompoundBorder(BorderFactory.createLineBorder(new Color(
				200, 200, 200), 1), padding);
		rules_area.setBorder(padding);
		rules_area.setBackground(Color.WHITE);
		rules_area.setToolTipText("Scroll down to read and accept the rules");
		rules_area.setText(Rules);
		rules_area.setCaretPosition(0);
		rules_area.setFont(font);
		rules_area.setForeground(Color.GRAY);

		scroll_pane.setMinimumSize(rules_area.getMinimumSize());

		JScrollBar scrollbar = scroll_pane.getVerticalScrollBar();
		scrollbar.addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				int p = e.getValue();
				System.out.println(p);
				if (p == 747 && entry_list.size() < 3) {
					button_arr[2].setEnabled(true);
				}
			}
		});
		labelContainer.add(new JLabel("Contest Rules: "));
		labelContainer.add(new Box.Filler(null, null, null));
		rulesContainer.add(labelContainer);
		rulesContainer.add(scroll_pane);
		return rulesContainer;
	}

	/**
	 * Sets up the multiple entries into separate containers.
	 * 
	 */
	private void setupEntriesContainer() {
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
	}

	/**
	 * Sets up the buttons container.
	 * 
	 * @return Container the container of the buttons
	 */
	private Container setupButtonsContainer() {
		Container buttonContainer = new Container();
		buttonContainer.setLayout(new BoxLayout(buttonContainer,
				BoxLayout.X_AXIS));
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
		return buttonContainer;
	}

	/**
	 * Sets up the title container.
	 * 
	 * @return Container the container of the title and user
	 */
	private Container setupTitleContainer() {
		Container titleContainer = new Container();
		titleContainer
				.setLayout(new BoxLayout(titleContainer, BoxLayout.X_AXIS));

		JLabel title = new JLabel("User Entries", null, JLabel.CENTER);
		title.setAlignmentX(Component.LEFT_ALIGNMENT);
		title.setForeground(new Color(13, 102, 255));
		title.setFont(title.getFont().deriveFont(Font.PLAIN, 25));
		user_button = new LiteButton("User: " + u.getName());
		user_button.setBorder(new EmptyBorder(8, 8, 8, 8));
		user_button.setForeground(new Color(170, 170, 170));
		user_button.setBackground(Color.WHITE);
		user_button.setContentAreaFilled(false);
		user_button.setBorderPainted(false);
		user_button.setFocusable(false);
		user_button.addActionListener(this);
		titleContainer.add(title);
		titleContainer.add(Box.createRigidArea(new Dimension(60, 30)));
		titleContainer.add(user_button);
		return titleContainer;
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
				categoryID = ckbx_txt_arr[i];
				break;
			}
			i++;
		}
		if (entry_list.size() >= 1) {
			// enable the remove button if an entry is present
			button_arr[1].setEnabled(true);
		}

		// Handle the logout button
		if (event_object.equals(button_arr[0])) {
			application.loginScreen = null;
			application.showLogin();
		}
		// Handle the remove button
		if (event_object.equals(button_arr[1])) {
			ArrayList<Entry> entryList = dp.getEntriesByUserId(u.getID());
			Entry entry = entryList.get(i);
			entry_list.remove(i);
			button_arr[1].setEnabled(false);
			System.out.println("Entry list size: " + entry_list.size());
			dp.removeItem(entry);
		}
		// Handle the add button
		if (event_object.equals(button_arr[2])) {
			application.changeScreen(new SubmissionScreen(application, this,
					categoryID, i));
		}
		// Handle click on the user (editing)
		if (event_object.equals(user_button)) {
			application.changeScreen(new EditRegScreen(application, this));
		}

	}
}
