/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export.Action;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import lapr4.green.s3.lang.n1150657.XML.Export.UI.ExportSelectedWorkbookUI;

/**
 *
 * @author Eric with improvements of Sofia Gonçalves (1150657)
 */
public class ExportWorkBookActionUI extends BaseAction{
    /**
     * The user interface controller
     */
    protected UIController uiController;

    /**
     * Creates a new action.
     *
     * @param uiController the user interface controller
     */
    public ExportWorkBookActionUI(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Selected workbook";
    }

    /**
     * A simple action that presents a confirmation dialog. If the user confirms
     * then the workbook is exported to xml
     *
     * @param event the event that was fired
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        ExportSelectedWorkbookUI exportSelectedWorkbookUI = new ExportSelectedWorkbookUI(uiController);
    }
}
