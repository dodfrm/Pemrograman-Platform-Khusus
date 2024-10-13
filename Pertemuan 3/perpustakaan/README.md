
## Contoh SOAP Request

url = http://localhost:8080/ws/books.wsdl

### 1. Menambahkan Buku

Gunakan request berikut untuk menambahkan buku ke layanan SOAP:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gen="http://polstat.com/perpustakaan/gen">
   <soapenv:Header/>
   <soapenv:Body>
      <gen:AddBookRequest>
         <gen:title>Effective Java</gen:title>
         <gen:author>Joshua Bloch</gen:author>
         <gen:description>A guide to best practices in Java programming.</gen:description>
      </gen:AddBookRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
### 2. Mendapatkan Semua Buku

Gunakan request berikut untuk mendapatkan semua buku ke layanan SOAP:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gen="http://polstat.com/perpustakaan/gen">
   <soapenv:Header/>
   <soapenv:Body>
      <gen:GetAllBookRequest/>
   </soapenv:Body>
</soapenv:Envelope>
```
