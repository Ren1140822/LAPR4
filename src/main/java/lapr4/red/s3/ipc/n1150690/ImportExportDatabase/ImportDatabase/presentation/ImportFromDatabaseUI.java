/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ImportDatabase.presentation;

import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ImportDatabase.application.ImportFromDatabaseController;

/**
 * The user interface to import data from database.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ImportFromDatabaseUI extends JDialog {

    /**
     * The width of the JDialog.
     */
    private static final int WIDTH = 400;

    /**
     * The height of the JDialog.
     */
    private static final int HEIGHT = 300;

    /**
     * The controller of this use case.
     */
    private ImportFromDatabaseController controller;

    /**
     * The user interface controller.
     */
    private UIController uiController;

    private JTextField txtFieldFirstCell;

    private JTextField txtFieldLastCell;

    /**
     * The JTextField to insert the table name.
     */
    private JTextField txtTableName;

    /**
     * The JTextField to insert the database connection.
     */
    private JTextField txtDatabaseConnection;

    /**
     * The JLabel to indicate that the importation was successful.
     */
    private JLabel success;

    /**
     * The JPanel of this JDialog
     */
    private JPanel panel;

    /**
     * Creates a new Import from database interface.
     *
     * @param uiController the user interface controller
     */
    public ImportFromDatabaseUI(UIController uiController) {
        this.uiController = uiController;
        super.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        super.add(createComponents());
        setFocusable(true);
        setTitle("Import From Database");
        setLocationRelativeTo(null);
        super.setVisible(true);
    }

    /**
     * Creates the components for this user interface.
     *
     * @return the created JPanel
     */
    private JPanel createComponents() {
        JPanel panelRange = new JPanel(new FlowLayout());
        JLabel labelFirstCell = new JLabel("First Cell:");
        panelRange.add(labelFirstCell);
        txtFieldFirstCell = new JTextField(5);
        panelRange.add(txtFieldFirstCell);
        JLabel labelLastCell = new JLabel("Last Cell:");
        panelRange.add(labelLastCell);
        txtFieldLastCell = new JTextField(5);
        panelRange.add(txtFieldLastCell);

        JPanel panelTableName = new JPanel();
        JLabel labelTableName = new JLabel("Table name:");
        panelTableName.add(labelTableName);
        txtTableName = new JTextField(15);
        panelTableName.add(txtTableName);

        JPanel panelConnection = new JPanel();
        JLabel labelDatabaseConnection = new JLabel("Database Connection:");
        panelConnection.add(labelDatabaseConnection);
        txtDatabaseConnection = new JTextField(20);
        txtDatabaseConnection.setText("jdbc:h2:..\\db\\csheets-crm-extension");
        panelConnection.add(txtDatabaseConnection);

        JButton b = new JButton("Import");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CellRange cellRange = null;
                if (!(txtFieldFirstCell.getText().equals("") && txtFieldLastCell.getText().equals(""))) {
                    String addressStrFirstCell = txtFieldFirstCell.getText();
                    String addressStrLastCell = txtFieldLastCell.getText();
                    cellRange = new CellRange(addressStrFirstCell, addressStrLastCell, uiController);
                }
                controller = new ImportFromDatabaseController(uiController, txtTableName.getText(), txtDatabaseConnection.getText(), cellRange);
                try {
                    controller.importFromDatabase();
                    success.setText("Importation Successful!");
                    success.setForeground(Color.GREEN);
                } catch (SQLException ex) {
                    //if (ex.getErrorCode() == 42101) {
                    //System.out.println("ola");
                    //tableAlreadyExists();
                    //}
                } catch (InterruptedException ex) {
                    Logger.getLogger(ImportFromDatabaseUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();

        grid.gridx = 0;
        grid.gridy = 0;
        JLabel labelRange = new JLabel("<html><b>Range of Cells</b></html>");
        panel.add(labelRange, grid);
        grid.gridy = 1;
        panel.add(panelRange, grid);
        grid.gridy = 2;
        panel.add(panelTableName, grid);
        grid.gridy = 3;
        panel.add(panelConnection, grid);
        grid.gridy = 4;
        panel.add(b, grid);
        grid.gridy = 5;
        success = new JLabel();
        panel.add(success, grid);

        return panel;
    }

}
