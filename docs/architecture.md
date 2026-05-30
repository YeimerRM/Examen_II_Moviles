# Arquitectura

El proyecto se diseña como una prueba de concepto para resolver dicha problemática presentada en el enunciado y no como una aplicación para producción. La decisión de utilizar la arquitectura de MVVM con Compose y `StateFlow`es porque permite que se pueda demostrar un flujo que es reactivo, separación de responsabilidades y para una mejor mantenimiento sin que se agregue una complejidad que sea necesaria.

## Qué exige el problema

- iniciar sesión con credenciales simuladas;
- listar tickets de soporte;
- ver el detalle de un ticket;
- crear un ticket;
- cambiar estado y prioridad;
- mostrar reactividad inmediata ante cambios;
- usar Compose, navegación, ViewModels y una capa de datos clara;
- documentar el diseño de forma técnica y crítica.

## Cómo responde esta implementación

- **UI:** pantallas Compose desacopladas por caso de uso.
- **Estado:** cada pantalla consume un `UiState` observable.
- **Datos:** un repositorio en memoria actúa como única fuente de verdad.
- **Navegación:** se usan rutas explícitas para ir de lista → detalle → creación.
- **Contratos API:** existen DTOs y servicios Retrofit para dejar preparado un reemplazo futuro.

## Decisiones deliberadas

### 1. No usar Room
En esta implementación se utiliza la persistencia local ya que se solicita PoC y no una base de datos completa, con esto se reduce el tiempo de desarrollo y se evita la sobreingeniería.

### 2. Fuente de verdad en memoria
Se mantiene una lista de memoria con MutableStateFlow ya que con esto se cumple con la reactividad inmediata pero también presenta una limitación que es que se pierdan los datos al cerrar la aplicación.

### 4. Contratos sin backend real
Se crearon archivos OpenAPI, DTOs y Retrofit para que despúes se pueda realizar una mejor implementación y se tomen como ejemplos y además esto demuestra una intención de escalanbilidad.

## Fortalezas de esta decisión

- El flujo de datos es fácil de seguir.
- Los cambios se reflejan de inmediato en pantalla.
- La separación entre UI, lógica y datos es clara.
- La solución es más fácil de compilar, revisar y presentar.

## Limitaciones reales

- No hay persistencia entre sesiones.
- No existe un servidor real para validar el contrato API.
- El login es simulado y no valida contra un sistema externo.
- Las banderas de funcionalidad son locales; no hay administración remota.

## Conclusión crítica

La solucion que se presenta cuenta una priorización clara, reactiva y mantenibilidad aunque aun no es muy operativa debido al alcance que se solicita.

