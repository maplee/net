# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepparameternames
-renamesourcefileattribute seven
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,EnclosingMethod
-optimizationpasses 1 # 指定代码的压缩级别
-keep class com.matt.module.net.inner.model.* {*;}
-keep class com.matt.module.net.inner.parse.* {*;}
-keep class com.matt.module.net.inner.InspectApi {*;}
-keep class com.matt.module.net.openapi.* {*;}

#okhttp
-dontwarn okio.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable,EnclosingMethod

-dontwarn com.squareup.**

-keep class okio.** {*;}
-keep class com.squareup.** {*;}
-keep public class org.codehaus.* { *; }
-keep public class java.nio.* { *; }

-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
#retrofit2  混淆
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keep class retrofit2.http.** {*;}
