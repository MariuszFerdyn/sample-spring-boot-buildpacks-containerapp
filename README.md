# sample-spring-boot-buildpacks-containerapp
A sample repository demonstrating how to build, package, and deploy a Java Spring Boot application as a container using Cloud Native Buildpacks. This project shows end-to-end automation—from source code to container image—optimized for Azure Container Apps or any cloud-native platform supporting OCI containers. It leverages Buildpacks (such as Paketo) to produce efficient, production-ready images without the need for a custom Dockerfile, and highlights best practices for cloud application deployment with Java and Spring Boot.

## 🧰 Prerequisites

- Java Development Kit (JDK) 17 or newer on your PATH (`java -version`)
- Apache Maven 3.9+ on your PATH (`mvn -version`)

```bash
sudo apt update
sudo apt install openjdk-17-jdk maven
```


Install the [Paketo Buildpacks `pack` CLI](https://buildpacks.io/docs/tools/pack/) if you plan to build an OCI image locally.

```bash
sudo apt update
curl -sSL "https://github.com/buildpacks/pack/releases/download/v0.32.0/pack-v0.32.0-linux.tgz" -o pack.tgz
sudo tar -C /usr/local/bin -xzf pack.tgz pack
rm pack.tgz
pack --version
```

## 🚀 Run the application locally

```bash
mvn spring-boot:run
```

Then open <http://localhost:8080> to view the interactive UI. Add `?name=YourName` to the URL to personalize the greeting.

To stop the app, press `Ctrl+C` in the same terminal.

## 🏗️ Build a runnable JAR

```bash
mvn clean package
```

The executable JAR will be placed at `target/containerapp-0.0.1-SNAPSHOT.jar`. Run it with:

```bash
java -jar target/containerapp-0.0.1-SNAPSHOT.jar
```

## ✅ Run automated tests

```bash
mvn test
```

## 📦 Create a container image with Buildpacks (optional)

Use the Paketo Java buildpack to build an OCI image without a Dockerfile:

```bash
pack build sample-containerapp --path . --builder paketobuildpacks/builder-jammy-base
```


### ▶️ Run the `sample-containerapp` image locally

Make sure Docker Desktop, Docker Engine, or Podman is installed and running, then execute:

```bash
docker run --rm -p 8080:8080 sample-containerapp
```

Or, with Podman:

```bash
podman run --rm -p 8080:8080 sample-containerapp
```

After the container starts, open <http://localhost:8080> to interact with the same UI. Use `Ctrl+C` in the terminal (or `podman stop`/`docker stop` from another shell) to stop the container.

### ☁️ (Optional - you can just use mafamafa/sample-spring-boot-buildpacks-containerapp:latest) Push the image to Docker Hub

Tag the image and push it to your Docker Hub repository (`mafamafa/sample-spring-boot-buildpacks-containerapp`).

```bash
docker login
docker tag sample-containerapp mafamafa/sample-spring-boot-buildpacks-containerapp:latest   # Modify to your registry
docker push mafamafa/sample-spring-boot-buildpacks-containerapp:latest                      # Modify to your registry
```

If you build a new version later, rebuild with `pack build` and rerun the tag/push commands to publish the update.

## 🌐 Deploy to Azure Container Apps

Launch the ARM template in the Azure Portal using the button below:

[![Deploy to Azure](https://aka.ms/deploytoazurebutton)](https://portal.azure.com/#create/Microsoft.Template/uri/https%3A%2F%2Fraw.githubusercontent.com%2FMariuszFerdyn%2Fsample-spring-boot-buildpacks-containerapp%2Fmain%2Finfra%2Farm%2Fcontainerapp-template.json)

This pre-populates the deployment experience with the Container Apps environment and the `mafamafa/sample-spring-boot-buildpacks-containerapp` image. Adjust parameters like CPU, memory, or image tag before submitting if needed.


