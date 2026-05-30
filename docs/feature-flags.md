# Banderas de Funcionalidad
Para que la implementación tuviese la capacidad de ajustarse sin tener que reescribir todas la pantalla se utilizo una bandera simple en `core/FeatureFlags.kt`.  

## Banderas implementadas

- `CREATE_TICKET_ENABLED`: controla si se muestran los accesos para crear tickets.
- `PRIORITY_UPDATE_ENABLED`: controla si se muestra el editor de prioridad en el detalle.
- `RESOLVED_TICKETS_VISIBLE`: permite ocultar tickets resueltos o cerrados en la lista.

## Qué aportan en esta PoC

- permiten demostrar control condicional de interfaz
- hacen visible la separación entre configuración y presentación

## Lectura crítica
Las banderas son útiles pero a como están implementadas actualmente que es de forma local y estática tienen la siguiente implementación:

- no se cambian desde un panel administrativo;
- no dependen de un servicio remoto;
- no permiten activación progresiva;
- no sirven para pruebas A/B reales.

## Riesgo de abuso
Si estas se llegan a utilizar mucho es decir que hay muchas que no tienen un criterio se puede llegar a ser una interfaz difícil de mantener y es debido a esto que solo se dejan banderas que si están justificadas directamente.

## Evolución recomendada
Cuando el sistema empiece a crecer las banderas deberían de migrar a algo remoto o un repositorio para esto, con esto se evita estar compilando cada vez que se cambie una condición del negocio.

## Conclusión crítica
La implementación de las banderas están bien pero a como están actualmente es una solución mínima. Esto es aceptable para el PoC ya que el alcance no es muy grande.