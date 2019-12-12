package br.com.sefaz.dao;

import java.util.List;

import br.com.sefaz.model.Cliente;

public interface ClienteDAO {

	
	Cliente getClienteAutenticado(String email, String senha);
	boolean alterarCliente(Cliente cliente);
	boolean deletarCliente(Cliente cliente);
	List<Cliente> listarClientes();
	boolean inserirCliente(Cliente cliente);
}
