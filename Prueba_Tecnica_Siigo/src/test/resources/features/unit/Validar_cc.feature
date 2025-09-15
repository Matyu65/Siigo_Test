# language: es
@Unit_Siigo
Característica: Validar Límite de longitud de CC
  Límite de longitud de CC
  @TestCase1
  Esquema del escenario: Validar longitudes mínimas y máximas
    Dado Una CC para validacion "<CC>"
    Cuando valido la longitud de la cc
    Entonces el resultado debe ser "<resultado>"
    Ejemplos:
      | CC            | resultado |
      | 1234          | invalido  |
      | 12345         | valido    |
      | 1234567890123 | valido  |
      | 098SSS7890123 | invalido|