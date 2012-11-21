= Google+ Platform Hangout Sample App =

If you're reading this, you've probably taken a look at the Hangouts API
reference docs and want to get started creating your own hangout app. This
simple starter project provides simple code to get you started, and the
docs below cover everything from setting up a simple hosting solution to
running your hangout app from within the developer console.

== What's Included ==
This project provides the following fundamental components of a hangout app:

 - static/app.xml, static/app.js
   The app's XML and JavaScript

 - *.yaml, main.py 
   AppEngine configuration files to help you host your app

== Using This Starter ==
Before you start modifying the provided code, it's a good idea to set up your 
hangout app and AppEngine projects. By doing so, you can make sure the starter's
working before you start making things complicated.

== Prerequisites ==
 - The Python AppEngine SDK: http://goo.gl/IdoOW

=== Hosting This App ===
Before we can run the starter, we'll need to make it visible to the world by
hosting it publicly.

1. Visit https://appengine.google.com/ and click 'Create Application'.  Follow
   the on screen instructions to create a new AppEngine Application.

2. Edit app.yaml,  static/app.xml and static/app.js. Replace all three
   occurrences of YOUR_APP_ID with the application identifier you selected in
   step 1.

3. Push the application live by running the following command (you will be
   prompted for your login and password):
   
   > appcfg.py update path-to-starter-app/

   That's it! Your app is now publicly hosted.

=== Running Your Hangout App ==
You're done editing files! Now we need to enable the Hangouts API for your 
project.

1. Visit https://code.google.com/apis/console/?api=plusHangouts to create a
   new project with access to the Google+ Hangouts API or to add the Google+
   Hangouts API to an existing project.

   (Note:
     During the developer preview only members of your project team can run
     your Hangouts app. Add your fellow developers and Hangout App testers to
     the project team by clicking on 'Team' in the left side menu and adding
     their email addresses.
   )

   After you've enabled the Hangouts API, a 'Hangouts' item will appear in the
   left side menu.

2. In the left side menu, click the 'Hangouts' option.

3. In the Gadget URL field enter this value (substituting your AppEngine app ID)
   https://YOUR_APP_ID.appspot.com/static/app.xml and click 'Save'.

4. Click on the 'Enter a hangout!' to create a special development hangout.

5. After entering the hangout, first click 'Hung out' to start the hangout.
   Then, click on the 'Apps' button in the lower left corner of the hangout
   to start your app.

== What's Next? ==
Congratulations! You now have a working foundation from which to realize
your great ideas. 

- Edit the static/app.xml and static/app.js files to use the Hangout APIs.
- Add assets like images and CSS to the /static directory to give your app some
  flare.
- You can edit main.py or create other AppEngine back-end code to do any heavy
  lifting required by your Hangout App.

== Hints ==
- The static/app.xml file must be publicly visible on the Internet for your
  Hangouts app to work, but the assets it loads (including static/apps.js) only
  need to be visible to your web browser. This means that, during testing,
  you can use a local web server and keep your JavaScript on http://localhost.

  (Note:
    While hosting JavaScript locally considerably speeds up your development,
    only you will be able to run your hangout app.
  )

- Keep an eye on the JavaScript console--this starter project logs many useful
  events to the console.

== Getting Help == 
If you somehow got here without finding the documentation, check out
http://developer.google.com/+/hangouts/

If you've found a bug or have questions please visit our developer forum:
http://groups.google.com/group/google-plus-developers

