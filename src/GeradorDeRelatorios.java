

import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.text.DateFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorios {
	
	private Document doc = new Document(PageSize.A4,72,72,72,72);
	
	private Font f1 = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private Font f2 = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	
	private DateTimeFormatter modelo = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy");
	String dataDeHoje = modelo.format(LocalDate.now());
	
	public void relatorioVisualizacoes(ArrayList<Livro> lista) {
	
		try {
			
			PdfWriter.getInstance(doc, new FileOutputStream("Livros-Visualizacoes.pdf"));
			
			doc.open();
			
			doc.add(new Paragraph("Livraria Stile / " + dataDeHoje + "\n\n",f1));
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
	
	public void relatorioRequisitados(ArrayList<Livro> lista) {
		
		
		try {
			
			PdfWriter.getInstance(doc, new FileOutputStream("Livros-Requisitados.pdf"));
			
			doc.add(new Paragraph("Livraria Stile / " + dataDeHoje + "\n\n",f1));
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
	
	public void boletoDeCobranca(Livro liv) {
		
		
		try {
			
			PdfWriter.getInstance(doc, new FileOutputStream("Livros-BoletoCobranca.pdf"));
			
			doc.open();
			
			doc.add(new Paragraph("Livraria Stile / " + dataDeHoje + "\n\n",f1));
			doc.add(new Paragraph("BOLETO DE COBRANÇA\n\n",f1));
			
			doc.add(new Paragraph("produto: " + liv.getTitulo(),f1));
			doc.add(new Paragraph("\nID: " + liv.getId(),f2));
			doc.add(new Paragraph("\npreço: " + liv.getPreco(),f2));
			doc.add(new Paragraph("\n\nAgradecemos a preferência!",f1));
			
			doc.close();
			
		}catch(FileNotFoundException | DocumentException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao gerar relatório de Boleto de Cobrança", "Gerar Relatório", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}

