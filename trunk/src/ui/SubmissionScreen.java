package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import data.DataProvider;
import entities.Entry;
import entities.User;

public class SubmissionScreen extends BaseScreen implements ActionListener {

    private static final long serialVersionUID = -7491538055377138917L;
    private User current_user;
    private DataProvider user_data;
    private ArrayList<Entry> entry_list = new ArrayList<Entry>();

    private String userID = "";
    private String categoryID = "";
    private String weavingPattern;
    private String fibersInWeave;
    private String otherDetails;

    private LiteButton logout, back, submit, user, upload, label;
    private LiteButton[] button_arr = { logout, back, submit };
    private String[] button_txt = { "Logout", "Back", "Submit" };
    private Color[] button_color = { LiteButton.RED, LiteButton.BLUE, LiteButton.GREEN };

    private BaseScreen last_screen;
    private BufferedImage my_image;
    private JTextArea details_area;

    public SubmissionScreen(final App the_application, final BaseScreen the_screen, final String the_category) {
        super(the_application);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setPreferredSize(new Dimension(300, 400));

        last_screen = the_screen;
        current_user = the_application.getLoggedInUser();
        user_data = the_application.getDataProvider();

        setupOtherDetailsComponnent();

        // userID = current_user.id;
        categoryID = the_category;
        // weavingPattern
        // fibersInWeave
        // otherDetails

        // entry_list = user_data.getEntriesByUserId(current_user.id);

        Container buttonContainer = new Container();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));

        Border padding = new EmptyBorder(8, 8, 8, 8);
        padding = new CompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), padding);

        Container titleContainer = setupTitleContainer(current_user);
        setUploadLabel();
        Container picContainer = setupPicContainer();
        setupButtons(buttonContainer);

        this.add(Box.createVerticalStrut(10));
        this.add(titleContainer);
        this.add(new JSeparator(JSeparator.HORIZONTAL), -1);
        this.add(Box.createRigidArea(new Dimension(100, 30)));

        this.add(picContainer);
        this.add(details_area);
        this.add(Box.createRigidArea(new Dimension(100, 50)));
        this.add(Box.createVerticalStrut(30));
        this.add(buttonContainer);
    }

    private void setupOtherDetailsComponnent() {
        details_area = new JTextArea(3, 1);
        details_area.setLineWrap(true);
        details_area.setWrapStyleWord(true);
        details_area.setBorder(new TitledBorder(BorderFactory.createTitledBorder("Enter Other Details here...")));
        details_area.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                check();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                check();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                check();
            }

            public void check() {
                if (details_area.getLineCount() > 3) {
                    JOptionPane.showMessageDialog(null, "You cannot have more than 3 lines", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void setUploadLabel() {
        upload = new LiteButton("Upload...");
        upload.addActionListener(this);
        label = new LiteButton();
        label.setBackground(Color.WHITE);
        label.setFocusable(false);
        label.setFocusPainted(false);
    }

    private void setupButtons(Container buttonContainer) {
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
    }

    private Container setupPicContainer() {
        Container picContainer = new Container();
        picContainer.setLayout(new BoxLayout(picContainer, BoxLayout.X_AXIS));
        picContainer.add(upload);
        picContainer.add(Box.createRigidArea(new Dimension(80, 30)));
        picContainer.add(new Box.Filler(null, null, null));
        picContainer.add(label);
        return picContainer;
    }

    private Container setupTitleContainer(final User current_user2) {
        Container titleContainer = new Container();
        titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.X_AXIS));
        JLabel title = new JLabel("Submission", null, JLabel.CENTER);
        titleContainer.add(title);
        titleContainer.add(Box.createRigidArea(new Dimension(60, 30)));
        user = new LiteButton("User: " + "user");
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
        return titleContainer;
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
            // TODO: handle Submit using categoryID
        }
        if (event_object.equals(user)) {
            application.changeScreen(new EditRegScreen(application, this));
        }

        if (event_object.equals(upload)) {
            openEvent(file_chooser);
        }
    }

    /**
     * This method opens the file pointed to by the user.
     * 
     * @param the_file
     *            the file to be open.
     */
    private void openEvent(final JFileChooser the_file) {
        final int open_option = the_file.showOpenDialog(this);
        final File chosen_file = the_file.getSelectedFile();

        if (open_option == JFileChooser.APPROVE_OPTION && getTypeDescription(chosen_file)) {

            try {
                my_image = ImageIO.read(chosen_file);
                final ImageIcon image_icon = new ImageIcon(my_image.getScaledInstance(100, 100, Image.SCALE_DEFAULT));

                label.setIcon(image_icon);
                button_arr[2].setEnabled(true);
            } catch (final IOException exception) {
                JOptionPane.showMessageDialog(null, "File did not contain a valid image:" + chosen_file, "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public Boolean getTypeDescription(final File the_file) {
        String extension = Utils.getExtension(the_file);
        Boolean type = false;

        if (extension != null) {
            if (extension.equals(Utils.jpeg) || extension.equals(Utils.jpg) || extension.equals(Utils.gif) || extension.equals(Utils.tiff) || extension.equals(Utils.tif) || extension.equals(Utils.png)) {
                type = true;
            }
        }

        return type;
    }

    public static class Utils {
        public final static String jpeg = "jpeg";
        public final static String jpg = "jpg";
        public final static String gif = "gif";
        public final static String tiff = "tiff";
        public final static String tif = "tif";
        public final static String png = "png";

        /* Get the extension of a file. */
        public static String getExtension(File f) {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i + 1).toLowerCase();
            }
            return ext;
        }
    }
}
