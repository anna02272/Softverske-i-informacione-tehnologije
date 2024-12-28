package lucene.word;

import java.io.InputStream;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.poi.POIXMLProperties;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Word2007Handler extends WordHandler{

	public Document getDocument(InputStream is) {
		Document doc=new Document();
		String bodyText=null;
		try{
			XWPFDocument wordDoc=new XWPFDocument(is);
			
			XWPFWordExtractor we=new XWPFWordExtractor(wordDoc);
			bodyText=we.getText();
			if(bodyText != null && bodyText.trim().length()>0){
				doc.add(new TextField("sadrzaj",bodyText, Store.NO));
			}
			
			POIXMLProperties props=wordDoc.getProperties();
			
			String title = props.getCoreProperties().getTitle();
	        if (title != null)
	            doc.add(new TextField("naslov", title, Store.YES));
	        String keywords = props.getCoreProperties().getUnderlyingProperties().getKeywordsProperty().getValue();
	        if(keywords!=null)
	        	doc.add(new TextField("kljucneReci", keywords, Store.NO));
	        String author = props.getCoreProperties().getCreator();
	        if(author!=null)
	        	doc.add(new TextField("autor", author, Store.YES));
			
	        
		}catch(Exception e){
			System.out.println("Problem pri parsiranju docx fajla");
			e.printStackTrace();
		}
		
		return doc;
	}
	
}
