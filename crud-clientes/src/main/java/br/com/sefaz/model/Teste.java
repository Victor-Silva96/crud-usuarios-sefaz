package br.com.sefaz.model;

import java.util.ArrayList;
import java.util.List;

import br.com.sefaz.dao.ClienteDAO;
import br.com.sefaz.dao.impl.ClienteDAOImpl;

public class Teste {
	public static void main(String[] args) {
		ClienteDAO clienteDao = new ClienteDAOImpl();
		Cliente cliente = new Cliente();
		Telefone telefone = new Telefone();
		telefone.setDdd(81);
		telefone.setNumero("918232421");
		telefone.setTipo("residencial");
		List<Telefone> telefones = new ArrayList<Telefone>();
		
		telefones.add(telefone);
		
		cliente.setEmail("amanda@hotmail.com");
		cliente.setNome("amanda");
		cliente.setSenha("7432");
		cliente.setTelefones(telefones);
		
		clienteDao.inserirCliente(cliente); 
		
		Cliente cliente2=clienteDao.getClienteAutenticado(cliente.getEmail(), cliente.getSenha());
		
		System.out.println(cliente2);
		
		
	}
}
