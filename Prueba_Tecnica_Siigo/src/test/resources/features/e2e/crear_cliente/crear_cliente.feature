# language: es
@E2E_Capa
Característica: Aplicativo Siigo
  Realizar una creacion de cliente exitosamente
  @TestCase1
  Esquema del escenario: Llevar a cabo la creacion de un usuario
    Dado que el usuario tiene toda la informacion que necesita
      | usuario | password |
      | <usuario> | <password> |
    Cuando realiza el login de la aplicacion

    Ejemplos:
    | usuario | password |
      ##@externaldata@./src/test/resources/datadriver/crearcliente/crear_cliente.xlsx@Datos
	|retoautomationsiigo@yopmail.com|T4b4ck0ff1c3P455w0rd658*|
