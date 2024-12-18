package webt1.zad02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/PrihvatanjePodataka" })
public class PrihvatanjePodataka extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
    	//ako se pozove pribavljanje HTTP stringa kasnije nece prikazati podatke
//    	ispisiZahtev(request);
        
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<body>");
        pw.println("	<h1>Prihvatanje podataka</h1>");
        pw.println("	<p>Klijent koji je pozvao ovaj servlet je: " + request.getHeader("User-Agent")+"</p>");
        pw.println("	<p>Poslati sadržaj je: " + request.getHeader("Content-Type")+"</p>");
        pw.println("	<p>Poslali ste:" + request.getParameter("ime") + " " + request.getParameter("prezime") + "</p>");
        pw.println("	<p>Poslali ste datoteku:" + request.getParameter("datoteka") + "</p>");
        pw.println("	<a href=\"pocetna.html\">povratak</a>");
        pw.println("</body>");
        pw.println("</html>");
        pw.flush();
        pw.close();
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    private void ispisiZahtev(HttpServletRequest request) throws IOException {
    	
    	String line = null;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }
    }
}