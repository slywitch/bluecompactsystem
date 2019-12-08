
package classes;

/* 
 FixedLengthDocument.java
*/

//import com.sun.java.swing.text.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
//import com.sun.java.swing.SwingUtilities3;

public class FixedLengthDocument extends PlainDocument {
  private int limit;
  // optional uppercase conversion
  private boolean toUppercase = false;

  FixedLengthDocument(int limit) {
   super();
   this.limit = limit;
   }

  FixedLengthDocument(int limit, boolean upper) {
   super();
   this.limit = limit;
   toUppercase = upper;
   }

  public void insertString
    (int offset, String  str, AttributeSet attr)
      throws BadLocationException {
   if (str == null) return;

   if ((getLength() + str.length()) <= limit) {
     if (toUppercase) str = str.toUpperCase();
     super.insertString(offset, str, attr);
     }
   }
}
