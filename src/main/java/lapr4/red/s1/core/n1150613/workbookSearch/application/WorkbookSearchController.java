/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150613.workbookSearch.application;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lapr4.red.s1.core.n1150613.workbookSearch.RegexUtil;

/**
 *
 * @author Diogo Guedes
 */
public class WorkbookSearchController {

    /**
     * Contentor to return desired cells information
     */
    private List<String> info;

    /**
     * Utilities from Regex
     */
    private RegexUtil util;
    private UIController ctrl;

    /**
     * Active workbook at the moment
     */
    private Workbook w;
    private Spreadsheet s;
    private Cell c;

    public WorkbookSearchController(UIController ctrl) {
        info = new ArrayList<>();
        this.ctrl = ctrl;

    }

    /**
     * The call to the method to search through the whole woorkbook and match
     * it's contents with the inserted regular expression.
     *
     * @param regex regular expression inserted by user
     * @return desired cells information in String array
     */
    public List<String> checkifRegexMatches(String regex) {
        String content;
        int cont = 0;
        util = new RegexUtil(regex);
        w = ctrl.getActiveWorkbook();

        if (!util.isRegexValid()) {
            return null;
        }

        Iterator<Spreadsheet> it = w.iterator();
        Iterator<Cell> itCell;

        while (it.hasNext()) {
            s = it.next();
            cont++;
            itCell = s.iterator();
            while (itCell.hasNext()) {
                c = itCell.next();
                content = c.getContent();
                if (util.checkIfMatches(content)) {
                    content = content.substring(0, Math.min(content.length(), 10));
                    info.add(content + " Spreadsheet:" + cont + " Adress:" + c.getAddress().toString());
                }
            }
        }

        return info;
    }

}
