package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Variante de l'exercice profils : un seul DAO est actif, sans qualifier fixe.
 * @author Ali Mahraoui
 */
@Component("metier")
@Profile({"dev", "prod"})
public class MetierProfil implements IMetier {
    private static final double COEFFICIENT = 2.0;
    private final IDao daoService;

    @Autowired
    public MetierProfil(IDao daoService) {
        this.daoService = daoService;
    }

    @Override
    public double calcul() {
        return daoService.getValue() * COEFFICIENT;
    }
}
