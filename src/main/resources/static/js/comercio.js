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

corcovado.config(function($stateProvider, $urlRouterProvider, $httpProvider) {
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
    });

    $stateProvider.state('cadastro', {
        url: '/cadastro',
        templateUrl: 'comercio/cadastro',
        controller: 'cadastro'
    });

    $stateProvider.state('finaliza', {
        url: '/finaliza',
        templateUrl: 'comercio/finaliza',
        controller: 'finaliza'
    });

    /*
    $stateProvider.state('recibo', {
        url: '/recibo',
        templateUrl: 'comercio/recibo2',
        controller: 'recibo'
    });
    */

    $urlRouterProvider.otherwise('/produtos');
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});

corcovado.controller('product', function($rootScope, $loader, $state) {
    if (!$rootScope.carrinho) {
        $loader.loadCarrinho().then(function(cart) {
            if (cart.produtoVendidos.length > 0) {
                $rootScope.carrinho = new Carrinho(cart.id, cart.produtoVendidos.map(pv => { return new ItemCarrinho(pv.produto, pv.quantidade); } ));
            } else {
                $rootScope.carrinho = new Carrinho(0, []);
            }
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

        $rootScope.saveCart = function() {
            $loader.postCarrinho($rootScope.carrinho).then((cart) => {
                $rootScope.carrinho = new Carrinho(cart.id, cart.produtoVendidos.map(pv => { return new ItemCarrinho(pv.produto, pv.quantidade); } ));
                if($rootScope.authenticated) {
                    $state.go('finaliza');
                } else {
                    $state.go('login');
                }
            });
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
    $scope.produto = $rootScope.produtos.find((p) => { return p.id == $stateParams.id });
});

corcovado.controller('finaliza', function ($scope, $rootScope, $state, $loader) {
    if (!$rootScope.authenticated || $rootScope.carrinho || $rootScope.carrinho.itens.length >= 1) {
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
                window.location.assign("/comercio/recibo/" + cart.id);
            });
        };
        
    } else {
        $state.go("list");
    }
});

/*
corcovado.controller('recibo', function ($scope, $rootScope, $loader) {
    $loader.loadCarrinho($rootScope.carrinho.vendaId).then(function(cart) {
        $scope.cart = new Carrinho(cart.id, cart.produtoVendidos.map(pv => { return new ItemCarrinho(pv.produto, pv.quantidade); } ));
        //$scope.pedido = 
        $rootScope.carrinho = undefined;
    });
});
*/


corcovado.controller('navigation', function($rootScope, $scope, $http, $state) {
    var authenticate = function(credentials, callback) {
        var headers = credentials ? { authorization : "Basic " + btoa(credentials.username + ":" + credentials.password) } : {};
        $http.get('user', {headers : headers})
            .then(function(response) {
                if (response.data.nome) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback();
            }, function(response) {
                $rootScope.authenticated = false;
                callback && callback();
            });
    };

    authenticate();
    $scope.credentials = {};
    $scope.login = function() {
        authenticate($scope.credentials, function() {
            if ($rootScope.authenticated) {
                //$location.path("/comercio");
                $state.go('list');
                $scope.error = false;
            } else {
                //$location.path("/login");
                $state.go('login');
                $scope.error = true;
            }
        });
    };
    
    $scope.logout = function() {
        $http.post('logout', {}).then(function() {
            $rootScope.authenticated = false;
            //$location.path("/comercio");
            $state.go('list');
        }, function(data) {
            $rootScope.authenticated = false;
        });
    };
});


corcovado.controller('cadastro', function ($scope, $state, $http) {
    var cadastrar = function(pessoa, callback) {
        $http.post('/comercio/pessoa_json/add', pessoa)
            .then(function (response) {
                callback && callback(response.data);
            });
    };
    
    $scope.pesosa = { enderecos: [] };
    $scope.cadastro = function() {
        cadastrar(data, function (data) {
            if (data.id && data.id > 0) {
                $state.go('login');
            } else {
                alert('Failed');
            }
        });
    };
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

    function postFinaliza(venda) {
        return $q(function (resolve, reject) {
            $http.post('/comercio/venda_json/add').then(function (response) {
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

    return { loadProdutos, getPessoa, postPessoa, calcFrete, loadCarrinho, postCarrinho, postFinaliza};
});
