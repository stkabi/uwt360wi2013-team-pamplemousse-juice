package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;

import data.DataProvider;
import entities.User;

public class UserListScreen extends BaseScreen {
    private static final long serialVersionUID = -1373614477586478093L;
    private LiteTable table;
    private LiteButton logout;

    public UserListScreen(App application) {
        super(application);

        this.setPreferredSize(new Dimension(600, 400));

        this.createTable();
        this.createButtonBar();
        this.setOpaque(true);
        this.setBackground(new Color(255,255,255,255));
    }
    
    private void createButtonBar() {
        logout = new LiteButton("Logout");
        logout.setBackground(LiteButton.RED);
        
        Container buttonContainer = new Container();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));
        buttonContainer.add(logout);
        buttonContainer.add(new Box.Filler(null, null, null));
        
        final App application = this.application;
        logout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                application.loginScreen = null;
                application.showLogin();
            }
            
        });
        this.add(buttonContainer, BorderLayout.SOUTH);
    }

    private void createTable() {
        DataProvider dp = new DataProvider();
        ArrayList<User> users = dp.getAllUsers();
        Object[][] data = new Object[users.size()][5];
        
        for (int i = 0; i < users.size(); i += 1) {
            data[i][0] = users.get(i).getEmail();
            data[i][1] = users.get(i).getName();
            data[i][2] = users.get(i).getRole();
            data[i][3] = users.get(i).getAddress();
            data[i][4] = users.get(i).getPhoneNumber();
        }
        
        table = new LiteTable(data, new Object[] {"Email", "Name", "Role", "Address", "Phone"});
        this.setLayout(new BorderLayout());
        this.add(table.getTableHeader(), BorderLayout.NORTH);
        this.add(table, BorderLayout.CENTER);
    }

}
