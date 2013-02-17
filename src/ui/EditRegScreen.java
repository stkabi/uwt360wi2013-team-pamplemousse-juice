
package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import data.DataProvider;

import entities.User;

public class EditRegScreen extends BaseScreen implements ActionListener {

    private static final long serialVersionUID = -7834083558260164475L;

    private BaseScreen lastScreen;

    private User current_user;
    private DataProvider user_data;

    private LiteButton user, logout, back, update;
    private LiteTextField name, email, address, pass, pass2, general;
    private LiteTextField[] user_info_fields = { name, email, address, pass, pass2, general };
    private String[] user_fields_txt = { "Name", "Email", "Address", "New Password",
	    "Confirm Password", "General" };

    public EditRegScreen(App application, BaseScreen screen) {

	super(application);
	current_user = application.getLoggedInUser();
	user_data = application.getDataProvider();

	lastScreen = screen;
	logout = new LiteButton("Logout");
	back = new LiteButton("Back");
	update = new LiteButton("Update");
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	this.setBackground(Color.white);
	Container titleContainer = new Container();
	titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.X_AXIS));

	Container buttonContainer = new Container();
	buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));

	this.setPreferredSize(new Dimension(300, 400));
	Border padding = new EmptyBorder(8, 8, 8, 8);
	padding = new CompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
		padding);

	JLabel title = new JLabel("Edit User", null, JLabel.CENTER);
	titleContainer.add(title);
	titleContainer.add(Box.createRigidArea(new Dimension(100, 40)));

	user = new LiteButton("User: " + current_user);
	user.setBorder(new EmptyBorder(8, 8, 8, 8));
	user.setForeground(new Color(170, 170, 170));
	user.setBackground(Color.WHITE);
	user.setContentAreaFilled(false);
	user.setBorderPainted(false);
	user.setFocusable(false);
	titleContainer.add(user);

	title.setAlignmentX(Component.LEFT_ALIGNMENT);
	title.setForeground(new Color(13, 102, 255));
	title.setFont(title.getFont().deriveFont(Font.PLAIN, 25));

	for (int i = 0; i < user_info_fields.length; i++) {
	    user_info_fields[i] = new LiteTextField(""); // TODO: change to data
							 // from dataprovider
	    user_info_fields[i].setBorder(new TitledBorder(BorderFactory
		    .createTitledBorder(user_fields_txt[i])));
	    user_info_fields[i].addKeyListener(inputChangeListener);
	}

	user_info_fields[3].maskText = true;
	user_info_fields[4].maskText = true;

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

	this.setBorder(new EmptyBorder(10, 10, 10, 10));
	this.add(titleContainer);
	this.add(new JSeparator(JSeparator.HORIZONTAL));

	this.add(Box.createVerticalStrut(20));
	for (int i = 0; i < user_info_fields.length; i++) {
	    this.add(user_info_fields[i]);
	}
	this.add(Box.createVerticalStrut(60));
	this.add(buttonContainer);
	this.add(Box.createRigidArea(new Dimension(300, 8)));
    }

    // TODO: update dataprovider after validation
    private void performValidation() {
	// if (email.getText().trim().length() > 0 && email.getText().compareTo("Email") != 0
	// && pass.getText().trim().length() > 0 && pass.getText().compareTo("Password") != 0) {
	// update.setEnabled(true);
	// } else {
	// update.setEnabled(false);
	// }
    }

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

    @Override
    public void actionPerformed(final ActionEvent the_event) {
	Object event_object = the_event.getSource();

	if (event_object.equals(logout)) {
	    application.loginScreen = null;
	    application.showLogin();
	}
	if (event_object.equals(update)) {
	    // TODO: Update values in dataprovider then go back to the last screen
	    application.changeScreen(lastScreen);
	}
	if (event_object.equals(back)) {
	    application.changeScreen(lastScreen);
	}

    }
}
