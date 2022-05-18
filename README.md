<!-- Readme Template V1.0 -->
![Product Name Screen Shot][product-screenshot]

<!-- TABLE OF CONTENTS -->
## Table of Contents

  1. [Getting Started](#getting-started)
  2. [Installing](#installing)
     1. [Installing on Linux or Windows](#installing-on-linux-or-windows)
        1. [Installing Podman and Docker Compose](#installing-podman-and-docker-compose)
           1. [Menu](#menu)
           2. [Podman](#podman)
     2. [Installing on macOS](#installing-on-macos) 
  4. [Checking environment](#checking-environment)

**[back to top](#table-of-contents)**

## Getting Started

This repo help you about how you may give instructions on setting up your local environment to work with containers in WSL2.

## Installing
   
### Installing on Linux or Windows

1. Launch the next command in a Powershell terminal opened like Administrator:
   ```sh
   wsl --install -d Ubuntu-20.04
   ```
2. Continues in Admin Powershell terminal and launch:
   ```sh
   dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart
   ```
3. Reboot your computer and launch the last config command:
   ```sh
   wsl.exe --set-version Ubuntu-20.04 2
   ```

#### Installing Podman and Docker Compose

##### Menu
Current options when you execute the next command:
```bash
$ ./menu.sh
1) Podman
2) Quit
Please select your choice: 
```

##### Podman

```bash
Please select your choice: 1
1) Install Podman + Docker Compose
2) View all
3) Stop containers
4) Prune images
5) Remove containers
6) Remove images
7) Clean all
8) Quit
```

### Installing on macOs

```bash
$ brew install podman
$ brew install docker-compose
```

**[back to top](#table-of-contents)**

<!-- MARKDOWN LINKS & IMAGES -->
[product-screenshot]: ../images/iskaytech_logo_project.png
