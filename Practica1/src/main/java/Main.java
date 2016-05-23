/**
 * Created by Ernesto on 5/20/2016.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Scanner;

public class Main {

    public static void main(String [] args) {
        Document doc ;
        String URL;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creación Cliente HTTP");
        /*
                File input = new File("input.html");
                Document doc = Jsoup.parse(input, "UTF-8", "https://jsoup.org/cookbook/extracting-data/dom-navigation");
        */



        while(true){
                try{

                        System.out.print("Inserte URL (Ej.:google.com): ");
                        URL = scanner.nextLine();

                        doc = Jsoup.connect("http://"+URL).get();


                }catch(Exception e){

                    System.out.println("URL incorrecto.");
                    continue;
                }
            System.out.println("URL correcto.");
            break;
        }

        System.out.println("La cantidad de lineas es: "+ doc.html().split("\n").length);

        Elements links = doc.getElementsByTag("p");
        System.out.println("La cantidad de párrafos es: "+ links.size());

        links = doc.getElementsByTag("img");
        System.out.println("La cantidad de imágenes es: "+ links.size());

        links = doc.getElementsByTag("form");
        System.out.println("\nForms");
        System.out.println("La cantidad de forms es: "+ links.size());
        int i=1;
        for (Element linkF : links) {

            System.out.println("Form "+i +": "+linkF.attr("name"));
            int j=1;

            for (Element linkInput : linkF.getAllElements()) {

                  if(linkInput.tagName().equals("input"))
                  {

                      System.out.println("\tInput " + j + ": " + linkInput.attr("name"));

                      System.out.println("\t-----------------------------");
                      for(Attribute attribute : linkInput.attributes())
                      {


                          System.out.println("\t"+attribute.getKey() +" = "+ attribute.getValue());


                      }
                      System.out.println("\t-----------------------------");
                      j++;
                      System.out.println();

                  }

            }
            i++;

        }




    }
}