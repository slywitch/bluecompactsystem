/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 *
 * @author Jeferson
 */
public class Combo {

    private String Combo;
    private int codCombo;

    public Combo(int codigo, String nome) {
        this.codCombo = codigo;
        this.Combo = nome;
    }

    public String toString() {
        return Combo;
    }

    public int getKey() {
        return codCombo;
    }
}

//}
