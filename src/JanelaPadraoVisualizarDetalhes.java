import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class JanelaPadraoVisualizarDetalhes extends JFrame{
	ImageIcon img;
	JLabel titulo;
	JTable tabela;
	JScrollPane rolo;
	JButton botao;
	JTextArea resumo;
	JMenu menu;
	JMenuBar barra;
	JMenuItem itens;
	DefaultTableModel modelo;
	public JanelaPadraoVisualizarDetalhes() { 
		setSize(700, 610);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
	}
}
