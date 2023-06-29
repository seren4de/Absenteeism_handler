# AMS App

This repository contains code for an AMS app that uses an optical fingerprint to authenticate users and manage events.

## Getting Started

To get started, clone this repository and navigate to the project directory. The project uses Maven as its build tool, and you can use it to build and run the project.

1. Make sure you have Maven installed on your system. If not, you can download it from the [Maven website](https://maven.apache.org/download.cgi) and follow the [installation instructions](https://maven.apache.org/install.html).
2. Open a terminal or command prompt and navigate to the project directory.
3. Run the command `mvn clean install` to build the project.
4. Run the command `mvn exec:java` to start the app.

## Project Structure

The project consists of several important files and modules:

- `target`: Contains the output of the build process.
- `test`: Contains test files for the project.
- `fxml`: Contains the different FXML frames for the interface.
- `event controller`: Contains the main event controller for the interface.
- `udp server`: Contains a UDP server to exchange UDP packets with the optical reader via an Arduino Uno.
- `fingerprint id`: Contains code to add or remove a fingerprint ID from a database.
- `scenes switching`: Contains code to manage scene switching in a JavaFX application.
- `Monitor class`: Contains a Monitor class with two methods: `hours` as a native incrementer and `update` to update the database with the latest event changes.
- `customer class`: Contains a Customer class with two methods: `login` and `typelogin` for two layers of authentication to the database (normal and privileged).
- `log class`: Contains a Log class with two methods: `show` to display the optical scanner's log and `check` to check the fingerprint session ID.
- `amsapp's main frame`: Contains the main frame for the AMS app.
- `capture_fingerprint.ino`: Contains Arduino code to capture fingerprint identification.

## License

This project is licensed under the BSD 3-Clause License. See the LICENSE file for more information.
