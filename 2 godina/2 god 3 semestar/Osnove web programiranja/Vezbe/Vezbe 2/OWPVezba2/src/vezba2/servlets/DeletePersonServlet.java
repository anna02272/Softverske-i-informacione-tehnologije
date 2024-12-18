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

@SuppressWarnings("serial")
@WebServlet("/DeletePersonServlet")
public class DeletePersonServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();

		List<Person> persons = (List<Person>) context.getAttribute("persons");
		if (persons == null) {
			persons = new ArrayList<>();
			context.setAttribute("persons", persons);
		}

		int index = Integer.parseInt(request.getParameter("index"));
		persons.remove(index);

		response.sendRedirect("./AllPersonsServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
