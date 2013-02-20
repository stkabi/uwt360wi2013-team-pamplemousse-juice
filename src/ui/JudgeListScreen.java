package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import data.DataProvider;
import entities.Entry;

public class JudgeListScreen extends BaseScreen {
    private static final long serialVersionUID = -1373614477586478093L;
    private LiteTable table;

    public JudgeListScreen(App application) {
        super(application);

        this.setPreferredSize(new Dimension(300, 400));

        this.createTable();
    }

    private void createTable() {
        DataProvider dp = this.application.getDataProvider();
        ArrayList<Entry> entries = dp.getEntriesForJudge(this.application.getLoggedInUser().getID());

        Object[][] data = new Object[entries.size()][3];

        for (int i = 0; i < entries.size(); i += 1) {
            data[i][0] = entries.get(i).getID();
            data[i][1] = dp.getUserById(entries.get(i).getUserID()).getName();
            data[i][2] = dp.getCategoryById(entries.get(i).getCategoryID()).getName();
        }

        table = new LiteTable(data, new Object[] { "EntryID", "Name", "Category" });
        this.setLayout(new BorderLayout());
        this.add(table.getTableHeader(), BorderLayout.NORTH);
        this.add(table, BorderLayout.CENTER);
    }

}
