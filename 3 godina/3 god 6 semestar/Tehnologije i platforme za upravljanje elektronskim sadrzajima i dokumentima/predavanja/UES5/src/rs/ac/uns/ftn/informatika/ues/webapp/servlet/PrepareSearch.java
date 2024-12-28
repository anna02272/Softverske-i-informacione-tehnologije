package rs.ac.uns.ftn.informatika.ues.webapp.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.search.BooleanClause.Occur;

import rs.ac.uns.ftn.informatika.ues.lucene.indexing.Indexer;
import rs.ac.uns.ftn.informatika.ues.lucene.model.SearchType;

@SuppressWarnings("serial")
public class PrepareSearch extends Mock {
	
	@Override
	public void init() throws ServletException{
		super.init();
		Indexer.getInstance().index(new File(ResourceBundle.getBundle("app").getString("storage")));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> occures = new ArrayList<String>();
		for(Occur o : Occur.values()){
			occures.add(o.toString());
		}
		
		request.getSession().setAttribute("occures", occures);
		request.getSession().setAttribute("searchTypes", SearchType.getMessages());
		
		response.sendRedirect("search.jsp");
	}

}
