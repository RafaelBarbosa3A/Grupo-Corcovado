# Parametrização do Sistema

## Pacotes
#### Pacote principal: br.senac.corcovado
#### Hierarquia de pacotes:
* **model** (deve conter as classes referentes a manipulação e armazenamento dos dados) 
 	* **dao** (Data access Object)
 	* **entity** (POJO e Enums)
 	* **validator** (Validadores, builders para as entidades)
 	* **exception** (Classes de erros do modelo)
 	* **utils** (classes singletons úteis em múltiplos lugares da aplicação, como conversores de data, moeda,..)
* **controller** (Classes responsáveis pelas requisições web utilizando spring)
* **resources** (View responsável pela criação das telas html)
	* **template** (HTML com thymeleaf)
	* **static** (~~CSS, JS e Imagens~~)


## Nomes
#### Exemplo de classes:
Table |	Model |	Controller | View
----- | ----- | ---------- | ----
produtos | Produto.java (entity) | ProdutoController.java | produto_list.html
<j> | ProdutoDao.java | <j> | produto_show.html
<j> | ProdutoValidador.java | <j> | prooduto_form.html
<j> | ProdutoException.java | <j> | <j>

#### Exemplo de propriedades:
Table |	Model |	View (name in a form) |	View id
----- | ----- | --------------------- | -------
produto_id | produtoId | produto_id | {obj}-produto_id <br> input-produto_id <br> label-produto_id


## Rotas
##### Exemplo de rotas para produto:
Método | Função do Controller | Rota | Função do Dao
------ | -------------------- | ---- | -------------
GET | list | /produtos | query
GET | show | /produtos/{id} | read
GET | new | /produtos/new | <j>
POST | create | /produtos/create | create
GET | edit | /produtos/edit/{id} | read
POST (~~PUT or PATCH~~) | update | /produtos/update/{id} | update
POST (~~DELETE~~) | destroy | /produtos/destroy/{id} | delete


## Tabelas
Todas as tabelas devem conter os campos
* **id** (exceto tabelas de ligação de relacionamentos muitos-para-muitos)
* **created_at** (apenas modificada pelo método create do dao)
* **updated_at** (modificada pelos métodos create, update e delete)
* **active** (modificada pelo método delete, quando active == false esta linha não retorna na maioria das querys, ainda retorna em relatórios)

