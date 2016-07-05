# Speech2Text Library for Android

Speech2Text library for Android can be integrated in Android applications. The library is supported from API Level: 18, Android 4.3 (JELLY_BEAN_MR2) 

### Changelog
- ##### 1.05 2016-07-05
    - Returining intent object if requested
    
- ##### 1.04 2016-07-02
    - Performance optimization
    
- ##### 1.03 2016-06-22
    - Optimized network payload in Speech2Text

- ##### 1.02 2016-03-09
    - Using http-client, http-mime, http-core library version 4.2.5

- ##### 1.01 2016-03-01
   - Released library with Speech2TextIntent to be triggered at the beginning of speech recognition


### Including S2Tlibrary in Android project

- Create a new project in Android Studio or Open an existing application project
- Copy the s2t-release library in the **libs** folder located in the **apps** folder of your Android application
- In the app build.gradle, add following dependencies 
```sh
compile 'com.mcxiaoke.volley:library:1.0.17'
compile 'com.github.wendykierp:JTransforms:3.1'
compile (name:'s2tlibrary-release', ext:'aar')
```

- In the app build.gradle, add following snippet
```sh
repositories {
   flatDir {
       dirs 'libs'
   }
}
```

- In app build.gradle, add the following inside android tag -
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

- In proguard-rules.pro, include the following lines -
```sh
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }
-dontwarn org.apache.commons.**
-keep class org.apache.http.** { *; }
-dontwarn org.apache.http.**
```


### Using s2tlibrary in application code
 - In strings.java located at app/src/res/values/, add the following line 
 ```sh
 <string name="livtoken"><liv.ai_api_token_value></string>
 ```
 Please replace liv.ai_api_token_value with app token obtained from Liv AI. If you don't have a token, please write to us at hello@liv.ai
 
 - Create a Speech2TextIntent and pass necessary flags to it when you wish to trigger Speech recognition. The allowed flags are Speech2TextIntent.LANGUAGE and Speech2TextIntent.INTENT. 
 
 - Values of Speech2TextIntent.LANGUAGE flag can be Speech2TextIntent.LANGUAGE_ENGLISH or Speech2TextIntent.LANGUAGE_HINDI. Speech2TextIntent.LANGUAGE flag is optional.

```sh
int REQUEST_CODE = 1;
Intent i = new Intent(getActivity(), Speech2TextIntent.class);
i.putExtra(Speech2TextIntent.LANGUAGE, Speech2TextIntent.LANGUAGE_ENGLISH);
startActivityForResult(i, REQUEST_CODE);
```
 
 - Values of Speech2TextIntent.INTENT flag can be Speech2TextIntent.INTENT_ENABLE or Speech2TextIntent.INTENT_DISABLE. Speech2TextIntent.INTENT flag is also optional.

```sh
int REQUEST_CODE = 1;
Intent i = new Intent(getActivity(), Speech2TextIntent.class);
i.putExtra(Speech2TextIntent.INTENT, Speech2TextIntent.INTENT_ENABLE);
startActivityForResult(i, REQUEST_CODE);
```

- Once the speech input is done, you need to read the results from onActivityResult and take appropriate actions. The result can be read from data.getStringArrayExtra("resultList") or from data.getBundleExtra("intent") of the passed intent.

```sh
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String[] results = data.getStringArrayExtra("resultList"); } } }
```

```sh
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Bundle bundle= data.getBundleExtra("intent"); } } }
```

- The Speech2TextIntent returns a Bundle object having values with keys "action","vehicle","processed_time" and "time". If Speech2TextIntent.INTENT_DISABLE is passed the Bundle is returned with values as empty string for the given keys 

- Example:
For the string 'book an uber to indira nagar' following is the json output
{"action": "book_cab", "destination": "indira nagar", "time": "now", "processed_time": "2:10 PM\t05-07-2016", "vehicle": "uber"}

For the string 'book an uber' following is the json output
{"action": "book_cab", "destination": "UNK", "time": "now", "processed_time": "2:10 PM\t05-07-2016", "vehicle": "uber"}

If the value for a key is not known, its default value is set to "UNK" except for the key "time" which has a default value of "now"
### Version
1.05
