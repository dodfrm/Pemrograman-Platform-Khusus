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

### 2. Menampilkan Semua Koleksi Buku

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

### 3. Menampilkan Koleksi Buku Berdasarkan ID

```graphql
query {
  bookById(id: "12") {
    title
    author
  }
}
```

### 4. Mengupdate Data Buku

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

### 5. Menghapus Buku

```graphql
mutation Book {
  deleteBook(id: "2") {
    id
  }
}
```

### 6. Menambahkan Member

```graphql
mutation Member {
  createMember(
    memberID: "333"
    name: "Wilfa"
    address: "Jalan Asem"
    phoneNumber: "08587234092"
  ) {
    id
    memberID
    name
    address
    phoneNumber
  }
}
```

### 7. Menampilkan Semua Member

```graphql
query Member {
  members {
    id
    memberID
    name
    address
    phoneNumber
  }
}
```

### 8. Menampilkan Member Berdasarkan ID

```graphql
query {
  memberById(id: "2") {
    memberID
    name
    address
    phoneNumber
  }
}
```

### 9. Mengubah Data Member

```graphql
mutation Member {
  updateMember(
    id: "1"
    memberID: "111"
    name: "Adit"
    address: "Belakang Kampus"
    phoneNumber: "08187342567"
  ) {
    id
    memberID
    name
    address
    phoneNumber
  }
}
```

### 10. Menghapus Member

```graphql
mutation Member {
  deleteMember(id: "2") {
    id
  }
}
```

### 10. Menampilkan Member Berdasarkan ID

```graphql
query {
  memberById(id: "1") {
    memberID
    name
    address
    phoneNumber
  }
}
```
