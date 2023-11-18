package pe.com.moneda;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pe.com.moneda.service.TipoDeCambioService;

@SpringBootApplication
public class CambioDeMonedasApplication {

	private static TipoDeCambioService tipoCambioService;
	
	@Autowired
	public void TipoDeCambioInitializer(TipoDeCambioService tipoCambioService) {
	    this.tipoCambioService = tipoCambioService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CambioDeMonedasApplication.class, args);
        Map<String, Float> exchangeRates = tipoCambioService.inicializarTiposDeCambio();
        tipoCambioService.actualizarTiposDeCambio(exchangeRates);
    }
}
