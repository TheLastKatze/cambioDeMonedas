package pe.com.moneda.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import pe.com.moneda.model.MonedaRequest;
import pe.com.moneda.model.MonedaResponse;

@Service
public class TipoDeCambioService {
	
	private Map<String, Float> rates;
	
	public TipoDeCambioService() {
        this.rates = inicializarTiposDeCambio();
    }
	
	public Map<String, Float> tiposDeCambio() {
	    return new HashMap<>(rates);
	}
	
	public Map<String, Float> inicializarTiposDeCambio() {//tiposDeCambio() {
        Map<String, Float> exchangeRates = new HashMap<>();
        exchangeRates.put("USD_PEN", 3.76f);
        exchangeRates.put("EUR_PEN", 4.11f);
        exchangeRates.put("GBP_PEN", 4.69f);
        exchangeRates.put("USD_EUR", 0.91f);
        exchangeRates.put("USD_GBP", 0.80f);
        exchangeRates.put("GBP_EUR", 1.14f);
        exchangeRates.put("PEN_USD", 0.26f);
        exchangeRates.put("PEN_EUR", 0.24f);
        exchangeRates.put("PEN_GBP", 0.21f);
        return exchangeRates;
    }
	
	public void actualizarTiposDeCambio(Map<String, Float> nuevosTiposDeCambio) {
		if (nuevosTiposDeCambio == null || nuevosTiposDeCambio.isEmpty()) {
	        throw new IllegalArgumentException("Los nuevos tipos no deben estar vacion o nulos.");
	    }

		for (Map.Entry<String, Float> entry : nuevosTiposDeCambio.entrySet()) {
	        if (!isValidCurrencyFormat(entry.getKey())) {
	            throw new IllegalArgumentException("Formato de moneda no válido para: " + entry.getKey());
	        }

	        if (entry.getValue() == null || entry.getValue() <= 0) {
	            throw new IllegalArgumentException("Entrada no válida para: " + entry.getKey());
	        }
	    }
		rates.putAll(nuevosTiposDeCambio);
    }
	
	private boolean isValidCurrencyFormat(String currency) {
	    return currency != null && currency.matches("^[A-Z]{3}_[A-Z]{3}$");
	}
	
	 public MonedaResponse calculateExchange(MonedaRequest request) {
		 
	        String key = request.getMonedaOrigen().toUpperCase() + "_" + request.getMonedaDestino().toUpperCase();
	        Float tipoCambio = rates.get(key);

	        if (tipoCambio != null) {
	            
	            float montoConTipoCambio = request.getMonto() * tipoCambio;
	            
	            return new MonedaResponse(
	                    request.getMonto(),
	                    montoConTipoCambio,
	                    request.getMonedaOrigen(),
	                    request.getMonedaDestino(),
	                    String.valueOf(tipoCambio)
	            );
	        } else {
	            throw new RuntimeException("Cambio no disponible para esas monedas");
	        }
	    }
}
