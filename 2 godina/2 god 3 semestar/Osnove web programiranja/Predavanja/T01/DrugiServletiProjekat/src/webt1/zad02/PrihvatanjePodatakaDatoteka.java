package webt1.zad02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/PrihvatanjePodatakaDatoteka" })
@MultipartConfig
public class PrihvatanjePodatakaDatoteka extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String HTTPBodyString ="";
		String datotekaNaziv = "";
		String datotekaSadrzaj = "";
		
		//ako se pozove pribavljanje HTTP tela zahteva kao string kasnije nece prikazati podatke
//		HTTPBodyString = pribaviHTTPBodyString(request);
		
        Part datotekaPart = request.getPart("datoteka");
        if(datotekaPart!=null) {
        	datotekaNaziv = Paths.get(datotekaPart.getSubmittedFileName()).getFileName().toString();
            InputStream fileContent = datotekaPart.getInputStream();
            Scanner scnr = new Scanner(fileContent,"UTF-8");
            StringBuilder sb = new StringBuilder();
            while(scnr.hasNextLine()){
                String line = scnr.nextLine();
                sb.append(line+"<br/>");
            }
            datotekaSadrzaj = sb.toString();
        }

        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<body>");
        pw.println("	<h1>Prihvatanje podataka</h1>");
        pw.println("	<p>Izgled tela HTTP zahteva:<br/>" + HTTPBodyString+"</p>");
        pw.println("	<p>Klijent koji je pozvao ovaj servlet je: " + request.getHeader("User-Agent")+"</p>");
        pw.println("	<p>Poslati sadržaj je: " + request.getHeader("Content-Type")+"</p>");
        pw.println("	<p>Poslali ste:" + request.getParameter("ime") + " " + request.getParameter("prezime") + "</p>");
        pw.println("	<p>Poslali ste datoteku:" + datotekaNaziv + "</p>");
        pw.println("	<p>Poslali ste datoteku čiji je sadržaj:<br/>" + datotekaSadrzaj + "</p>");
        pw.println("	<a href=\"pocetna.html\">povratak</a>");
        pw.println("</body>");
        pw.println("</html>");
        pw.flush();
        pw.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	private String HTTPBodyString(HttpServletRequest request) throws IOException {

		ServletInputStream in = request.getInputStream();
		Scanner scnr = new Scanner(in, "UTF-8");
		StringBuilder sb = new StringBuilder();
		while (scnr.hasNextLine()) {
          String line = scnr.nextLine();
          sb.append(line+"<br/>");
		}
		return sb.toString();
	}
}