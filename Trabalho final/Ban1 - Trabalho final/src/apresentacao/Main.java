package apresentacao;

import negocio.Gerenciador;

import java.util.ArrayList;
import java.util.List;

import dados.Cartao;
import dados.Compra;
import dados.Rede;
import dados.Usuario;
import excessoes.SelectException;
import excessoes.InsertException;
import java.util.Scanner;

public class Main {
	
	Scanner scan = new Scanner(System.in);
	Gerenciador g = new Gerenciador();

	public static void main(String[] args) {
		
		int op;
		
		Main main = new Main();
		System.out.println("Opções:\n1.Mostrar usuarios.\n2.Mostrar cartoes.\n3.Mostrar Redes.\n"
				+ "4.Mostrar Compras\n5.Mostrar total gasto por um usuário.\n6.Mostrar total de cartões cadastrados em uma cidade\n"
				+ "7.Cadastrar usuario\n8.Cadastrar cartao\n9Cadastrar rede\n10.Cadastrar compra");
		
		do {
			
			System.out.println("Digite a opção desejada:");
			op = main.scan.nextInt();
			switch (op) {
				
			case 1: 
				main.mostrarUsuarios(main.g);
				break;
				
			case 2:
				main.mostrarCartoes(main.g);
				break;
				
			case 3:
				main.mostrarRedes(main.g);
				break;
				
			case 4:
				main.mostrarCompras(main.g);
				break;
				
			case 5:
				System.out.println("Digite o numero do usuario:\n");
				int codigo = main.scan.nextInt();
				main.somaComprasUsuario(codigo, main.g);
				break;
				
			case 6: 
				System.out.println("Digite o nome da cidade:\n");
				String cidade = main.scan.next();
				main.quantidadeCartoesCidade(cidade, main.g);
				break;
				
			case 7:
				Usuario u = new Usuario();
				u.setNome("Anna");
				u.setCidade("Curitiba");
				u.setEstado("Paraná");
				u.setCpf("00100110022");
				main.cadastrarUsuario(u, main.g);
				break;
			
			case 8:
				Cartao c1 = new Cartao();
				c1.setSaldo(300);
				c1.setSegmento("Varejo");
				c1.setSituacao("Ativo");
				c1.setUsuarioID(0);
				main.cadastrarCartao(c1, main.g);
				break;
				
			case 9:
				Rede r = new Rede();
				r.setNome("Bombom");
				r.setSegmento("Restaurante");
				r.setCidade("Joinville");
				r.setCnpj("10010010023");
				r.setEstado("Santa Catarina");
				main.cadastrarRede(r, main.g);
				break;
				
			case 10:
				Compra c = new Compra();
				c.setCartaoID(1);
				c.setRedeID(1);
				c.setSegmento("Varejo");
				c.setValor(50);
				main.cadastrarCompra(c, main.g);
				break;
				
			default:
				System.out.println("Opção inválida.");
			}
		} while( op != 0 );
		
		
		}
		
	
	void mostrarUsuarios(Gerenciador g) {
		try { 
			List<Usuario> u = new ArrayList<Usuario>();
			u = g.getUsuarios();
			System.out.print("Usuarios:\n");
			for (Usuario i : u) {
				System.out.println("Id: " + i.getId() +  ", Nome:" + i.getNome() + ", CPF:" + i.getCpf() + ", Nasc.:" + i.getData_nascimento() + ", Cidade:" + i.getCidade() + ", Estado:" + i.getEstado() +  ", Cadastro:" + i.getData_inclusao() + '\n');
			}
		}
		catch (SelectException e) {System.out.println(e.getMessage()); 
		}
	}
	
	void mostrarCartoes (Gerenciador g) {	
		try { 
			List<Cartao> c = new ArrayList<Cartao>();
			c = g.getCartoes();
			System.out.print("Cartões:\n");
			for (Cartao i : c) {
				System.out.println("Id: " + i.getId() + ", Id do Usuario:" + i.getUsuarioID() +  ", Saldo:" + i.getSaldo() + ", Segmento:" + i.getSegmento() + ", Situação:" + i.getSituacao() + '\n');
			}
		}
		catch (SelectException e) {System.out.println(e.getMessage()); 
		}
	}
	
	void mostrarRedes(Gerenciador g) {
		try { 
			List<Rede> r = new ArrayList<Rede>();
			r = g.getRedes();
			System.out.print("Redes:\n");
			for (Rede i : r) {
				System.out.println("Id: " + i.getId() +  ", Nome:" + i.getNome() + ", CNPJ:" + i.getCnpj() + ", Segmento:" + i.getSegmento() + ", Cidade:" + i.getCidade() + ", Estado:" + i.getEstado() +  ", Cadastro:" + i.getData_inclusao() + '\n');
			}
		}
		catch (SelectException e) {System.out.println(e.getMessage()); 
		}
	}
	
	void mostrarCompras(Gerenciador g) {
		try { 
			List<Compra> cp = new ArrayList<Compra>();
			cp = g.getCompras();
			System.out.print("Compras:\n");
			for (Compra i : cp) {
				System.out.println("Id: " + i.getId() +  ", Id do Cartão: " + i.getCartaoID() + ", Id da Rede:" + i.getRedeID() + ", Segmento: " + i.getSegmento() + ", Valor: " + i.getValor() +  ", Data: " + i.getData() + '\n');
			}
		}
		catch (SelectException e) {System.out.println(e.getMessage()); 
		}
	}
	
	void somaComprasUsuario(int cdg, Gerenciador g) {
		try { 
			System.out.print("Soma das Compras do Usuário:\n");
			System.out.print(g.somaComprasUsuario(g.getUsuarios().get(cdg - 1)) + "\n");
		}
		catch (SelectException e) {System.out.println(e.getMessage()); 
		}
	}
	
	void quantidadeCartoesCidade(String cidade, Gerenciador g) {
		try { 
			System.out.print("Quantidade de Cartões na cidade:\n");
			System.out.print(g.quatidadeCartoesCidade(cidade) + "\n");
		}
		catch (SelectException e) {System.out.println(e.getMessage()); 
		}
	}
	
	void cadastrarUsuario(Usuario u, Gerenciador g) {
		try { 
			g.cadastrarUsuario(u);
		}
		catch (InsertException e1 ){System.out.println(e1.getMessage());
		}
		catch (SelectException e2) {System.out.println(e2.getMessage()); 
		}
	}
	
	void cadastrarCartao(Cartao c, Gerenciador g) {
		try { 
			g.cadastrarCartao(c);
		}
		catch (InsertException e1 ){System.out.println(e1.getMessage());
		}
		catch (SelectException e2) {System.out.println(e2.getMessage()); 
		}
	}
	
	void cadastrarCompra(Compra c, Gerenciador g) {
		try { 
			g.cadastrarCompra(c);
		}
		catch (InsertException e1 ){System.out.println(e1.getMessage());
		}
		catch (SelectException e2) {System.out.println(e2.getMessage()); 
		}
	}
	
	void cadastrarRede(Rede r, Gerenciador g) {
		try { 
			g.cadastrarRede(r);
		}
		catch (InsertException e1 ){System.out.println(e1.getMessage());
		}
		catch (SelectException e2) {System.out.println(e2.getMessage()); 
		}
	}

}
