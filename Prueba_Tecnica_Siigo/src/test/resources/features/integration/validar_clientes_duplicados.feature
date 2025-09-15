# language: es
@integration_Siigo
Característica: Validar el manejo de clientes duplicados en el sistema
  validar manejo de clientes duplicados
  @TestCase1
  Esquema del escenario: Intento crear cliente con CC existente retorna 409
    Dado un cliente con cc "<CC>" y email "<Email>"
    Cuando solicito crear el cliente
    Entonces la API responde 409
    Y se valida que no exista duplicado mensaje "El cliente ya existe"
    Ejemplos:
      | CC          | Email |
      | 1234          | c001@empresa.com  |
      | 12345         | c002@empresa.com  |
      | 123456789012345 | c003@empresa.com  |
      | 1234567890123456 | c004@empresa.com|