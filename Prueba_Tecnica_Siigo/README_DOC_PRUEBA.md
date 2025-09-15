Este documento resume el diseño de pruebas funcionales para el flujo de **creación y mantenimiento de Clientes/Terceros**.

## 1) Objetivo
Validar que el formulario y las reglas de negocio para **Clientes/Terceros** cumplan con los requisitos: captura y validaciones de datos, dependencias de campos, transiciones de estado y restricciones (duplicados, inactividad, etc.).

## 2) Diseño de pruebas (Clientes/Terceros)

### 2.1 Partición de equivalencias

| Campo                 | Clases válidas                                      | Clases inválidas                                          | Resultado esperado                                                                 |
|----------------------|------------------------------------------------------|-----------------------------------------------------------|------------------------------------------------------------------------------------|
| Identificación / NIT | Numérico **5–15** dígitos; **único**                 | Longitud **<5** o **>15**; **no numérico**; **duplicado** | Acepta válidos; bloquea inválidos; **mensaje claro** y no permite guardar         |
| Email                | Formato RFC básico (por ej. `a@b.co`)                | Sin `@`; dominio inválido; espacios                       | Valida formato; evita guardar inválidos                                           |
| Tipo de cliente      | *Persona Natural* / *Persona Jurídica*               | Valor fuera de catálogo                                   | Despliegue correcto de **campos dependientes**                                    |
| Teléfono             | **7–15** dígitos                                     | **<7**, **>15**, letras                                   | Normaliza (si aplica) y valida                                                     |

### 2.2 Valores límite (ejemplos)

- **NIT o CC**: **4** (inválido), **5** (válido), **15** (válido), **16** (inválido).
- **Nombre / Razón social**: **0** (inválido), **1** (válido), **100** (válido), **101** (inválido).
- **Teléfono**: **6** (inválido), **7** (válido), **15** (válido), **16** (inválido).

### 2.3 Tabla de decisión (dependencias de campos)

| Tipo     | ¿DV requerido? | Campos obligatorios                                       | Observaciones                                                                 |
|----------|-----------------|-----------------------------------------------------------|--------------------------------------------------------------------------------|
| Natural  | No              | Nombre(s), Apellido(s), Identificación, Email            | Si **Responsable de IVA = Sí** → exigir **Régimen**                           |
| Jurídica | Sí              | Razón social, NIT, **DV**, Email, Representante          | Si **Gran contribuyente = Sí** → exigir **Resolución**                         |

### 2.4 Transición de estados (modelo)

**Estados**: `Borrador → Guardado → Editado → Inactivo (o Eliminado)`

**Transiciones clave**
- **Borrador → Guardado**: al cumplir validaciones y presionar *Guardar*.
- **Guardado → Editado**: al modificar campos y re‑guardar (debe quedar auditoría).
- **Guardado → Inactivo**: acción administrativa; no debe permitir **facturar**.

**Reglas**
- No permitir **duplicados** en **Guardado**.
- **Inactivo**: bloquear acciones operativas (p. ej., facturación).

---

## 3) Casos de prueba representativos (muestras)

> Nota: los IDs son orientativos; pueden parametrizarse en el *runner* o *dataset* de automatización.

### 3.1 Equivalencias y límites
1. **NIT válido** (5 dígitos) → guarda OK.
2. **NIT 4 dígitos** → mensaje de longitud mínima, no guarda.
3. **NIT 16 dígitos** → mensaje de longitud máxima, no guarda.
4. **NIT con letras** → mensaje de solo numérico, no guarda.
5. **NIT duplicado** (existente) → mensaje de duplicado, no guarda.
6. **Email válido** (`qa@empresa.co`) → guarda OK.
7. **Email sin @** → mensaje de formato inválido, no guarda.
8. **Teléfono 6 dígitos** → inválido.
9. **Teléfono 7 dígitos** → válido.
10. **Nombre 0/101 chars** → inválido; **1/100 chars** → válido.

### 3.2 Dependencias (tabla de decisión)
11. **Tipo = Natural** → NO pide DV; exige Nombres/Apellidos/Identificación/Email.
12. **Tipo = Jurídica** → PIDE DV; exige Razón social/NIT/DV/Email/Representante.
13. **Responsable de IVA = Sí** → **Régimen** obligatorio.
14. **Gran contribuyente = Sí** → **Resolución** obligatoria.

### 3.3 Flujo y estados
15. Crear **Borrador** incompleto → no guarda.
16. Completar y **Guardar** → estado Guardado, no duplicado.
17. **Editar** registro guardado y re‑guardar → registrado en auditoría.
18. Cambiar a **Inactivo** → bloquear facturación u operaciones dependientes.

---

## 4) Datos de prueba sugeridos (muestra)

| Caso | Tipo    | NIT      | DV | Nombre/Razón social     | Email               | Teléfono  | Extra                         |
|------|---------|----------|----|-------------------------|---------------------|-----------|-------------------------------|
| TC01 | Jurídica| 90012345 | 6  | ACME S.A.S.             | contacto@acme.co    | 6011234567| Gran contribuyente = No       |
| TC02 | Natural | 10203040 | —  | Ana María / Pérez López | ana.perez@demo.co   | 3158881122| Responsable IVA = Sí; Régimen=Común |
| TC03 | Jurídica| 1234     | 0  | Prueba Inválida         | invalido@correo     | 123       | Espera: errores por límites   |

---

## 5) Estrategia de automatización (resumen)

- **Stack**: Serenity BDD + Cucumber + Selenium WebDriver.
- **Shadow DOM**: localización por **host** (`siigo-textfield-web`, `siigo-dropdownlist-web`) + acceso a `shadowRoot` y búsqueda de elementos internos (`.mdc-floating-label`, `input.mdc-text-field__input`, `.mdc-select__anchor`).
- **Buenas prácticas**:
    - Utilidades de lookup **por label** (reutilizables) para campos *shadow*.
    - **Particiones y límites** como *datasets* parametrizados.
    - Hooks de **limpieza** y **pre-condiciones** (ej.: borrar cliente si quedó en borrador).
    - Evidencias: capturas y reportes de Serenity.

