# FishyFlappy

##Descripción general y propósito del juego

Fishy es un juego diseñado en Android Studio para concienciar a la gente del peligro
que tiene tirar residuos plásticos al mar mediante el no reciclaje. A continuación se
detallará como es la aplicación y transcurso que siguió.

##Prototipo de la aplicación

El [prototipo de la aplicación sería](https://www.figma.com/proto/jwDlWC1p46czR1ZfwXxDD1/Fishy-Flappy?node-id=2%3A2&scaling=min-zoom&page-id=0%3A1&starting-point-node-id=2%3A2) algo simple,
pues cuenta con una splash screen, la cual en el producto final estará animada con
Adobe After Effects, un menú en el cual se puede tanto jugar, como valorar en la Play Store,
como poder salir del juego fácilmente.

Una vez en el juego, vemos la puntuación, nuestro personaje y los obstáculos por esquivar.
También vemos el botón de pausa que al clickarlo nos lleva al menú de pausa, el cual cuenta
con diversas opciones, como volver al juego, reiniciar el juego y poder ver la configuración,
la cual cuenta con control de volumen de la música y de efectos de sonido.

Una vez morimos, se nos muestra una escena de Game Over, como motas de petróleo
apareciendo por pantalla, dando a entender que el pez murió por la contaminación y seguidamente
un menú en el que podemos empezar una nueva partida, o volver al menú.

También, en el prototipo de figma se puede ver otra escena del juego y es que queremos
en un inicio probar a hacer el estilo de "Flappy Bird" para poder controlar a la perfección
el uso de colliders y división de sprites, ya que no estamos en un motor de videojuegos,
por lo que nos dificulta hacerlo de un modo más sencillo.

Cabe decir que todos los sprites utilizados fueron hechos a mano por nuestro equipo.

![Imagen del prototipo en Figma](assets/Main.png)

##Paso del prototipo a la aplicación

Una vez que el prototipo estaba terminado, se pasó a la aplicación, para ello, se creó
el proyecto utilizando la librería [LibGDX](https://libgdx.com/), la cual nos permite crear juegos en Java.

Gracias a esto y a cierta ayuda de algunos videotutoriales de Youtube, se pudo simular
el popular juego "Flappy Bird", y utilizarla como base para poder aprender cómo utilizar
esta nueva librería.

En el vídeo a continuación se puede ver este tipo de simulación para aprender a utilizar
[valores vectoriales de posición de objetos](./core/src/com/erdarkniel/fishyflappy/sprites/Fish.java), [cámaras](./core/src/com/erdarkniel/fishyflappy/states/MenuState.java), [colliders mediante rectángulos invisibles](./core/src/com/erdarkniel/fishyflappy/sprites/Tube.java), [divisiones de imagen](./core/src/com/erdarkniel/fishyflappy/sprites/Fish.java), etc.

https://user-images.githubusercontent.com/105220864/211762644-49f031ec-6a51-4837-87f5-b8d9e252d557.mp4

Llegados aquí, hemos de cambiar los sprites que estabamos utilizando, por los del juego propio, y aquí llegamos a un problema,
ya que los sprites utilizados y los realizados a mano no coincidían en tamaños, por lo que los sprites nuevos se veían gigantes.
Así que tuvimos que poner crear Pixmap para poder variar a nuestro gusto el tamaño de los sprites, y así poder utilizarlos. Esto
lo podemos encontrar en el archivo [Fish](./core/src/com/erdarkniel/fishyflappy/sprites/Fish.java).

##Progreso actual

Actualmente el proyecto se encuentra como las imágenes mostradas a continuación.
