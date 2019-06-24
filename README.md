# Lost-Paradise.FM

## Description
Lost-Paradise.FM is an app that connects to Last.fm to get the list of albums, artist or tracks for a specific search, also it will give you the full details of each one mentioned before. 

## Project structure:
The purpose is the separation of concerns by keeping the business rules not knowing anything at all about the outside world. Thus, they can be tested without any dependency on any external element.

To achieve this, my proposal is about breaking up the project into three different layers, in which each one has its purpose and works separately from the others.

It is worth mentioning that each layer uses its data model so this independence can be reached.

### Presentation Layer
Android Kotling module: Is here, where the logic related to views and animations happens. It uses no more than an MVVM, but you can use any other pattern like MVP. I will not get into details on it, but here fragments and activities are only views, there is no logic inside them other than UI logic, and this is where all the rendering stuff takes place.

ViewModels, in this layer, is composed with interactors (use cases) that perform the job in a new thread outside the main android UI thread, and come back using a callback with the data that will be rendered in the view.

### Domain Layer
Kotling module: Business rules here: all the logic happens in this layer.

### Data Layer
Kotlin module: All data needed for the application comes from this layer.
The idea behind all this is that the data origin is transparent for the client, which does not care if the data is coming from memory, disk or the cloud, the only truth is that the data will arrive and will be got.

### Conclusion
As Uncle Bob says, "Architecture is About Intent, not Frameworks"


## Sugestions
As a improvement I would recomend use use Google Naviation component.
