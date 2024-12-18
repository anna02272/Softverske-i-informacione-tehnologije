package webt1.zad03;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/PrviServlet" })
public class PrviServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ArrayList<String> lista = new ArrayList<String>(Arrays.asList("pera", "mika", "\u017eika"));
        getServletContext().setAttribute("nekaLista", (Object)lista);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("	<h1>Servlet u kome je lista imena uba\u010dena u ServletContext</h1>");
        pw.println("	<a href=\"DrugiServlet\">prikaz podataka</a><br/>");
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
