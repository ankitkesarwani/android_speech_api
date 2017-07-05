# Speech2Text Library for Android

Speech2Text library for Android can be integrated in Android applications. The library is supported from API Level: 18, Android 4.3 (JELLY_BEAN_MR2) 

### Changelog

- ##### 1.37 2017-07-05
    - Multiple language support, crash fixes, stability fixes

- ##### 1.36 2017-06-22
    - NPE Volley bug fix

- ##### 1.35 2017-06-21
    - Migration from Fabric Crashlytics to HockeyApp for crashes and events

- ##### 1.34 2017-06-16
    - Changed SDK integration to host on private Maven Repository 

- ##### 1.32 2017-06-10
    - Every start service call kills current service and start a new one

- ##### 1.31 2017-06-09
    - Bug fixes

- ##### 1.27 2017-06-01
    - Animation optimizations on the UI thread

- ##### 1.26 2017-05-30
    - Changed scheme to index based splitting and syncing, space after every transcription

- ##### 1.25 2017-05-27
    - Performance optimization

- ##### 1.24 2017-05-26
    - Slow internet handling, timeout changes, rotation fixes

- ##### 1.22 2017-05-25
    - Added Activity support for apps not wanting to create a window overlay and take draw over apps permission

- ##### 1.21 2017-05-24
    - Analytics Crash fix

- ##### 1.20 2017-05-22
    - Split functionality, network and speed optimizations, bug fixes

- ##### 1.19 2017-05-19
    - Google Analytics integrated

- ##### 1.18 2017-05-18
    - Crashlytics integrated

- ##### 1.17 2017-05-18
    - Bugfixes related to service instantiation

- ##### 1.16 2017-05-17
    - More Error types for each case

- ##### 1.15 2017-05-5
    - Close functionality and callback, bug fixes

- ##### 1.14 2017-05-3
    - Error framework and overlay bug fixes

- ##### 1.13 2017-05-2
    - Runtime permissions for window overlay, animation change

- ##### 1.12 2017-04-28
    - Changed Activity interface to callback via service interface in the library

- ##### 1.11 2017-04-20
    - TargetSDK version 25 

- ##### 1.10 2017-04-08
    - Additional Language Support(MARATHI,GUJARATI)

- ##### 1.09 2017-04-05
    - Internal features

- ##### 1.08 2017-03-21
    - Additional Language Support

- ##### 1.07 2016-12-19
    - Stop button for audio recorder

- ##### 1.06 2016-09-20
    - Optimizing the audio recording

- ##### 1.05 2016-07-05
    - Returning intent object if requested
    
- ##### 1.04 2016-07-02
    - Performance optimization
    
- ##### 1.03 2016-06-22
    - Optimized network payload in Speech2Text

- ##### 1.02 2016-03-09
    - Using http-client, http-mime, http-core library version 4.2.5

- ##### 1.01 2016-03-01
   - Released library with Speech2TextIntent to be triggered at the beginning of speech recognition

   
Including S2Tlibrary in Android project

1. Create a new project in Android Studio or Open an existing application project

2. In the app build.gradle, add following outside dependencies, outermost scope

```sh
repositories {
    mavenCentral()
    maven {
        url "https://mymavenrepo.com/repo/5BmmKbLa0MoxjZW3GYAS/"
    }
}
```

3. In the app build.gradle, add following snippet inside dependencies
```sh
    compile ('ai.liv:s2tlibrary:1.35@aar') {
        transitive = true
    }
```

Using s2tlibrary in application code

1. In strings.java located at app/src/res/values/, add the following line
```sh
      <string name="livtoken"><liv.ai_api_token_value></string>
```

  Please replace liv.ai_api_token_value with app token obtained from Liv AI. If you dont have a token, please write to us at hello@liv.ai


2. Create a Speech2TextIntentCallback object to listen to the events from our Library. Transcription results can be read in onTranscriptionReceived() and Error thrown can be read in onError() callbacks.

```java
      Speech2TextIntent.Speech2TextIntentCallback callbackFromS2T = new Speech2TextIntent.Speech2TextIntentCallback() {
            @Override
            public void onTranscriptionReceived(ArrayList<Transcription> transcriptions) {
                //Write Code to handle the list of transcriptions received with their confidence scores
                if (transcriptions.size() > 0) {
                    for(Transcription transcription : transcriptions){
                      Log.d(TAG, "Transcription:"+transcription.getText()+", Confidence:"+transcription.getConfidence());
                    }
                }
            }

            @Override
            public void onError(S2TError error) {
                //Write code to handle each error from the error codes by comparing error.errorCode to constants in Error class
              if(error.errorCode == S2TError.ERROR_NO_USER_ID){
                    Log.d(TAG, "No user id,"+error.message);
              }
            }

            @Override
            public void onRecordingCancelled() {
                //Called when the user explicitly presses the close button
            }
      };
```

2. Initialize the Speech2TextIntent object by using the Speech2TextIntent.Speech2TextIntentBuilder methods.

```java
      Speech2TextIntent s2TIntent = new Speech2TextIntent.Speech2TextIntentBuilder(getActivity(), callbackFromS2T)
                                .setLanguage(Speech2TextIntent.LANGUAGE_HINDI)
                                .setView(Speech2TextIntent.VIEW_KEYBOARD)
                                .setHeightInPixels(540) //Height in Pixels for the view wanted, default height is 260dp
                                .build();
```

3. Call the start service method when the recording and transcription needs to be started

```java
    s2TIntent.setLanguage(lang);
    s2TIntent.startService();
```

4. There is also a provision to stop the Speech2TextIntent service with

```java
    s2TIntent.stopService();
```


The mechanism attaches itself to the Activity Lifecycle, so there is no specific need of stopping service from those methods.


### NOTE:

-   setLanguage() is optional. The default value is Speech2TextIntent.LANGUAGE_ENGLISH.

    setLanguage() supported parameters:
    ```java

        Speech2TextIntent.LANGUAGE_ENGLISH
        Speech2TextIntent.LANGUAGE_HINDI
        Speech2TextIntent.LANGUAGE_TELUGU
        Speech2TextIntent.LANGUAGE_KANNADA
        Speech2TextIntent.LANGUAGE_PUNJABI  
        Speech2TextIntent.LANGUAGE_BENGALI
        Speech2TextIntent.LANGUAGE_GUJARATI
        Speech2TextIntent.LANGUAGE_MARATHI
    ```

-   setView() is optional. The default value is Speech2TextIntent.VIEW_KEYBOARD
    setView() supported parameters:
    ```java
        Speech2TextIntent.ACTIVITY_POPUP
	      Speech2TextIntent.VIEW_KEYBOARD
        Speech2TextIntent.VIEW_POPUP
    ```

-   setHeightInPixels() can be optionally used to set height of the recording view. The default value is 260dp(in pixels)

-   onError() parameter error code values can be checked comparing to the constants below:
    ```java
            S2TError.ERROR_NO_INTERNET
            S2TError.ERROR_NO_USER_ID
            S2TError.ERROR_RECORDING_PERMISSION_NOT_AVAILABLE
            S2TError.ERROR_READ_EXTERNAL_STORAGE_NOT_AVAILABLE
            S2TError.ERROR_WRITE_EXTERNAL_STORAGE_NOT_AVAILABLE
            S2TError.ERROR_WINDOW_OVERLAY_PERMISSION_RESTART_REQUIRED
            S2TError.ERROR_IN_AUDIO
            S2TError.ERROR_NO_AUDIO_FILE_SENT
            S2TError.ERROR_UNAUTHORIZED_REQUEST
            S2TError.ERROR_SERVER_ERROR

    ```


-   Xiaomi MIUI OS by default doesn't give permission to draw overlay above apps and also doesn't allow you to get it on runtime like other OSes. So eventhough the recording is started UI wouldn't be drawn unless the permission is provided. 

Users will have to go to get it as described at http://en.miui.com/thread-304844-1-1.html.


-  It is advised to developers integrating the aar to restrict launch of SDK to only once till the recording transaction is over, disable button click after launch or on the lines. Eventhough multiple calls multiple startService() has been handled, it can lead to inconsistent conditions/states of the SDK.

Version

1.37
