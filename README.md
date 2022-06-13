# iTunesMVVMHiltApp
This is a music app which allows you to browse through three genres of music - Classic, Rock and Pop. 
The api used is https://itunes.apple.com/search?term=classic&amp;media=music&amp;entity=song&amp;limit=50.
It lists out the artist name, collection name and the cost of each track using a recycler view. 
When clicked on a track, it plays the preview of the track for 30s using any music player on the device. 
It is programmed in Kotlin and uses corountines for live data. 
It uses MVVM architecture with a view model and repository. 
It uses Hilt dependency injection to inject the view model and the network modules.
It uses Mockito unit test case to test the View Model
