# PalTales

In response to the ongoing Israeli offensive Forces, the ethnic cleansing and aparthhied of the Palestinian People, this app emerges as a platform to illuminate our unique narrative. Palestinians are resilient and creative, garnering recognition through various awards in cinema and literature with these works. The app's primary objective is to present insight into the inspiring resilience of our people and our tales. Hence the name (Pal: Palestine, Tales).

## Features:
A complete android app was developed that provides the following functionalities:

#### Web Services
- The app reads data from real external web servicces, Only **Volly API** was used.
  _Over time, I have personally compiled a list of Palestinian movies and documentaries, both created by Palestinians and others, accessible through the Internet Movies Database (IMDB) which I have used in this app. I have also created a list of Palestinian books list on the open library database online._
  So this requiremnt is met by these by these two services:
- List of books that delves into the Palestinian narrative:
-     https://openlibrary.org/people/sondos_aabed/lists/OL243427L/Palestinian/export?format=json
- Collection of films, providing a visual representation of our experiences:
-     https://github.com/sondosaabed/Palestinian-Movies-JSON-Dataset/tree/main

### Requiremnts
- The project contains the following activities (was required at least 4):
  - **Create Account Activity:** Users are able to to register and create multiple accounts in the same device. Where there data is saved locally.
  - **Loign Activity**: Provides login Functionality, with a remeber me feature that saves on their local storage (the last account set to remeber me is the one that is remebered).
  - **Home Activity**: let's the user chose if tehy want to broswe movies or books. 
  - **List Activity**: based on what they want to broswe they are shown either a list of movies or a list of books provided by the json response of two web services.
  - **Item Activity**: is shown on click the item (Movie || Book) Details
  - **Tow Informational Activites**:
    - Getting Started: If (is first time) and redirects to create account.
    - Welcome Back: If rember me is set leads to Home, if there is no remeber me then Login.
- The activites have **variety of attributes**: TextEdit(Diffrent input Types), TextView, Button, ListView, ImageView, ScrollView, CheckBox
- The layouts have **variety of Layout Managers**: Contriant Layout, Relative Layout, Linear Layout.
- The project have **Centralized and coceptual design** styles (light and dark themes), that are related to the concept (Palestine Flag Colors) 

<div align="center">
<img src="https://github.com/sondosaabed/PalTales/assets/65151701/3db20286-2448-43e8-83ba-e2076c44faa8" alt="PalTales Logo" width="200" height="200">
<img src="https://github.com/sondosaabed/PalTales/assets/65151701/fd07a2f8-c32e-4d45-8678-f27ed514be54" alt="Palestine Flag Colors" width="300">

    -	#F6F5F4 White
    -	#000000 Black
    -	#993B3B Red
    -	#9A1212 Red Light
    -	#064C2B Green
    -	#A8A196 Grey
</div>

### Additional Features
- Password Encryption using SHA-256 algorithm. (MessageDigest Java)
- One device can create multiple accounts and be saved in their shared prefrences.
- Check if first time users from prefrences.
- Provided an ADMIN account _(I created it for testing although I don't like this logic of me doing that because it was hard coded #TODO)_
- Created Custom List Items using Linear Layout for Book & Movie Object.
- Created Custom Adapters for books & movies.
- Implemneting Generic Programming _(in some cases I fail to do so, but have reduced a lot of code redundancy since there exists intersections in the two Data types)_
- Created (API) interface to be implemnted for both books & movies (I figured since they have the same methods but didn't finish this yet since it's addtional)

### DEMO (UX)

<div align=center>

</div>

#### (UI)
<div>
    <table>
        <tr>
            <th>Getting Started</th>
            <th>Create Account</th>
            <th>Login</th>
            <th>Welcome Back</th>
            <th>Home option</th>
        </tr>
        <tr>
            <td><img src="https://github.com/sondosaabed/PalTales/assets/65151701/2512d561-bda9-4d02-81eb-167eb03fda9b" alt="Getting Started"></td>
            <td><img src="https://github.com/sondosaabed/PalTales/assets/65151701/2cf903ce-852f-40ea-8d7f-385c77dc5d6c" alt="Create Account"></td>
            <td><img src="https://github.com/sondosaabed/PalTales/assets/65151701/1e56c12d-df0f-4025-b86c-56eaef898584" alt="Login"></td>
            <td><img src="https://github.com/sondosaabed/PalTales/assets/65151701/8141c9d1-1d3a-4f5c-845e-6b707aed207b" alt="Welcome Back"></td>
            <td><img src="https://github.com/sondosaabed/PalTales/assets/65151701/8f3e5425-6870-41c6-9021-a8a4e123e6e9" alt="Home option"></td>
        </tr>
        <tr>
            <th>Book List</th>
            <th>Book Item</th>
            <th>Movie List</th>
            <th>Movie Item</th>
        </tr>
        <tr>
            <td><img src="https://github.com/sondosaabed/PalTales/assets/65151701/4d9a903b-0f90-47c6-86a2-fec741718792" alt="Book List"></td>
            <td><img src="https://github.com/sondosaabed/PalTales/assets/65151701/c26ab27c-06ac-4afa-80ca-08a84101de11" alt="Book Item"></td>
            <td><img src="https://github.com/sondosaabed/PalTales/assets/65151701/93ef84c9-ae96-4168-a973-7458a9d517f7" alt="Movie List"></td>
            <td><img src="https://github.com/sondosaabed/PalTales/assets/65151701/c99d4773-13e9-4651-8013-ced2d9e11839" alt="Movie Item"></td>
        </tr>
    </table>
</div>

### Resources 
- ![Stackoverflow custom item of listvew](https://stackoverflow.com/questions/15832335/android-custom-row-item-for-listview)
- ![Stackoverflow custom listAdapter](https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view)
- ![My Palestinian Movies List](https://www.imdb.com/list/ls563010565/?sort=alpha,asc&st_dt=&mode=detail&page=1)
- ![APIfy IMDB Advanced Scrapper](https://console.apify.com/actors/tFtRJkJ8nIiFx2Qq7/console)
- ![My Palestinian Books List](https://openlibrary.org/people/sondos_aabed/lists/OL243427L/Palestinian/export?format=json)
- ![picasso for cached images](https://github.com/square/picasso)
- ![Android Image Loading Libraries?](https://medium.com/@kostadin.georgiev90/android-image-loading-libraries-picasso-vs-glide-vs-coil-90e3fb6c0068)
- ![Volly Documentation](https://google.github.io/volley/)
- ![Login Activity I created before](https://github.com/sondosaabed/Mobile-Application-Login/)
- ![Lecture Code Volley2](https://github.com/szainbzu/volley2/)
- ![Lecture Code Shared Pref2 ](https://github.com/szainbzu/sharedprefs2)
- ![Prefrences I used in prev. assignment](https://github.com/sondosaabed/Taskaty/tree/main)
- ![Prev. Project I worked on Hash for password](https://github.com/sondosaabed/File-Carving-Tool/blob/main/model/CalculateCompareHash.java)
