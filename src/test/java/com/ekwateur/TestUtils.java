package com.ekwateur;

import com.ekwateur.dtos.ConsommationClient;
import com.ekwateur.models.Facture;
import com.ekwateur.models.clients.Client;
import com.ekwateur.models.clients.ClientPro;
import com.ekwateur.models.clients.Particulier;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class TestUtils {

    public List<Client> creerClients() {
        return List.of(
                Particulier.builder()
                        .referenceClient("EKW12345678")
                        .consommationElectrique(new BigDecimal("350"))
                        .consommationGaz(new BigDecimal("150"))
                        .civilite("M.")
                        .nom("Dupont")
                        .prenom("Jean")
                        .build(),
                ClientPro.builder()
                        .referenceClient("EKW34567890")
                        .consommationElectrique(new BigDecimal("500"))
                        .consommationGaz(new BigDecimal("250"))
                        .siret("12345678901234")
                        .raisonSociale("PME")
                        .ca(new BigDecimal("500000"))
                        .build(),
                ClientPro.builder()
                        .referenceClient("EKW56789012")
                        .consommationElectrique(new BigDecimal("1000"))
                        .consommationGaz(new BigDecimal("500"))
                        .siret("34567890123456")
                        .raisonSociale("Industrie")
                        .ca(new BigDecimal("1500000"))
                        .build()
        );
    }

    public List<Facture> creerFactures(List<Client> clientList) {
        return Arrays.asList(
                new Facture(clientList.get(0), new BigDecimal("58.250")),
                new Facture(clientList.get(1), new BigDecimal("87.250")),
                new Facture(clientList.get(2), new BigDecimal("174.500"))
        );
    }

    public List<ConsommationClient> creerConsommations() {
        return List.of(
                ConsommationClient.builder()
                        .consommationElectrique(new BigDecimal("350"))
                        .consommationGaz(new BigDecimal("150"))
                        .build(),
                ConsommationClient.builder()
                        .consommationElectrique(new BigDecimal("500"))
                        .consommationGaz(new BigDecimal("250"))
                        .build(),
                ConsommationClient.builder()
                        .consommationElectrique(new BigDecimal("1000"))
                        .consommationGaz(new BigDecimal("500"))
                        .build()
        );
    }
}
