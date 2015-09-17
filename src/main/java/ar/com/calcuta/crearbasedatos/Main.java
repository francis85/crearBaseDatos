/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.calcuta.crearbasedatos;

import javax.swing.JOptionPane;

/**
 *
 * @author Francisco M. Viola <francimviola@gmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CreateDataBase cdb = new CreateDataBase();
        if (cdb.createDB()) {
            JOptionPane.showMessageDialog(null, "Se creó exitosamente la base de datos: " + CreateDataBase.getDB_NAME());
        } else {
            JOptionPane.showMessageDialog(null, "Hubo un error al crear la base de datos!!!");
        }
    }

}
