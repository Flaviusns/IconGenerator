# IconGenerator

## Added Java FX 11 support

Finally we added support for JavaFX and Java 11 to our project.

---
Icon Sizes Generator it´s a program that can be use to generate the icons for your app in iOS and Android.

The input image  it´s recomendable to be a .png and a 1024x1024 originial size for better results.

## How to use

Tested with Netbeans 11, OpenJDK `openjdk 11.0.1 2018-10-16 OpenJDK Runtime Environment 18.9 (build 11.0.1+13) OpenJDK 64-Bit Server VM 18.9 (build 11.0.1+13, mixed mode)`, JavaFX SDK 11.0.2 and MacOS Mojave.

Clone the netbeans project. Once you open it, make sure to configure the project with Java FX correctly, for more information, check this [link](https://openjfx.io/openjfx-docs/#install-javafx)

Then build and run the project. If everything was configured correctly, the app will start.

To run it inside the command line use the next command: 

`java  --module-path /Path/To/javafx-sdk-11.0.1/lib --add-modules=javafx.controls,javafx.fxml -jar "/Path/to/IconGeneratorBeta081.jar"`

Normally, the jar file is generated in the dist folder after build it in Netbeans.

Installers no more supported.

For more questions, contact with [me](https://twitter.com/FlaviusStan_Dev).

## Knowing Issues

The minimize button currently not working.

