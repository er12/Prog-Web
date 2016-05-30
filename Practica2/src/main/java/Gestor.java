/**
 * Created by Ernesto on 5/29/2016.
 */

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.*;
import java.util.ArrayList;

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

            prepareStatement.setString(1, est.getMatricula());
            prepareStatement.setString(2, est.getNombre());
            prepareStatement.setString(3, est.getApellidos());
            prepareStatement.setString(4, est.getTelefono());

            prepareStatement.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


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
