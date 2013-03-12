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
import entities.Entry;

public class JudgeListScreen extends BaseScreen {
	private static final long serialVersionUID = -1373614477586478093L;
	private LiteTable table;
	private LiteButton logout;

	public JudgeListScreen(App application) {
		super(application);

		this.setPreferredSize(new Dimension(300, 400));

		this.createTable();
		this.createButtonBar();
		this.setOpaque(true);
		this.setBackground(new Color(255, 255, 255, 255));
	}

	private void createButtonBar() {
		logout = new LiteButton("Logout");
		logout.setBackground(LiteButton.RED);

		Container buttonContainer = new Container();
		buttonContainer.setLayout(new BoxLayout(buttonContainer,
				BoxLayout.X_AXIS));
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
		DataProvider dp = this.application.getDataProvider();
		ArrayList<Entry> entries = dp.getEntriesForJudge(this.application
				.getLoggedInUser().getID());

		Object[][] data = new Object[entries.size()][2];

		for (int i = 0; i < entries.size(); i += 1) {

			data[i][0] = dp.getUserById(entries.get(i).getUserID()).getName();
			data[i][1] = dp.getCategoryById(entries.get(i).getCategoryID())
					.getName();
		}

		table = new LiteTable(data, new Object[] { "Name", "Category" });
		this.setLayout(new BorderLayout());
		this.add(table.getTableHeader(), BorderLayout.NORTH);
		this.add(table, BorderLayout.CENTER);
	}

}
