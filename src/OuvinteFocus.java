import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class OuvinteFocus implements FocusListener{
	JTextField texto;
	String nome;
	public OuvinteFocus(JTextField T, String S) {
		texto = T;
		nome = S;
	}
	public void focusGained(FocusEvent e) {
		if (texto.getText().equals(nome)){
			texto.setText("");
			texto.repaint();
		}
	}
	public void focusLost(FocusEvent e) {
		if (texto.getText().equals("")){
			texto.setText(nome);
			texto.repaint();
		}
	}
}
