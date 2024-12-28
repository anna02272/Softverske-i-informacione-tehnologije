package lucene.word;

import java.io.InputStream;

import org.apache.lucene.document.Document;

public abstract class WordHandler {
	public abstract Document getDocument(InputStream is);
}
