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
    
    $rootScope.removeFromCart = function(item) {
        $loader.removeCart(item.produto).then(function(cart) {
            $rootScope.carrinho = cart;
        });
    };

    $rootScope.editFromCart = function(produto_id, quantidade) {
        $loader.editCart(produto_id, quantidade).then(function(cart) {
            $rootScope.carrinho = cart;
        });
    };
    
    $rootScope.clearCart = function() {
        // $rootScope.cart = [];
    };
    
    $rootScope.carrinhoTotalPreco = function() {
        return 0;
       // return $rootScope.cart.reduce((acc, prod) => { return acc + prod.totalPreco(); }, 0);
    };
});

corcovado.controller('show', function ($scope, $stateParams, $loader) {
    $scope.produto = $loader.getProduto($stateParams.id);
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
    
    function addCart(produto_id, quantidade) {
        return $q(function (resolve, reject) {
            $http.post('/comercio/carrinho_json/add', 
                    { "produtoId": produto_id, 'quantidade': quantidade },
                    { headers: { 'Content-Type': 'application/json' } }
                ).then(function (response) {
                    carrinho = response.data;
                    resolve(carrinho);
                });
        });
    }
        
    function removeCart(produto_id) {
        return $q(function (resolve, reject) {
            $http.post('/comercio/carrinho_json/remove', 
                    { "produtoId": produto_id, 'quantidade': 0 },
                    { headers: { 'Content-Type': 'application/json' } }
                ).then(function (response) {
                    carrinho = response.data;
                    resolve(carrinho);
                });
        });
    }

    function editCart(produto_id, quantidade) {
        return $q(function (resolve, reject) {
            $http.post('/comercio/carrinho_json/edit', 
                    { "produtoId": produto_id, 'quantidade': quantidade },
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
     
    return { loadProdutos, getProduto, addCart, removeCart, loadCart };
});
