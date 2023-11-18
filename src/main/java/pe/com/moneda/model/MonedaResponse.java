package pe.com.moneda.model;

public class MonedaResponse {
	private float monto;
	private float montoConTipoCambio;
	private String monedaOrigen;
	private String monedaDestino;
	private String tipoDeCambio;
	
	public MonedaResponse(float monto, float montoConTipoCambio, String monedaOrigen, String monedaDestino,
            String tipoDeCambio) {
        this.monto = monto;
        this.montoConTipoCambio = montoConTipoCambio;
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.tipoDeCambio = tipoDeCambio;
    }
	
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public float getMontoConTipoCambio() {
		return montoConTipoCambio;
	}
	public void setMontoConTipoCambio(float montoConTipoCambio) {
		this.montoConTipoCambio = montoConTipoCambio;
	}
	public String getMonedaOrigen() {
		return monedaOrigen;
	}
	public void setMonedaOrigen(String monedaOrigen) {
		this.monedaOrigen = monedaOrigen;
	}
	public String getMonedaDestino() {
		return monedaDestino;
	}
	public void setMonedaDestino(String monedaDestino) {
		this.monedaDestino = monedaDestino;
	}
	public String getTipoDeCambio() {
		return tipoDeCambio;
	}
	public void setTipoDeCambio(String tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
	}
	
	
}
