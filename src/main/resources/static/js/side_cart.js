const corcovado = angular.module('corcovado', ['ui.router']);

corcovado.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider.state('list', {
        url: '/produtos',
        templateUrl: 'comercio/list',
        controller: 'list'
    });

    $stateProvider.state('show', {
        url: '/produtos/:id',
        templateUrl: 'comercio/show',
        controller: 'show'
    });
/*
    $stateProvider.state('cart', {
        url: '/carrinho',
        templateUrl: 'comercio/_cart.html',
        controller: 'cart'
    });
*/
    $urlRouterProvider.otherwise('/produtos');
});

corcovado.controller('list', function($scope, $loader) {
    $scope.produtos = [];

    /*
    console.log($loader.batata);
    
    $loader.loadProducts.then(function(prods) {
        $scope.produtos = prods;
    });
    */
});

corcovado.factory('$loader', function ($http, $q) {
    var produtos = [];

    return {
        produtos: produtos,
        batata: "batata"
        /*
        loadProdutos: function() {
            return $q(function(resolve, reject) {
                $http.get('/comercio_json')
                .then(function(response) {
                    produtos = response.data;
                    resolve(produtos);
                });
            });
        },
        getProduto: function(id) {
            var encontrado = null;
            for (var i = 0; i < produtos.length; i++) {
                var produto = produtos[i];
                if (produto.id === id) {
                    encontrado = produto;
                    break;
                }
            }
            return encontrado;
        }
        */
     };
});
