
package ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import data.DataProvider;
import entities.*;

public class SubmissionScreen extends BaseScreen implements ActionListener {

    private static final long serialVersionUID = -7491538055377138917L;
    private User current_user;
    private DataProvider user_data;
    private ArrayList<Entry> entry_list = new ArrayList<Entry>();
    private LiteButton logout, back, submit, user, upload, label;
    private LiteButton[] button_arr = { logout, back, submit };
    private String[] button_txt = { "Logout", "Back", "Submit" };
    private Color[] button_color = { LiteButton.RED, LiteButton.BLUE, LiteButton.GREEN };

    private String entryTxt = "empty";
    private BaseScreen last_screen;
    private BufferedImage my_image;

    public SubmissionScreen(final App the_application, final BaseScreen the_screen) {
	super(the_application);
	last_screen = the_screen;
	current_user = the_application.getLoggedInUser();
	user_data = the_application.getDataProvider();
	// entry_list = user_data.getEntriesByUserId(current_user.id);

	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	Container titleContainer = new Container();
	titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.X_AXIS));

	Container picContainer = new Container();
	picContainer.setLayout(new BoxLayout(picContainer, BoxLayout.X_AXIS));

	Container buttonContainer = new Container();
	buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));

	this.setPreferredSize(new Dimension(300, 400));
	Border padding = new EmptyBorder(8, 8, 8, 8);
	padding = new CompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
		padding);

	JLabel title = new JLabel("Submission", null, JLabel.CENTER);
	titleContainer.add(title);
	titleContainer.add(Box.createRigidArea(new Dimension(60, 30)));
	user = new LiteButton("User: " + the_application.getLoggedInUser());
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

	upload = new LiteButton("Upload...");
	upload.addActionListener(this);
	label = new LiteButton();
	label.setBackground(Color.WHITE);
	label.setFocusable(false);
	label.setFocusPainted(false);

	picContainer.add(upload);
	picContainer.add(new Box.Filler(null, null, null));
	picContainer.add(label);

	for (int i = 0; i < button_arr.length; i++) {
	    button_arr[i] = new LiteButton(button_txt[i]);
	    button_arr[i].addActionListener(this);
	    button_arr[i].setBackground(button_color[i]);
	    button_arr[i].setEnabled(true);
	    buttonContainer.add(button_arr[i]);
	    if (i == 0) buttonContainer.add(new Box.Filler(null, null, null));
	    if (i == 1) buttonContainer.add(Box.createRigidArea(new Dimension(10, 0)));
	}
	button_arr[2].setEnabled(false);

	this.setBackground(Color.white);
	this.setBorder(new EmptyBorder(10, 10, 10, 10));

	this.add(Box.createVerticalStrut(10));
	this.add(titleContainer);
	this.add(new JSeparator(JSeparator.HORIZONTAL), -1);
	this.add(Box.createRigidArea(new Dimension(100, 30)));
	this.add(Box.createVerticalStrut(10));

	this.add(picContainer);
	this.add(Box.createRigidArea(new Dimension(100, 100)));
	this.add(Box.createVerticalStrut(60));
	this.add(buttonContainer);
    }

    @Override
    public void actionPerformed(final ActionEvent the_event) {
	final JFileChooser file_chooser = new JFileChooser();
	Object event_object = the_event.getSource();

	// Handle the logout button
	if (event_object.equals(button_arr[0])) {
	    application.loginScreen = null;
	    application.showLogin();
	}
	// Handle the back button
	if (event_object.equals(button_arr[1])) {
	    application.changeScreen(last_screen);
	}
	// Handle the submit button
	if (event_object.equals(button_arr[2])) {
	    // TODO: handle Submit
	}
	if (event_object.equals(user)) application
		.changeScreen(new EditRegScreen(application, this));
	if (event_object.equals(upload)) {
	    openEvent(file_chooser);
	}
    }

    /** This method opens the file pointed to by the user.
     * 
     * @param the_file the file to be open. */
    private void openEvent(final JFileChooser the_file) {
	final int open_option = the_file.showOpenDialog(this);
	final File chosen_file = the_file.getSelectedFile();

	if (open_option == JFileChooser.APPROVE_OPTION) {

	    try {
		my_image = ImageIO.read(chosen_file);
		final ImageIcon image_icon = new ImageIcon(my_image.getScaledInstance(100, 100,
			Image.SCALE_DEFAULT));

		label.setIcon(image_icon);
		button_arr[2].setEnabled(true);
	    }
	    catch (final IOException exception) {
		JOptionPane.showMessageDialog(null, "File did not contain a valid image:"
			+ chosen_file, "Message", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
    }
}
