var corcovado = angular.module('corcovado', ['ui.router']);

class ItemCarrinho {
    constructor(produto, quantidade) {
        this.produtoId = produto.id;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    total() {
        return this.produto.preco * this.quantidade;
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
        this.vendaId = vendaId
        this.pessoaId = pessoaId
        this.frete = frete
        this.pagamento = pagamento
        this.cartao = cartao
        this.comprovante = comprovante
        this.rastreamento = rastreamento
        this.enderecoId = enderecoId
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

    $stateProvider.state('login', {
        url: '/login',
        templateUrl: 'comercio/login'
    })

    $stateProvider.state('signup', {
        url: '/signup',
        templateUrl: 'comercio/signup',
        controller: 'signup'
    })

    $stateProvider.state('finaliza', {
        url: '/finaliza',
        templateUrl: 'comercio/finaliza',
        controller: 'finaliza'
    });

    $stateProvider.state('recibo', {
      url: '/recibo',
      templateUrl: 'comercio/recibo',
      controller: 'recibo'
    });

    $urlRouterProvider.otherwise('/produtos');
});

corcovado.controller('product', function($rootScope, $loader, $state) {
    if (!$rootScope.carrinho) {
        $loader.loadCarrinho().then(function(cart) {
            //if (cart.produtoVendidos.length > 0) {
                $rootScope.carrinho = new Carrinho(cart.id, cart.produtoVendidos.map(pv => { return new ItemCarrinho(pv.produto, pv.quantidade); } ));
            /*} else {
                $rootScope.carrinho = new Carrinho(0, []);
            }*/
        });

        $rootScope.addToCart = function(produto, quantidade) {
            if (quantidade >= 1 && quantidade <= (produto.estoque - produto.reservado)) {
                let index = $rootScope.carrinho.itens.findIndex((e) => e.produto.id == produto.id);

                if (index === -1) {
                    $rootScope.carrinho.itens.push(new ItemCarrinho(produto, quantidade));
                } else {
                    $rootScope.carrinho.itens[index].quantidade += quantidade;
                }
            }
        };

        $rootScope.removeFromCart = function(produto) {
            $rootScope.carrinho.itens = $rootScope.carrinho.itens.filter((p) => p.produto.id !== produto.id);
        };

        $rootScope.saveCart = function() {
            $loader.postCarrinho($rootScope.carrinho).then((cart) => {
                $rootScope.carrinho = new Carrinho(cart.id, cart.produtoVendidos.map(pv => { return new ItemCarrinho(pv.produto, pv.quantidade); } ));
                $state.go('finaliza')
            })
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
    //$scope.produto = $loader.getProduto($stateParams.id);
    $scope.produto = $rootScope.produtos.find((p) => { return p.id == $stateParams.id });
});

corcovado.controller('finaliza', function ($scope, $rootScope, $state, $loader) {
    $scope.pessoa = {};
    $scope.frete = null;
    $scope.enderecoId = 0;

    $loader.getPessoa().then(function(pess) {
        $scope.pessoa = pess;
    });

    $scope.calcFrete = function(endereco) {
        $scope.enderecoId = endereco;
        $loader.calcFrete(endereco).then(function(dist) {
            $scope.frete = dist * 3.0;
        });
    };

    $scope.finalizarCompra = function() {
        if (!$scope.frete) {
            $scope.calcFrete($scope.pessoa.enderecos[0]);
        }

        let pedido = new Pedido($rootScope.carrinho.vendaId, $scope.pessoa.id, $scope.frete, $scope.pagamento, $scope.cartao, "", "", $scope.enderecoId);

        $loader.postFinaliza(pedido).then((cart) => {
            $rootScope.carrinho = new Carrinho(cart.id, cart.produtoVendidos.map(pv => { return new ItemCarrinho(pv.produto, pv.quantidade); } ));
            $state.go('recibo')
        })

    };
});


corcovado.controller('recibo', function ($scope, $loader) {
    $loader.loadCarrinho().then(function(cart) {
        $scope.carrinho = new Carrinho(cart.id, cart.produtoVendidos.map(pv => {return new ItemCarrinho(pv.produto, pv.quantidade);} ) );
        $scope.pedido = new Pedido(cart.id, cart.pessoa.id, cart.frete, cart.pagamento, cart.cartao, cart.comprovante, cart.codigoRastreamento, cart.enderecoId);
    });
});

corcovado.controller('signup', function ($scope, $state, $loader) {

    $scope.criaPessoa = function(pessoa) {
        $loader.postPessoa(pessoa).then(function(pess) {
            // $scope.pessoa = pess;
            $state.go('login');
        });
    }
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

    function getPessoa() {
        return $q(function (resolve, reject) {
            $http.get('/comercio/pessoa_json').then(function (response) {
                resolve(response.data);
            });
        });
    }

    function calcFrete() {
        // gospe um número aleatório até resolver o problema de CORS
        return $q(function (resolve, reject) {
            resolve(Math.floor((Math.random() * 51)));
        });
    }

    function loadCarrinho() {
        return $q(function (resolve, reject) {
            $http.get('/comercio/carrinho_json').then(function (response) {
                resolve(response.data);
            });
        });
    }

    function postCarrinho(carrinho) {
        return $q(function (resolve, reject) {
            $http.post('/comercio/carrinho_json/add', carrinho).then(function (response) {
                resolve(response.data);
            });
        });
    }

    function postFinaliza(venda) {
        return $q(function (resolve, reject) {
            $http.post('/comercio/venda_json/add', venda).then(function (response) {
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

    return { loadProdutos, getPessoa, postPessoa, calcFrete, loadCarrinho, postCarrinho, postFinaliza };
});
