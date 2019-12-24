package br.com.sefaz.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sefaz.bo.ClienteBO;
import br.com.sefaz.constants.Constants;
import br.com.sefaz.model.Cliente;
import br.com.sefaz.model.Telefone;
import br.com.sefaz.session.Session;
import java.io.Serializable;

@Named(value="clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 3202599747989602124L;
	@Inject
	private transient ClienteBO clienteBO;
	private transient List<Cliente> clientes;
	private transient Cliente cliente;
	@Inject
	private FacesContext context;
	
	@PostConstruct
	public void init() {
		clientes = clienteBO.listarClientes();
	}
	
	public String redirectAlterarCliente(Cliente cliente) {
		this.cliente = cliente;
		return "/home/alterar.xhtml?faces-redirect=true";
	}
	
	public void alterarCliente() {
		boolean status = clienteBO.alterarCliente(cliente);
		
		if(status) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.SUCESSO.getNome(),"Cliente alterado"));
			clientes = clienteBO.listarClientes();
		}
		else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,Constants.ERROR.getNome(),Constants.ERROTECNICO.getNome()));
		}
	}
	
	public void cadastrarCliente() {
		boolean status = clienteBO.inserirCliente(cliente);
		
		if(status) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso","Cliente cadastrado"));
			clientes = clienteBO.listarClientes();
		}
		else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Erro tecnico inesperado"));
		}
	}
	
	public void addTelefone() {
		cliente.getTelefones().add(new Telefone());
	}
	
	public void removeTelefone(Telefone telefone) {
		cliente.getTelefones().remove(telefone);
	}
	
	public String excluirCliente(Cliente cliente) {
		boolean status = clienteBO.deletarCliente(cliente);
		
		if(status) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso","Cliente removido com sucesso"));
			clientes = clienteBO.listarClientes();
			Cliente clienteSession = (Cliente) Session.getInstance().getAttribute("cliente");
			if(cliente.getEmail().equals(clienteSession.getEmail())) {
				Session.getInstance().encerrarSessao();
				return "/login.xhtml";
			}
			return "";
		}
		
		else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Erro tecnico inesperado"));
			return "";
		}
		
		
	}
	
	public String redirectCadastrar() {
		this.cliente = new Cliente();
		this.cliente.setTelefones(new ArrayList<Telefone>());
		return "/cadastrar.xhtml?faces-redirect=true";
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
