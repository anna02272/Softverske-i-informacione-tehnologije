package rs.ac.uns.ftn.informatika.ues.webapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;

import rs.ac.uns.ftn.informatika.ues.lucene.model.RequiredHighlight;
import rs.ac.uns.ftn.informatika.ues.lucene.model.SearchType;
import rs.ac.uns.ftn.informatika.ues.lucene.search.QueryBuilder;
import rs.ac.uns.ftn.informatika.ues.lucene.search.ResultRetriever;

@SuppressWarnings("serial")
public class Search extends Mock {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("text");
		String textst = request.getParameter("textst");
		SearchType.Type textSearchType = SearchType.getType(textst);
		String textsc = request.getParameter("textsc");
		Occur textOccur = getOccur(textsc);
		
		String kw = request.getParameter("kw");
		String kwst = request.getParameter("kwst");
		SearchType.Type kwSearchType = SearchType.getType(kwst);
		String kwsc = request.getParameter("kwsc");
		Occur kwOccur = getOccur(kwsc);
		
		String title = request.getParameter("title");
		String titlest = request.getParameter("titlest");
		SearchType.Type titleSearchType = SearchType.getType(titlest);
		String titlesc = request.getParameter("titlesc");
		Occur titleOccur = getOccur(titlesc);
		
		try {
			BooleanQuery bquery = new BooleanQuery();
			List<RequiredHighlight> rhs = new ArrayList<RequiredHighlight>();
			
			if(!(text == null || text.equals(""))){
				Query query = QueryBuilder.buildQuery(textSearchType, "text", text);
				bquery.add(query, textOccur);
				RequiredHighlight rh = new RequiredHighlight("text", text);
				rhs.add(rh);
			}
			
			if(!(title == null || title.equals(""))){
				Query query = QueryBuilder.buildQuery(titleSearchType, "title", title);
				bquery.add(query, titleOccur);
				//RequiredHighlight rh = new RequiredHighlight("title", title);
				//rhs.add(rh);
			}
			
			if(!(kw == null || kw.equals(""))){
				Query query = QueryBuilder.buildQuery(kwSearchType, "keyword", kw);
				bquery.add(query, kwOccur);
				//RequiredHighlight rh = new RequiredHighlight("keyword", kw);
				//rhs.add(rh);
			}
			
			request.getSession().setAttribute("data", ResultRetriever.getResults(bquery, rhs));
			response.sendRedirect("results.jsp");
		} catch (IllegalArgumentException e) {
			response.sendRedirect("search.jsp?QueryError");
		} catch (ParseException e) {
			response.sendRedirect("search.jsp?QueryError");
		}

	}
	
	private Occur getOccur(String value){
		if(value.equals("+")){
			return Occur.MUST;
		}else if(value.equals("-")){
			return Occur.MUST_NOT;
		}else{
			return Occur.SHOULD;
		}
	}

}
