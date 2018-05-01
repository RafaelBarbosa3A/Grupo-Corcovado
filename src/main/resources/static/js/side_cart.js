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
    $rootScope.cart = [];
    
    $loader.loadProdutos().then(function(prods) {
        $scope.produtos = prods;
    });
    
    $rootScope.addToCart = function(produto, quantidade) {
        if (quantidade >= 1 && quantidade <= (produto.estoque - produto.reservado)) {
            let found = $rootScope.cart.find((p) => { return p.produto === produto; });
            if(found) {
                found.quantidade += quantidade;
            } else {
                $rootScope.cart.push({
                    produto: produto,
                    quantidade: quantidade,
                    totalPreco: function() { return this.quantidade * this.produto.precos[0].preco; }
                });
            }
        }
    };
    
    $rootScope.removeFromCart = function(item) {
        $rootScope.cart = $rootScope.cart.filter(e => e !== item);
    };
    
    $rootScope.clearCart = function() {
        $rootScope.cart = [];
    };
    
    $rootScope.carrinhoTotalPreco = function() {
       return $rootScope.cart.reduce((acc, prod) => { return acc + prod.totalPreco(); }, 0);
    };
});

corcovado.controller('show', function ($scope, $stateParams, $loader) {
    $scope.produto = $loader.getProduto($stateParams.id);
});

corcovado.factory('$loader', function ($http, $q) {
    var produtos = [];
    var carrinho = [];
    
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
    
    function loadCart() {
        return $q(function (resolve, reject) {
            if (produtos.length === 0) {
                $http.get('/carrinho_json').then(function (response) {
                    carrinho = response.data;
                    resolve(carrinho);
                });
            } else {
                resolve(carrinho);
            }
        });
    }
     
    return { loadProdutos, getProduto, loadCart };
});
