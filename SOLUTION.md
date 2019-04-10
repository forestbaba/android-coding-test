# Solution

Libraries:
 com.squareup.retrofit2:retrofit:2.0.2
 com.squareup.retrofit2:converter-gson:2.0.2
 com.squareup.okhttp3:logging-interceptor:3.4.1
 com.android.support:design:28.0.0
 
 Manifest Permission:
 uses-permission android:name="android.permission.INTERNET"
 

Four packages was created inside the source folder apart from the MainActivity
First package: adapter. It contains a class named CommitAdapter. Which is the adapter class for the recyclerview
Second package: model. It contains model class for all the objects present in the commit response.
Third package: rest. This is the package that holds the interface and class that make resful calls
Fourth package: util. It contains constants object to be used throughout the app
MainActivity: The class that creates the view and implement all created objects
