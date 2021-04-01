package totoye;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.*;

public class EditorListener {
	
    private int findLastNonWordChar(String text, int index) 
	{
        while (--index >= 0) 
		{
            if (String.valueOf(text.charAt(index)).matches("\\W"))
			{
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar (String text, int index) 
	{
        while (index < text.length()) 
		{
            if (String.valueOf(text.charAt(index)).matches("\\W")) 
			{
                break;
            }
            index++;
        }
        return index;
    }
    
    
    private void updating(int offset, String str, DefaultStyledDocument document) throws BadLocationException
    {
    	content = container.getText();
    	
    	if(str.length() < 2)
		{
			document.setCharacterAttributes(0, content.length(), attrBlack, false);
		}
    	
    	String text = document.getText(0, document.getLength());
		int before = findLastNonWordChar(text, offset);
		if (before < 0) before = 0;
		int after = findFirstNonWordChar(text, offset + str.length());
		int wordL = before;
		int wordR = before;
		
		//-----------------------------
		
		//Customize keywords
		while (wordR <= after) 
		{
			if(wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W"))
			{	
				if (text.substring(wordL, wordR).matches("(\\W)*(await|break|case|catch|class|const|continue|debugger|default|delete|do|else|enum|export|extends|false|finally|for|function|if|implements|import|in|instanceof|interface|let|new|null|package|private|protected|public|return|super|switch|self|static|this|throw|try|true|typeof|var|void|while|with|yield)"))
				{
					document.setCharacterAttributes(wordL, wordR - wordL, attr, false);
				}
				else if(text.substring(wordL, wordR).matches("(\\W)*(document|console|window|eval|toString|undefined|valueOf|Number|parseInt|Date|isNaN|NaN|alert|setInterval|clearInterval|innerHeight|innerWidth|screenX|screenY|prompt|confirm|onkeydown|onkeypress|onkeyup|onmouseover|onclick|onerror|onfocus|onload|onmouseup|onmousedown|onsubmit|TotoyeVN)"))
				{
					document.setCharacterAttributes(wordL, wordR - wordL, attrW, false);
				}
				else if(text.substring(wordL, wordR).matches("(\\W)*(Book|Character|Reader|Screen|button|image|Audio|Key|.addDialog|.addChoice|.background|.show|.hide|.play|.pause|.resume|.stop|.volume|.startChapter|.endChapter|.setLetterByLetterDisplay|.addBlackScreen|.setBook|.startAt|.read|.close|.roundedNameBox|.roundedDialogBox|.setTextColor|.setBoxColor|.saveJSON|.loadJSON|.createLayer|.addToLayer|.open|.changeView|.setPageIcon|.setTitle|.setBackground|.setFont|.setFontColor|.setFontSize|.setFontStyle|.setBorder|.setBorderWidth|.setBorderColor|.setBorderStyle|.roundedBorder|.setWidth|.setHeight|.alignX|.alignY|.setX|.setY|.eventListener|.src|.loop|.setTime)"))
				{
					document.setCharacterAttributes(wordL, wordR - wordL, attrT, false);
				}
				else
				{
					document.setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
				}
				
				wordL = wordR;
			}
			
			wordR++;
		}
		
		//Customize String 			
		Pattern pl = Pattern.compile("\"");
		Matcher match2 = pl.matcher(text);
		int beg = 0; int end = 0;
		
		while(match2.find())
		{
			beg = match2.start();
			match2.find();
			end = match2.start();
			
			document.setCharacterAttributes(beg, end - beg + 1, attrS, true);
		}
		
		//Customize single-line and multiple-lines comments
		
		Pattern singleLinecommentsPattern = Pattern.compile("\\/\\/.*");
		Matcher matcher = singleLinecommentsPattern.matcher(text);

		while (matcher.find()) 
		{
		    document.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), attrC, true);
		}

		Pattern multipleLinecommentsPattern = Pattern.compile("\\/\\*.*?\\*\\/", Pattern.DOTALL);
		matcher = multipleLinecommentsPattern.matcher(text);

		while (matcher.find()) 
		{
		    document.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), attrC, true);
		}	
	}
    
    final StyleContext cont = StyleContext.getDefaultStyleContext();
    //for Keywords
    AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(246,40,23));
    //for key functions
    AttributeSet attrW = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(21,105,199));
    //for comments
    AttributeSet attrC = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(94,125,126));
    //for strings
    AttributeSet attrS = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(255,174,66));
    //for API methods
    AttributeSet attrT = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(226,56,236));
    //for normal
    AttributeSet attrBlack;
    
    private String content = "";
    private JTextPane container;
	
    private DefaultStyledDocument doc = new DefaultStyledDocument() 
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void insertString (int offset, String str, AttributeSet a) throws BadLocationException 
		{
			super.insertString(offset, str, a);
			
			updating(offset,str, this);
		}

        public void remove (int offs, int len) throws BadLocationException 
		{
            super.remove(offs, len);
            
            content = container.getText();
            String text = getText(0, getLength());
            
            //Customize String 
			
			Pattern pl = Pattern.compile("\"");
			Matcher match2 = pl.matcher(text);
			int beg = 0; int end = 0;
			
			while(match2.find())
			{
				beg = match2.start();
				match2.find();
				end = match2.start();
				
				setCharacterAttributes(beg, end - beg + 1, attrS, true);
			}
            
            //-----Customize single-line and multiple-lines comments
			
			Pattern singleLinecommentsPattern = Pattern.compile("\\/\\/.*");
			Matcher matcher = singleLinecommentsPattern.matcher(text);

			while (matcher.find()) 
			{
			    setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), attrC, true);
			}

			Pattern multipleLinecommentsPattern = Pattern.compile("\\/\\*.*?\\*\\/", Pattern.DOTALL);
			matcher = multipleLinecommentsPattern.matcher(text);

			while (matcher.find()) 
			{
			    setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), attrC, true);
			}
			
        }
    };
        
    EditorListener()
	{
    	attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.WHITE);
    }
    
    public DefaultStyledDocument getDoc()
	{
    	return doc;
    }
    
    public void setContainer(JTextPane re)
    {
    	container = re;
    }
    
    public void setComment(Color col)
    {
        attrC = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, col);
    }
    
    public void setString(Color col)
    {
    	attrS = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, col);   
    }
    
    public void setFunction(Color col)
    {
        attrW = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, col); 
    }
    
    public void setKeyword(Color col)
    {
    	attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, col);
    }
    
    public void setAPI(Color col)
    {
        attrT = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, col);  
    }
    
    public void setBackColor(Color col)
    {
    	attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, col);
    }
    
    EditorListener(JTextPane txt) 
	{	       
    	attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, txt.getForeground());   	
    }
}


