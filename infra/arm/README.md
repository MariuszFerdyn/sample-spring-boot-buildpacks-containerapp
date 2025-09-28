# Azure Container Apps ARM Template

This folder contains an Azure Resource Manager (ARM) template that provisions a minimal Azure Container Apps environment and deploys the Spring Boot container image `mafamafa/sample-spring-boot-buildpacks-containerapp`.

## Resources created

- Container Apps managed environment (Consumption workload profile).
- Container App with external ingress on port 8080 running the provided container image.

## Parameters

| Name | Description | Default |
| --- | --- | --- |
| `environmentName` | Name of the Container Apps managed environment. | — |
| `containerAppName` | Name of the Container App. | `<environmentName>-app` |
| `location` | Azure region for all resources. | Resource group location |
| `containerImage` | OCI image to deploy. | `mafamafa/sample-spring-boot-buildpacks-containerapp:latest` |
| `containerCpu` | vCPU allocation for the app. | `0.5` |
| `containerMemory` | RAM allocation for the app. | `1Gi` |

## Deploy with Azure CLI

```bash
az deployment group create \
  --name containerapp-deployment \
  --resource-group <your-resource-group> \
  --template-file containerapp-template.json \
  --parameters environmentName=<env-name>
```

Override other parameters as needed, for example `--parameters containerCpu=1 containerMemory=2Gi`.

## Output

- `containerAppUrl` – Fully qualified domain name for the deployed Container App.

After deployment completes, browse to `https://<containerAppUrl>` to reach the Spring Boot UI.
