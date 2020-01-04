package formularioServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class consultarServlet
 */
@WebServlet("/consultarServlet")
public class consultarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/proyecto";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "admin";
       

    /**
     * @see HttpServlet#HttpServlet()
     */
    public consultarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		PrintWriter salida= response.getWriter();
			
		salida.println("<h1 style='text-align:center'>Consulta  Datos</h1>");
		
		try{
		      // Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      // Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      // Execute a query
		      System.out.println("Creating statement...");
		      String sql = "Select nombre, apellido, edad, carrera, semestre from alumnos;";
		      stmt = conn.prepareStatement(sql);
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         
		         String first = rs.getString("nombre");
		         String last = rs.getString("apellido");
		         int age = rs.getInt("edad");
		         String career = rs.getString("carrera");
		         int sem = rs.getInt("semestre");

		         
		         salida.write("<div>"+"Nombre: "+first+"<BR> Apellido: "+last+"<br> Edad:"+age+
					     "<br> Carrera: "+career+"<br> Semestre:"+sem +"</div>");
		         salida.write("------------------------<br>");
		         
		         
		      }
		    
		      // Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
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
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
