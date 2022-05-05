
# AnyControl

It's just an attempt to implement something 
like AnyDesk, TeamViewer, but so far only in the local
network and with limited functionality - mouse control.

There are 2 parts of the working application.
Sender[Client](PC or android app) and Receiver(PC)[Server].
Sender is a JavaFx application where you have an stage/scene.
You perform different events there - mouse events(movements, 
clicks), keyboard events. Each event is sent to the server as
a separate command through "own protocol". Other PC receives
this command, parses and performes it.

## Thoughts/todo

- keyboard events (gui scene with keyboard or straight events of the real keyboard)
- scale command
- retrieve system info command
- get/send files command

## Used technologies

- Java 17 ([Azul Zulu JDK FX](https://www.azul.com/downloads/?version=java-17-lts&package=jdk-fx)) 
- JavaFx (included in azul zulu jdk fx)
- SceneBuilder (to build FXML structure) 
- Sockets

## Demo
https://user-images.githubusercontent.com/72043323/166488147-41cbd774-f5f0-4f12-8622-660a7a61e909.mp4
