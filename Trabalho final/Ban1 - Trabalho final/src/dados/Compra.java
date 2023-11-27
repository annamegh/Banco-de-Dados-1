package dados;

import java.sql.Date;

public class Compra {
	
	private int id;
	private Date data;
	private float valor;
	private String segmento;
	private int cartaoID;
	private int redeID;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getSegmento() {
		return segmento;
	}
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	public int getCartaoID() {
		return cartaoID;
	}
	public void setCartaoID(int cartaoID) {
		this.cartaoID = cartaoID;
	}
	public int getRedeID() {
		return redeID;
	}
	public void setRedeID(int redeID) {
		this.redeID = redeID;
	}
}
