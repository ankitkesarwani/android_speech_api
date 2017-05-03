# Speech2Text Library for Android

Speech2Text library for Android can be integrated in Android applications. The library is supported from API Level: 18, Android 4.3 (JELLY_BEAN_MR2) 

### Changelog

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

2. Copy the s2t-release library in the libs folder located in the apps folder of your Android application

3. In the app build.gradle, add following dependencies
```sh
      compile 'com.mcxiaoke.volley:library:1.0.17'
      compile 'com.github.wendykierp:JTransforms:3.1'
      compile (name:'s2tlibrary-release', ext:'aar')
```

4. In the app build.gradle, add following snippet
```sh
repositories {
   flatDir {
       dirs 'libs'
   }
}
```

5. In app build.gradle, add the following inside android tag -
```sh
    android {
      packagingOptions {
       exclude 'META-INF/DEPENDENCIES.txt'
       exclude 'META-INF/DEPENDENCIES'
       exclude 'META-INF/dependencies.txt'
       exclude 'META-INF/LICENSE.txt'
       exclude 'META-INF/LICENSE'
       exclude 'META-INF/license.txt'
       exclude 'META-INF/LGPL2.1'
       exclude 'META-INF/NOTICE.txt'
       exclude 'META-INF/NOTICE'
       exclude 'META-INF/notice.txt'
    }
  }
```

6. In proguard-rules.pro, include the following lines
```sh
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }
-dontwarn org.apache.commons.**
-keep class org.apache.http.** { *; }
-dontwarn org.apache.http.**
-keepattributes EnclosingMethod
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
      };
```

2. Initialize the Speech2TextIntent object by using the Speech2TextIntent.Speech2TextIntentBuilder methods.

```java
      Speech2TextIntent s2TIntent = new Speech2TextIntent.Speech2TextIntentBuilder(getActivity(), callbackFromS2T)
                                .setLanguage(Speech2TextIntent.LANGUAGE_HINDI)
                                .setView(Speech2TextIntent.VIEW_KEYBOARD)
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
        Speech2TextIntent.VIEW_KEYBOARD
        Speech2TextIntent.VIEW_POPUP
    ```

-   onError() parameter error code values can be checked comparing to the constants below:
    ```java
            S2TError.ERROR_NO_INTERNET;
            S2TError.ERROR_NO_USER_ID;
            S2TError.ERROR_RECORDING_PERMISSION_NOT_AVAILABLE;
            S2TError.ERROR_READ_EXTERNAL_STORAGE_NOT_AVAILABLE;
            S2TError.ERROR_WRITE_EXTERNAL_STORAGE_NOT_AVAILABLE;
            S2TError.ERROR_WINDOW_OVERLAY_PERMISSION_RESTART_REQUIRED;
    ```

Version

1.14
