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

@WebServlet("/AddPersonServlet")
public class AddPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		ServletContext context = getServletContext();

		List<Person> persons = (List<Person>) context.getAttribute("persons");
		if (persons == null) {
			persons = new ArrayList<>();
			context.setAttribute("persons", persons);
		}
		
		Person newPerson = new Person(firstName, lastName);
		persons.add(newPerson);

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
					"Dodavanje uspešno!<br/>\n" + 
					"<br/>\n" + 
					"<a href=\"AllPersonsServlet\">Osobe</a>\n" + 
				"</body>\n" + 
			"</html>"
		);

		response.sendRedirect("./AllPersonsServlet");
	}

}
