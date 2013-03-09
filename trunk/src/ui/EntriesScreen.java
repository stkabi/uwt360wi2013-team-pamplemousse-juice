/*
 * TCSS360 Winter 2013
 * Team Pamplemouse_Juice
 * Talal SADAK
 * Weaving application
 */

package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import data.DataProvider;
import entities.Category;
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

	/** the buttons of this screen */
	private LiteButton logout, remove, add, user_button;

	/** the buttons array */
	private LiteButton[] button_arr = { logout, remove, add };

	/** the buttons text array */
	private String[] button_txt = { "Logout", "Remove", "Add" };

	/** the button colors */
	private Color[] button_color = { LiteButton.RED, LiteButton.BLUE,
			LiteButton.GREEN };

	/** the rules text area */
	private JTextPane rules_area = new JTextPane();

	/** the rules scroll pane container */
	private JScrollPane rules_scroll_pane = new JScrollPane(rules_area);

	/** the entries scroll pane container */
	private JScrollPane entries_scroll_pane;

	/** the category ID text */
	private String categoryID = "";

	/** rules area text font */
	final Font font = new Font("Plain", Font.PLAIN, 12);

	/** the user entries table */
	private LiteTable entriesTable;

	/** the table data */
	private Object[][] data;

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
		data = new Object[entry_list.size()][2];
		initialize();
	}

	/** sets up the panel */
	private void initialize() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(300, 400));
		this.setBackground(Color.white);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(Box.createVerticalStrut(10));
		this.add(setupTitleContainer());
		this.add(new JSeparator(JSeparator.HORIZONTAL), -1);
		this.add(Box.createRigidArea(new Dimension(100, 30)));
		this.add(Box.createVerticalStrut(10));
		this.add(setupEntriesContainer());
		this.add(Box.createRigidArea(new Dimension(300, 30)));
		this.add(setupRulesComponent());
		this.add(Box.createVerticalStrut(60));
		this.add(setupButtonsContainer());
	}

	/**
	 * Sets up the multiple entries into separate containers.
	 * 
	 * @return the entries container
	 * 
	 */
	private Component setupEntriesContainer() {

		if (entry_list.size() == 0) {
			return getEmptyLabel();
		}
		for (int i = 0; i < entry_list.size(); i += 1) {
			Category c = dp.getCategoryById(entry_list.get(i).getCategoryID());
			data[i][0] = c != null ? c.getName() : "Unknown Category";
			data[i][1] = entry_list.get(i).getOtherDetails();
		}

		entriesTable = new LiteTable();
		DefaultTableModel mdl = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		mdl.setDataVector(data, new Object[] { "Entered Categories",
				"Description" });

		entriesTable.setModel(mdl);
		entriesTable.setGridColor(Color.BLACK);
		entriesTable.setToolTipText("Select a category to delete");
		entriesTable.setRowSelectionAllowed(true);
		entriesTable.setSelectionBackground(new Color(222, 207, 182));
		entriesTable.setShowGrid(true);
		entriesTable.setIntercellSpacing(new Dimension(0, 0));
		
		entriesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        button_arr[1].setEnabled(entriesTable.getSelectedRow() >= -1);
		    }
		});
		
		entries_scroll_pane = new JScrollPane(entriesTable);
		Dimension tDim = entriesTable.getPreferredScrollableViewportSize();
		entries_scroll_pane.setBackground(Color.WHITE);
		entries_scroll_pane.setPreferredSize(new Dimension(tDim.width,
				tDim.height + 1500));
		return entries_scroll_pane;

	}

	/**
	 * sets up the initial empty message.
	 * 
	 * @return the empty label component
	 */
	private Component getEmptyLabel() {
		LiteTextField empty_label = new LiteTextField("No entries !");
		empty_label.setForeground(Color.BLACK);
		empty_label.setBackground(Color.WHITE);
		empty_label.setHorizontalAlignment(SwingConstants.CENTER);
		empty_label.setBorder(new EmptyBorder(0, 0, 0, 0));
		empty_label.setFocusable(false);
		empty_label.setEnabled(false);
		this.add(empty_label);
		return empty_label;
	}

	/**
	 * sets up the rules component.
	 * 
	 * @return the rules component
	 */
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

		rules_scroll_pane.setMinimumSize(rules_area.getMinimumSize());
		JScrollBar scrollbar = rules_scroll_pane.getVerticalScrollBar();
		scrollbar.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				int p = e.getValue();
				if (p > 300 && entry_list.size() < 3) {
					button_arr[2].setEnabled(true);
					rules_area.setToolTipText("You have accepted the rules");
				}
			}
		});
		rules_scroll_pane.getViewport().setViewPosition(new Point(0, 0));
		labelContainer.add(new JLabel("Contest Rules: "));
		labelContainer.add(new Box.Filler(null, null, null));
		rulesContainer.add(labelContainer);
		rulesContainer.add(rules_scroll_pane);
		return rulesContainer;
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
			button_arr[i].setToolTipText(button_txt[i]);
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

		// Handle the logout button
		if (event_object.equals(button_arr[0])) {
			application.loginScreen = null;
			application.showLogin();
		}
		// Handle the remove button
		if (event_object.equals(button_arr[1])) {
		    
		    //get entry. Entry_list sorting will match entries table row index
		    Entry en = entry_list.get(entriesTable.getSelectedRow());
		    
		    //remove it from database
		    dp.removeItem(en);
		    entry_list = dp.getEntriesByUserId(u.getID());
		    
		    //remove it from table
		    DefaultTableModel mdl = (DefaultTableModel) entriesTable.getModel();
            mdl.removeRow(entriesTable.getSelectedRow());

            //disable remove button because now the table doesn't have a selection
            button_arr[1].setEnabled(false);
		}
		// Handle the add button
		if (event_object.equals(button_arr[2])) {
			application.changeScreen(new SubmissionScreen(application, this));
		}
        
		// Handle click on the user (editing)
		if (event_object.equals(user_button)) {
			application.changeScreen(new EditRegScreen(application, this));
		}
	}
}
