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
	        throw new IllegalArgumentException("New exchange rates cannot be null or empty.");
	    }

	    for (Map.Entry<String, Float> entry : nuevosTiposDeCambio.entrySet()) {
	        if (entry.getValue() == null || entry.getValue() <= 0) {
	            throw new IllegalArgumentException("Invalid exchange rate for " + entry.getKey());
	        }
	    }
		rates.putAll(nuevosTiposDeCambio);
    }
	
	 public MonedaResponse calculateExchange(MonedaRequest request) {
	        //Map<String, Float> exchangeRates = tiposDeCambio();

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
	            throw new RuntimeException("Exchange rate not available for the specified currencies");
	        }
	    }
}
