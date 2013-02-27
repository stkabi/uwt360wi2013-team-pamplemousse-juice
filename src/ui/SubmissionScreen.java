/*
 * TCSS360 Winter 2013
 * Team Pamplemouse_Juice
 * Talal SADAK
 * Weaving application
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import data.DataProvider;
import entities.Entry;
import entities.User;

/**
 * Submission screen class.
 * 
 * Sets up the submission screen UI
 * 
 * @author Talal Sadak
 */
public class SubmissionScreen extends BaseScreen implements ActionListener {

	/** Unique serial ID */
	private static final long serialVersionUID = -7491538055377138917L;

	/** the current user of this app */
	private User u;

	/** the data provider of the application */
	private DataProvider dp;

	/** the entry list */
	private ArrayList<Entry> entry_list;

	/** the current user ID */
	private String userID = "";

	/** the current category */
	private String categoryID = "";

	/** the weaving pattern description */
	private String weavingPattern = "";

	/** the fibers in weave description */
	private String fibersInWeave = "";

	/** the other details string */
	private String otherDetails = "";

	/** the index of the category to update */
	private int categoryIndex = 0;

	/** the buttons of this screen */
	private LiteButton logout, back, submit, user, upload;

	/** the current category title */
	private LiteTextField cat;

	/** the label holding the picture */
	private JLabel label;

	/** the buttons array */
	private LiteButton[] button_arr = { logout, back, submit };

	/** the buttons text array */
	private String[] button_txt = { "Logout", "Back", "Submit" };

	/** the button colors array */
	private Color[] button_color = { LiteButton.RED, LiteButton.BLUE,
			LiteButton.GREEN };

	/** the previous screen */
	private BaseScreen last_screen;

	/** the image */
	private BufferedImage my_image;

	/** the text areas holding the description of the entry */
	private JTextArea details_area, description_area, fibers_in_weave_area;

	/** the path of the image */
	private String image_path;

	/**
	 * Constructor of the class.
	 * 
	 * @param the_application
	 *            the application
	 * @param the_screen
	 *            the last screen
	 * @param the_category
	 *            the categoryID to be added
	 * @param the_categoryNum
	 *            the category index from the check box
	 */
	public SubmissionScreen(final App the_application,
			final BaseScreen the_screen, final String the_category,
			final int the_categoryNum) {

		super(the_application);

		last_screen = the_screen;
		u = the_application.getLoggedInUser();
		dp = the_application.getDataProvider();
		userID = u.getID();
		categoryID = the_category;
		categoryIndex = the_categoryNum;
		entry_list = dp.getEntriesByUserId(userID);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.white);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setPreferredSize(new Dimension(300, 400));
		this.add(Box.createVerticalStrut(10));
		this.add(setupTitleContainer());
		this.add(new JSeparator(JSeparator.HORIZONTAL), -1);
		this.add(setupCategoryContainer());
		this.add(setupDescription());
		this.add(setupPicContainer());
		this.add(setupFibersInWeave());
		this.add(setupOtherDetails());
		this.add(Box.createVerticalStrut(10));
		this.add(setupButtonsContainer());
	}

	/**
	 * Sets up the fibers in weave component.
	 * 
	 * @return Component the fibers in weave component
	 */
	private Component setupFibersInWeave() {
		fibers_in_weave_area = new JTextArea(3, 1);
		fibers_in_weave_area.setPreferredSize(fibers_in_weave_area
				.getMinimumSize());
		fibers_in_weave_area.setLineWrap(true);
		fibers_in_weave_area.setWrapStyleWord(true);
		fibers_in_weave_area.setBorder(new TitledBorder(BorderFactory
				.createTitledBorder("Enter the fibers ...")));
		fibers_in_weave_area.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					fibers_in_weave_area.setText("");
					fibersInWeave = "";
					fibers_in_weave_area.setEditable(true);
				}
			}
		});
		fibers_in_weave_area.getDocument().addDocumentListener(
				new DocumentListener() {
					@Override
					public void changedUpdate(DocumentEvent e) {
						checkLength();
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						checkLength();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						checkLength();
					}

					public void checkLength() {
						if (fibers_in_weave_area.getText().length() > 50
								|| fibers_in_weave_area.getLineCount() > 2) {
							JOptionPane.showMessageDialog(null,
									"You cannot have more than 2 lines",
									"Message", JOptionPane.INFORMATION_MESSAGE);
							fibers_in_weave_area.setEditable(false);

						}
					}
				});
		return fibers_in_weave_area;
	}

	/**
	 * Sets up the category container.
	 * 
	 * @return Container the category container
	 */
	private Container setupCategoryContainer() {
		Container category_Container = new Container();
		category_Container.setLayout(new BoxLayout(category_Container,
				BoxLayout.X_AXIS));
		cat = new LiteTextField("Category: " + categoryID);
		cat.setHorizontalAlignment(SwingConstants.LEFT);
		cat.setBackground(Color.WHITE);
		cat.setBorder(new EmptyBorder(0, 0, 0, 0));
		cat.setFocusable(false);
		cat.setEnabled(false);
		category_Container.add(cat);
		category_Container.add(new Box.Filler(null, null, null));
		return cat;
	}

	/**
	 * Sets up the description component.
	 * 
	 * @return Component the description component
	 */
	private Component setupDescription() {
		description_area = new JTextArea(3, 1);
		description_area.setPreferredSize(description_area.getMinimumSize());
		description_area.setLineWrap(true);
		description_area.setWrapStyleWord(true);
		description_area.setBorder(new TitledBorder(BorderFactory
				.createTitledBorder("Enter Description here...")));
		description_area.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					description_area.setText("");
					weavingPattern = "";
					description_area.setEditable(true);
				}
			}
		});

		description_area.getDocument().addDocumentListener(
				new DocumentListener() {
					@Override
					public void changedUpdate(DocumentEvent e) {
						checkLength();
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						checkLength();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						checkLength();
					}

					public void checkLength() {
						if (description_area.getText().length() > 50
								|| description_area.getLineCount() > 2) {
							JOptionPane.showMessageDialog(null,
									"You cannot have more than 2 lines",
									"Message", JOptionPane.INFORMATION_MESSAGE);
							description_area.setEditable(false);
						}
					}
				});
		return description_area;
	}

	/**
	 * Sets up the other details component.
	 * 
	 * @return Component the other details component
	 */
	private Component setupOtherDetails() {
		details_area = new JTextArea(3, 1);
		details_area.setPreferredSize(details_area.getMinimumSize());
		details_area.setLineWrap(true);
		details_area.setWrapStyleWord(true);
		details_area.setBorder(new TitledBorder(BorderFactory
				.createTitledBorder("Enter Other Details here...")));
		details_area.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					details_area.setText("");
					otherDetails = "";
					details_area.setEditable(true);
				}
			}
		});
		details_area.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				checkLength();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkLength();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				checkLength();
			}

			public void checkLength() {
				if (details_area.getText().length() > 50
						|| details_area.getLineCount() > 2) {
					JOptionPane.showMessageDialog(null,
							"You cannot have more than 2 lines", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					details_area.setEditable(false);
				}
			}
		});
		return details_area;
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
			button_arr[i].setEnabled(true);
			buttonContainer.add(button_arr[i]);
			if (i == 0) {
				buttonContainer.add(new Box.Filler(null, null, null));
			}
			if (i == 1) {
				buttonContainer.add(Box.createRigidArea(new Dimension(10, 0)));
			}
		}
		button_arr[2].setEnabled(false);
		return buttonContainer;
	}

	/**
	 * Sets up the picture container.
	 * 
	 * @return Container the container of the picture and upload button
	 */
	private Container setupPicContainer() {
		Container picContainer = new Container();
		picContainer.setLayout(new BoxLayout(picContainer, BoxLayout.X_AXIS));
		upload = new LiteButton("Upload...");
		upload.addActionListener(this);
		label = new JLabel(new ImageIcon(getClass().getResource(
				"/res/images/placeholder.png")));
		picContainer.add(upload);
		picContainer.add(Box.createRigidArea(new Dimension(80, 30)));
		picContainer.add(new Box.Filler(null, null, null));
		picContainer.add(label);
		return picContainer;
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
		JLabel title = new JLabel("Submission", null, JLabel.CENTER);
		titleContainer.add(title);
		titleContainer.add(Box.createRigidArea(new Dimension(60, 30)));
		user = new LiteButton("User: " + u.getName());
		user.setBorder(new EmptyBorder(0, 0, 0, 0));
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
			weavingPattern = description_area.getText();
			otherDetails = details_area.getText();
			fibersInWeave = fibers_in_weave_area.getText();

			// TODO: Fix stored category location. entry gets stored in
			// loc now
			// Create a new entry with the data from the submission form
			Entry entry = new Entry(userID, categoryID, false, weavingPattern,
					fibersInWeave, otherDetails, image_path);
			entry_list.add(entry);
			dp.saveItem(entry);
			application.changeScreen(new EntriesScreen(application));
		}
		if (event_object.equals(user)) {
			application.changeScreen(new EditRegScreen(application, this));
		}

		if (event_object.equals(upload)) {
			openEvent(file_chooser);
		}
	}

	/**
	 * Opens the file pointed to by the user.
	 * 
	 * @param the_file
	 *            the file to be opened.
	 */
	private void openEvent(final JFileChooser the_file) {
		final int open_option = the_file.showOpenDialog(this);
		final File chosen_file = the_file.getSelectedFile();

		if (open_option == JFileChooser.APPROVE_OPTION
				&& getTypeDescription(chosen_file)) {

			try {
				my_image = ImageIO.read(chosen_file);
				final ImageIcon image_icon = new ImageIcon(
						my_image.getScaledInstance(64, 64, Image.SCALE_DEFAULT));
				image_path = chosen_file.getAbsolutePath();
				label.setIcon(image_icon);
				button_arr[2].setEnabled(true);
			} catch (final IOException exception) {
				JOptionPane.showMessageDialog(null,
						"File did not contain a valid image:" + chosen_file,
						"Message", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * Makes sure an image is used.
	 * 
	 * @param the_file
	 *            the file to analyze
	 * @return Boolean if the file is of the right type
	 */
	public Boolean getTypeDescription(final File the_file) {
		String extension = Utils.getExtension(the_file);
		Boolean type = false;

		if (extension != null) {
			if (extension.equals(Utils.jpeg) || extension.equals(Utils.jpg)
					|| extension.equals(Utils.gif)
					|| extension.equals(Utils.tiff)
					|| extension.equals(Utils.tif)
					|| extension.equals(Utils.png)) {
				type = true;
			}
		}

		return type;
	}

	/**
	 * Inner class to handle the file extensions of the image to upload.
	 * 
	 * @author talal Sadak
	 * 
	 */
	public static class Utils {
		public final static String jpeg = "jpeg";
		public final static String jpg = "jpg";
		public final static String gif = "gif";
		public final static String tiff = "tiff";
		public final static String tif = "tif";
		public final static String png = "png";

		/**
		 * Get the extension of a file.
		 * 
		 * @param the_file
		 * @return String the file extension
		 */
		public static String getExtension(final File the_file) {
			String ext = null;
			String s = the_file.getName();
			int i = s.lastIndexOf('.');

			if (i > 0 && i < s.length() - 1) {
				ext = s.substring(i + 1).toLowerCase();
			}
			return ext;
		}
	}
}
