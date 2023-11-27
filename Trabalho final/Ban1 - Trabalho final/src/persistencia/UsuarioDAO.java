package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import dados.Usuario;
import excessoes.DeleteException;
import excessoes.InsertException;
import excessoes.SelectException;

public class UsuarioDAO  {
	
		private static UsuarioDAO instance = null;
		private PreparedStatement selectNewId;
		private PreparedStatement select;
		private PreparedStatement insert;
		private PreparedStatement delete;
		private PreparedStatement selectAll;
		private PreparedStatement sum;
		
		public static UsuarioDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
		if (instance == null)
			instance = new UsuarioDAO();
			return instance;
		}
		
		private UsuarioDAO() throws ClassNotFoundException , SQLException , SelectException {
			Connection conn = DatabaseConnection.getConnection();
			selectNewId = conn.prepareStatement( "select nextval ('usuarioID')" ) ;
			select = conn.prepareStatement("select * from usuario where usuarioID = ? ");
			selectAll = conn.prepareStatement("select * from usuario");
			insert = conn.prepareStatement("insert into public.usuario" + "(nome, cpf, data_nascimento, cidade, estado, data_inclusao) values (?, ?, ?, ?, ?, ?)");
			delete = conn.prepareStatement("delete from usuario where usuarioID = ?");
			sum = conn.prepareStatement("select sum(c.valor) as soma from compra c "
					+ "join cartao ct on ct.cartaoID = c.cartaoID "
					+ "join usuario u on u.usuarioID = ct.usuarioID "
					+ "where u.usuarioID = ?");
		}


		public Usuario select (int code) throws SelectException {
			ResultSet rs;
			Usuario us = null;
			try {
				select.setInt(1, code);
				rs = select.executeQuery();
				if (rs.next()) {
					us = new Usuario();
					us.setId(rs.getInt("usuarioID"));
					us.setNome(rs.getString("nome"));
					us.setCpf(rs.getString("cpf"));
					us.setData_nascimento(rs.getDate("data_nascimento"));
					us.setCidade(rs.getString("cidade"));
					us.setEstado(rs.getString("estado"));
					us.setData_inclusao(rs.getDate("data_inclusao"));

				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar usuario");
			}
			return us;
		}
		
		public void insert (Usuario us) throws InsertException, SelectException{
			try {
				insert.setString(1, us.getNome());
				insert.setString(2, us.getCpf());
				insert.setDate(3, us.getData_nascimento());
				insert.setString(4, us.getCidade());
				insert.setString(5, us.getEstado());
				insert.setDate(6, us.getData_inclusao());
				insert.executeUpdate();
			} catch (SQLException e) {
				throw new InsertException("Erro ao inserir usuario");
			}
		}
		
		public void delete (Usuario us) throws DeleteException {
			try {
				delete.setInt(1, us.getId());
				delete.executeUpdate();
			} catch (SQLException e) {
				throw new DeleteException("Erro ao deletar usuário");
			}
		} 
		
		public List<Usuario> selectAll () throws SelectException {
			List<Usuario> us = new ArrayList<Usuario>();
			Usuario u;
			
			try {
				ResultSet rs = selectAll.executeQuery();
				while(rs.next()) {
					u = new Usuario();
					u.setId(rs.getInt(1));
					u.setNome(rs.getString(2));
					u.setCpf(rs.getString(3));
					u.setData_nascimento(rs.getDate(4));
					u.setCidade(rs.getString(5));
					u.setEstado(rs.getString(6));
					u.setData_inclusao(rs.getDate(7));
					
					us.add(u);
				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar todos os usuários");
			}
			return us;
		}
		
		public float sum_compras_usuario (Usuario us) throws SelectException {
			ResultSet rs;
			float valor = 0;
			try {
				sum.setInt(1, us.getId());
				rs = sum.executeQuery();
				if (rs.next()) {
					valor = rs.getFloat("soma");
				}
			} catch (SQLException e) {
				throw new SelectException("Erro ao buscar as compras do usuario");
			}
			return valor;
		}
}