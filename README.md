# AcaryaApp:

An App made for Changing your lifestyle

### Problem Statement
People nowadays are more concerned with their work than with their health. Approximately 75% of the population has an unhealthy lifestyle. India's Happiness Index score has been declining, with one of the main reasons being an unhealthy lifestyle. We all understand how vital it is to be healthy and live in peace with our families and ourselves, to eat whatever we like, and to incorporate exercise into our daily routine. 

### Proposed Solution
Our objective is to assist people with the importance of health, how they may improve their health in today's hectic world, and what efforts they should take to maintain their health. They can complete the 10-minute workout by following the steps given. It will serve as a daily reminder. People will be reminded to stay hydrated, and they will be able to review their training history. Also, assess your BMI and set a diet alarm for yourself (Diet alarms are under-development).

### App Screenshots
<img src="https://github.com/theyashgrover/AcaryaApp/blob/master/MergedImages.jpg" height = "1350px" width="900px">

### Functionality and concepts used:
- The app offers a user interface that is both simple and interactive. The following are a few Android principles that were used to achieve the app's functionalities.
   - ### `Constraint Layout` 
        Most of the activities in the app uses a flexible constraint layout, which is easy to handle for different screen sizes. 
   - ### `Bottom Navigation Menu` 
        The App Implements Bottom Navigation Menu using fragments in a single activity using Jetpack Navigation. 
   - ### `SQLite Database` 
        The App Implements the SqliteOpenHelper class and stores the workout history of the user.
   - ### `Text-to-Speech` 
        The App also Implements the text to speech Functionality for narrating the exercise name.
   - ### `RecyclerView` 
        The app Implements RecyclerView twice. Once to keep the track of exercises during workout and then to display the workout history of the user. ts 
   - ### `Notification Feature`
        The app uses the BroadcastReciever class and implements the notification feature for alarm activity. Clicking on the notification redirects user to a destination        activity.

### Application Link & Future Scope :
The app is currently testing the feature of DietAlarms. We could improve the UI of the application and Create a new Fragment Dedicated to  Mental Health using REST server , with this we will also be able to use Internet in out Application (which as labelled as optional in App Submission Guidelines).
<br>
[Google Drive Link for the app](https://drive.google.com/drive/folders/1BAb2ccmF54-Vlozos4RtPvoRWVtL01Ya?usp=sharing)
<br>
[Github Link For the Repository](https://github.com/theyashgrover/AcaryaApp)
