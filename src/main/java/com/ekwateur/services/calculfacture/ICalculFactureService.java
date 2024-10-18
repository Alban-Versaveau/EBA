package com.ekwateur.services.calculfacture;

import com.ekwateur.models.clients.Client;

import java.math.BigDecimal;

public interface ICalculFactureService {

    BigDecimal calculerMontantDu(Client client) throws IllegalAccessException;
}
