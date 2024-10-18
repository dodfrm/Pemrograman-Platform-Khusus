
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
### 2. Mendapatkan Data Peminjaman Buku

Method : GET

#### a. Semua Peminjaman

url = http://localhost:8080/bookLoan

#### b. Peminjaman Berdasarkan Member

url = http://localhost:8080/bookLoan/search/findByMemberId?member_id={memberId}

#### c. Peminjaman Berdasarkan Buku

url = http://localhost:8080/bookLoan/search/findByBookId?book_id={bookId}

### 3. Mengembalikan Buku

url = http://localhost:8080/bookLoan/{loan_id} 

Method : PATCH

Gunakan request body berikut untuk mengembalikan buku:

```json
{
  "loanStatus": "sudah dikembalikan"
}
```
