/*
 * TCSS360 Winter 2013
 * Team Pamplemouse_Juice
 * Talal SADAK
 * Weaving application
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import data.DataProvider;
import entities.User;

/**
 * Class of the edit screen UI.
 * 
 * @author Talal Sadak
 */
public class EditRegScreen extends BaseScreen implements ActionListener {

	/** Unique serial ID */
	private static final long serialVersionUID = -7834083558260164475L;

	/** the last screen */
	private BaseScreen lastScreen;

	/** the current user */
	private User u = application.getLoggedInUser();

	/** the data provider */
	private DataProvider dp = application.getDataProvider();

	/** the buttons of this screen */
	private LiteButton user, logout, back, update;

	/** the fields of the user data to be updated */
	private LiteTextField name, email, address, phone, pass, pass2, general;

	/** the array of the user data fields */
	private LiteTextField[] user_info_fields = { name, email, address, phone,
			pass, pass2, general };

	/** the user data text fields titles array */
	private String[] user_fields_txt = { "Name", "Email", "Address", "phone",
			"New Password", "Confirm Password", "General" };

	/** the string array containing the data entered at the registration phase */
	private String[] user_info_data = { u.getName(), u.getEmail(),
			u.getAddress(), u.getPhoneNumber(), "", "", "" };

	/**
	 * Constructor of the class.
	 * 
	 * @param application
	 *            the application
	 * @param screen
	 *            the last screen
	 */
	public EditRegScreen(final App application, final BaseScreen screen) {

		super(application);

		lastScreen = screen;
		logout = new LiteButton("Logout");
		back = new LiteButton("Back");
		update = new LiteButton("Update");
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(300, 400));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(setupTitleContainer());
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		this.add(Box.createVerticalStrut(20));
		final Container cont = setupButtonsContainer();
		this.add(Box.createVerticalStrut(30));
		this.add(cont);
		this.add(Box.createRigidArea(new Dimension(300, 8)));
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
		for (int i = 0; i < user_info_fields.length; i++) {
			user_info_fields[i] = new LiteTextField(user_info_data[i]);
			user_info_fields[i].setBorder(new TitledBorder(BorderFactory
					.createTitledBorder(user_fields_txt[i])));
			user_info_fields[i].addKeyListener(inputChangeListener);
		}
		// user has to re-enter password otherwise you can't login again
		user_info_fields[4].setText("Re-enter Password");
		user_info_fields[5].setText("Re-enter Password");
		user_info_fields[4].maskText = true;
		user_info_fields[5].maskText = true;
		logout.addActionListener(this);
		update.addActionListener(this);
		back.addActionListener(this);
		logout.setBackground(LiteButton.RED);
		back.setBackground(LiteButton.BLUE);
		update.setBackground(LiteButton.GREEN);
		update.setEnabled(false);
		buttonContainer.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonContainer.add(logout);
		buttonContainer.add(new Box.Filler(null, null, null));
		buttonContainer.add(back);
		buttonContainer.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonContainer.add(update);
		buttonContainer.add(Box.createRigidArea(new Dimension(10, 0)));
		for (int i = 0; i < user_info_fields.length; i++) {
			this.add(user_info_fields[i]);
		}
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
		JLabel title = new JLabel("Edit User", null, JLabel.CENTER);
		title.setAlignmentX(Component.LEFT_ALIGNMENT);
		title.setForeground(new Color(13, 102, 255));
		title.setFont(title.getFont().deriveFont(Font.PLAIN, 25));
		titleContainer.add(title);
		titleContainer.add(Box.createRigidArea(new Dimension(100, 40)));
		user = new LiteButton("User: " + u.getName());
		user.setBorder(new EmptyBorder(8, 8, 8, 8));
		user.setForeground(new Color(170, 170, 170));
		user.setBackground(Color.WHITE);
		user.setContentAreaFilled(false);
		user.setBorderPainted(false);
		user.setFocusable(false);
		titleContainer.add(user);
		return titleContainer;
	}

	/**
	 * user input change listener.
	 */
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

	/**
	 * Handles the event actions for the buttons.
	 * 
	 * @param the_event
	 *            the action event
	 */
	@Override
	public void actionPerformed(final ActionEvent the_event) {
		Object event_object = the_event.getSource();

		if (event_object.equals(logout)) {
			application.loginScreen = null;
			application.showLogin();
		}
		if (event_object.equals(update)) {
			u.setName(user_info_fields[0].getText());
			u.setEmail(user_info_fields[1].getText());
			u.setAddress(user_info_fields[2].getText());
			u.setPhoneNumber(user_info_fields[3].getText());

			u.setPassword(user_info_fields[4].getText());
			dp.saveItem(u);
			application.changeScreen(new EntriesScreen(application));
		}
		if (event_object.equals(back)) {
			application.changeScreen(lastScreen);
		}
	}

	/**
	 * Performs the validation of the new entries.
	 * 
	 * @author Brian Mathews
	 * @author Talal Sadak (amended to reflect not changing the email)
	 */
	private void performValidation() {
		boolean valid = true;
		// validate email
		if (user_info_fields[1].getText().length() == 0
				|| user_info_fields[1].getText().indexOf('@') == -1
				|| user_info_fields[1].getText() == "email") {
			valid = false;
			user_info_fields[1]
					.setToolTipText("Please enter a valid email address");
		} else if (dp.getUserByEmail(user_info_fields[1].getText()) != null
				&& !u.getEmail().equals(user_info_fields[1].getText())) {
			valid = false;
			user_info_fields[1].setToolTipText("Email already in use");
		} else {
			user_info_fields[1].setToolTipText("");
		}
		// validate password match
		if (!user_info_fields[4].getText()
				.equals(user_info_fields[5].getText())) {
			user_info_fields[4].setToolTipText("Passwords do not match");
			user_info_fields[5].setToolTipText("Passwords do not match");
			valid = false;
		} else if (user_info_fields[4].getText() == "Password") {
			valid = false;
			user_info_fields[4].setToolTipText("Please enter a password");
			user_info_fields[5].setToolTipText("Please enter a password");
		} else {
			user_info_fields[4].setToolTipText("");
			user_info_fields[5].setToolTipText("");
		}
		// validate name
		if (user_info_fields[0].getText().length() == 0
				|| user_info_fields[0].getText().equals("Name")) {
			user_info_fields[0].setToolTipText("Please enter a name");
			valid = false;
		} else {
			user_info_fields[0].setToolTipText("");
		}
		// validate number, simply checking it's not empty atm
		if (user_info_fields[3].getText().length() == 0
				|| user_info_fields[3].getText().equals("Phone Number")) {
			valid = false;
			user_info_fields[3].setToolTipText("Please enter a phone number");
		} else {
			user_info_fields[3].setToolTipText("");
		}
		// validate address
		if (user_info_fields[2].getText().length() == 0
				|| user_info_fields[2].getText().equals("Address")) {
			user_info_fields[2].setToolTipText("Invalid Address");
			valid = false;
		} else {
			user_info_fields[2].setToolTipText("");
		}
		update.setEnabled(valid);
	}
}
