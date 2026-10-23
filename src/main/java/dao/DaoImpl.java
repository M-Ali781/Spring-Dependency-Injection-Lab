package dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Ali Mahraoui
 */
@Component("dao")
@Profile({"default", "prod"})
public class DaoImpl implements IDao {
    private static final double VALEUR_PROD = 100.0;

    @Override
    public double getValue() {
        System.out.println("[DAO] Version de production : " + VALEUR_PROD);
        return VALEUR_PROD;
    }
}
