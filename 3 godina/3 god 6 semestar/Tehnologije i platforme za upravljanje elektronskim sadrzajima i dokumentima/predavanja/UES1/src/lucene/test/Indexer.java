package lucene.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {
	public static void main(String[] args) throws Exception {
		Directory indexDir;
		File dataDir;
		if (args.length != 2) {
			try{
				ResourceBundle rb=ResourceBundle.getBundle("lucene.test.luceneindex");
				indexDir=new SimpleFSDirectory(new File(rb.getString("indexDir")));
				dataDir=new File(rb.getString("dataDir"));
			}catch(Exception e1){
				for(String arg :args)
					System.out.println(arg);
				throw new Exception("Usage: java " + Indexer.class.getName()+ " <index dir> <data dir> or properties file needed");
			}
		}else{
			indexDir = new SimpleFSDirectory(new File(args[0]));
			dataDir = new File(args[1]);
		}
		
		long start = new Date().getTime();
		int numIndexed = index(indexDir, dataDir);
		long end = new Date().getTime();
		System.out.println("Indexing " + numIndexed + " files took "
		+ (end - start) + " milliseconds");
	}
	
	// open an index and start file directory traversal
	public static int index(Directory indexDir, File dataDir) throws IOException {
		if (!dataDir.exists() || !dataDir.isDirectory()) {
			throw new IOException(dataDir + " does not exist or is not a directory");
		}
		//IndexWriter writer = new IndexWriter(indexDir,new StandardAnalyzer(), true); lucene < 2.4
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, new StandardAnalyzer(Version.LUCENE_40));
		iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
		IndexWriter writer = new IndexWriter(indexDir, iwc);
		indexDirectory(writer, dataDir);
		//int numIndexed = writer.docCount(); lucene < 2.4
		int numIndexed = writer.numDocs();
		writer.close();
		return numIndexed;
	}
	// recursive method that calls itself when it finds a directory
	private static void indexDirectory(IndexWriter writer, File dir) throws IOException {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				indexDirectory(writer, f);
			} else if (f.getName().endsWith(".txt")) {
				indexFile(writer, f);
			}
		}
	}
	
	// method to actually index a file using Lucene
	private static void indexFile(IndexWriter writer, File f)throws IOException {
		if (f.isHidden() || !f.exists() || !f.canRead()) {
			return;
		}
		System.out.println("Indexing " + f.getCanonicalPath());
			
		
		//Kreirati Lucene Document za ovaj fajl
		
		//U novokreirani dokument dodati polja naslov, sadrzaj_fajla, naziv_fajla
		//polje naslov je prosto prvi red ucitan iz txt fajla - osim sto se analizira i snima se
		//polje sadrzaj fajla sadrzi sav ostali tekst iz dokumenta - kreirati ga koristeci BufferedReader
		//polje naziv_fajla treba da sadrzi naziv fajla koji je indeksiran
		//dodati kreirani Lucene dokument u index
	}
}