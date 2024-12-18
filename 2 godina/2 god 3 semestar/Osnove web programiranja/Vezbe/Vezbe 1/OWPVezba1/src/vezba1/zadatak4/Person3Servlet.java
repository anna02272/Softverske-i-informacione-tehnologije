package vezba1.zadatak4;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vezba1.entities.Person;

@WebServlet("/PrijavaKorisnika3")
public class Person3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private List<Person> persons = new ArrayList<>();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		Person newPerson = new Person(firstName, lastName, new Date());
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
				"<body>\n"
		);
		for (Person itPerson: persons)
			out.append(
					"Ime:&nbsp;" + itPerson.getFirstName() + "<br/>\n" + 
					"Prezime:&nbsp;" + itPerson.getLastName() + "<br/>\n" + 
					"Datum prijave:&nbsp;" + sdf.format(itPerson.getDate()) + "<br/>\n" + 
					"<br/>"
			);
		out.append(
					"<a href=\"person-zadatak4.html\">Unos</a>" + 
				"</body>\n" + 
			"</html>"
		);
	}

}
