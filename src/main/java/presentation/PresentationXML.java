package presentation;

import metier.IMetier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Ali Mahraoui
 */
public class PresentationXML {
    private static final String FICHIER_CONFIG_XML = "applicationContext.xml";

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(FICHIER_CONFIG_XML)) {
            System.out.println("[SPRING XML] Injection par setter du bean dao2");
            System.out.println("Résultat (XML) = " + context.getBean("metier", IMetier.class).calcul());
        }
    }
}
