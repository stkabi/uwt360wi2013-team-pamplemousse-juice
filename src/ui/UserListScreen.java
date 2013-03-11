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
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;

import data.DataProvider;
import entities.User;
import entities.User.Role;

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

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void createTable() {
        final DataProvider dp = new DataProvider();
        final ArrayList<User> users = dp.getAllUsers();
        Object[][] data = new Object[users.size()][5];
        
        for (int i = 0; i < users.size(); i += 1) {
            data[i][0] = users.get(i).getEmail();
            data[i][1] = users.get(i).getName();
            data[i][2] = users.get(i).getRole();
            data[i][3] = users.get(i).getAddress();
            data[i][4] = users.get(i).getPhoneNumber();
        }
        
        table = new LiteTable(data, new Object[] {"Email", "Name", "Role", "Address", "Phone"});

        TableColumn roleColumn = table.getColumnModel().getColumn(2);
        JComboBox roleCombo = new JComboBox();
        roleCombo.addItem("ORGANIZER");
        roleCombo.addItem("JUDGE");
        roleCombo.addItem("CONTESTANT");
        roleColumn.setCellEditor(new DefaultCellEditor(roleCombo));
        roleCombo.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		JComboBox source = (JComboBox) arg0.getSource();
		Role role = User.Role.valueOf((String) source.getSelectedItem());
		User user = users.get(table.getSelectedRow());
		user.setRole(role);
		dp.saveItem(user);
	    }            
        });
        
        this.setLayout(new BorderLayout());
        this.add(table.getTableHeader(), BorderLayout.NORTH);
        this.add(table, BorderLayout.CENTER);
    }
}