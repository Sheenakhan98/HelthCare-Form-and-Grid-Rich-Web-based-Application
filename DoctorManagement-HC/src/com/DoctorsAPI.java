package com;
import com.Doctor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


@WebServlet("/DoctorsAPI")
public class DoctorsAPI extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	Doctor doc = new Doctor();
       
    public DoctorsAPI() {
        super();
   
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String output = doc.insertDoctor(request.getParameter("FirstName"),
				request.getParameter("LastName"),
				request.getParameter("Specialization"),
				request.getParameter("Gender"),
				request.getParameter("Email"),
				request.getParameter("ContactNo"),
				request.getParameter("StandardFees"),
				request.getParameter("Password"));
				response.getWriter().write(output);
				
	
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);

		String output = doc.updateDoctor(paras.get("hidDocIDSave").toString(),
						paras.get("FirstName").toString(),
						paras.get("LastName").toString(),
						paras.get("Specialization").toString(),
						paras.get("Gender").toString(),
						paras.get("Email").toString(),
						paras.get("ContactNo").toString(),
						paras.get("StandardFees").toString(),
						paras.get("Password").toString());
		response.getWriter().write(output);
	
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = doc.deleteDoctor(paras.get("docId").toString());
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
			private static Map getParasMap(HttpServletRequest request)
			{
				Map<String, String> map = new HashMap<String, String>();
				try
				{
					Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
					String queryString = scanner.hasNext() ?
										scanner.useDelimiter("\\A").next() : "";
					scanner.close();
					String[] params = queryString.split("&");
					for (String param : params)
					{
						String[] p = param.split("=");
						map.put(p[0], p[1]);
					}
				}
				catch (Exception e)
				{
				}
				return map;
			}
	
	

}
