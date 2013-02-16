package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class LiteTable extends JTable {
    private static final long serialVersionUID = -8929617881810754990L;
    
    private void init() {
        this.getTableHeader().setDefaultRenderer(new LiteHeaderCellRenderer());
        this.setDefaultRenderer(Object.class, new LiteCellRenderer());
        
        this.setForeground(new Color(80, 80, 80));
        this.setBackground(new Color(255,255,255));
        this.setRowHeight(25);
        this.setShowGrid(false);
        this.setRowMargin(0);
        this.setIntercellSpacing(new Dimension(0, 0));
        this.setRowSelectionAllowed(false);
        this.setColumnSelectionAllowed(false);
        this.setCellSelectionEnabled(false);
        this.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.setGridColor(new Color(200,200,200));
        this.getTableHeader().setReorderingAllowed(false);
    }

    public LiteTable() {
        this.init();
    }

    public LiteTable(TableModel arg0) {
        super(arg0);
        this.init();
    }

    public LiteTable(TableModel arg0, TableColumnModel arg1) {
        super(arg0, arg1);
        this.init();
    }

    public LiteTable(int arg0, int arg1) {
        super(arg0, arg1);
        this.init();
    }

    @SuppressWarnings("rawtypes")
    public LiteTable(Vector arg0, Vector arg1) {
        super(arg0, arg1);
        this.init();
    }

    public LiteTable(Object[][] arg0, Object[] arg1) {
        super(arg0, arg1);
        this.init();
    }

    public LiteTable(TableModel arg0, TableColumnModel arg1, ListSelectionModel arg2) {
        super(arg0, arg1, arg2);
        this.init();
    }

}
