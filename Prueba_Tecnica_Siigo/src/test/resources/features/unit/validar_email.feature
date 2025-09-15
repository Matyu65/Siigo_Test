# language: es
  @Unit_Siigo
  Característica: Validación de email
  Aceptar formatos válidos y rechazar inválidos
  @TestCase1
  Esquema del escenario: Validar el correo electroinico ingresado
    Dado un email "<valor>"
    Cuando valido el formato del email
    Entonces el resultado debe ser "<esperado>"
    Ejemplos:
      | valor                | esperado |
      | tester@siigo.com     | valido   |
      | a@b.co               | valido   |
      | sin-arroba.com       | invalido |
      | user@dominio         | invalido |
