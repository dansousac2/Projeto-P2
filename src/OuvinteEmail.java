import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class OuvinteEmail implements KeyListener{
	String caractere = "0123456789";
	JTextField T;
	public OuvinteEmail(JTextField texto) {
		T = texto;
	}
	public void keyTyped(KeyEvent e) {
		char C = e.getKeyChar();
		if(!Character.isLetter(C) && C != '_' && C != '@' && C != '.' && !caractere.contains(C+"")){
			e.consume();
		}
	}
	public void keyPressed(KeyEvent e) {		
	}
	public void keyReleased(KeyEvent e) {		
	}

}
