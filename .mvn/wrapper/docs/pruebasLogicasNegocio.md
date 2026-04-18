# Prueba (Lógica de Negocio) — Asignació de Citas Odontológicas

## 1 Requisitos antes de empezar

### 1.1 Tener la API corriendo

Inicia la aplicación (desde la raíz del proyecto):
```bash
.\mvnw.cmd spring-boot:run
```

Cuando esté arriba, abre Swagger:
- `http://localhost:8080/swagger-ui.html`
- `http://localhost:8080/api-docs`



### 1. Crear base de datos: Pacientes y Odontologos

*PACIENTES*

```json
{
    "edad": "10",
    "tieneSeguro": false,
    "nombreCompleto": {
      "nombre": "Ana",
      "apellido": "Ana"
    }
}
{
    "edad": "25",
    "tieneSeguro": true,
    "nombreCompleto": {
      "nombre": "Sofi",
      "apellido": "Sofi"
    }
}
{
    "edad": "30",
    "tieneSeguro": false,
    "nombreCompleto": {
      "nombre": "Jose",
      "apellido": "Jose"
    }
}
```

*ODONTOLOGOS*
Body:
```json
{
  "especialidad": "Endodoncia",
  "tarjetaProfesional": "T-123",
  "nombreCompleto": {
    "nombre": "Ivan",
    "apellido": "Ivan"
    }
}
{
  "especialidad": "Rehabilitación oral",
  "tarjetaProfesional": "T-789",
  "nombreCompleto": {
    "nombre": "John",
    "apellido": "Jhon"
    }
}
{
  "especialidad": "Ortodoncia",
  "tarjetaProfesional": "T-321",
  "nombreCompleto": {
    "nombre": "Maria",
    "apellido": "Maria"
    }
}
```


## 2. Validar la disponiblidad del Odontologo
Se agenda una cita con exito
```json
{
  "fechaHora": "2026-05-15T08:00:00",
  "motivo": "LIMPIEZA",
  "odontologo": {"id": 2},
  "paciente": {"id": 1},
  "nombreAcudiente": "Jorge Perez"
}
```

Se agenda otra cita en el mismo horario y con el mismo Odontologo
```json
{
  "fechaHora": "2026-05-15T08:00:00",
  "motivo": "LIMPIEZA",
  "odontologo": {"id": 2},
  "paciente": {"id": 2},
  "nombreAcudiente": "Camila Lara"
}
```


### 3. Validar acudiente para paciente menor de edad : paciente {id1} Odontologo {id1}
```json
{
  "paciente": { "id": 1 },
  "odontologo": { "id": 1 },
  "fechaHora": "2026-05-10T14:00:00",
  "nombreAcudiente": "" 
}
```


### 4. Validar el limite de citas programadas por semana (No superar 2 citas)
```json
{
  "paciente": { "id": 2 },
  "odontologo": { "id": 2 },
  "fechaHora": "2026-05-15T09:00:00"
}

{
  "paciente": { "id": 2 },
  "odontologo": { "id": 1 },
  "fechaHora": "2026-05-16T09:00:00"
}

{
  "paciente": { "id": 2 },
  "odontologo": { "id": 3 },
  "fechaHora": "2026-05-17T09:00:00"
}
```


### 5. Validar descuento en el costo de la cita
Se agenda una cita para el id:2 que tiene seguro

```json
{
  "paciente": { "id": 2 },
  "odontologo": { "id": 1 },
  "fechaHora": "2026-05-10T15:00:00", 
}
```


