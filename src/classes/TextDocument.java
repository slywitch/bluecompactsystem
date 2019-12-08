/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Jeferson
 */

public class TextDocument extends PlainDocument {

    private int tamanhoMax = 4000;


    public TextDocument() {
        super();
    }

    public TextDocument(int tamanho) {
        super();
        tamanhoMax = tamanho;
    }

    /**
    * A cada tecla pressionada valida a tecla verifica se não está no máximo que o campo pode aguentar.
    *
    * @see javax.swing.text.Document#insertString(int, java.lang.String, javax.swing.text.AttributeSet)
    */
    
    public void insertString(int offs, String str, AttributeSet a)
    throws BadLocationException {

        if (str == null) return;

            String oldString = getText (0, getLength() );
            String newString = oldString.substring(0, offs) + str + oldString.substring(offs);

        if (newString.length() > tamanhoMax) {
            super.insertString(offs, "", a);
        } else {
            // Repassa para o pai.
            super.insertString(offs, str, a);
    }

    }

}