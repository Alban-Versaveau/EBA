package com.ekwateur.controllers;

import com.ekwateur.TestUtils;
import com.ekwateur.dtos.ConsommationClient;
import com.ekwateur.models.Facture;
import com.ekwateur.models.clients.Client;
import com.ekwateur.services.facturation.IFacturationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class FactureControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private IFacturationService facturationService;

    @InjectMocks
    private FacturationController facturationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(facturationController).build();
    }

    @Test
    void testCalculerFacturesDuMois() throws Exception {
        List<Client> clientList = TestUtils.creerClients();
        List<Facture> factureList = TestUtils.creerFactures(clientList);
        List<ConsommationClient> consommationClientList = TestUtils.creerConsommations();
        String jsonString = mapper.writeValueAsString(consommationClientList);

        ArgumentCaptor<List<ConsommationClient>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        when(facturationService.calculerFacturesDuMois(Mockito.anyList())).thenReturn(factureList);

        mockMvc.perform(post("/factures/calculer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].client.referenceClient").value("EKW12345678"))
                .andExpect(jsonPath("$[0].client.consommationElectrique").value(350))
                .andExpect(jsonPath("$[0].client.consommationGaz").value(150))
                .andExpect(jsonPath("$[0].client.civilite").value("M."))
                .andExpect(jsonPath("$[0].client.nom").value("Dupont"))
                .andExpect(jsonPath("$[0].client.prenom").value("Jean"))
                .andExpect(jsonPath("$[0].montantDu").value(58.250))
                .andExpect(jsonPath("$[1].client.referenceClient").value("EKW34567890"))
                .andExpect(jsonPath("$[1].client.consommationElectrique").value(500))
                .andExpect(jsonPath("$[1].client.consommationGaz").value(250))
                .andExpect(jsonPath("$[1].client.siret").value("12345678901234"))
                .andExpect(jsonPath("$[1].client.raisonSociale").value("PME"))
                .andExpect(jsonPath("$[1].montantDu").value(87.250))
                .andExpect(jsonPath("$[2].client.referenceClient").value("EKW56789012"))
                .andExpect(jsonPath("$[2].client.consommationElectrique").value(1000))
                .andExpect(jsonPath("$[2].client.consommationGaz").value(500))
                .andExpect(jsonPath("$[2].client.siret").value("34567890123456"))
                .andExpect(jsonPath("$[2].client.raisonSociale").value("Industrie"))
                .andExpect(jsonPath("$[2].montantDu").value(174.500));

        verify(facturationService, times(1)).calculerFacturesDuMois(argumentCaptor.capture());

        List<ConsommationClient> captorValue = argumentCaptor.getValue();
        assertEquals(consommationClientList, captorValue);
    }
}
