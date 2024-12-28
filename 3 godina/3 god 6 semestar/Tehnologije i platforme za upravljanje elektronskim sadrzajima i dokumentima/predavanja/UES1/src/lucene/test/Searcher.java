package lucene.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class Searcher {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		File indexDir;
		String q;
		if (args.length != 2) {
			try{
				ResourceBundle rb=ResourceBundle.getBundle("lucene.test.luceneindex");
				indexDir=new File(rb.getString("indexDir"));
				System.out.println("unesite izraz za pretragu:");
				q=in.readLine();
			}catch(Exception e1){
				for(String arg :args)
					System.out.println(arg);
				throw new Exception("Usage: java " + Indexer.class.getName()+ " <index dir> <data dir> or properties file needed");
			}
		}else{
			indexDir = new File(args[0]);
		 	q = args[1];
		}
		if (!indexDir.exists() || !indexDir.isDirectory()) {
			throw new Exception(indexDir +" does not exist or is not a directory.");
		}
		search(indexDir, q);
	}
	
	public static void search(File indexDir, String q)throws Exception {
		
		Directory fsDir=new SimpleFSDirectory(indexDir);
		DirectoryReader ireader = DirectoryReader.open(fsDir);
		IndexSearcher is = new IndexSearcher(ireader);
		QueryParser qp=new QueryParser(Version.LUCENE_40,"sadrzaj_fajla", new StandardAnalyzer(Version.LUCENE_40));
		Query query=qp.parse(q);
		//System.out.println(query);
		long start = new Date().getTime();
		TopScoreDocCollector collector=TopScoreDocCollector.create(10,true);
		is.search(query, collector);
		long end = new Date().getTime();
		System.out.println("Found " + collector.getTotalHits() + " document(s) (in " + (end - start) +	" milliseconds) that matched query '" + q + "':");
		ScoreDoc[] hits=collector.topDocs().scoreDocs;
		for (int i = 0; i < collector.getTotalHits(); i++) {
			int docId=hits[i].doc;
			Document doc = is.doc(docId);
			System.out.println(doc.get("filename"));
		}
	}
}

