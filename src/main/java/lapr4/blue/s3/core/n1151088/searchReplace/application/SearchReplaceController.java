package lapr4.blue.s3.core.n1151088.searchReplace.application;

import csheets.core.Cell;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import lapr4.blue.s3.core.n1151088.searchReplace.domain.Previewer;
import lapr4.green.s2.core.n1150838.GlobalSearch.application.GlobalSearchController;
import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.CellInfoDTO;
import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.CellList;

/**
 *
 * @author Diana Silva [1151088@isep.ipp.pt]
 */

public class SearchReplaceController extends GlobalSearchController{

    protected Thread threadReplace;
    protected Thread threadPreview;
    
    public SearchReplaceController(UIController ctrl) {
         super(ctrl);
    }
    
    /**
     * Starts the thread to search 
     * @param content replaced content
     */
     public void  startPreviewThread(Cell cell, String content,String exp){
         threadPreview = new Thread(new Previewer(cell, exp, content, ctrl));
         threadPreview.start();
    }
    /**
     * Stop the search thread
     */
    public void stopPreviewThread(){
        if(threadPreview!=null){
            threadPreview.stop();
        }
    }
    
     /**
     * Starts the thread to search 
     * @param cellList list of cell which will be replaced
     * @param content content to replace
     */
     public void  startReplaceThread(CellList cellList, String content){
//         threadReplace = new Thread(new Previewer(cellList, content, ctrl));
//         threadReplace.start();
    }
    /**
     * Stop the search thread
     */
    public void stopReplaceThread(){
//        if(threadReplace!=null){
//            threadReplace.stop();
//        }
    }
    
    
    
}
