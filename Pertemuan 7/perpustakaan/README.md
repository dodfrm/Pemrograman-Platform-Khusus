# JSON Web Token

### 1. Registration

Method : POST

URL : https://localhost:8080/register

```json
{
  "name": "dodi",
  "email": "dodi@gmail.com",
  "password": "123"
}
```

### 2. Login

Method : POST

url = http://localhost:8080/login

```json
{
  "email": "dodi@gmail.com",
  "password": "123"
}
```

### 3. Mengakses Data Member

Method : GET

url = http://localhost:8080/member

Data member dapat diakses pada endpoint /member. Untuk mengakses endpoint ini harus menyertakan accessToken yang telah di-generate pada endpoint /login sebelumnya. Pada tab Auth, pilih type Bearer Token. Setelah itu, masukkan accessToken pada field Token.
