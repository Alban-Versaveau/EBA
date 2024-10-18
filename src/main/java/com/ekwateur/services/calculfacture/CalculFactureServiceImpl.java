package com.ekwateur.services.calculfacture;

import com.ekwateur.enums.TarifEnum;
import com.ekwateur.models.clients.Client;
import com.ekwateur.models.clients.ClientPro;
import com.ekwateur.models.clients.Particulier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class CalculFactureServiceImpl implements ICalculFactureService {
    @Override
    public BigDecimal calculerMontantDu(Client client) throws IllegalAccessException {
        return switch (client) {
            case Particulier particulier ->
                    calculerMontant(TarifEnum.obtenirTarif(), particulier.getConsommationElectrique(), particulier.getConsommationGaz());
            case ClientPro clientPro ->
                    calculerMontant(TarifEnum.obtenirTarif(clientPro.getCa()), clientPro.getConsommationElectrique(), clientPro.getConsommationGaz());
            default -> throw new IllegalAccessException("Type de client non reconnu");
        };
    }

    private BigDecimal calculerMontant(TarifEnum tarif, BigDecimal consommationElectrique, BigDecimal consommationGaz) {
        BigDecimal tarifElectricite = (consommationElectrique.multiply(tarif.getTarifElectricite()));
        BigDecimal tarifGaz = (consommationGaz.multiply(tarif.getTarifGaz()));
        return tarifElectricite.add(tarifGaz);
    }
}
