const corcovado = angular.module('corcovado', ['ui.router']);

corcovado.config(($stateProvider, $urlRouterProvider) => {
    $stateProvider.state('list', {
        url: '/produtos',
        templateUrl: 'comercio/list',
        controller: 'list'
    });
    
/*
    $stateProvider.state('show', {
        url: '/produtos/:id',
        templateUrl: 'comercio/_show.html',
        controller: 'show'
    });
    
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
   
    $loader.loadProducts().then((p) => {
        $scope.produtos = p;
    });
});

corcovado.factory('$loader', function ($http, $q) {
    var produtos = [];

    return { 
        loadProdutos: function() {
            return $q((resolve, reject) => {
                $http.get('/comercio_json').then((response) => {
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
     };
});

