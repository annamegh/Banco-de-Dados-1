package negocio;

import java.sql.SQLException;
import java.util.List;

import dados.Cartao;
import dados.Compra;
import dados.Rede;
import dados.Usuario;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;
import persistencia.CartaoDAO;
import persistencia.CompraDAO;
import persistencia.RedeDAO;
import persistencia.UsuarioDAO;

public class Gerenciador {

	private UsuarioDAO usuarioDAO; 
	private CartaoDAO cartaoDAO;
	private CompraDAO compraDAO;
	private RedeDAO redeDAO;
	
	private Usuario u;
	
	public Gerenciador() {
		try {
			usuarioDAO = UsuarioDAO.getInstance();
			cartaoDAO = CartaoDAO.getInstance();
			compraDAO = CompraDAO.getInstance();
			redeDAO = RedeDAO.getInstance();
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		} catch (SelectException e3) {
			System.out.println(e3.getMessage());
		}
	}
	
	public List<Usuario> getUsuarios () throws SelectException{
		return usuarioDAO.selectAll();
	}
	
	public void cadastrarUsuario (Usuario usuario) throws InsertException, SelectException {
		usuarioDAO.insert(usuario);
	}
	
	public void excluirUsuario (Usuario usuario) throws DeleteException {
		usuarioDAO.delete(usuario);
	}
	
	public float somaComprasUsuario (Usuario usuario) throws SelectException {
		return usuarioDAO.sum_compras_usuario(usuario);
	}

	public List<Cartao> getCartoes () throws SelectException {
		return cartaoDAO.selectAll();
	}
	
	public void cadastrarCartao (Cartao cartao) throws InsertException, SelectException {
		cartaoDAO.insert(cartao);
	}
	
	public int quatidadeCartoesCidade (String cidade) throws SelectException {
		return cartaoDAO.qtd_cartoes_cidade(cidade);
	}
	
	public void excluirMusica (Cartao cartao) throws DeleteException {
		cartaoDAO.delete(cartao);
	}
	
	public List<Compra> getCompras () throws SelectException {
		return compraDAO.selectAll();
	}
	
	public void cadastrarCompra (Compra compra) throws InsertException, SelectException {
		compraDAO.insert(compra);
	}
	
	public void excluirCompra (Compra compra) throws DeleteException {
		compraDAO.delete(compra);
	}
	
	public List<Rede> getRedes () throws SelectException {
		return redeDAO.selectAll();
	}
	
	public void cadastrarRede (Rede rede) throws InsertException, SelectException {
		redeDAO.insert(rede);
	}
	
	public void excluirRede (Rede rede) throws DeleteException {
		redeDAO.delete(rede);
	}
	
}