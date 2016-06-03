/**
 * Created by Ernesto on 5/29/2016.
 */

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Gestor
{


    public void startConection()
    {

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:h2:~/test");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void testConection()
    {
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Pracica2", "sa", "");
        Connection conn = null;
        try {
            conn = cp.getConnection();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Failed");
        }
         cp.dispose();
    }
    public void create() {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Pracica2", "sa", "");
        try {
            conn = cp.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS ESTUDIANTES(matricula varchar(8) NOT NULL," +
                    "nombre varchar(100) NOT NULL," +
                    "apellidos varchar(100) NOT NULL," +
                    "telefono varchar(50) NOT NULL," +
                    "PRIMARY KEY (matricula))";
            stmt.executeUpdate(sql);
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void insertarEstudiante(Estudiante est) {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Pracica2", "sa", "");
        try {
            conn = cp.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO ESTUDIANTES (matricula,nombre," +
                    "apellidos,telefono)VALUES (?,?,?,?)";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);


            String id  = est.getMatricula();
            String first = est.getNombre();
            String last = est.getApellidos();
            String phone  = est.getTelefono();

            if(id ==null || first ==null || last ==null || phone==null)
                return;

            prepareStatement.setString(1,id );
            prepareStatement.setString(2, first);
            prepareStatement.setString(3, last);
            prepareStatement.setString(4, phone);

            prepareStatement.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public HashMap obtener_datos(String est)
    {
        ArrayList<Estudiante> lista = getAllStudents();
        HashMap<String,String> result = new HashMap<>();
        for(Estudiante e : lista)
        {
            if(e.getMatricula().equals(est))
            {
                result.put("matricula",e.getMatricula());
                result.put("nombre",e.getNombre());
                result.put("apellidos",e.getApellidos());
                result.put("telefono",e.getTelefono());
                break;
            }
        }
        return result;
    }

    public void eliminarEstudiante(String mat) {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Pracica2", "sa", "");
        try {
            conn = cp.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "DELETE FROM ESTUDIANTES WHERE MATRICULA = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);

            prepareStatement.setString(1, mat);
            prepareStatement.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public boolean exists(String matricula)
    {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Pracica2", "sa", "");
        boolean b= false;
        try {
            conn = cp.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM ESTUDIANTES WHERE matricula = '"+matricula +"'";
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next())
            {
                b = true;
            }
            else b = false;
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return b;

    }

    public ArrayList<Estudiante> getAllStudents()
    {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Pracica2", "sa", "");
        try {
            conn = cp.getConnection();
            Statement stmt = conn.createStatement();


            String sql = "SELECT * FROM ESTUDIANTES";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){

                String id  = rs.getString("Matricula");
                String first = rs.getString("Nombre");
                String last = rs.getString("Apellidos");
                String phone  = rs.getString("Telefono");

                estudiantes.add(new Estudiante(id,first,last,phone));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return estudiantes;
    }

}
