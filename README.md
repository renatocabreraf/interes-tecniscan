# Interés App: Cálculo de Interés Compuesto y Simple

Este proyecto es una aplicación web completa desarrollada como parte de la "Prueba Técnica II - Ingeniero de Sistemas de Desarrollo Web". Consta de un backend en **Kotlin con Spring Boot** y un frontend en **Angular**, y permite calcular y comparar el crecimiento del capital usando interés compuesto e interés simple.

---

## Tecnologías Usadas

### Backend
- Kotlin 1.9+
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- Base de Datos: H2 (opcional PostgreSQL)
- JUnit 5 + Mockito

### Frontend
- Angular 17+
- Angular Reactive Forms
- Angular Material
- ngx-charts

---

## Requisitos del Sistema
- JDK 17 o superior
- Node.js 18+ y npm
- Angular CLI
- IDE recomendado: IntelliJ IDEA y VSCode

---

## Estructura del Proyecto

```
interes-app/
├── backend/
│   ├── src/main/kotlin/
│   │   └── com/interes/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── model/
│   │       └── repository/
│   └── resources/
├── frontend/
│   ├── src/app/
│   │   ├── components/
│   │   ├── services/
│   │   ├── models/
│   │   └── app.component.ts
```

---

## Instalación y Ejecución

### Backend (Kotlin + Spring Boot)
```bash
cd backend
./gradlew bootRun
```

### Frontend (Angular)
```bash
cd frontend
npm install
ng serve
```

---

## Endpoints API

### POST `/calcular-interes`
- Entrada JSON:
```json
{
  "montoInicial": 1000,
  "tasaInteres": 5,
  "anios": 5
}
```
- Validaciones:
  - `montoInicial` > 0
  - `tasaInteres` > 0
  - `anios` >= 1 y <= 50
- Respuesta:
```json
{
  "id": 12345,
  "fecha": "2025-03-25T14:00:00Z",
  "interes_compuesto": [ { "anio": 1, "monto": 1050.00 }, ... ],
  "interes_simple": [ { "anio": 1, "monto": 1050.00 }, ... ]
}
```

### GET `/historial-calculos`
- Devuelve todos los registros previos de cálculo.

---

## Frontend: Código Angular

### Componente principal
- Formulario Reactivo con validaciones:
  - `montoInicial`, `tasaInteres` deben ser > 0
  - `anios` entre 1 y 50
- Llama a la API REST y recibe los resultados

### Gráfico
- ngx-charts para comparación de interés simple y compuesto
- Colores: Azul (compuesto) y Rojo (simple)

### Tabla
- Lista con historial de cálculos
- Usa Angular Material Table con filtros y paginación

### Dependencias importantes
```bash
npm install @swimlane/ngx-charts @angular/material @angular/cdk
```

### Estructura básica Angular
```ts
// src/app/models/interes-request.model.ts
export interface InteresRequest {
  montoInicial: number;
  tasaInteres: number;
  anios: number;
}

// src/app/models/interes-response.model.ts
export interface MontoAnual {
  anio: number;
  monto: number;
}

export interface InteresResponse {
  id: number;
  fecha: string;
  interes_compuesto: MontoAnual[];
  interes_simple: MontoAnual[];
}

// src/app/services/interes.service.ts
@Injectable({ providedIn: 'root' })
export class InteresService {
  constructor(private http: HttpClient) {}

  calcularInteres(req: InteresRequest): Observable<InteresResponse> {
    return this.http.post<InteresResponse>(`/api/calcular-interes`, req);
  }

  obtenerHistorial(): Observable<any[]> {
    return this.http.get<any[]>(`/api/historial-calculos`);
  }
}

// src/app/components/formulario/formulario.component.ts
@Component({ selector: 'app-formulario', ... })
export class FormularioComponent implements OnInit {
  form: FormGroup;
  resultado?: InteresResponse;

  constructor(private fb: FormBuilder, private interesService: InteresService) {}

  ngOnInit() {
    this.form = this.fb.group({
      montoInicial: [null, [Validators.required, Validators.min(1)]],
      tasaInteres: [null, [Validators.required, Validators.min(1)]],
      anios: [null, [Validators.required, Validators.min(1), Validators.max(50)]]
    });
  }

  enviar() {
    if (this.form.valid) {
      this.interesService.calcularInteres(this.form.value).subscribe(
        data => this.resultado = data,
        err => alert(err.error.error || 'Error desconocido')
      );
    }
  }
}
```

---

## Pruebas Unitarias
- Backend: JUnit + Mockito
- Frontend: Angular TestBed + Jasmine
- Lógica de cálculo y componentes UI validados

---

## Consideraciones de Seguridad
- Manejo adecuado de errores (HTTP 400 en datos inválidos)
- Validaciones del lado del backend y frontend

---

## Autenticación (opcional)
- Puede integrarse JWT o Auth0 para usuarios si se desea escalar

---

## Autor
**Renato Cabrera**  

---

## Licencia
Este proyecto se entrega como parte de una prueba técnica. Puedes adaptarlo libremente.
