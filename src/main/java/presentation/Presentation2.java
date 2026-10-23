package presentation;

import dao.IDao;
import metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ali Mahraoui
 */
@Configuration
@ComponentScan(basePackages = {"dao", "metier"})
public class Presentation2 {
    private static final String PROFIL_DEFAUT = "default";

    public static AnnotationConfigApplicationContext creerContexte(String profil) {
        if (!profil.equals(PROFIL_DEFAUT) && !profil.equals("dev") && !profil.equals("prod")) {
            throw new IllegalArgumentException("Profil attendu : default, dev ou prod.");
        }
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        try {
            context.getEnvironment().setActiveProfiles(profil);
            context.register(Presentation2.class);
            context.refresh();
            return context;
        } catch (RuntimeException e) {
            context.close();
            throw e;
        }
    }

    public static void main(String[] args) {
        if (args.length > 1) throw new IllegalArgumentException("Usage : Presentation2 [default|dev|prod|xml]");
        String mode = args.length == 0 ? PROFIL_DEFAUT : args[0];
        if (mode.equals("xml")) {
            PresentationXML.main(new String[0]);
            return;
        }
        try (AnnotationConfigApplicationContext context = creerContexte(mode)) {
            System.out.println("[SPRING] Profil : " + mode);
            context.getBeansOfType(IDao.class).forEach((nom, dao) ->
                    System.out.println("[SPRING] Bean " + nom + " : " + dao.getClass().getName()));
            if (mode.equals(PROFIL_DEFAUT)) System.out.println("[SPRING] Sélection explicite : @Qualifier(\"dao2\")");
            IMetier metier = context.getBean(IMetier.class);
            System.out.println("Résultat = " + metier.calcul());
        }
    }
}
