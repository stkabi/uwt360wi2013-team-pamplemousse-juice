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
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import data.DataProvider;
import entities.Category;
import entities.User;

public class UserListScreen extends BaseScreen {
    private static final long serialVersionUID = -1373614477586478093L;
    private LiteTable userTable;
    private LiteTable categoryTable;
    private LiteButton logout, remove, add;
    private DataProvider dp;

    public UserListScreen(App application) {
        super(application);
        dp = application.getDataProvider();

        this.setPreferredSize(new Dimension(600, 400));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        userTable = this.createUserTable();
        JScrollPane scrollPane = new JScrollPane(userTable);
        this.add(scrollPane);
        this.add(new Box.Filler(null, null, null));
        categoryTable = this.createCategoryTable();
        
        scrollPane = new JScrollPane(categoryTable);
        this.add(scrollPane);
        
        this.createButtonBar();
        this.setOpaque(true);
        this.setBackground(new Color(255,255,255,255));
    }
    
    private void createButtonBar() {
        logout = new LiteButton("Logout");
        logout.setBackground(LiteButton.BLUE);
        add = new LiteButton("Add Category");
        add.setBackground(LiteButton.GREEN);
        remove = new LiteButton("Remove Category");
        remove.setBackground(LiteButton.RED);
        
        Container buttonContainer = new Container();
        
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));
        buttonContainer.add(add);
        buttonContainer.add(remove);
        buttonContainer.add(new Box.Filler(null, null, null));
        buttonContainer.add(logout);
        
        final App application = this.application;
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                application.loginScreen = null;
                application.showLogin();
            }
        });
        
        //add category
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Category c = new Category();
                c.setName("New Category");
                dp.saveItem(c);
                ((DefaultTableModel) categoryTable.getModel()).addRow(new Object[]{c.getName()});
                categoryTable.editCellAt(categoryTable.getRowCount() - 1, 0);
            }
        });
        
        //remove category
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  
                int row = categoryTable.convertRowIndexToModel(categoryTable.getSelectedRow()); 
                if (row > -1) {
                    ((DefaultTableModel) categoryTable.getModel()).removeRow(row);
                    dp.removeItem(dp.getAllCategories().get(row));
                }
            }
        });
        
        this.add(buttonContainer, BorderLayout.SOUTH);
    }

    private LiteTable createUserTable() {
        ArrayList<User> users = dp.getAllUsers();
        Object[][] data = new Object[users.size()][5];
        
        for (int i = 0; i < users.size(); i += 1) {
            data[i][0] = users.get(i).getEmail();
            data[i][1] = users.get(i).getName();
            data[i][2] = users.get(i).getRole();
            data[i][3] = users.get(i).getAddress();
            data[i][4] = users.get(i).getPhoneNumber();
        }
        
        LiteTable table = new LiteTable();
        DefaultTableModel mdl = new DefaultTableModel();
        mdl.setDataVector(data, new Object[] {"Email", "Name", "Role", "Address", "Phone"});
        table.setModel(mdl);
        table.setRowSelectionAllowed(false);
        
        return table;
    }
    
    private LiteTable createCategoryTable() {
        final ArrayList<Category> categories = dp.getAllCategories();
        Object[][] data = new Object[categories.size()][5];
        
        for (int i = 0; i < categories.size(); i += 1) {
            data[i][0] = categories.get(i).getName();
        }
        
        final LiteTable table = new LiteTable();
        DefaultTableModel mdl = new DefaultTableModel();
        mdl.setDataVector(data, new Object[] {"Categories"});
        table.setModel(mdl); 
        table.setRowSelectionAllowed(true);
        
        //handles editing of category name
        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                TableModel model = (TableModel)e.getSource();
                if (row > -1 && column > -1) {
                    String name = (String)model.getValueAt(row, column);
                    Category c = categories.get(row);
                    c.setName(name);
                    dp.saveItem(c);
                }
            }
        });
        return table;
    }

}
