package br.com.sefaz.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.sefaz.bo.ClienteBO;
import br.com.sefaz.bo.ClienteBOImpl;
import br.com.sefaz.model.Cliente;
import br.com.sefaz.model.Telefone;
import br.com.sefaz.session.Session;

@ManagedBean(name="clienteBean")
@SessionScoped
public class ClienteBean {
	private ClienteBO clienteBO = new ClienteBOImpl();
	private List<Cliente> clientes;
	private Cliente cliente;
	
	@PostConstruct
	public void init() {
		clientes = clienteBO.listarClientes();
	}
	
	public String redirectAlterarCliente(Cliente cliente) {
		this.cliente = cliente;
		return "/home/alterar.xhtml?faces-redirect=true";
	}
	
	public void alterarCliente() {
		FacesContext context = FacesContext.getCurrentInstance();
		boolean status = clienteBO.alterarCliente(cliente);
		
		if(status) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso","Cliente alterado"));
			clientes = clienteBO.listarClientes();
		}
		else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Erro tecnico inesperado"));
		}
	}
	
	public void cadastrarCliente() {
		FacesContext context = FacesContext.getCurrentInstance();
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
		FacesContext context = FacesContext.getCurrentInstance();
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
