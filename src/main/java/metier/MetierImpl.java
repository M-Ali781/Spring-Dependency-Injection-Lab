package metier;

import dao.IDao;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Ali Mahraoui
 */
@Component("metier")
@Profile("default")
public class MetierImpl implements IMetier {
    private static final double COEFFICIENT = 2.0;

    @Autowired
    @Qualifier("dao2")
    private IDao daoService;

    @Override
    public double calcul() {
        if (daoService == null) throw new IllegalStateException("Le DAO n'a pas été injecté.");
        return daoService.getValue() * COEFFICIENT;
    }

    // Utilisé pour la configuration XML et les tests sans contexte Spring.
    public void setDao(IDao dao) {
        this.daoService = Objects.requireNonNull(dao, "Le DAO est obligatoire.");
    }

    public IDao getDaoService() {
        return this.daoService;
    }

    public void setDaoService(IDao daoService) {
        this.daoService = Objects.requireNonNull(daoService, "Le DAO est obligatoire.");
    }
}
