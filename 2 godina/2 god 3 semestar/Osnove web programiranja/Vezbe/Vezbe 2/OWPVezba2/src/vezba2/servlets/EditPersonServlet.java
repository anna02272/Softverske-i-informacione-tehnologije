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
@WebServlet("/EditPersonServlet")
public class EditPersonServlet extends HttpServlet {

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
			"	<meta charset=\"UTF-8\">\n" + 
			"	<title>Osoba</title>\n" + 
			"</head>\n" + 
			"<body>\n" + 
			"	<form action=\"EditPersonServlet\" method=\"post\">\n" + 
			"		Ime:&nbsp;<input type=\"text\" name=\"firstName\" value=\"" + person.getFirstName() + "\"/><br/>\n" + 
			"		Prezime:&nbsp;<input type=\"text\" name=\"lastName\" value=\"" + person.getLastName() + "\"/><br/>\n" +
			"		<input type=\"hidden\" name=\"index\" value=\"" + index + "\">\n" + 
			"		<input type=\"submit\" value=\"Posalji\">\n" + 
			"	</form>\n" + 
			"</body>\n" + 
			"</html>"
		);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();

		List<Person> persons = (List<Person>) context.getAttribute("persons");
		if (persons == null) {
			persons = new ArrayList<>();
			context.setAttribute("persons", persons);
		}

		int index = Integer.parseInt(request.getParameter("index"));
		Person person = persons.get(index);

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		person.setFirstName(firstName);
		person.setLastName(lastName);

		response.sendRedirect("./AllPersonsServlet");
	}

}
