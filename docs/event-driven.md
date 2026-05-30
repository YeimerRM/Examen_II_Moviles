# Flujo Dirigido por Eventos
Uno de las implementaciones mas importantes es que la interfaz pueda reaccionar de manera inmediata cuando se cambia el estado. Esto se logro debido a que se utiliza tateFlow` y con ViewModels que exponen un estado observable por Compose.
No solo se espera que las pantallas sean visibles, sino que también el flujo de datos logre ser coherente:

1. el usuario ejecuta una acción
2. el ViewModel procesa la intención
3. el repositorio modifica la fuente de verdad
4. la UI se recompone sin refresco manual.

## Escenario 1: creación de ticket

### Flujo esperado

1. El usuario completa el formulario de creación.
2. El ViewModel valida campos mínimos.
3. El repositorio agrega el ticket a la lista en memoria.
4. El listado observa el cambio y se actualiza solo.

### Lectura crítica
Con este flujo se cumple la reactividad pero con la limitación de que estos datos solo van axistir mientras la app este abierta si esta se cierra los nuevos datos desaparecen.

## Escenario 2: cambio de prioridad

### Flujo esperado

1. El usuario entra al detalle de un ticket.
2. Cambia la prioridad desde el selector.
3. El repositorio actualiza el ticket.
4. El listado se reordena de acuerdo con la prioridad.

### Lectura crítica
El comportamiento que se utiliza en PoC es el debido ya que este demuestra un ordenamiento reactivo pero este no esta resolviendo problemas de negocio reales como lo serian:

- conflictos de edición simultánea
- validación con backend
- auditoría de cambios
- sincronización entre varios dispositivos.

## Escenario 3: cambio de estado
El estado también es de forma reactiva y esto es útil para que se pueda hacer una demostración de consistencia interna pero sigue siendo de forma local y contra una transacción real.

## Qué aporta `StateFlow`

- emisión inmediata de cambios
- observación simple desde ViewModels
- integración directa con Compose
- código fácil de seguir durante la defensa

## Qué no resuelve

- persistencia entre sesiones
- sincronización de red
- recuperación ante fallo del proceso
- validaciones de servidor

## Conclusión crítica
El flujo que esta dirigido por los eventos si cumple con el alcance que se solicita ya que el comportamiento que se observa es inmediato y fácil de entender.