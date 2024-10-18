package com.ekwateur.services.facturation;

import com.ekwateur.dtos.ConsommationClient;
import com.ekwateur.models.Facture;
import jakarta.validation.Valid;

import java.util.List;

public interface IFacturationService {
    List<Facture> calculerFacturesDuMois(List<ConsommationClient> consommationClientList);
}
