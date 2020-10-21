# Rabobank assessment - by Tarek Ben Driss

This Android app is developed for the 'RaboBank' recruiting process.


## Architecture

The architecture used for the development of this application is "MVVM", Model, View, ViewModel. It is not used in the "read CSV file" process, but it can be seen in the getPayments method in the MainActivity. \
The choice for the MVVM architecture is due to its automatic propagation of changes, inside the state of ViewModel to the view. This allows us to quickly and instantly modify the data received when changing. \
The app includes four activities and three fragments:
- Activity of the splashScreen, the splash screen includes the company's logo.
- The main activity which parse the CSV file and shows it's content.



## CI/CD
I integrated "GitHub Actions" and wrote the script that runs the tests as well as build and generate an apk and make it available for download. This script is triggered automatically after each push.

![alt text](https://i.ibb.co/0ywRvR2/Capture-d-e-cran-2020-09-22-a-11-14-13-AM.png)

![alt text](https://i.ibb.co/KwV4vCR/Capture-d-e-cran-2020-09-22-a-11-14-30-AM.png)


## Start the app
To launch the application, just click on the run button in Android Studio once the project is cloned on your machine.

Another alternative to launch the application is to download the previously generated apk file to your smartphone and install it.


## Features

This app allows:
- Display the content of a CSV file regardless of its structure.
- Search from the CSV content.
- Refresh the list.


## Tests
The tests developed in this project are UI tests relating to the recyclerView.
These tests ensure that the recyclerView is clearly visible, the correct operation of the click as well as the scroll on the recyclerView.

Adding to the UI tests, unit tests are developed to check if a CSV file exists and if it's empty or not.


## Screenshots


Splash screen\
![alt text](https://i.ibb.co/ZYTw719/Splash-screen.png)

CSV file content\
![alt text](https://i.ibb.co/DWxr6dG/Csv-content.png)

When the CSV file is empty\
![alt text](https://i.ibb.co/23Rd08r/Empty-csv.png)


When the CSV file contains only titles (the first line)\
![alt text](https://i.ibb.co/fdRPqKB/Only-titles.png)


## Libraries
Libraries used are:
- Lottie to show GIFs.
- SDP/SSP to make design more flexible.
- Retrofit to consume APIs.



