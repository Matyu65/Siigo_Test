# language: es
@E2E_Siigo
Característica: Aplicativo Siigo
  Realizar una creacion de cliente exitosamente
  @TestCase1
  Esquema del escenario: Llevar a cabo la creacion de un usuario
    Dado que el usuario tiene toda la informacion que necesita
      | usuario | password | opcionPrincipal |Tip_documento |
      | <usuario> | <password> | <opcionPrincipal> |<Tip_documento> |
    Cuando realiza el login de la aplicacion
    Y selecciona la opcion del menu
    Cuando Ingresa la informacion del cliente
    Ejemplos:
    | usuario | password | opcionPrincipal | Tip_documento |
      ##@externaldata@./src/test/resources/datadriver/crearcliente/crear_cliente.xlsx@Datos
	|retoautomationsiigo@yopmail.com|T4b4ck0ff1c3P455w0rd658*|Clientes|Cédula de ciudadanía|
