package com.ekwateur.controllers;

import com.ekwateur.dtos.ConsommationClient;
import com.ekwateur.models.Facture;
import com.ekwateur.services.facturation.IFacturationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FacturationController {

    private final IFacturationService facturationService;

    @PostMapping("/factures/calculer")
    public List<Facture> calculerFacturesDuMois(@NotNull @NotEmpty @Valid @RequestBody List<@Valid ConsommationClient> consommationClientList) {
        return facturationService.calculerFacturesDuMois(consommationClientList);
    }
}
