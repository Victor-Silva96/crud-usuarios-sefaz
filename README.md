# crud-usuarios-sefaz
Crud de clientes que podem conter n telefones.Também tendo no sistema um sistema de login que permite o cliente se logar ou se cadastrar e também realizar o logout.

# Regras espécificas:

1- Todos os usuarios tem o mesmo nivel, podendo cada um estando logando alterar, remover e verificar os outros usuarios. Quando um usuario deleta a si mesmo, o sistema encerra a sessão daquele usuario e o mesmo e redirecionando para a pagina de login.
2- o email do usuario e tido como chave unica no banco de dados, com isso na hora do cadastro ou alteração do usuario o mesmo não pode colocar um email ja existente no sistema.

3- so pode ter acesso a pagina de home, alteração e remoção quem ja estiver logado no sistema, caso não esteja será redirecionado para pagina de login.

# Ferramentas utilizadas:

foi utilizado java na versão 1.8, primefaces versão 7.0, maven para gerenciador de dependências e o banco de dados foi o hsqldb na versão 2.5.0.
