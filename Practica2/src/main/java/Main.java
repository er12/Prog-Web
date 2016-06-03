/**
 * Created by Ernesto on 5/28/2016.
 */


import java.util.*;

import spark.ModelAndView;

import static spark.Spark.*;

public class Main {

    public static void main(String args[]) {

        Gestor basedato= new Gestor();

        basedato.startConection();
        basedato.testConection();
        basedato.create();



        staticFileLocation("/styleSheets");


        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            List<Estudiante> dummy = basedato.getAllStudents();
            attributes.put("estudiantes",dummy);
            return new ModelAndView(attributes, "hello.ftl");
        }, new FreeMarkerEngine());

        post("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String mat = request.queryParams("matricula");
            String nom = request.queryParams("nombre");
            String apl = request.queryParams("apellidos");
            String tel = request.queryParams("telefono");
            //System.out.println("Yes "+ mat+ " "+nom +" "+apl + " "+ tel);

                Estudiante e = new Estudiante(mat,nom,apl,tel);
                basedato.insertarEstudiante(e);
                attributes.put("estudiantes", basedato.getAllStudents());


            return new ModelAndView(attributes, "hello.ftl");
         }, new FreeMarkerEngine());




   get("/editarX", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            basedato.eliminarEstudiante(request.queryParams("MatriculaEd"));

           String mat = ((request.queryParams("matricula").equals("")? request.queryParams("MatriculaEd") : request.queryParams("matricula")));
            String nom = ((request.queryParams("nombre").equals("")? request.queryParams("NombreEd") : request.queryParams("nombre")));
            String apl = ((request.queryParams("apellidos").equals("")? request.queryParams("ApellidosEd") : request.queryParams("apellidos")));
            String tel = ((request.queryParams("telefono").equals("")? request.queryParams("TelefonoEd") : request.queryParams("telefono")));

       basedato.insertarEstudiante(new Estudiante(mat,nom,apl,tel));

       attributes.put("message","Estudiante editado.");

       return new ModelAndView(attributes, "editX.ftl");

   }, new FreeMarkerEngine());



        post("/update", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();

            if(request.queryParams("editar")!=null)
            {
                String matricula = request.queryParams("matricula");


                if(basedato.exists(matricula))
                {
                    HashMap<String,String > dummy = basedato.obtener_datos(matricula);
                    attributes.put("MatriculaEd",matricula);
                    attributes.put("NombreEd",dummy.get("nombre"));
                    attributes.put("ApellidosEd",dummy.get("apellidos"));
                    attributes.put("TelefonoEd",dummy.get("telefono"));

                    request.attribute("MatriculaEd",matricula);
                    request.attribute("NombreEd",dummy.get("nombre"));
                    request.attribute("ApellidosEd",dummy.get("apellidos"));
                    request.attribute("TelefonoEd",dummy.get("telefono"));

                    return new ModelAndView(attributes, "editStu.ftl");
                }

                attributes.put("message","La matrícula no existe.");
                return new ModelAndView(attributes, "deleted.ftl");



            }
            attributes.put("message","Estudiante eliminado");
                basedato.eliminarEstudiante(request.queryParams("matricula"));
                return new ModelAndView(attributes, "deleted.ftl");


        }, new FreeMarkerEngine());


        get("/editar", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();

            List<Estudiante> dummy = basedato.getAllStudents();

            attributes.put("estudiantes",dummy);

            return new ModelAndView(attributes, "edit.ftl");
        }, new FreeMarkerEngine());


        get("/crear", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "create.ftl");
        }, new FreeMarkerEngine());

    }


}
