package vezba1.zadatak5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet({ "/KreiranjeManifestacije" })
public class ManifestationServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String place = request.getParameter("place");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		double ticketPrice = Double.parseDouble(request.getParameter("price"));

		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.append(
			"<!DOCTYPE html>\n" + 
			"<html>\n" + 
				"<head>\n" + 
					"<meta charset=\"UTF-8\">\n" + 
					"<title>Manifestacija</title>\n" + 
				"</head>\n" + 
				"<body>\n" + 
					"Naziv:&nbsp;" + name + "<br/>\n" + 
					"Mesto održavanja:&nbsp;" + place + "<br/>\n" + 
					"Broj mesta:&nbsp;" + capacity + "<br/>\n" + 
					"Cena karte:&nbsp;" + ticketPrice + "\n" + 
				"</body>\n" + 
			"</html>"
		);
	}

}
