import javax.swing.JButton;

public class ColecaoUsuario extends PadraoListarLivros{

	public ColecaoUsuario(Usuario user) {
		super(user,false,Imagens.ICONE_COLECAO,"Minha Coleção");
		super.addIconeUsuario();
		super.addNomeIconeUsuario();
		
		addBotaoNota();
		
		add(new BotaoHomePage(125, 10));
		
		repaint();
	}

	private void addBotaoNota() {
		
		JButton botaoNota = new JButton("Atribuir Nota");
		botaoNota.setBounds(480, 494, 120, 25);
		
		add(botaoNota);
	}
		
}
