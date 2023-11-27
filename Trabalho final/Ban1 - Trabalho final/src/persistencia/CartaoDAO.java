package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import dados.Cartao;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;

public class CartaoDAO  {
	
		private static CartaoDAO instance = null;
		private PreparedStatement selectNewId;
		private PreparedStatement select;
		private PreparedStatement insert;
		private PreparedStatement delete;
		private PreparedStatement selectAll;
		private PreparedStatement qtd_cartoes_cidade;
		
		public static CartaoDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
		if (instance == null)
			instance = new CartaoDAO();
			return instance;
		}
		
		private CartaoDAO() throws ClassNotFoundException , SQLException , SelectException {
			Connection conn = DatabaseConnection.getConnection();
			selectNewId = conn.prepareStatement( "select nextval ('cartaoID')" ) ;
			select = conn.prepareStatement("select * from cartao where cartaoID = ? ");
			selectAll = conn.prepareStatement("select * from cartao");
			insert = conn.prepareStatement("insert into public.cartao" + "(usuarioID, data_emissao, situacao, saldo, segmento) values (?, ?, ?, ?, ?)");
			delete = conn.prepareStatement("delete from cartao where cartaoID = ?");
			qtd_cartoes_cidade = conn.prepareStatement("select count(cartaoID) as qtd from cartao "
					+ "where usuarioID in (select usuarioID from usuario where cidade = ?) and situacao = 'Ativo'");
		}

		public Cartao select (int code) throws SelectException {
			ResultSet rs;
			Cartao us = null;
			try {
				select.setInt(1, code);
				rs = select.executeQuery();
				if (rs.next()) {
					us = new Cartao();
					us.setId(rs.getInt("cartaoID"));
					us.setId(rs.getInt("usuarioID"));
					us.setData_emissao(rs.getDate("data_emissao"));
					us.setSituacao(rs.getString("situacao"));
					us.setSaldo(rs.getFloat("saldo"));
					us.setSegmento(rs.getString("segmento"));

				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar cartao");
			}
			return us;
		}
		
		public void insert (Cartao us) throws InsertException, SelectException{
			try {
				insert.setInt(1, us.getUsuarioID());
				insert.setDate(2, us.getData_emissao());
				insert.setString(3, us.getSituacao());
				insert.setFloat(4, us.getSaldo());
				insert.setString(5, us.getSegmento());
				insert.executeUpdate();
			} catch (SQLException e) {
				throw new InsertException("Erro ao inserir cartao");
			}
		}
		
		public void delete (Cartao us) throws DeleteException {
			try {
				delete.setInt(1, us.getId());
				delete.executeUpdate();
			} catch (SQLException e) {
				throw new DeleteException("Erro ao deletar cartao");
			}
		} 
		
		public List<Cartao> selectAll () throws SelectException {
			List<Cartao> us = new ArrayList<Cartao>();
			Cartao u;
			
			try {
				ResultSet rs = selectAll.executeQuery();
				while(rs.next()) {
					u = new Cartao();
					u.setId(rs.getInt(1));
					u.setUsuarioID(rs.getInt(2));
					u.setData_emissao(rs.getDate(3));
					u.setSituacao(rs.getString(4));
					u.setSaldo(rs.getFloat(5));
					u.setSegmento(rs.getString(6));
					
					us.add(u);
				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar todos os cartoes");
			}
			return us;
		}
		
		public int qtd_cartoes_cidade (String cidade) throws SelectException {
			ResultSet rs;
			int count = 0;
			try {
				qtd_cartoes_cidade.setString(1, cidade);
				rs = qtd_cartoes_cidade.executeQuery();
				if (rs.next()) {
					count = rs.getInt("qtd");

				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar cartao");
			}
			return count;
		}
}