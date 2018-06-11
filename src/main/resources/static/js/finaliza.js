var corcovado = angular.module('finaliza', []);

corcovado.controller('finaliza', function ($scope) {
    $scope.total = Number(document.getElementById("venda_total").value)
    $scope.frete = null;
   
    $scope.calcFrete = function(endereco) {
        $scope.enderecoId = endereco;
        //TODO get the true distance based on an external API
        $scope.frete = Math.floor(Math.random() * 31) * 3.0;
    };
    
    $scope.finalizarCompra = function() {
        if (!$scope.frete) {
            let end = document.getElementById("enderecoId");
            end.options[1].selected = true;
            $scope.calcFrete(end.options[1].value);
        }
        
        document.getElementById("form_pedido").submit();
    };
});