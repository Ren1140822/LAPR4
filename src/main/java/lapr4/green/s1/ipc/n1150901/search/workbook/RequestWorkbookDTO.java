/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150901.search.workbook;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class RequestWorkbookDTO {

    private final String workbookName;

    public RequestWorkbookDTO(String wbName) {
        workbookName = wbName;
    }

    public String getWorkbookName() {
        return workbookName;
    }
}