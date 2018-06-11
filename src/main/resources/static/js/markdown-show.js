/*
  Renderiza o texto em outra tag, escondendo a primeira.
  Criar uma tag com o atributo class="to_render-md" e um div com id="rendered-md".
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

let to_render = document.querySelector(".to_render-markdown")
to_render.classList.add("d-none")
document.querySelector("#rendered-markdown").innerHTML = md.render(to_render.textContent)
