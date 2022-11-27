
# **DS Catalog**
[![npm](https://img.shields.io/npm/l/react)](https://github.com/DavidMoraes-DEV/DSCatalog/blob/master/LICENCE)

## Descrição
DS Catalog é uma aplicação full stack e mobile desenvolvida no curso BootCamp Spring React da DevSuperior.
O objetivo do projeto foi apresentar e praticar os conceitos e as ferramentas relacionadas com o
desenvolvimento de um catálogo de produtos. Disponibiliza uma área administrativa completa para adicionar,
alterar e remover os recursos com a aplicação do controle de acesso pelo perfil do usuário cadastrado no
sistema.

####  Modelo Conceitual
![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)

## Layout
![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)

####  Design Completo:
[![npm](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)](https://www.figma.com/file/1n0aifcfatWv9ozp16XCrq/DSCatalog-Bootcamp)



## Stack utilizada

**Front-end:** Typescript, React, React Router, React Hook, React Hook Form, React Select, React Paginate, React Toastify, Axios, JWT Decode, NodeJS, QS, Jest.

**Back-end:** Java, Spring Boot, Spring Data JPA, Spring Security, Maven, Oauth2, JWT, Jakarta ou Beans Validation, JUnit5

**Database:** MySQL, H2 Database (Profile: test), Postresql (Profile: dev, prod)


## Projeto Online

[![npm](https://img.shields.io/badge/Abrir_Projeto-000000?style=for-the-badge&logo=SoundCloud&logoColor=white)](https://dm-developer-ds-catalog.netlify.app)

Para acessar a área administrativa dos produtos e usuários contidos no projeto
é necessário efetuar o login no sistema. Clicando em **ADMIN** ou **LOGIN** na
barra de navegação.

### Usuário com Perfil Operador:
Acesso liberado apenas para a área administrativa dos recursos: Produtos, Categorias.

##### **Usuário:**
```bash
david@dmdeveloper.com
```
##### **Senha:**
```bash
123456
```

### Usuário com Perfil Admin:
Acesso liberado para a área administrativa de todos os recursos: Produtos, Categorias e os Usuários.

##### **Usuário:**
```bash
admin@dmdeveloper.com
```
##### **Senha:**
```bash
123456
```
## Rodando Localmente

Clone o projeto:

```bash
git clone git@github.com:DavidMoraes-DEV/DSCatalog.git
```

Entre no diretório do projeto:

```bash
cd DSCatalog
```

### Rodar a API:
Importe o projeto pelo Spring Tools Suite:

```bash
File -> import -> Maven -> Existing Maven Projects -> Next -> Browse...
-> Localizar e abrir a pasta DSCatalog 
-> Abrir a pasta backend -> Selecionar pasta
-> Selecionar: /pom.xml -> Finish
```
Rodar o projeto no Spring Tools Suite:

```bash
Botão direito do mouse na pasta raiz do projeto -> Run As... -> Spring Boot App
```

### Rodar o frontend:
No diretório do projeto navegue até a pasta do frontend:

```bash
cd frontend/
```
Opcional: Abrir o projeto no VS Code pelo terminal:
```bash
code .
```
Instalar as dependências: 
```bash
yarn
```
Rodar o projeto pelo terminal do git:
```bash
yarn start
```

### Teste as requisições da API local no Postman:
[![Run in Postman](https://run.pstmn.io/button.svg)](https://god.gw.postman.com/run-collection/13224574-a4d50c74-3992-48cf-82ee-a9949fc6c0a8?action=collection%2Ffork&collection-url=entityId%3D13224574-a4d50c74-3992-48cf-82ee-a9949fc6c0a8%26entityType%3Dcollection%26workspaceId%3D12264e20-d846-461a-9328-4f3aea62b603#?env%5Bdscatalog-bootcamp-local%5D=W3sia2V5IjoiaG9zdCIsInZhbHVlIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwIiwiZW5hYmxlZCI6dHJ1ZSwidHlwZSI6ImRlZmF1bHQifSx7ImtleSI6ImNsaWVudC1pZCIsInZhbHVlIjoiZHNjYXRhbG9nIiwiZW5hYmxlZCI6dHJ1ZSwidHlwZSI6ImRlZmF1bHQifSx7ImtleSI6ImNsaWVudC1zZWNyZXQiLCJ2YWx1ZSI6ImRzY2F0YWxvZzEyMzQiLCJlbmFibGVkIjp0cnVlLCJ0eXBlIjoiZGVmYXVsdCJ9LHsia2V5IjoidXNlcm5hbWUiLCJ2YWx1ZSI6ImRhdmlkQGRtZGV2ZWxvcGVyLmNvbSIsImVuYWJsZWQiOnRydWUsInR5cGUiOiJkZWZhdWx0In0seyJrZXkiOiJwYXNzd29yZCIsInZhbHVlIjoiMTIzNDU2IiwiZW5hYmxlZCI6dHJ1ZSwidHlwZSI6ImRlZmF1bHQifSx7ImtleSI6InRva2VuIiwidmFsdWUiOiIiLCJlbmFibGVkIjp0cnVlLCJ0eXBlIjoiZGVmYXVsdCJ9XQ==)
