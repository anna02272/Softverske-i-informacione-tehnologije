package vezba2.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vezba2.entities.Person;

@SuppressWarnings("serial")
@WebServlet("/ViewPersonServlet")
public class ViewPersonServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();

		List<Person> persons = (List<Person>) context.getAttribute("persons");
		if (persons == null) {
			persons = new ArrayList<>();
			context.setAttribute("persons", persons);
		}

		int index = Integer.parseInt(request.getParameter("index"));
		Person person = persons.get(index);

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.append(
			"<!DOCTYPE html>\n" + 
			"<html>\n" + 
				"<head>\n" + 
					"<meta charset=\"UTF-8\">\n" + 
					"<title>Osoba</title>\n" + 
				"</head>\n" + 
				"<body>\n" + 
					"Ime:&nbsp;" + person.getFirstName() + "<br/>\n" + 
					"Prezime:&nbsp;" + person.getLastName() + "<br/>\n" + 
					"<br/>" + 
					"<a href=\"AllPersonsServlet\">Osobe</a>" + 
				"</body>\n" + 
			"</html>"
		);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
