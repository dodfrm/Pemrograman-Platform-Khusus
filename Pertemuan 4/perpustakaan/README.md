
## Contoh Request

url = http://localhost:8080/bookLoan

### 1. Menambahkan Data Pinjaman Buku

Method : POST

Gunakan request body berikut untuk menambahkan data pinjaman buku:

```json
{
  "member": "members/1",
  "book": "books/2",
}
```
### 2. Mendapatkan Semua Buku

Method : GET

### 3. Mengembalikan Buku

url = http://localhost:8080/bookLoan/{loan_id} 

Method : PATCH

Gunakan request body berikut untuk mengembalikan buku:

```json
{
  "loanStatus": "sudah dikembalikan"
}
```
