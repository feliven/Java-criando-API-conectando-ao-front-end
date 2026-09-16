import getDados from "./getDados.js";

// Mapeia os elementos DOM que você deseja atualizar
const elementos = {
  top5: document.querySelector('[data-name="top5"]'),
  lancamentos: document.querySelector('[data-name="lancamentos"]'),
  series: document.querySelector('[data-name="series"]'),
};

// Função para criar a lista de séries
function criarListaSeries(elemento, dados) {
  // Verifique se há um elemento <ul> dentro da seção
  const ulExistente = elemento.querySelector("ul");

  // Se um elemento <ul> já existe dentro da seção, remova-o
  if (ulExistente) {
    elemento.removeChild(ulExistente);
  }

  const ul = document.createElement("ul");
  ul.className = "lista";
  const listaHTML = dados
    .map(
      (serie) => `
        <li>
            <a href="detalhes.html?id=${serie.id}">
                <img src="${serie.poster}" alt="${serie.titulo}">
            </a>
        </li>
    `,
    )
    .join("");

  ul.innerHTML = listaHTML;
  elemento.appendChild(ul);
}

const categoriaSelect = document.querySelector("[data-categorias]");
const sectionsParaOcultar = document.querySelectorAll(".section"); // Adicione a classe CSS 'hide-when-filtered' às seções e títulos que deseja ocultar.

categoriaSelect.addEventListener("change", function () {
  const categoria = document.querySelector('[data-name="categoria"]');
  const categoriaSelecionada = categoriaSelect.value;

  if (categoriaSelecionada === "todos") {
    for (const section of sectionsParaOcultar) {
      section.classList.remove("hidden");
    }
    categoria.classList.add("hidden");
  } else {
    for (const section of sectionsParaOcultar) {
      section.classList.add("hidden");
    }

    categoria.classList.remove("hidden");
    // Faça uma solicitação para o endpoint com a categoria selecionada
    getDados(`/series/categoria/${categoriaSelecionada}`)
      .then((data) => {
        criarListaSeries(categoria, data);
      })
      .catch((error) => {
        console.error("Ocorreu um erro ao carregar os dados da categoria.");
      });
  }
});

// Array de URLs para as solicitações
geraSeries();
function geraSeries() {
  const urls = ["/series/top5", "/series/lancamentos", "/series"];

  // Faz todas as solicitações em paralelo
  Promise.all(urls.map((url) => getDados(url)))
    .then((data) => {
      criarListaSeries(elementos.top5, data[0]);
      criarListaSeries(elementos.lancamentos, data[1]);
      criarListaSeries(elementos.series, data[2].slice(0, 5));
    })
    .catch((error) => {
      console.error("Ocorreu um erro ao carregar os dados.");
    });
}
