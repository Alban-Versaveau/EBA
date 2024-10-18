package com.ekwateur.services.facturation;

import com.ekwateur.dtos.ConsommationClient;
import com.ekwateur.models.clients.Client;
import com.ekwateur.models.Facture;
import com.ekwateur.services.calculfacture.ICalculFactureService;
import com.ekwateur.utilitaires.GenerationClienteleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class FacturationServiceImpl implements IFacturationService {

    private static final Logger logger = LoggerFactory.getLogger(FacturationServiceImpl.class);

    private final ICalculFactureService calculFactureService;

    @Override
    public List<Facture> calculerFacturesDuMois(List<ConsommationClient> consommationClientList) {
        List<Client> clientList = GenerationClienteleService.genererClients(consommationClientList);
        List<Facture> factureList = clientList.stream()
                .map(this::calculerUneFacture)
                .filter(Objects::nonNull)
                .toList();
        return factureList;
    }

    private Facture calculerUneFacture(Client client) {
        try {
            BigDecimal montantDu = calculFactureService.calculerMontantDu(client);
            Facture facture = Facture.builder()
                    .client(client)
                    .montantDu(montantDu)
                    .build();
            return facture;
        } catch (IllegalAccessException e) {
            logger.error("Erreur durant le calcul de la facture du client {}", client);
            return null;
        }
    }

}
