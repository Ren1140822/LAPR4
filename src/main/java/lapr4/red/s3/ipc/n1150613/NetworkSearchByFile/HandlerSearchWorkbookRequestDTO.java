package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile;

import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.blue.s2.ipc.n1151031.searchnetwork.SearchWorkbookRequestDTO;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.HandlerConnectionDetailsRequestDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;
import lapr4.red.s3.ipc.n1150613.NetworkSearchByFile.Directory;
import lapr4.red.s3.ipc.n1150613.NetworkSearchByFile.FileDTO;

/**
 * The class that handles request DTO's.
 *
 * @author Diogo Guedes - 1150613@isep.ipp.pt
 */
public class HandlerSearchWorkbookRequestDTO implements CommHandler, Serializable {

    private final UIController uiController;
    private Thread files;

    public HandlerSearchWorkbookRequestDTO(UIController uiController) {
        this.uiController = uiController;
    }

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It stored the received DTO as the last received DTO. It sends back a
     * SearchWorkbookResponseDTO.
     *
     * @param dto The received DTO. It is suppose to be an
     * SearchWorkbookRequestDTO.
     * @param outStream The output stream to write the reply.
     */
    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        lastReceivedDTO = dto;
        List<SearchResults> results = new ArrayList();
        PacketEncapsulatorDTO encapsulator = (PacketEncapsulatorDTO) dto;
        SearchWorkbookRequestDTO request = (SearchWorkbookRequestDTO) encapsulator.getDTO();
        String workbookName = request.getWorkbookName();
        Directory dic = new Directory(new File(System.getProperty("user.home") + "/Desktop"));

        try {
            dic.searchFiles();
            for (FileDTO f : dic.getDTO()) {
                Workbook w = dic.load(new File(f.getFilePath()));
                List<Spreadsheet> spreadsheetList = new ArrayList();
                int numSpreadsheets = w.getSpreadsheetCount();
                for (int i = 0; i < numSpreadsheets; i++) {
                    spreadsheetList.add(w.getSpreadsheet(i));
                }
                SearchResults se = new SearchResults(f.getFileName(), spreadsheetList, null);
                results.add(se);
            }
        } catch (IOException ex) {
            System.out.println("erro");
        } catch (ClassNotFoundException ex) {
            System.out.println("123");
        }

        //searches for active workbooks with received name pattern
        //returns a list of workbooks found with the name and spreadsheet list
        Stack<Workbook> activeWorkbooks = uiController.getActiveWorkbooks();
        for (Workbook workbook : activeWorkbooks) {
            if (uiController.getFile(workbook) != null) {
                String name = uiController.getFile(workbook).getName();
                List<Spreadsheet> spreadsheetList = new ArrayList();
                if (name.contains(workbookName) && !workbookName.equals("")) {
                    int numSpreadsheets = workbook.getSpreadsheetCount();
                    for (int i = 0; i < numSpreadsheets; i++) {
                        spreadsheetList.add(workbook.getSpreadsheet(i));
                    }
                    SearchResults searchResult = new SearchResults(name, spreadsheetList, null);
                    results.add(searchResult);
                }
            }
        }

        SearchWorkbookResponseDTO reply = new SearchWorkbookResponseDTO(results);
        try {
            outStream.write(reply);
            outStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(HandlerConnectionDetailsRequestDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * A getter of the last received DTO.
     *
     * @return It returns the last received DTO.
     */
    @Override
    public Object getLastReceivedDTO() {
        return lastReceivedDTO;
    }

}
