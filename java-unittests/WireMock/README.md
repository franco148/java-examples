# What is WireMock
- WireMock is a HTTP API simulator or HTTP Mock Web Server
- Configure N number of Stubs programmatically
- What is a Stub in WireMock context?
  - A stub refers to a combination of HttpRequest and HttpResponse
- WireMock is built on Java
- WireMock should be used only in the scopre of Unit/Integration testing.
- WireMock can be run in two modes:
  - Embedded
  - Standalone Mode

- Embedded:
  - WireMock runs alongside the test cases in the same process
- Standalone:
  - WireMock runs as a separate process.



# WIREMOCK VS MOCKITO
- WireMock provides simulation to the actual service. (Includes the whole REST Services)
  - WebServer
  - Real HTTP
  - Network fault simulation is possible
  - Testing equivalent to interacting with production environment.
- Mockito does the same (Works on objects)
  - Not a WebServer
  - No HTTP
  - Network fault simulation is not possible
  - Testing not equivalent to interacting with production environment
















