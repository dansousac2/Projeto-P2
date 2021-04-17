import javax.swing.JButton;

public class BotaoHomePage extends JButton{
	// é isso ai.
	public BotaoHomePage(int posiX,int posiY) {
		
		this.setIcon(Imagens.ICONE_HOMEPAGE);
		this.setBounds(posiX, posiY, 45, 45);
	}
}
