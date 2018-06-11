var corcovado = angular.module('corcovado', ['ui.router']);

class ItemCarrinho {
    constructor(produto, quantidade) {
        this.produtoId = produto.id;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    total() {
        if (this.produto.promocao) {
            return this.produto.promocao * this.quantidade;
        } else {
            return this.produto.preco * this.quantidade;
        }
    };
}

class Carrinho {
    constructor(vendaId, itens) {
        this.vendaId = vendaId;
        this.itens = itens;
    }

    total() {
        return this.itens.reduce((acc, item) => { return acc + item.total(); }, 0);
    };
}

class Pedido {
    constructor (vendaId, pessoaId, frete, pagamento, cartao, comprovante, rastreamento, enderecoId) {
        this.vendaId = vendaId;
        this.pessoaId = pessoaId;
        this.frete = frete;
        this.pagamento = pagamento;
        this.cartao = cartao;
        this.comprovante = comprovante;
        this.rastreamento = rastreamento;
        this.enderecoId = enderecoId;
    }
}

corcovado.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider.state('list', {
        url: '/produtos',
        templateUrl: 'comercio/list'
    });

    $stateProvider.state('show', {
        url: '/produtos/:id',
        templateUrl: 'comercio/show',
        controller: 'show'
    });

    $stateProvider.state('cart', {
        url: '/carrinho',
        templateUrl: 'comercio/cart'
    });

    $urlRouterProvider.otherwise('/produtos');
});

corcovado.controller('product', function($rootScope, $loader, $state) {
    if (!$rootScope.carrinho) {
        $loader.loadCarrinho().then(function(cart) {
            $rootScope.carrinho = new Carrinho(cart.id, cart.produtoVendidos.map(pv => { return new ItemCarrinho(pv.produto, pv.quantidade); } ));
        });

        $rootScope.addToCart = function(produto, quantidade) {
            if (quantidade >= 1 && quantidade <= (produto.estoque - produto.reservado)) {
                let index = $rootScope.carrinho.itens.findIndex((e) => e.produto.id == produto.id);

                if (index === -1) {
                    $rootScope.carrinho.itens.push(new ItemCarrinho(produto, quantidade));
                } else {
                    $rootScope.carrinho.itens[index].quantidade += quantidade;
                }
            } else {
                alert("Quantidade inadequada!");
            }
        };

        $rootScope.removeFromCart = function(produto) {
            $rootScope.carrinho.itens = $rootScope.carrinho.itens.filter((p) => p.produto.id !== produto.id);
        };
    }

    if (!$rootScope.produtos) {
        $rootScope.produtos = [];

        $loader.loadProdutos().then(function(prods) {
            $rootScope.produtos = prods;
        });
    }
});

corcovado.controller('show', function ($scope, $rootScope, $stateParams) {
    const md = window.markdownit("default", {
        html: true,
        breaks: true,
        linkify: true,
        typographer: true
    });
    
    md.renderer.rules.table_open = (tokens, idx) => {
        return '<table class="table table-bordered table-striped">'
    };
    
    md.renderer.rules.blockquote_open = (tokens, idx) => {
        return '<blockquote class="blockquote mb-2">'
    };

    $scope.produto = $rootScope.produtos.find((p) => { return p.id == $stateParams.id });
    //$scope.descricao_md = md.render($scope.produto.descricao)
    document.querySelector("#prod_descricao").innerHTML = md.render($scope.produto.descricao)
});

corcovado.factory('$loader', function ($http, $q) {
    var produtos = [];

    function loadProdutos() {
        return $q(function (resolve, reject) {
            if (produtos.length === 0) {
                $http.get('/comercio/produto_json').then(function (response) {
                    produtos = response.data;
                    resolve(produtos);
                });
            } else {
                resolve(produtos);
            }
        });
    }

    function getPessoa(token) {
        return $q(function (resolve, reject) {
            $http.get('/comercio/pessoa_json', 
                    { headers: { Authorization: token } }
            ).then(function (response) {
                resolve(response.data);
            });
        });
    }

    function calcFrete() {
        // gospe um número aleatório até resolver o problema de CORS
        return $q(function (resolve, reject) {
            resolve(Math.floor((Math.random() * 23)));
        });
    }

    function loadCarrinho(vendaId) {
        return $q(function (resolve, reject) {
            if(vendaId) {
                $http.get('/comercio/carrinho_json/' + vendaId).then(function (response) {
                    resolve(response.data);
                });
            } else {
                $http.get('/comercio/carrinho_json').then(function (response) {
                    resolve(response.data);
                });
            }
        });
    }

    function postCarrinho(carrinho) {
        return $q(function (resolve, reject) {
            $http.post('/comercio/carrinho_json/add', carrinho).then(function (response) {
                resolve(response.data);
            });
        });
    }

    function postFinaliza(venda, token) {
        return $q(function (resolve, reject) {
            $http.post('/comercio/venda_json/add', 
                    venda, { headers: { Authorization: token } }
            ).then(function (response) {
                resolve(response.data);
            });
        });
    }

    function postPessoa(pessoa) {
        return $q(function (resolve, reject) {
            $http.post('/comercio/pessoa_json/add', pessoa).then(function (response) {
                resolve(response.data);
            });
        });
    }

    function postLogin(credential) {
        return $q(function (resolve, reject) {
            $http.post('/auth/login', credential).then(function (response) {
                resolve(response.headers["Authorization"]);
            }, function(response){
                reject(response.data);
            });
        });
    }

    return { loadProdutos, getPessoa, postPessoa, calcFrete, loadCarrinho, postCarrinho, postFinaliza, postLogin };
});
