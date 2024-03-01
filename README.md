# SpringBoot Angular Starter Template

## Introduction

This repository represents a starter template for Java/SpringBoot using TypeScript/Angular as SPA (Single Page
Application) GUI.

This example consists of 3 modules:

- **spring-boot-angular-starter-template-service-api** (API)
- **spring-boot-angular-starter-template-ui** (UI)
- **spring-boot-angular-starter-template-reactive-service** (Service)

### API-Module

The API-Module contains a simple OpenAPI-Specification (v.3.0.3) which declares a HTTP GET-Request endpoint returning a
random number.

### UI-Module

This module contains an Angular-Project generated with the Angular-CLI on version 16.2.2 (was later migrated to 17.2.3)
using angular/material for the UI-components.

Used maven-plugins:

- **maven-remote-resources-plugin**: This plugin is used to copy the OpenAPI-Specification into the build-directory.


- **openapi-generator-maven-plugin**: This plugin is used to generate TypeScript/Angular Client code from the provided
  OpenAPI-Specification. We generate these files inside the angular-module to access the generated sources. These files
  are excluded with the .gitignore to prevent them from being committed to the repository.


- **maven-clean-plugin**: This plugin is used to clear the generated resources folder on each build to guarantee clean
  builds.


- **frontend-maven-plugin**: This plugin is used to build the Angular-Project using node and npm. It also downloads node
  and npm for the build process.


- **maven-resources-plugin**: This plugin is used to copy the generated frontend sources to the project build output
  directory.

In the Angular sources you will find a simple UI with 2 pages - an introduction page and a page where you can perform a
backend call.

### Service-Module

This module is a simple Java/SpringBoot application with a hexagonal project structure.

Used maven-plugins:

- **spring-boot-maven-plugin**: This plugin is used to build the SpringBoot application.


- **maven-remote-resources-plugin**: This plugin is used to copy the OpenAPI-Specification into the build-directory.


- **openapi-generator-maven-plugin**: This plugin is used to generate Java/SpringBoot Server code from the provided
  OpenAPI-Specification. These files are directly generated to the build-directory.

It is essential that the UI- and API-Module are included as dependencies in the POM. The UI module has a directory
called "static" in which the generated angular resources are located. SpringBoot serves all resources within the "
static" directory and therefore the angular UI is also returned when called. The SPA has its own routing, consequently
SpringBoot needs a way to determine which URL is served by the backend and which is served by the SPA. The
AngularSpaResolver class specifies that only URLs beginning with "/rest/*" are relevant for the backend.

## Additional information

### Future work

Additionally, it is planned to provide an example for a non-reactive SpringBoot context.

### Serving SPA from a Sub-URL

The example provided shows an implementation of an Angular SPA that can be accessed via the root URL. Alternatively,
this SPA could be offered via a sub-URL.

The following adjustments are necessary for serving from /ui/:

**angular.json**:

```
[...]
 "architect": {
        "build": {
          "builder": "@angular-devkit/build-angular:browser",
          "options": {
            "baseHref": "/ui/",
            "outputPath": "target/static/ui",
            "index": "src/index.html",
            "main": "src/main.ts",
            "polyfills": [
              "zone.js"
            ],
            "tsConfig": "tsconfig.app.json",
            "assets": [
              "src/favicon.ico",
              "src/assets"
            ],
            "styles": [
              "@angular/material/prebuilt-themes/purple-green.css",
              "src/styles.css"
            ],
            "scripts": []
          },
          [...]
        },
[...]
```

**AngularSpaResolver.class**:

```
@Service
class AngularSpaResolver implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (path.startsWith("/ui/") && path.matches("[^\\\\.]*")) {
            return chain.filter(
                    exchange.mutate().request(exchange.getRequest().mutate().path("/ui/index.html").build()
                    ).build());
        }

        return chain.filter(exchange);
    }
}
```

Additionally, you could need a permanent redirect to /ui/. Therefore, you could use the following implementation:
**RedirectRestController.class**:

```
@RestController
class RedirectRestController {

    @GetMapping("/")
    public Mono<Void> redirect(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
        response.getHeaders().add(HttpHeaders.LOCATION, "/ui/");
        return response.setComplete();
    }
}
```