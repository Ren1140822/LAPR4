/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Import.UI;

import csheets.ui.ctrl.UIController;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import lapr4.green.s3.lang.n1150657.XML.Export.Path;
import lapr4.green.s3.lang.n1150657.XML.Import.ImportXML;
import lapr4.green.s3.lang.n1150657.XML.Import.ImportXMLController;
import lapr4.red.s2.lang.n1151094.ImportXML.presentation.MyJFileChooser;

/**
 *
 * @author Sofia
 */
public class ImportSelectedSpreadsheetUI {

    private UIController uiController;

    public ImportSelectedSpreadsheetUI(UIController uiController) {
        this.uiController = uiController;
        initialize();
    }

    private void initialize() {
        MyJFileChooser fileChooser = new MyJFileChooser();
        int answer = fileChooser.showOpenDialog(null);

        if (answer == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            if (Path.validateExtension(file)) {
                ImportXMLController importXmlController = new ImportXMLController(uiController, file.getPath(), true);
                int numberSpreasheets = importXmlController.countNumberSpreadSheet(file.getPath());
                Object[] spreads = new Object[numberSpreasheets];
                for (int i = 0; i < spreads.length; i++) {
                    spreads[i] = i + 1;
                }

                int spreadChoice = JOptionPane.showOptionDialog(null,
                        "Choose spreadsheet:",
                        "Spreadsheet",
                        JOptionPane.OK_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, spreads, spreads[0]);
                ImportXML.choiceSpreadSheet(spreadChoice);
                Object[] options = {"Replace", "Append"};
                int choice = JOptionPane.showOptionDialog(null,
                        "Choose option:",
                        "Import option",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);

                if (choice == JOptionPane.YES_OPTION) {
                    importXmlController.importXml();
                } else {
                    importXmlController.setIsReplace(false);
                    importXmlController.importXml();
                }
                JOptionPane.showMessageDialog(
                        null,
                        "Uploaded File", "Import",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Unable to read file: " + file.getPath() + " !",
                        "Import",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
