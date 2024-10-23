## GraphQL

url = http://localhost:8080/graphiql?path=/graphql

### 1. Menambahkan Buku

```graphql
mutation Book {
  createBook(
    title: "Spring GraphQL"
    description: "This is spring graphql"
    author: "Dodi"
  ) {
    id
    title
    description
    author
  }
}
```

### 2. Menampilkan semua koleksi buku

```graphql
query {
  books {
    id
    title
    description
    author
  }
}
```

### 3. Menampilkan koleksi buku berdasarkan ID

```graphql
query {
  bookById(id: "12") {
    title
    author
  }
}
```

### 4. Mengupdate buku

```graphql
mutation Book {
  updateBook(
    id: "1"
    title: "Spring GraphQL"
    author: "Dodi"
    description: "Update Book"
  ) {
    id
    title
    author
    description
  }
}
```
