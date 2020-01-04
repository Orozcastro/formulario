package formularioServlet;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class formServlet
 */
@WebServlet("/formServlet")
public class formServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/proyecto";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "admin";

    /**
     * Default constructor. 
     */
    public formServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		Statement stmt = null;
		
		PrintWriter salida= response.getWriter();
			
		salida.println("<html> <body>");		
		salida.println("<h1 style='text-align:center'>D A T O S </h1>");
		

		   try{
		      // Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      if(conn!= null) {
		    	    String nombre = request.getParameter("nombre");
					String apellido = request.getParameter("apellido");
					int edad = Integer.parseInt(request.getParameter("edad"));
					String carrera = request.getParameter("carrera");
					int semestre = Integer.parseInt(request.getParameter("semestre"));
					
					// Execute a query
				      System.out.println("Creating statement...");				      
				      String sql;			      
				      sql = "insert into alumnos(nombre, apellido, edad, carrera, semestre)"
						      		+ " values (?, ?, ?, ?,? );";
				      
				      // create the mysql insert preparedstatement
				      PreparedStatement preparedStmt = conn.prepareStatement(sql);
				      preparedStmt.setString (1, nombre);
				      preparedStmt.setString (2, apellido);
				      preparedStmt.setInt   (3, edad);
				      preparedStmt.setString (4, carrera);
				      preparedStmt.setInt    (5, semestre);

				      // execute the preparedstatement
				      preparedStmt.execute();			      
				      conn.close();
				      
				      
				      
				     salida.write("<div>"+"Nombre: "+nombre+"<BR> Apellido: "+apellido+"<br> Edad:"+edad+
							     "<br> Carrera: "+carrera+"<br> Semestre:"+semestre +"</div>");
				    
		      }else 
		    	 System.out.print("<script> ('No hay conexion'); </script>");
		      
		      
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");



		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		response.setContentType("text/html");
		
        PrintWriter salida= response.getWriter();
			
		salida.println("<html> <body>");		
		salida.println("<h1 style='text-align:center'> prueba servlet</h1>");
		
		try {
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String edad = request.getParameter("edad");
			String carrera = request.getParameter("carrera");
			String semestre = request.getParameter("semestre");
			salida.write("<div>"+"Nombre: "+nombre+"<BR> Apellido: "+apellido+"<br> Edad:"+edad+
					     "<br> Carrera: "+carrera+"<br> Semestre:"+semestre +"</div>");
		} catch(NumberFormatException nfe) {
			salida.write("Specific Error To Your Transaction: " + nfe.toString());
		}
	
		salida.println("</body> </html> ");
	}

}
