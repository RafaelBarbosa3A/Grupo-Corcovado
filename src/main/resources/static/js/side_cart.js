var corcovado = angular.module('corcovado', ['ui.router']);

corcovado.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider.state('list', {
        url: '/produtos',
        templateUrl: 'comercio/list'/*,
        controller: 'list' */
    });

    $stateProvider.state('show', {
        url: '/produtos/:id',
        templateUrl: 'comercio/show',
        controller: 'show'
    });

    $stateProvider.state('cart', {
        url: '/carrinho',
        templateUrl: 'comercio/cart' 
        /*, controller: 'cart'*/
    });

    $stateProvider.state('finaliza', {
        url: '/finaliza',
        templateUrl: 'comercio/finaliza',
        controller: 'finaliza'
    });

    $urlRouterProvider.otherwise('/produtos');
});

corcovado.controller('list', function($scope, $loader, $rootScope) {
    $scope.produtos = [];
    $rootScope.carrinho = {};
    
    $loader.loadProdutos().then(function(prods) {
        $scope.produtos = prods;
    });
    
    $loader.loadCart().then(function(cart) {
        $rootScope.carrinho = cart;
    });
    
    $rootScope.addToCart = function(produto, quantidade) {
        if (quantidade >= 1 && quantidade <= (produto.estoque - produto.reservado)) {            
            $loader.addCart(produto, quantidade).then(function(cart) {
                $rootScope.carrinho = cart;
            });
        }
    };
    
    $rootScope.removeFromCart = function(produto) {
        $loader.removeCart(produto).then(function(cart) {
            $rootScope.carrinho = cart;
        });
    };

    $rootScope.editFromCart = function(produto, quantidade) {
        $loader.editCart(produto, quantidade).then(function(cart) {
            $rootScope.carrinho = cart;
        });
    };
    
    /*
    $rootScope.clearCart = function() {
        // $rootScope.cart = [];
    };
    */
    
    $rootScope.carrinhoTotalPreco = function() {
        if ($rootScope != null && $rootScope.carrinho != null && $rootScope.carrinho.produtoVendidos != null) {
            return $rootScope.carrinho.produtoVendidos.reduce((acc, pv) => {
                return acc + pv.precoTotal;
            }, 0);
        } else {
            return 0;
        }
        
       // return $rootScope.cart.reduce((acc, prod) => { return acc + prod.totalPreco(); }, 0);
    };
});

corcovado.controller('show', function ($scope, $stateParams, $loader) {
    $scope.produto = $loader.getProduto($stateParams.id);
});

corcovado.controller('finaliza', function ($scope, $loader) {
    $scope.pessoa = {};
    $scope.frete = null;
    
    $loader.getPessoa().then(function(pess) {
        $scope.pessoa = pess;
    });
    
    $scope.calcFrete = function(endereco) {
        $loader.calcFrete(endereco).then(function(dist) {
            $scope.frete = dist * 3.0;
        });
    };
    
    $scope.finalizarCompra = function() {
        if (!$scope.frete) {
            $scope.calcFrete($scope.pessoa.enderecos[0]);
        }
        
        
        var form = document.getElementById("entrega");
        form.submit();
    };
});

corcovado.factory('$loader', function ($http, $q) {
    var produtos = [];
    var carrinho = {};
    
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
    
    function getProduto(id) {
        var encontrado = null;
        for (var i = 0; i < produtos.length; i++) {
            var produto = produtos[i];
            if (produto.id == id) {
                encontrado = produto;
                break;
            }
        }
        return encontrado;
    }
    
    function addCart(produto, quantidade) {
        return $q(function (resolve, reject) {
            $http.post('/comercio/carrinho_json/add', 
                    { 'produtoId': produto.id, 'quantidade': quantidade },
                    { headers: { 'Content-Type': 'application/json' } }
                ).then(function (response) {
                    carrinho = response.data;
                    resolve(carrinho);
                });
        });
    }
        
    function removeCart(produto) {
        return $q(function (resolve, reject) {
            $http.post('/comercio/carrinho_json/remove', 
                    { "produtoId": produto.id, 'quantidade': 0 },
                    { headers: { 'Content-Type': 'application/json' } }
                ).then(function (response) {
                    carrinho = response.data;
                    resolve(carrinho);
                });
        });
    }

    function editCart(produto, quantidade) {
        return $q(function (resolve, reject) {
            $http.post('/comercio/carrinho_json/edit', 
                    { "produtoId": produto.id, 'quantidade': quantidade },
                    { headers: { 'Content-Type': 'application/json' } }
                ).then(function (response) {
                    carrinho = response.data;
                    resolve(carrinho);
                });
        });
    }
    
    function loadCart() {
        return $q(function (resolve, reject) {
            $http.get('/comercio/carrinho_json').then(function (response) {
                carrinho = response.data;
                resolve(carrinho);
            });
        });
    }
    
    function getPessoa() {
        return $q(function (resolve, reject) {
            $http.get('/comercio/pessoa_json').then(function (response) {
                resolve(response.data);
            });
        });
    }
    
    function calcFrete(endereco) {
        return $q(function (resolve, reject) {
            resolve(Math.floor((Math.random() * 51)));
            /*
            $http({
                method: 'GET',
                withCredentials: true,
                url: "http://maps.googleapis.com/maps/api/directions/json?origin=Av.+Engenheiro+Eusebio+Stevaux,+823&destination=" + endereco.rua + ",+" + endereco.numero + "&units=metric"
            }).then(function (response) {
                resolve(response.data.routes[0].legs[0].distance.value);
            });
            */
        });
    }
     
    return { loadProdutos, getProduto, addCart, removeCart, loadCart, getPessoa, calcFrete };
});
