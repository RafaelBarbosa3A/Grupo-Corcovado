<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no text/html" http-equiv="Content-Type" charset=utf-8">

        <!-- Adicionando JQuery -->
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"
                integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>

        <script> $(document).ready(function () {

                function limpa_formulario_cep() {
                    // Limpa valores do formulário de cep.
                    $("#rua").val("");
                    $("#bairro").val("");
                    $("#cidade").val("");
                    $("#uf").val("");
                }

                //Quando o campo cep perde o foco.
                $("#cep").blur(function () {

                    //Nova variável "cep" somente com dígitos.
                    var cep = $(this).val().replace(/\D/g, '');

                    //Verifica se campo cep possui valor informado.
                    if (cep != "") {

                        //Expressão regular para validar o CEP.
                        var validacep = /^[0-9]{8}$/;

                        //Valida o formato do CEP.
                        if (validacep.test(cep)) {

                            //Preenche os campos com "..." enquanto consulta webservice.
                            $("#rua").val("...");
                            $("#bairro").val("...");
                            $("#cidade").val("...");
                            $("#uf").val("...");

                            //teste com js puro
                            /*fetch("https://viacep.com.br/ws/04696000/json/")
                             .then(function(response){
                             response.json().then(function(data){
                             });
                             })*/

                            //Consulta o webservice viacep.com.br/
                            $.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function (dados) {

                                if (!("erro" in dados)) {
                                    //Atualiza os campos com os valores da consulta.
                                    $("#rua").val(dados.logradouro);
                                    $("#bairro").val(dados.bairro);
                                    $("#cidade").val(dados.localidade);
                                    $("#uf").val(dados.uf);
                                    $("#ibge").val(dados.ibge);
                                } //end if.
                                else {
                                    //CEP pesquisado não foi encontrado.
                                    limpa_formulario_cep();
                                    alert("CEP não encontrado.");
                                }
                            });
                        } //end if.
                        else {
                            //cep é inválido.
                            limpa_formulario_cep();
                            alert("Formato de CEP inválido.");
                        }
                    } //end if.
                    else {
                        //cep sem valor, limpa formulário.
                        limpa_formulario_cep();
                    }
                });
            });
        </script>


        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


    </head>
    <body>
        <form>
            <div class="row">
                <div class="col-sm-12 col-md-6 col-lg-4">
                    <label for="inputZip">CEP</label>
                    <input type="text" class="form-control" id="cep" >
                </div>
                <div class="col-sm-12 col-md-6 col-lg-4">
                    <label for="inputAddress">Rua</label>
                    <input type="text" class="form-control" id="rua">
                </div>
                <div class="col-sm-12 col-md-6 col-lg-4">
                    <label for="AddressNumber">Numero</label>
                    <input type="text" class="form-control" id="numeroRua">
                </div>
                <div class="col-sm-12 col-md-6 col-lg-4">
                    <label for="inputCity">Cidade</label>
                    <input type="text" class="form-control" id="cidade">
                </div>
                <div class="col-sm-12 col-md-6 col-lg-4">
                    <label for="inputState">Estado</label>
                    <input id="uf" class="form-control">
                </div>
                <div class="col-sm-12 col-md-6 col-lg-4">
                    <label for="inputAddress">Bairro</label>
                    <input type="text" class="form-control" id="bairro" >
                </div>
            </div>     
        </form>



        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->

        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>