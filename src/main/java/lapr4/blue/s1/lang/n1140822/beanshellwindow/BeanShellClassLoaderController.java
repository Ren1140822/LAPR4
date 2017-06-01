/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1140822.beanshellwindow;

import bsh.EvalError;
import csheets.ui.ctrl.UIController;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class BeanShellClassLoaderController {

    /**
     * Creates and executes a script using the various implemented bean shell classes.
     * @param scriptName the name of the script
     * @return the bean shell result class instance
     */
    public BeanShellResult createAndExecuteScript(String scriptName,UIController controller) {
        BeanShellLoader loader = new BeanShellLoader();
        BeanShellResult result = null;
        try {
            BeanShellInstance instance = loader.create(scriptName,controller);
            result = new BeanShellResult(instance.executeScript());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BeanShellClassLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EvalError ex) {
            Logger.getLogger(BeanShellClassLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
