package com.ekwateur.services;

import com.ekwateur.enums.TarifEnum;
import com.ekwateur.models.clients.Client;
import com.ekwateur.models.clients.ClientPro;
import com.ekwateur.models.clients.Particulier;
import com.ekwateur.services.calculfacture.CalculFactureServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculFactureServiceImplTest {

    @InjectMocks
    private CalculFactureServiceImpl calculFactureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculerMontantDu_Particulier() throws IllegalAccessException {
        Particulier particulier = mock(Particulier.class);
        when(particulier.getConsommationElectrique()).thenReturn(new BigDecimal("100"));
        when(particulier.getConsommationGaz()).thenReturn(new BigDecimal("50"));

        TarifEnum tarifParticulier = TarifEnum.obtenirTarif();

        BigDecimal montant = calculFactureService.calculerMontantDu(particulier);

        BigDecimal montantAttendu = new BigDecimal("100").multiply(tarifParticulier.getTarifElectricite())
                .add(new BigDecimal("50").multiply(tarifParticulier.getTarifGaz()));

        assertEquals(montantAttendu, montant);
    }

    @Test
    void testCalculerMontantDu_ClientPro_PetitCa() throws IllegalAccessException {
        ClientPro clientPro = mock(ClientPro.class);
        when(clientPro.getConsommationElectrique()).thenReturn(new BigDecimal("500"));
        when(clientPro.getConsommationGaz()).thenReturn(new BigDecimal("300"));
        when(clientPro.getCa()).thenReturn(new BigDecimal("800000"));

        TarifEnum tarifClientProPetitCa = TarifEnum.obtenirTarif(clientPro.getCa());

        BigDecimal montant = calculFactureService.calculerMontantDu(clientPro);

        BigDecimal montantAttendu = new BigDecimal("500").multiply(tarifClientProPetitCa.getTarifElectricite())
                .add(new BigDecimal("300").multiply(tarifClientProPetitCa.getTarifGaz()));

        assertEquals(montantAttendu, montant);
    }

    @Test
    void testCalculerMontantDu_ClientPro_GrandCa() throws IllegalAccessException {
        ClientPro clientPro = mock(ClientPro.class);
        when(clientPro.getConsommationElectrique()).thenReturn(new BigDecimal("1000"));
        when(clientPro.getConsommationGaz()).thenReturn(new BigDecimal("500"));
        when(clientPro.getCa()).thenReturn(new BigDecimal("1500000"));

        TarifEnum tarifClientProGrandCa = TarifEnum.obtenirTarif(clientPro.getCa());

        BigDecimal montant = calculFactureService.calculerMontantDu(clientPro);

        BigDecimal montantAttendu = new BigDecimal("1000").multiply(tarifClientProGrandCa.getTarifElectricite())
                .add(new BigDecimal("500").multiply(tarifClientProGrandCa.getTarifGaz()));

        assertEquals(montantAttendu, montant);
    }

    @Test
    void testCalculerMontantDu_ClientNonReconnu() {
        Client clientNonReconnu = mock(Client.class);

        assertThrows(IllegalAccessException.class, () -> {
            calculFactureService.calculerMontantDu(clientNonReconnu);
        });
    }
}
