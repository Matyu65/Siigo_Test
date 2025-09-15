# language: es
@integration_Siigo
Característica: Creación de cliente en el aplicativo
  Validar la creacion exitosa
  @TestCase1
  Esquema del escenario: Crear cliente nuevo retorna 200 y persiste
    Dado un cliente con cc "<CC>" y email "<Email>"
    Cuando solicito crear el cliente
    Entonces la API responde 201
    Y el cliente se valida en el sistema
    Ejemplos:
      | CC          | Email |
      | 1234          | c001@empresa.com  |
      | 12345         | c002@empresa.com  |
      | 123456789012345 | c003@empresa.com  |
      | 1234567890123456 | c004@empresa.com|
