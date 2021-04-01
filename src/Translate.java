package totoye;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;

public class Translate {
	
    private int findLastNonWordChar (String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar (String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }
    
    final StyleContext cont = StyleContext.getDefaultStyleContext();
    final AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.ORANGE);
    AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.WHITE);
    private DefaultStyledDocument doc = new DefaultStyledDocument() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

    public void insertString (int offset, String str, AttributeSet a) throws BadLocationException {
            super.insertString(offset, str, a);

            String text = getText(0, getLength());
            int before = findLastNonWordChar(text, offset);
            if (before < 0) before = 0;
            int after = findFirstNonWordChar(text, offset + str.length());
            int wordL = before;
            int wordR = before;

            while (wordR <= after) {
                if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                    if (text.substring(wordL, wordR).matches("(\\W)*(TotoyeVN|let|var|const|if|else|else if|for|while|switch|break|case|continue|function|new)"))
                        setCharacterAttributes(wordL, wordR - wordL, attr, false);
                    else
                        setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
                    wordL = wordR;
                }
                wordR++;
            }
        }

        public void remove (int offs, int len) throws BadLocationException {
            super.remove(offs, len);

            String text = getText(0, getLength());
            int before = findLastNonWordChar(text, offs);
            if (before < 0) before = 0;
            int after = findFirstNonWordChar(text, offs);

            if (text.substring(before, after).matches("(\\W)*(let|var|const|if|else|else if|for|while|switch|break|case|continue|function|new)")) {
                setCharacterAttributes(before, after - before, attr, false);
            } else {
                setCharacterAttributes(before, after - before, attrBlack, false);
            }
        }
    };
        
    Translate(){
    	
    }
    
    public DefaultStyledDocument getDoc(){
    	return doc;
    }
    
    Translate(JTextPane txt) {
    	       
    	attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, txt.getForeground());   	
    }
}
