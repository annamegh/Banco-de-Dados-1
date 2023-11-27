package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import dados.Rede;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;

public class RedeDAO  {
	
		private static RedeDAO instance = null;
		private PreparedStatement selectNewId;
		private PreparedStatement select;
		private PreparedStatement insert;
		private PreparedStatement delete;
		private PreparedStatement selectAll;
		
		public static RedeDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
		if (instance == null)
			instance = new RedeDAO();
			return instance;
		}
		
		private RedeDAO() throws ClassNotFoundException , SQLException , SelectException {
			Connection conn = DatabaseConnection.getConnection();
			selectNewId = conn.prepareStatement( "select nextval ('redeID')" ) ;
			select = conn.prepareStatement("select * from rede where redeID = ? ");
			selectAll = conn.prepareStatement("select * from rede");
			insert = conn.prepareStatement("insert into public.rede" + "(nome, cidade, estado, cnpj, segmento, data_inclusao) values (?, ?, ?, ?, ?, ?)");
			delete = conn.prepareStatement("delete from rede where redeID = ?");
			
		}

		public Rede select (int code) throws SelectException {
			ResultSet rs;
			Rede us = null;
			try {
				select.setInt(1, code);
				rs = select.executeQuery();
				if (rs.next()) {
					us = new Rede();
					us.setId(rs.getInt("redeID"));
					us.setNome(rs.getString("nome"));
					us.setCidade(rs.getString("cidade"));
					us.setEstado(rs.getString("estado"));
					us.setCnpj(rs.getString("cnpj"));
					us.setSegmento(rs.getString("segmento"));
					us.setData_inclusao(rs.getDate("data_inclusao"));

				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar rede");
			}
			return us;
		}
		
		public void insert (Rede us) throws InsertException, SelectException{
			try {
				insert.setString(1, us.getNome());
				insert.setString(2, us.getCidade());
				insert.setString(3, us.getEstado());
				insert.setString(4, us.getCnpj());
				insert.setString(5, us.getSegmento());
				insert.setDate(6, us.getData_inclusao());
				insert.executeUpdate();
			} catch (SQLException e) {
				throw new InsertException("Erro ao inserir rede");
			}
		}
		
		public void delete (Rede us) throws DeleteException {
			try {
				delete.setInt(1, us.getId());
				delete.executeUpdate();
			} catch (SQLException e) {
				throw new DeleteException("Erro ao deletar rede");
			}
		} 
		
		public List<Rede> selectAll () throws SelectException {
			List<Rede> us = new ArrayList<Rede>();
			Rede u;
			
			try {
				ResultSet rs = selectAll.executeQuery();
				while(rs.next()) {
					u = new Rede();
					u.setId(rs.getInt(1));
					u.setNome(rs.getString(2));
					u.setCidade(rs.getString(3));
					u.setEstado(rs.getString(4));
					u.setCnpj(rs.getString(5));
					u.setSegmento(rs.getString(6));
					u.setData_inclusao(rs.getDate(7));
					
					us.add(u);
				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar todos as redes");
			}
			return us;
		}
}