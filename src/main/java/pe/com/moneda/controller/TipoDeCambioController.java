package pe.com.moneda.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pe.com.moneda.model.MonedaRequest;
import pe.com.moneda.model.MonedaResponse;
import pe.com.moneda.service.TipoDeCambioService;

@Controller
public class TipoDeCambioController {
    
	@Autowired
	private TipoDeCambioService tipoCambioService;
	
    @PostMapping("/calcular")
    private ResponseEntity<MonedaResponse> calcularTipoCambio(@RequestBody MonedaRequest request) {
    	try {
            MonedaResponse response = tipoCambioService.calculateExchange(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @PostMapping("/actualizarCambio")
    public ResponseEntity<String> updateExchangeRates(@RequestBody Map<String, Float> nuevosRatios) {
        try {
        	tipoCambioService.actualizarTiposDeCambio(nuevosRatios);
            return ResponseEntity.ok("Se actualizaron los datos correctamnete");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar los datos");
        }
    }
}
