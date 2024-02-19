<template>
  <div>
    <b-container>
      <b-row class="mb-3">
        <b-col cols="10">
          <h1>Libros</h1>
        </b-col>
        <b-col cols="2">
          <b-button variant="primary" v-b-modal.modal-center style="width: 100%"
            >Crear</b-button
          >
        </b-col>
      </b-row>
      <b-row class="mb-5">
        <h5>Filtro de búsqueda</h5>
        <b-col cols="12" class="mb-3">
          <label for="example-datepicker">Nombre</label>
          <b-form-input id="search" v-model="searchName"></b-form-input>
        </b-col>
        <b-col cols="12" class="mb-3">
          <label for="example-datepicker">Autor</label>
          <b-form-input id="search" v-model="searchAuthor"></b-form-input>
        </b-col>

        <b-col class="mb-3">
          <label for="example-datepicker">Fecha de inicio</label>
          <b-form-datepicker
            id="example-datepicker"
            v-model="searchDateInit"
            class="mb-2"
          ></b-form-datepicker>
        </b-col>
        <b-col class="mb-3">
          <label for="example-datepicker">Fecha de fin</label>
          <b-form-datepicker
            id="example-datepicker"
            v-model="searchDateEnd"
            class="mb-2"
          ></b-form-datepicker>
        </b-col>
        <b-col cols="12" class="mb-3">
          <label for="example-datepicker">Genero</label>
          <b-form-input id="search" v-model="searchGenre"></b-form-input>
        </b-col>
        <b-col cols="12" class="mb-3">
          <b-button variant="primary" style="width: 100%" @click="search()"
            >Buscar</b-button
          >
        </b-col>
      </b-row>
    </b-container>
    <b-modal
      id="modal-center"
      hide-footer
      :title="editing ? 'Editar libro' : 'Crear libro'"
    >
      <div>
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
          <b-row>
            <b-form-group id="input-group-name" label="Nombre" label-for="name">
              <b-form-input
                id="name"
                v-model="form.name"
                type="text"
                required
              ></b-form-input>
            </b-form-group>
          </b-row>
          <b-row class="mt-3">
            <b-form-group
              id="input-group-author"
              label="Autor"
              label-for="author"
            >
              <b-form-input
                id="author"
                v-model="form.author"
                type="text"
                required
              ></b-form-input>
            </b-form-group>
          </b-row>
          <b-row class="mt-3">
            <b-form-group
              id="input-group-genre"
              label="Genero"
              label-for="genre"
            >
              <b-form-input
                id="genre"
                v-model="form.genre"
                type="text"
                required
              ></b-form-input>
            </b-form-group>
          </b-row>
          <b-row class="mt-3">
            <b-form-group id="input-group-year" label="Año" label-for="year">
              <b-form-input
                id="year"
                v-model="form.year"
                type="date"
                required
              ></b-form-input>
            </b-form-group>
          </b-row>
          <div class="d-flex justify-content-end mt-3">
            <b-button class="me-1" type="reset" variant="danger"
              >Limpiar</b-button
            >
            <b-button type="submit" variant="primary">Aceptar</b-button>
          </div>
        </b-form>
      </div>
    </b-modal>
    <div v-if="books.length === 0" class="m-5">
      <p>No se encontraron libros.</p>
    </div>
    <div v-else>
      <b-container>
        <b-row>
          <b-col
            md="4"
            sm="6"
            cols="12"
            v-for="(book, index) in books"
            :key="index"
            class="mb-4"
          >
            <b-card
              :title="book.name"
              style="background-color: #fcfcfc"
              class="mb-2"
            >
              <b-card-text>
                <b>Autor:</b> {{ book.author }}
                <br />
                <b>Genero:</b> {{ book.genre }}
                <br />
                <b>Año de publicación:</b> {{ book.year }}
              </b-card-text>
              <div class="d-flex justify-content-end">
                <b-button
                  href="#"
                  variant="warning"
                  class="me-2"
                  @click="onEdit(book)"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    style="
                      fill: rgba(255, 255, 255, 1);
                      transform: ;
                      msfilter: ;
                    "
                  >
                    <path
                      d="M19.045 7.401c.378-.378.586-.88.586-1.414s-.208-1.036-.586-1.414l-1.586-1.586c-.378-.378-.88-.586-1.414-.586s-1.036.208-1.413.585L4 13.585V18h4.413L19.045 7.401zm-3-3 1.587 1.585-1.59 1.584-1.586-1.585 1.589-1.584zM6 16v-1.585l7.04-7.018 1.586 1.586L7.587 16H6zm-2 4h16v2H4z"
                    ></path>
                  </svg>
                </b-button>
                <b-button
                  href="#"
                  variant="danger"
                  class="ml-2"
                  @click="onDelete(book.id)"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    style="
                      fill: rgba(255, 255, 255, 1);
                      transform: ;
                      msfilter: ;
                    "
                  >
                    <path
                      d="M5 20a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V8h2V6h-4V4a2 2 0 0 0-2-2H9a2 2 0 0 0-2 2v2H3v2h2zM9 4h6v2H9zM8 8h9v12H7V8z"
                    ></path>
                    <path d="M9 10h2v8H9zm4 0h2v8h-2z"></path>
                  </svg>
                </b-button>
              </div>
            </b-card>
          </b-col>
        </b-row>
      </b-container>
    </div>
  </div>
</template>

<script>
import moment from "moment";
export default {
  data() {
    return {
      form: {
        name: "",
        author: "",
        genre: "",
        year: null,
      },
      searchName: "",
      searchAuthor: "",
      searchGenre: "",
      searchDateInit: "",
      searchDateEnd: "",
      books: [],
      show: true,
      editing: false,
    };
  },
  mounted() {
    this.findAll();
  },
  methods: {
    search() {
      let dateInit = "";
      let dateEnd = "";
      if (this.searchDateInit && this.searchDateEnd) {
        dateInit = moment(this.searchDateInit)
          .format("DD-MM-YYYY")
          .replace(/-/g, "");
        dateEnd = moment(this.searchDateEnd)
          .format("DD-MM-YYYY")
          .replace(/-/g, "");
      }
      fetch(
        `http://localhost:8080/api/book/query?name=${this.searchName}&author=${this.searchAuthor}&genre=${this.searchGenre}&startYear=${dateInit}&endYear=${dateEnd}`
      )
        .then((response) => {
          if (!response.ok) {
            throw new Error("Error al obtener los libros");
          }
          0;
          return response.json();
        })
        .then((data) => {
          data.data.forEach((book) => {
            book.year = new Date(book.year).toLocaleDateString();
          });
          this.books = data.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    onSubmit(event) {
      event.preventDefault();
      if (!this.validateForm()) {
        return;
      }
      if (this.editing) {
        this.updateBook();
      } else {
        this.createBook();
      }
    },
    validateForm() {
      if (this.form.year < 1000 || this.form.year > new Date().getFullYear()) {
        alert("Ingresa un año válido");
        return false;
      }

      return true;
    },
    onReset(event) {
      event.preventDefault();
      this.form.name = "";
      this.form.author = "";
      this.form.genre = "";
      this.form.year = null;
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    findAll() {
      fetch("http://localhost:8080/api/book/")
        .then((response) => {
          if (!response.ok) {
            throw new Error("Error al obtener los libros");
          }
          return response.json();
        })
        .then((data) => {
          data.data.forEach((book) => {
            book.year = moment(book.year).format("DD-MM-YYYY");
          });
          this.books = data.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    createBook() {
      fetch("http://localhost:8080/api/book/", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.form),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Error al crear el libro");
          }
          this.findAll();
          return response.json();
        })
        .then((data) => {
          if (!data.data.error) {
            alert("Libro creado");
            this.form.name = "";
            this.form.author = "";
            this.form.genre = "";
            this.form.year = null;
          } else {
            console.error(data.data.message);
          }
          this.$bvModal.hide("modal-center");
        })
        .catch((error) => {
          console.error("Error al crear el libro:", error);
        });
    },
    updateBook() {
      fetch(`http://localhost:8080/api/book/`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: this.form.id,
          name: this.form.name,
          author: this.form.author,
          genre: this.form.genre,
          year: this.form.year,
        }),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Error al actualizar el libro");
          }
          this.findAll();
          return response.json();
        })
        .then((data) => {
          if (!data.data.error) {
            alert("Libro actualizado");
          } else {
            console.error(data.data.message);
          }
          this.$bvModal.hide("modal-center");
        })
        .catch((error) => {
          console.error(error);
        });
    },
    onDelete(bookId) {
      fetch(`http://localhost:8080/api/book/${bookId}`, {
        method: "DELETE",
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Error al eliminar el libro");
          }
          this.findAll();
          return response.json();
        })
        .then((data) => {
          if (!data.data.error) {
            alert("Libro eliminado");
          } else {
            console.error(data.data.message);
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    onEdit(book) {
      this.form.id = book.id;
      this.form.name = book.name;
      this.form.author = book.author;
      this.form.genre = book.genre;
      this.form.year = book.year;
      this.editing = true;
      this.$bvModal.show("modal-center");
    },
  },
};
</script>

<style>
</style>