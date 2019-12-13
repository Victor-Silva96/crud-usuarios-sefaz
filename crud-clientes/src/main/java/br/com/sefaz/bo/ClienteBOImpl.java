package br.com.sefaz.bo;

import java.util.List;

import br.com.sefaz.dao.ClienteDAO;
import br.com.sefaz.dao.impl.ClienteDAOImpl;
import br.com.sefaz.model.Cliente;

public class ClienteBOImpl implements ClienteBO {
	
	ClienteDAO clienteDAO = new ClienteDAOImpl();

	@Override
	public Cliente getUsuarioLogado(String email, String senha) {
		return clienteDAO.getClienteAutenticado(email, senha);
	}

	@Override
	public boolean alterarCliente(Cliente cliente) {
		return clienteDAO.alterarCliente(cliente);
	}

	@Override
	public boolean deletarCliente(Cliente cliente) {
		return clienteDAO.deletarCliente(cliente);
	}

	@Override
	public List<Cliente> listarClientes() {
		return clienteDAO.listarClientes();
	}

	@Override
	public boolean inserirCliente(Cliente cliente) {
		return clienteDAO.inserirCliente(cliente); 
	}

}
