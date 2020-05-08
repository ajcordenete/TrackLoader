# TrackLoader
 A master-detail application that loads tracks from iTunes Search API.
 
 <br/><br/>
<img src="https://github.com/ajcordenete/TrackLoader/blob/master/Screenshot_tracks.png" width="250"> 
<img src="https://github.com/ajcordenete/TrackLoader/blob/master/Screenshot_detail.png" width="250">&nbsp;
 
 ## Persistence
 - Offline Caching
   - tracks are saved in local storage and will be used if there is no connection
   - Room database library is used as the local cache mechanism
   - serves as the single source of truth within the app
   
- Saves the last screen that the user is viewing and restore it upon reopening of the app
- Displays the date when the user last visited the app
- SharedPreference is used for storing user's session
  
  
## Networking
- Retrofit library is used for consuming REST calls from the iTunes Search API.
- As provided by Google in their [Guide to App Architecture](https://developer.android.com/jetpack/docs/guide), the class [NetworkBoundResource](https://github.com/android/architecture-components-samples/blob/88747993139224a4bb6dbe985adf652d557de621/GithubBrowserSample/app/src/main/java/com/android/example/github/repository/NetworkBoundResource.kt) is used to handle whether loading of data should be from the network or local database, as well as handling the network status of the requests.


## MVVM Architecture

- **Ease of maintainability** - Since MVVM implements separation of concerns, it is easy to change, add or remove a feature with minimal effect to other parts of the app. As an example, changing remote data source to a different API can be as simple as changing the repository without changing any parts of the UI.

 - **Ease of testing** - Along with dependency injection, this allows independent testing of the views, viewModels, and repository should we decide to include tests in the future. 
 
 
 ### The app has following packages:

 - **data**: It contains all the data accessing and manipulating components(entities, api, database)
 - **di**: Dependency providing classes using Dagger2.
 - **ui**: View classes along with their corresponding ViewModel.
 - **utils**: Utility classes.
 
 ## Design
- **Material Design theme and principles**
- **Dark theme**

## Other libraries used:
- Glide - image loading
- Dagger2 - dependency injection
- Moshi - json parsing
- Threetenabp - date and time parsing
