/*
  Ao clicar no botão (ou link) o preview é renderizado dentro da div (substituindo seu conteúdo) de preview.
  Criar um botão ou link com o atributo class="button-md", input com class="textarea-md" e um div com id="preview-md".
*/

const md = window.markdownit("default", {
  html: true,
  breaks: true,
  linkify: true,
  typographer: true
})

md.renderer.rules.table_open = (tokens, idx) => {
  return '<table class="table table-bordered table-striped">'
}

md.renderer.rules.blockquote_open = (tokens, idx) => {
  return '<blockquote class="blockquote mb-2">'
}

document.querySelector(".button-markdown").addEventListener("click", ev => {
  ev.preventDefault();
  document.querySelector("#preview-markdown").innerHTML = md.render(document.querySelector(".textarea-markdown").value);
  $('#descricaoModal').modal('toggle');
  //document.querySelector("#descricaoModal").modal('toggle');
})
