package br.com.sefaz.bo;

import java.util.List;

import br.com.sefaz.model.Cliente;

public interface ClienteBO {
	Cliente getUsuarioLogado(String email, String senha);
	boolean alterarCliente(Cliente cliente);
	boolean deletarCliente(Cliente cliente);
	List<Cliente> listarClientes();
	boolean inserirCliente(Cliente cliente);
}
