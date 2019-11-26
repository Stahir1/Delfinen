/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfinen.Model.Medlemmer;

import delfinen.Model.Info;

/**
 *
 * @author Danie
 */
public class Medlem extends Info {
    
    public Medlem(String name, int age, String email, int phoneNumber, String city, int zipCode, String adress) {
        super(name, age, email, phoneNumber, city, zipCode, adress);
    }
    
}
