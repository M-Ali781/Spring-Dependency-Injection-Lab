package presentation;

import dao.IDao;
import metier.IMetier;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.junit.Assert.*;

public class SpringContextTest {
    private static final double DELTA = 0.001;

    @Test public void qualifierSelectionneDao2ParmiDeuxBeans() {
        try (AnnotationConfigApplicationContext ctx = Presentation2.creerContexte("default")) {
            assertEquals(2, ctx.getBeansOfType(IDao.class).size());
            assertEquals(100.0, ctx.getBean("dao", IDao.class).getValue(), DELTA);
            assertEquals(150.0, ctx.getBean("dao2", IDao.class).getValue(), DELTA);
            assertEquals(300.0, ctx.getBean(IMetier.class).calcul(), DELTA);
        }
    }
    @Test public void profilProdActiveSeulementDao() {
        try (AnnotationConfigApplicationContext ctx = Presentation2.creerContexte("prod")) {
            assertEquals(1, ctx.getBeansOfType(IDao.class).size());
            assertTrue(ctx.containsBean("dao"));
            assertFalse(ctx.containsBean("dao2"));
            assertEquals(200.0, ctx.getBean(IMetier.class).calcul(), DELTA);
        }
    }
    @Test public void profilDevActiveSeulementDao2() {
        try (AnnotationConfigApplicationContext ctx = Presentation2.creerContexte("dev")) {
            assertEquals(1, ctx.getBeansOfType(IDao.class).size());
            assertTrue(ctx.containsBean("dao2"));
            assertFalse(ctx.containsBean("dao"));
            assertEquals(300.0, ctx.getBean(IMetier.class).calcul(), DELTA);
        }
    }
    @Test public void xmlInjecteLeDaoParSetter() {
        try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            assertEquals(2, ctx.getBeansOfType(IDao.class).size());
            assertEquals(300.0, ctx.getBean(IMetier.class).calcul(), DELTA);
        }
    }
    @Test public void profilInconnuRefuse() {
        assertThrows(IllegalArgumentException.class, () -> Presentation2.creerContexte("absent"));
    }
}
