/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150690.comments.domain;

import java.io.Serializable;

/**
 * This class represents the system user.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class User implements Serializable {

    /**
     * The name of the user.
     */
    private String usersName;

    /**
     * Creates a user using the current name of the logged session.
     */
    public User() {
        this.usersName = System.getProperty("user.name");
    }
    
    /**
     * Returns the name of the user.
     *
     * @return the user's name
     */
    public String name() {
        return this.usersName;
    }
}
