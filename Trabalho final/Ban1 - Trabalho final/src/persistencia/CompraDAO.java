package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import dados.Compra;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;

public class CompraDAO  {
	
		private static CompraDAO instance = null;
		private PreparedStatement selectNewId;
		private PreparedStatement select;
		private PreparedStatement insert;
		private PreparedStatement delete;
		private PreparedStatement selectAll;
		
		public static CompraDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
		if (instance == null)
			instance = new CompraDAO();
			return instance;
		}
		
		private CompraDAO() throws ClassNotFoundException , SQLException , SelectException {
			Connection conn = DatabaseConnection.getConnection();
			selectNewId = conn.prepareStatement( "select nextval ('compraID')" ) ;
			select = conn.prepareStatement("select * from compra where compraID = ? ");
			selectAll = conn.prepareStatement("select * from compra");
			insert = conn.prepareStatement("insert into public.compra" + "(cartaoID, redeID, data, valor, segmento) values (?, ?, ?, ?, ?)");
			delete = conn.prepareStatement("delete from compra where compraID = ?");
		}	

		
		public Compra select (int code) throws SelectException {
			ResultSet rs;
			Compra us = null;
			try {
				select.setInt(1, code);
				rs = select.executeQuery();
				if (rs.next()) {
					us = new Compra();
					us.setId(rs.getInt("compraID"));
					us.setCartaoID(rs.getInt("cartaoID"));
					us.setRedeID(rs.getInt("redeID"));
					us.setData(rs.getDate("data"));
					us.setValor(rs.getFloat("valor"));
					us.setSegmento(rs.getString("segmento"));

				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar compra");
			}
			return us;
		}
		
		public void insert (Compra us) throws InsertException, SelectException{
			try {
				insert.setInt(1, us.getCartaoID());
				insert.setInt(2, us.getRedeID());
				insert.setDate(3, us.getData());
				insert.setFloat(4, us.getValor());
				insert.setString(5, us.getSegmento());
				insert.executeUpdate();
			} catch (SQLException e) {
				throw new InsertException("Erro ao inserir compra");
			}
		}
		
		public void delete (Compra us) throws DeleteException {
			try {
				delete.setInt(1, us.getId());
				delete.executeUpdate();
			} catch (SQLException e) {
				throw new DeleteException("Erro ao deletar compra");
			}
		} 
		
		public List<Compra> selectAll () throws SelectException {
			List<Compra> us = new ArrayList<Compra>();
			Compra u;
			
			try {
				ResultSet rs = selectAll.executeQuery();
				while(rs.next()) {
					u = new Compra();
					u.setId(rs.getInt(1));
					u.setCartaoID(rs.getInt(2));
					u.setRedeID(rs.getInt(3));
					u.setData(rs.getDate(4));
					u.setValor(rs.getFloat(5));
					u.setSegmento(rs.getString(6));
					
					us.add(u);
				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar todas as compras");
			}
			return us;
		}
		
}