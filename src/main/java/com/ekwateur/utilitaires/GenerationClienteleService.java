package com.ekwateur.utilitaires;

import com.ekwateur.dtos.ConsommationClient;
import com.ekwateur.models.clients.Client;
import com.ekwateur.models.clients.ClientPro;
import com.ekwateur.models.clients.Particulier;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class GenerationClienteleService {

    public static List<Client> genererClients(List<ConsommationClient> consommationClientList) {
        return consommationClientList.stream().map(GenerationClienteleService::genererUnClient).toList();
    }

    private static Client genererUnClient(ConsommationClient consommationClient) {
        int random = ThreadLocalRandom.current().nextInt(1, 102);
        if (1 <= random && random < 34) {
            return Particulier.builder()
                    .referenceClient("EKW12345678")
                    .consommationElectrique(consommationClient.consommationElectrique())
                    .consommationGaz(consommationClient.consommationGaz())
                    .civilite("M.")
                    .nom("Dupont")
                    .prenom("Jean")
                    .build();
        }
        if (35 <= random && random < 68) {
            return ClientPro.builder()
                    .referenceClient("EKW34567890")
                    .consommationElectrique(consommationClient.consommationElectrique())
                    .consommationGaz(consommationClient.consommationGaz())
                    .siret("12345678901234")
                    .raisonSociale("PME")
                    .ca(new BigDecimal("500000"))
                    .build();
        }
        return ClientPro.builder()
                .referenceClient("EKW56789012")
                .consommationElectrique(consommationClient.consommationElectrique())
                .consommationGaz(consommationClient.consommationGaz())
                .siret("34567890123456")
                .raisonSociale("Industrie")
                .ca(new BigDecimal("1500000"))
                .build();
    }
}
