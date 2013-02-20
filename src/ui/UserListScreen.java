package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import data.DataProvider;
import entities.User;

public class UserListScreen extends BaseScreen {
    private static final long serialVersionUID = -1373614477586478093L;
    private LiteTable table;

    public UserListScreen(App application) {
        super(application);

        this.setPreferredSize(new Dimension(600, 400));

        this.createTable();
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
