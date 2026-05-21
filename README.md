# Sistema de Gestión Bibliotecaria - Ejercicio de Inversión de Control

## Descripción

Es un proyecto sencillo de gestión de una biblioteca con sistema de préstamos que tiene algunos problemas de acoplamiento.

El sistema permite gestionar:
- **Libros**: catálogo con control de ejemplares disponibles
- **Socios**: registro de usuarios de la biblioteca
- **Préstamos**: sistema de préstamo con control de fechas y devoluciones

## Estructura del Proyecto

```
org.biblioteca
├── Aplicacion.java                    # Punto de entrada
├── entidades/
│   ├── Socio.java
│   ├── Libro.java
│   └── Prestamo.java
├── repositorio/
│   ├── AlmacenSocios.java
│   ├── AlmacenLibros.java
│   └── AlmacenPrestamos.java
├── negocio/
│   ├── GestionSocios.java
│   ├── GestionLibros.java
│   └── GestionPrestamos.java
├── interfaz/
│   └── MenuPrincipal.java
└── herramientas/
    └── JsonUtil.java
```

## Tecnologías

- **Java 21**
- **Maven** para gestión de dependencias
- **Gson 2.10.1** para persistencia JSON

## Ejecución

```bash
mvn exec:java
```

---

## 📋 Tareas a Realizar

### 1️⃣ Crear rama de trabajo
Crea nueva rama que se llame **"ioc"**.

```bash
git checkout -b ioc
```

### 2️⃣ Inversión de Dependencias (IoC)
Desacopla la aplicación según el **principio de inversión de dependencias**, inyectando las dependencias de forma manual.

- Debes crear interfaces y aplicar inyección de dependencias manual

**Haz un commit comentando los cambios realizados.**

### 3️⃣ Weld CDI
Utiliza **Weld** para automatizar la inyección de dependencias.

**Haz un commit comentando los cambios realizados.**

### 4️⃣ Pruebas Unitarias
Crear una prueba unitaria para el módulo **`consultarPorIsbn`** de `AlmacenLibros`.

- Probar casos: libro existente, libro no existente

**Haz un commit comentando los cambios realizados.**

### 5️⃣ Merge y Pull Request
- Haz un **merge** a la rama principal
- Haz un último **commit**
- Solicita un **PR** al repositorio original

**Entrega**: Captura de GitHub donde se vea tu PR.

---

## 🎯 Criterios de Evaluación

- ✅ Correcta aplicación del principio de Inversión de Dependencias
- ✅ Uso adecuado de interfaces para desacoplamiento
- ✅ Configuración correcta de Weld CDI
- ✅ Pruebas unitarias funcionales y significativas
- ✅ Commits claros y descriptivos
- ✅ PR correctamente formateado

---

## 📚 Conceptos Clave

### Inversión de Dependencias (DIP)
Los módulos de alto nivel no deben depender de módulos de bajo nivel. Ambos deben depender de abstracciones.

### Inyección de Dependencias (DI)
Patrón de diseño que permite proporcionar las dependencias desde el exterior en lugar de crearlas internamente.

### Weld CDI
Implementación de referencia de Contexts and Dependency Injection para Java EE/Jakarta EE.

---

## 🔍 Notas Adicionales

- El proyecto usa **persistencia en JSON** mediante archivos en `/datos`
- El sistema controla **ejemplares disponibles** automáticamente
- Se detectan **préstamos retrasados** al visualizarlos
- Las **validaciones** de negocio están en la capa de gestión

---

## 📝 Datos de Ejemplo

El proyecto incluye datos precargados:
- 3 socios registrados
- 5 libros en el catálogo (literatura clásica y contemporánea)
- 3 préstamos (2 activos, 1 devuelto)

Los archivos JSON se encuentran en `/datos/`:
- `socios.json`
- `libros.json`
- `prestamos.json`

---

## 📖 Modelo de Datos

### Socio
- `numeroSocio`: Identificador único
- `apellidos`: Apellidos del socio
- `nombre`: Nombre del socio
- `direccion`: Dirección postal
- `telefono`: Número de contacto

### Libro
- `isbn`: Identificador único (ISBN)
- `titulo`: Título de la obra
- `autor`: Nombre del autor
- `editorial`: Casa editorial
- `ejemplaresTotales`: Total de ejemplares
- `ejemplaresDisponibles`: Ejemplares disponibles para préstamo

### Prestamo
- `identificador`: ID único del préstamo
- `isbnLibro`: ISBN del libro prestado
- `numeroSocio`: Número del socio
- `fechaPrestamo`: Fecha de inicio
- `fechaDevolucionPrevista`: Fecha límite de devolución
- `fechaDevolucionReal`: Fecha real de devolución (null si activo)
- `activo`: Estado del préstamo

---

## 🚀 Funcionalidades

### Gestión de Libros
- Añadir nuevos libros al catálogo
- Eliminar libros del sistema
- Consultar el catálogo completo

### Gestión de Socios
- Registrar nuevos socios
- Dar de baja socios
- Listar todos los socios

### Gestión de Préstamos
- Realizar préstamos (con fecha de devolución configurable)
- Registrar devoluciones de libros
- Ver préstamos activos
- Detectar préstamos retrasados
- Control automático de disponibilidad de ejemplares

