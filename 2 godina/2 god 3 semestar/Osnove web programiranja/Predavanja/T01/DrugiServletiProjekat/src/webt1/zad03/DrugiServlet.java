package webt1.zad03;

import java.io.IOException;
import java.util.Iterator;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/DrugiServlet" })
public class DrugiServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> lista = (ArrayList<String>)getServletContext().getAttribute("nekaLista");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("	<h1>Servlet u kome je prikazana lista iz ServletContext</h1>");
        pw.println("	<ul>");
        for (String string : lista) {
            pw.println(
            	   "		<li>" + string + "</li>");
        }
        pw.println("	</ul>");
        pw.println("	<a href=\"pocetna.html\">povratak</a>");
        pw.println("</body>");
        pw.println("</html>");
        pw.flush();
        pw.close();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}