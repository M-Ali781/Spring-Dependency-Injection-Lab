package dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Ali Mahraoui
 */
@Component("dao2")
@Profile({"default", "dev"})
public class DaoImpl2 implements IDao {
    private static final double VALEUR_DEV = 150.0;

    @Override
    public double getValue() {
        System.out.println("[DAO 2] Version de développement : " + VALEUR_DEV);
        return VALEUR_DEV;
    }
}
