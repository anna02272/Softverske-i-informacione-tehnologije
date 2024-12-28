package lucene.word;

import java.io.InputStream;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class WordPOIHandler extends WordHandler{

	public Document getDocument(InputStream is) {
		Document doc=new Document();
		String bodyText=null;
		try{
			//pomocu WordExtractor objekta izvuci metapodatke navedene ispod i kreirati field-ove sa onim nazivima koji su navedeni u Word2007Handler-u
			
			//pomocu SummaryInformation objekta izvuci metapodatke navedene ispod i kreirati field-ove sa onim nazivima koji su navedeni u Word2007Handler-u
			String title = null;
	        String keywords = null;
	        String author = null;
	        
			
		}catch(Exception e){
			System.out.println("Problem pri parsiranju doc fajla");
			e.printStackTrace();
		}
		
		return doc;
	}
	
}
