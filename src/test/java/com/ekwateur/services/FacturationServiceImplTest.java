package com.ekwateur.services;

import com.ekwateur.TestUtils;
import com.ekwateur.dtos.ConsommationClient;
import com.ekwateur.models.Facture;
import com.ekwateur.models.clients.Client;
import com.ekwateur.services.calculfacture.ICalculFactureService;
import com.ekwateur.services.facturation.FacturationServiceImpl;
import com.ekwateur.utilitaires.GenerationClienteleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FacturationServiceImplTest {

    @Mock
    private ICalculFactureService calculFactureService;

    @InjectMocks
    private FacturationServiceImpl facturationService;

    @BeforeAll
    static void setUpForAll() {
        mockStatic(GenerationClienteleService.class);
    }

    @BeforeEach
    void setUpForEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculerFacturesDuMois() throws IllegalAccessException {
        List<ConsommationClient> consommationClientList = TestUtils.creerConsommations();

        Client client1 = mock(Client.class);
        Client client2 = mock(Client.class);
        Client client3 = mock(Client.class);
        List<Client> clientList = Arrays.asList(client1, client2, client3);

        when(GenerationClienteleService.genererClients(anyList())).thenReturn(clientList);

        when(calculFactureService.calculerMontantDu(client1)).thenReturn(new BigDecimal("58.250"));
        when(calculFactureService.calculerMontantDu(client2)).thenReturn(new BigDecimal("87.250"));
        when(calculFactureService.calculerMontantDu(client3)).thenReturn(new BigDecimal("174.500"));

        List<Facture> factures = facturationService.calculerFacturesDuMois(consommationClientList);

        assertEquals(3, factures.size());
        assertEquals(new BigDecimal("58.250"), factures.get(0).montantDu());
        assertEquals(new BigDecimal("87.250"), factures.get(1).montantDu());
        assertEquals(new BigDecimal("174.500"), factures.get(2).montantDu());

        verify(calculFactureService, times(1)).calculerMontantDu(client1);
        verify(calculFactureService, times(1)).calculerMontantDu(client2);
        verify(calculFactureService, times(1)).calculerMontantDu(client3);
    }

    @Test
    void testCalculerFacturesDuMoisAvecException() throws IllegalAccessException {
        List<ConsommationClient> consommationClientList = TestUtils.creerConsommations();

        Client client1 = mock(Client.class);
        Client client2 = mock(Client.class);
        Client client3 = mock(Client.class);
        List<Client> clientList = Arrays.asList(client1, client2, client3);

        when(GenerationClienteleService.genererClients(anyList())).thenReturn(clientList);

        when(calculFactureService.calculerMontantDu(client1)).thenReturn(new BigDecimal("58.250"));
        when(calculFactureService.calculerMontantDu(client2)).thenReturn(new BigDecimal("87.250"));
        when(calculFactureService.calculerMontantDu(client3)).thenThrow(new IllegalAccessException("Erreur de calcul"));

        List<Facture> factures = facturationService.calculerFacturesDuMois(consommationClientList);

        assertEquals(2, factures.size());
        assertEquals(new BigDecimal("58.250"), factures.get(0).montantDu());
        assertEquals(new BigDecimal("87.250"), factures.get(1).montantDu());

        verify(calculFactureService, times(1)).calculerMontantDu(client1);
        verify(calculFactureService, times(1)).calculerMontantDu(client2);
    }
}
