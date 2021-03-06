
# [LLogger](https://github.com/LiCola/LLogger)

[ ![Download](https://api.bintray.com/packages/licola/maven/LLogger/images/download.svg) ](https://bintray.com/licola/maven/LLogger/_latestVersion)

# 作用
日志工具，支持更多信息的打印
 - 支持打印行号、方法、内部类名
 - 支持在Logcat中的点击行号跳转代码
 - 支持空参，单一参数，多参数打印
 - 支持log日志信息写入本地文件,以时间为节点，避免日志内容过长，且支持获取和压缩打包log文件
 - 支持Java环境log打印，如在android的test本地单元测试中打印
 - 支持JSON字符串、JSON对象、JSON数组友好格式化打印
 - 支持超长4000+字符串长度打印

# 引用

```java
  implementation "com.licola:llogger:1.3.1"
```

# 使用
静态方法，一行代码调用
```java
    LLogger.d();
    LLogger.d("debug");
    LLogger.d("debug", "more info");
```

# 配置
LLogger默认打印log，默认tag为```LLogger```，默认不写入log文件，导入即可用。

在实际项目需要配置参数，建议在```Application```类中初始化配置，下面示例代码
```java
/**
 * Created by LiCola on 2018/5/14.
 */
public class MyApplication extends Application {

  public static final String LOG_FILE_PREFIX = "LLogger_";
  public static final String LOG_FILE_DIR = "log-file";

  private static final boolean showLog = BuildConfig.DEBUG;

  private static final String TAG = "Demo";

  @Override
  public void onCreate() {
    super.onCreate();
//    LLogger.init(showLog);//打开log显示
//    LLogger.init(showLog, TAG);//打开log显示 配置Tag

    //建议在cache下指定二级目录 存放log文件 避免cache中文件杂乱
    File logDir = new File(getCacheDir(), LOG_FILE_DIR);
//    LLogger.init(showLog, TAG, logDir);//打开log显示 配置Tag log信息写入本地目录
    LLogger.init(showLog, TAG, logDir, LOG_FILE_PREFIX);//打开log显示 配置tag log信息写入本地目录 并固定log文件后缀
  }
}
```

# 效果图

![log信息](https://github.com/LiCola/LLogger/blob/master/image/log.png)

# 关于log本地文件
可以看到上图的```llogger_2018-05-22_10.log```log文件信息。

**文件名格式：文件名前缀_日期信息_小时信息**

因为只要开启写入本地文件功能，程序运行就会log日志打印，为了避免信息太过冗长。
采用小时为节点，写入到本地文件，效果如下

![log文件信息](https://github.com/LiCola/LLogger/blob/master/image/log-file.png)

# 获取log文件
因为log文件以时间为节点分开，会存在大量的日志文件，所以提供静态方法获取日志文件列表或压缩日志文件
```java
    List<File> files = LLogger.fetchLogList();//获取所有的日志文件列表
    List<File> files = LLogger.fetchLogList(24);//获取前24小时内的日志文件列表

    File logZipFile = LLogger.makeLogZipFile("log.zip");//打包所有的日志文件
    File logZipFile = LLogger.makeLogZipFile("log.zip", 0);//打包当前时间的日志文件 ，如果为0表示当前小时

```

# 关于Java环境
LLogger内部初始化时判定运行环境，如果是Java环境（比如android单元测试test目录下运行本地测试代码环境），也可以打印出log信息
![java环境-log信息](https://github.com/LiCola/LLogger/blob/master/image/java-log.png)

在IDEA的纯Java环境也可以在Run中打印出信息

基本仿照Logcat格式：也是支持点击行号跳转代码
```log
log：05-22 15:05:51.316 main Verbose/LLogger: [ (ExampleUnitTest.java:19)#LLoggerTest ] verbose
格式：日期 时间 线程名 log类型 log的Tag：[ (类名:行号)#方法名 ] 参数
```

# 参考
本项目基础参考自：[KLog](https://github.com/ZhaoKaiQiang/KLog)。感谢提供基础思路。