
## Contoh Request

url = http://localhost:8080/jsonrpc

### 1. Menambahkan Buku

Gunakan request berikut untuk menambahkan buku:

```json
{
  "jsonrpc": "2.0",
  "method": "createBook",
  "params":
    {
      "title":"Belajar Web Service",
      "author":"Budi",
      "description":"Ini adalah buku web service terbaik"
    },
  "id": "1"
}
```
### 2. Mendapatkan Semua Buku

Gunakan request berikut untuk mendapatkan semua buku:

```json
{
  "jsonrpc": "2.0",
  "method": "getBooks",
  "params": {},
  "id": "2"
}
```

### 3. Mencari Buku

Gunakan request berikut untuk mencari buku berdasarkan author dan judul buku:

```json
{
  "jsonrpc": "2.0",
  "method": "searchBooks",
  "params": {
    "keyword": "harry"  
  },
  "id": "2"
}
```
