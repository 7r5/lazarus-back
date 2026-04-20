# WebNotes API

REST API backend para WebNotes construida con Spring Boot 3.x, Java 17 y PostgreSQL.


## Quick Commands

```powershell
mvn compile
```

```powershell
mvn spring-boot:run
```

```powershell
mvn test
```

```powershell
mvn -Dtest=ProductControllerTest test
```

- `mvn test` ejecuta todas las pruebas.
- `mvn -Dtest=ProductControllerTest test` ejecuta solo la prueba del endpoint `/api/products/{id}`.

## Stack Tecnológico

- **Lenguaje:** Java 17+
- **Framework:** Spring Boot 3.x (Web, Data JPA)
- **Base de Datos:** PostgreSQL
- **Build Tool:** Maven
- **Despliegue:** Render

## Requisitos Previos

### Windows
- Windows 10/11
- WSL (Windows Subsystem for Linux) instalado

### WSL
- Ubuntu 20.04 o superior
- sdkman
- Java 17
- Maven

## Pasos de Instalación

### 1. Instalar WSL en Windows

Abre PowerShell como administrador y ejecuta:

```powershell
wsl --install
```

Reinicia tu computadora cuando se solicite.

### 2. Instalar sdkman en WSL

Abre WSL y ejecuta:

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```

Verifica la instalación:

```bash
sdk version
```

### 3. Instalar Java 17

```bash
sdk install java 17.0.9-tem
```

Verifica la instalación:

```bash
java -version
```

### 4. Instalar Maven

```bash
sdk install maven
```

Verifica la instalación:

```bash
mvn -version
```

### 5. Cambiar Terminal Integrada a WSL

1. En VS Code, abre Command Palette (`Ctrl+Shift+P`)
2. Escribe: `Terminal: Select Default Profile`
3. Selecciona **WSL**
4. Abre una nueva terminal (`Ctrl+Ñ`)


## Extensiones para VSCode

- Java (asi nomas dice tiene 5.5 millones de descargas y ya que lo instalen les saldra algo del JDK, le dan que si e instalar)

## Estructura del Proyecto

```
webnotes-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── Application.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## Problemas Comunes

### Java no se reconoce en WSL
- Verifica que usas la terminal WSL: `ric@LA-CHONA:...`
- Ejecuta: `java -version` para confirmar que Java está disponible

### sdkman no funciona
- Reinicia tu terminal WSL
- Ejecuta: `source "$HOME/.sdkman/bin/sdkman-init.sh"`

## Próximos Pasos

1. **Compilar el Proyecto**
   ```bash
   mvn clean install
   ```

2. **Configurar PostgreSQL** (opcional por ahora)
   - Instalar PostgreSQL en WSL o Windows
   - Actualizar `src/main/resources/application.properties` con credenciales

3. **Ejecutar la Aplicación**
   ```bash
   mvn spring-boot:run
   ```
   La API estará disponible en: `http://localhost:8080/api/hello`

4. **Desarrollo**
   - [ ] Crear modelos adicionales
   - [ ] Implementar más endpoints
   - [ ] Implementar autenticación JWT
   - [ ] Crear frontend con React
   - [ ] Desplegar en Render

## Licencia

MIT