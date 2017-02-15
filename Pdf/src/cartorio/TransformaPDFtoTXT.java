package cartorio;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class TransformaPDFtoTXT {

	public ArrayList<String> tranforma(String arquivoPdf) throws IOException {

		ArrayList<String> retorno = new ArrayList<>();

		PdfReader reader = new PdfReader(arquivoPdf);
		int pages = reader.getNumberOfPages();
		String texto = "";
		for (int i = 1; i <= pages; i++) {
			texto += PdfTextExtractor.getTextFromPage(reader, i);
		}

		String[] linhas = texto.split("\n");
		for (String lin : linhas) {

			retorno.add(lin);
		}

		return retorno;
	}

}
