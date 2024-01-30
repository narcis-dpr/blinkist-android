# sample book android application

## This is a simple app of a list of categoriezed books
to get a overall glance of the application check out this small preview:
As you can see the view of the app is one single screen with a list of books.
The stack of this application is kotlin and here is the list of the main tools that have been used for this project :Retrofit, Room, Hilt, Jetpack compose, junit, coroutines.
The code base is multi module and here is a brief explanation of each module and their components :

### 1- Build Src :

The base module for every other parts of the app for managing depencies and libraries 
### 2- theme :

Basic theme setting, colors, some fonts, icons, typography, etc.### 3- data

Class and api for server connection, database components, and one repository for connecting to data source

### 4- domain

The module that connects the view layer to data layer, a separate useCase for every access to sperate the concerns 

### 5- presentation 
Basic widgets, one main screen, viewmodel for handling the logic of composable screen, event handler for handling the triggered event by view and state for updating infos in the screen### 6- app

Containg the one activity for the whole application and the app class that initiates hilt. ( we try to keep this module out of clutter)


###What could have been done better?

1- test coverage: the test coverage is at 50 percent or less

2- more DRY code (there is is theory that if you have a code challenge work gets crazy that week! )


