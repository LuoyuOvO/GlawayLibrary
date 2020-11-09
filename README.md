# GlawayLibrary
国睿信维android基本依赖
>
>
>***Glaway Android Library 使用（里面包含了其他项目，如果运行使用报错，可以根据需要添加依赖，依赖在说明尾部）***

1. BaseApplication

   ```
   BaseApplication ：
   onCreate  初始化SQLiteStudio，初始化logger日志，初始化文件压缩/解压配置数据库
   initGreenDao 抽象方法，初始化项目数据库和Bean
   ```

2. ZiipUtil（不支持多线程）

   ```
   ZipUtil：
   readFile，读取zip文件。返回文件名称和数据的Map集合
   writeFile，，写入文件，传递文件名称和数据
   zip：压缩指定目录的文件（目录指定在上面写死的路径）
   ```

3. ToastUtil

   ```
   ToastUtil：
   showSuccess：显示成功提示
   showError：显示错误提示
   showErrorLong：显示错误长时间提示
   showInfo：显示信息
   showWarning：显示警告信息
   ```

4. RegexUtil

   ```
   RegexUtil:常用的正则表达式
   ```

5. PermissionUtil

   ```
   PermissionUtil：权限申请
   isGetPermissions：是否需要申请权限
   applyPermissions：申请权限，String[]的权限数组，和回调接口
   ```

6. JSONUtil

   ```
   JSONUtil：
   removeNullAttr：除去jsonObject中空值
   toJsonStr：lombok的toString转json
   ListStringToJsonArr：ListData转换成jsonarrary
   ```

7. FileUtil

   ```
   FileUtil：
   isDir：判断是否是目录
   fileDelete：删除文件
   fileisExit：判定文件是否存在，存在则删除重建，不存在则新建
   ```

   

8. DateUti

   ```
   DateUtil：
   dateToString：时间转字符串
   dateToStringTime：时间转字符串，带时分秒
   dataToString：格式化时间为String
   getCurrYearFirst：获取当年的第一天
   getCurrYearLast：获取当年的最后一天
   getYearFirst：获取某年第一天日期
   getYearLast：获取某年最后一天日期
   getYear：获取年
   getMonth：获取月
   getDay：获取日
   getHour：获取时
   getMinute：获取分
   ```

   l

9. BaseDialog

   ```
   BaseDialog：继承DialogFragment，初始化基本弹出样式，
   initView：初始化界面
   initData：初始化数据
   checkInput：检测输入
   setCallback：回调接口
   ```

10. MyBaseAdapter

    ```
    MyBaseAdapter：继承BaseAdapter
    CallType：回调方式
    MyBaseAdapter：传递Context，和objectList的list
    AdapterCallback：回调接口
    ```

11. BaseActivity

    ```
    BaseActivity：继承AppCompatActivity
    ```

    



>
>
>**项目使用gradle如下**

1. 首先项目gradle配置

```properties
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
    		//maven库的使用
        mavenCentral() 
        google()
        jcenter()
        
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.3'
				//添加依赖
        classpath 'org.greenrobot:greendao-gradle-plugin:3.3.0' // add plugin
        classpath 'com.jakewharton:butterknife-gradle-plugin:10.2.3'
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.8'
    }
}

allprojects {
    repositories {
				//使用jitpack
        maven {
            url "https://jitpack.io"
        }
        google()
        jcenter()
        
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

```

2. app gradle配置

```properties
apply plugin: 'com.android.library'
//添加部分
apply plugin: 'org.greenrobot.greendao' 
apply plugin: 'com.jakewharton.butterknife'
apply plugin: 'android-aspectjx'


android {
    compileSdkVersion 29
    buildToolsVersion "30.0.2"

		//注意：最小Api为24
    defaultConfig {
        minSdkVersion 24
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles 'consumer-rules.pro'
        //lombok添加如下配置 开启annotation processor
        javaCompileOptions {
            annotationProcessorOptions {
                includeCompileClasspath true
            }
        }
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    //使用jdk1.8
    compileOptions {
        sourceCompatibility = 1.8
        targetCompatibility = 1.8
    }

}

//添加部分
greendao {
    schemaVersion 1
    daoPackage 'www.glaway.assetmanagementsystem.dao'
    targetGenDir 'src/main/java'
}

//添加部分
aspectjx {
    //指定只对含有关键字'universal-image-loader', 'AspectJX-Demo/library'的库进行织入扫描，忽略其他库，提升编译效率
//    includeJarFilter 'universal-image-loader', 'AspectJX-Demo/library'
//    excludeJarFilter '.jar'
//    ajcArgs '-Xlint:warning'
    //exclude 'com.google','com.appsflyer','com.android'
    exclude 'com.google'
//    exclude 'com.google.firebase','com.google.android'

}

dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation 'androidx.appcompat:appcompat:1.2.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
    implementation files('libs/SQLiteStudioRemote.jar')
    implementation files('libs/jackson-all-2.8.0.jar')



		//添加部分
    implementation 'org.greenrobot:greendao:3.3.0'
    implementation 'com.orhanobut:logger:2.2.0'
    compile 'com.squareup.tape2:tape:2.0.0-beta1'
    implementation 'com.jakewharton:butterknife:10.2.3'
    annotationProcessor 'com.jakewharton:butterknife-compiler:10.2.3'
    implementation 'com.jakewharton:disklrucache:2.0.2'
    implementation 'com.squareup.okhttp3:okhttp:4.9.0'
    //下/上拉刷新
    implementation 'com.mbg.refreshrelativelayout:library:1.3.2'
    //toast
    implementation 'com.github.GrenderG:Toasty:1.4.2'
    //缓存
    implementation 'com.safframework:saf-cache:1.1.0'
    implementation 'org.projectlombok:lombok:1.16.8'
    //添加lombok依赖
    implementation 'org.glassfish:javax.annotation:10.0-b28'
    //java注解
    implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
    implementation 'io.reactivex.rxjava3:rxjava:3.0.0'
    implementation 'org.greenrobot:eventbus:3.1.1'
    implementation 'com.blankj:utilcode:1.30.4'

    compile 'cn.yipianfengye.android:zxing-library:2.2'

    //echarts
    compile 'com.github.abel533:ECharts:3.0.0.2'
    implementation 'com.google.code.gson:gson:2.8.1'
    //MPAndroidChart
    implementation 'com.github.PhilJay:MPAndroidChart:v3.0.3'
    //文件选择
    compile 'ru.bartwell:exfilepicker:2.1'

}

```

3. lombok配置（可选）

```properties
新建lombok.config（和app目录平级）

lombok.anyConstructor.suppressConstructorProperties=true
config.stopBubbling=true
lombok.equalsAndHashCode.callSuper=call
```

>
>
>项目内容说明



