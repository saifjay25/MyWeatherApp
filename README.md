# MyWeatherApp

After you run the app it will show the 7 day forecast using tabs. Each tab shows a day and when the app opens,
the first tab (today's date) will show at the start. For today's date tab I retrieved data from the current weather API from 
darksky.com and the daily weather API and displayed both of their data parsing the JSON objects. For the rest of the days 
I just retrieved the daily weather API. When the phone is offline it will display the most recent data that was added to
the SQLite room database. There is a "more info" button on each tab displaying more data on the 
weather for each day and when it is clicked it shows an animation sliding the dialog from the left to right. I have also 
applied unit testing to each tab.
