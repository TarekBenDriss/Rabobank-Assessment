# bunq - assessment

This Android app is developed for the 'RaboBank' recruiting process.


## Architecture

The architecture used for the development of this application is "MVVM", Model, View, ViewModel. It is not used in the "read CSV file" process, but it can be seen in the getPayments method in the MainActivity"\
The choice for the MVVM architecture is due to its automatic propagation of changes, inside the state of ViewModel to the view. This allows us to quickly and instantly modify the data received when changing. \
The app includes four activities and three fragments:
- Activity of the splashScreen, the splash screen includes the company's logo.
- The main activity which parse the CSV file and shows it's content



## CI/CD
I integrated "GitHub Actions" and wrote the script that runs the tests as well as build and generate an apk and make it available for download. This script is triggered automatically after each push.

![alt text](https://i.ibb.co/0ywRvR2/Capture-d-e-cran-2020-09-22-a-11-14-13-AM.png)

![alt text](https://i.ibb.co/KwV4vCR/Capture-d-e-cran-2020-09-22-a-11-14-30-AM.png)


## Start the app
To launch the application, just click on the run button in Android Studio once the project is cloned on your machine.

Another alternative to launch the application is to download the previously generated apk file to your smartphone and install it.


## Features

This app allows:
- Showing a CSV content.
- Showing a list of payments done by the user and filtering the list.
- Letting the user do a payment.
- Letting the user request money.
- Showing the payment's details.

## Screenshots

Splash screen\
![alt text](https://i.ibb.co/y5m7wty/splash.jpg)

Setting up a new account\
![alt text](https://i.ibb.co/r7xD5yY/Screenshot-2020-09-23-02-01-49-45-c9766aab1d619db9bc94a42d6ae8dff4.jpg)

Tutorial\
![alt text](https://i.ibb.co/G5CqhRn/tuto.jpg)

All payments list\
![alt text](https://i.ibb.co/Jz18YX5/list.jpg)

Incomes\
![alt text](https://i.ibb.co/sJVnb6W/incomes.jpg)

Outcomes\
![alt text](https://i.ibb.co/D7fqNYj/outcomes.jpg)

Filter by date\
![alt text](https://i.ibb.co/KGyfr2S/date.jpg)

How to make/request money\
![alt text](https://i.ibb.co/Xk68PL4/make-payment-tuto.jpg)

Make a payment\
![alt text](https://i.ibb.co/PT8nrKD/make-payment.jpg)

Request money\
![alt text](https://i.ibb.co/5MZQTth/request-money.jpg)


Login screen\
![alt text](https://i.ibb.co/D9sYNPf/Screenshot-2020-09-23-02-01-58-23-c9766aab1d619db9bc94a42d6ae8dff4.jpg)


## Libraries
Libraries used are:
- Retrofit to consume APIs.
- Gide to load photos.
- Lottie to show GIFs.
- SDP/SSP to make design more flexible.


