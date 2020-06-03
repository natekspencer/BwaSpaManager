# BWA Spa Manager
Free SmartThings integration for Balboa Worldwide Group Spas controlled by the Balboa Worldwide - Spa Control app (BWA) (probably not compatible with ControlMySpa)

This SmartApp offers integration with [Balboa Water Group](http://www.balboawater.com/bwa) WiFi enabled devices to [SmartThings](https://www.smartthings.com/).

> I'm not employed by BWG/BWA, and provide this SmartApp purely for our own enjoyment and home automation needs. 
>

NOTE: You must use the SmartThings Classic app as the new app does not support Device Handlers

Step 1 - Install Using GitHub Integration
1. Login to the SmartThings IDE at graph.api.smartthings.com
2. Click on "My Locations" at the top of the page
3. Click on the name of the location that you want to install to
4. Click on the "My Device Handlers" tab
5. Click "Settings"
6. Click "Add new repository" and use the following parameters:
-- Owner: natekspencer
-- Name: BwaSpaManager
-- Branch: master
7. Click Save

Step 2 - Install Device Handler
Once the GitHub repository is connected to your IDE, utilize the integration to install the current device handler version into your workspace. In the IDE:
1. Click "Update from Repo" and select the BwaSpaManager repository you just added
2. Select "bwa-spa.groovy"
3. Select "Publish" (bottom right of screen near the "Cancel" button)
4. Click "Execute Update"
5. Verify that the device shows up in the list and is marked with Status "Published". You may need to refresh the page

Step 3 - Install SmartApp
Once you have the Device Handler added and published in your IDE, it is time to add the SmartApps
1. Click on the "My SmartApps" tab
2. Click "Update from Repo" and select the BwaSpaManager repository you added earlier
3. Select "bwa-spa-manager.groovy"
4. Select Publish (bottom right of screen near the "Cancel" button)
5. Click "Execute Update"
6. Verify that the SmartApp shows up in the list and is marked with Status "Published". You may need to refresh the page

Step 4 - Add a SmartApp on mobile device
Follow these steps for the SmartApp on your mobile device:
1. Open the SmartThings app and select your Location
2. Open the "Marketplace" tab
3. Open the "SmartApps" tab
4. Scroll down and select "My Apps" (at the bottom of the list)
5. Find and open the "BWA Spa Manager" SmartApp
6. Click on "Cloud Authorization"
7. Enter your "User ID" and "Password", then click "Next"
8. If successful, you should now see a response that you were logged in and to click "Next". Otherwise, go back and try again
9. Now, select your "Spas" and then click "Save". You may also choose a different "Polling Interval" and "Assign a name" to the SmartApp

You should now be able to go to your home's "Things" and find the spa device that was just created. By opening preferences on the device, you can rename your device and set a default "On" temperature if you desire