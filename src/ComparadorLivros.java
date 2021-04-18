import java.util.Comparator;

public class ComparadorLivros implements Comparator<Livro>{

	public int compare(Livro o1, Livro o2) {
		return o1.getTitulo().compareToIgnoreCase(o2.getTitulo());
		
	}
}
