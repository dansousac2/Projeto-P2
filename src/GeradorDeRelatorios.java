

import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorios {
	
	public static void relatorioVisualizacoes(ArrayList<Livro> lista) {
		
		Document doc = new Document(PageSize.A4,72,72,72,72);
		
		try {
			
			PdfWriter.getInstance(doc, new FileOutputStream("Livros-Visualizacoes.pdf"));
			
			doc.open();
			
			Font f1 = new Font(FontFamily.COURIER, 14, Font.BOLD);
			Font f2 = new Font(FontFamily.COURIER, 12, Font.NORMAL);
			
			doc.add(new Paragraph("Livraria Stile / " + LocalDate.now() + "\n\n",f1));
			doc.add(new Paragraph("Visualizações de Livros\n\n",f2));
			
			for (Livro elemento : lista) {
				
				Paragraph p1 = new Paragraph(elemento.getTitulo() + " / Visualizações: " + elemento.getVisualizacoes(), f2);
			
				doc.add(p1);
			}
			
			doc.close();
			
		}catch(FileNotFoundException | DocumentException e) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar relatório de Visualizações", "Gerar Relatório", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public static void relatorioRequisitados(ArrayList<Livro> lista) {
		
		Document doc = new Document(PageSize.A4,72,72,72,72);
		
		try {
			
			PdfWriter.getInstance(doc, new FileOutputStream("Livros-Requisitados.pdf"));
			
			doc.open();
			
			Font f1 = new Font(FontFamily.COURIER, 14, Font.BOLD);
			Font f2 = new Font(FontFamily.COURIER, 12, Font.NORMAL);
			
			doc.add(new Paragraph("Livraria Stile / " + LocalDate.now() + "\n\n",f1));
			doc.add(new Paragraph("10 Livros mais Requisitados: \n\n"));
			
			for (Livro elemento : lista) {
				
				Paragraph p1 = new Paragraph(elemento.getTitulo() + " / Quantidade de interessados: " + elemento.getInteressados(), f2);
				
				doc.add(p1);
			}
			
			doc.close();
			
		}catch(FileNotFoundException | DocumentException e) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar relatório de Visualizações", "Gerar Relatório", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}

