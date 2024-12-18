package vezba1.zadatak6;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vezba1.entities.Manifestation;
import vezba1.entities.Person;

@SuppressWarnings("serial")
@WebServlet({ "/KreiranjeManifestacije2" })
public class Manifestation2Servlet extends HttpServlet {
	
	private List<Manifestation> manifestations = new ArrayList<Manifestation>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String place = request.getParameter("place");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		double ticketPrice = Double.parseDouble(request.getParameter("price"));
		
		Manifestation manifestation = new Manifestation(name, place, capacity, ticketPrice);
		manifestations.add(manifestation);

		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.append(
			"<!DOCTYPE html>\n" + 
			"<html>\n" + 
				"<head>\n" + 
					"<meta charset=\"UTF-8\">\n" + 
					"<title>Osoba</title>\n" + 
				"</head>\n" + 
				"<body>\n"
		);
		for (Manifestation itManifestation: manifestations)
			out.append(
					"Naziv:&nbsp;" + itManifestation.getName() + "<br/>\n" + 
					"Mesto održavanja:&nbsp;" + itManifestation.getPlace() + "<br/>\n" + 
					"Broj mesta:&nbsp;" + itManifestation.getCapacity() + "<br/>\n" + 
					"Cena karte:&nbsp;" + itManifestation.getTicketPrice() + "<br/>\n" + 
					"<br/>"
			);
		out.append(
					"<a href=\"unos-manifestacije2.html\">Unos</a>" + 
				"</body>\n" + 
			"</html>"
		);
	}

}
