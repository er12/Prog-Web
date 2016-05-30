/**
 * Created by Ernesto on 5/28/2016.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            List<Estudiante> estudiantes = new ArrayList<Estudiante>();estudiantes.add(new Estudiante("20120201", "Ernesto","Rodríguez","809-471-3978"));
            List<Estudiante> dummy = basedato.getAllStudents();


            if(dummy.size()==0||dummy==null)
            attributes.put("estudiantes",estudiantes);
            else attributes.put("estudiantes",dummy);


            return new ModelAndView(attributes, "hello.ftl");
        }, new FreeMarkerEngine());


        post("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String mat = request.params("matricula");
            String nom = request.params("nombre");
            String apl = request.params("apellidos");
            String tel = request.params("telefono");
            System.out.println(mat +" "+nom+" "+apl+" "+tel);



            Estudiante e = new Estudiante(mat,nom,apl,tel);
            basedato.insertarEstudiante(e);
            attributes.put("estudiantes", basedato.getAllStudents());


            return new ModelAndView(attributes, "hello.ftl");
        }, new FreeMarkerEngine());





        get("/editar", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            List<Estudiante> estudiantes = new ArrayList<Estudiante>();estudiantes.add(new Estudiante("20120201", "Ernesto","Rodríguez","809-471-3978"));
            List<Estudiante> dummy = basedato.getAllStudents();


            if(dummy.size()==0||dummy==null)
                attributes.put("estudiantes",estudiantes);
            else attributes.put("estudiantes",dummy);

            return new ModelAndView(attributes, "edit.ftl");
        }, new FreeMarkerEngine());





        get("/crear", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();




            return new ModelAndView(attributes, "create.ftl");
        }, new FreeMarkerEngine());

    }


}
/*
import static spark.Spark.*;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args)
    {

        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        Configuration freeMarkerConfiguration = new Configuration();
        freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(BlogService.class, "/"));
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);

        get("/posts", (request, response) -> {
            if (shouldReturnHtml(request)) {
                response.status(200);
                response.type("text/html");
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("posts", model.getAllPosts());
                return freeMarkerEngine.render(new ModelAndView(attributes, "posts.ftl"));
            } else {
                response.status(200);
                response.type("application/json");
                return dataToJson(model.getAllPosts());
            }
        });
        Map attributes = new HashMap<>();
        attributes.put("posts", model.getAllPosts());

        return freeMarkerEngine.render(new ModelAndView(attributes, "posts.ftl"));
    }
}*/
/*---------------------------------------------------------------------------------------------------------*/
/*
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class Main {

  public static void main(String[] args) throws Exception {

    // 1. Configure FreeMarker
    //
    // You should do this ONLY ONCE, when your application starts,
    // then reuse the same Configuration object elsewhere.

    Configuration cfg = new Configuration();

    // Where do we load the templates from:
    cfg.setClassForTemplateLoading(Main.class, "templates");

    // Some other recommended settings:
    cfg.setIncompatibleImprovements(new Version(2, 3, 20));
    cfg.setDefaultEncoding("UTF-8");
    cfg.setLocale(Locale.US);
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    // 2. Proccess template(s)
    //
    // You will do this for several times in typical applications.

    // 2.1. Prepare the template input:

    Map<String, Object> input = new HashMap<String, Object>();

    input.put("title", "Vogella example");

    input.put("exampleObject", new Estudiante("12", "E","R","809"));

    List<Estudiante> systems = new ArrayList<Estudiante>();
    systems.add(new Estudiante("20120201", "Ernesto","Rodríguez","809-471-3978"));
      systems.add(new Estudiante("20120844", "Eduardo","Veras","829-456-2379"));

    input.put("systems", systems);

    // 2.2. Get the template

    Template template = cfg.getTemplate("helloworld.ftl");

    // 2.3. Generate the output

    // Write output to the console
    Writer consoleWriter = new OutputStreamWriter(System.out);
    template.process(input, consoleWriter);

    // For the sake of example, also write output into a file:
    Writer fileWriter = new FileWriter(new File("output.html"));
    try {
      template.process(input, fileWriter);
    } finally {
      fileWriter.close();
    }

  }
}
*/