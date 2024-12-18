package vezba2.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vezba2.entities.Person;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		
		ServletContext context = getServletContext();

		List<Person> persons = (List<Person>) context.getAttribute("persons");
		if (persons == null) {
			persons = new ArrayList<>();
			context.setAttribute("persons", persons);
		}
		
		// ako postoji lista proveriti da li se korisnik nalazi u listi
		boolean found = false;
		for (Person person : persons) {
			if (person.getUsername().equals(username) && person.getPassword().equals(password)) {
				found = true;
				break;
			}
		}
		
		// ako postoji redirect na prikaz svih, ako ga ne nadjemo u listi vracamo korisnika ponovo na unos
		if (found) {
			response.sendRedirect("./AllPersonsServlet");
		} else {
			response.sendRedirect("./login.html");
		}
	}

}
