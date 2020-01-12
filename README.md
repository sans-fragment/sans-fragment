# sans-fragment

Easy to use "Fragment" replacement for multiscreen single activity apps

Add lib to project: <br />
in root build.gradle
```groovy
 allprojects {
  repositories {
   //...
   maven { url 'https://jitpack.io' }
  }
 }
```
in app module build.gradle
```groovy
 dependencies {
  //...
  implementation 'com.github.sans-fragment:sans-fragment:1.0.8'
 }
```
Example of use:

<image src=sans-fragment-demo.gif width=300 />

```kotlin

enum class Screen {
    FIRST, SECOND
}

class MainActivity : AppCompatActivity() {

    private val screenContainer: ViewStackFrameLayout by lazy {
        findViewById<ViewStackFrameLayout>(R.id.screen_container)
    }

    private lateinit var screenNavigator: Navigator<Screen>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenNavigator = screenContainer.setAdapter { container, screen, navigator ->
            when (screen) {
                Screen.FIRST -> Screen1ViewHolder(container, navigator)
                Screen.SECOND -> Screen2ViewHolder(container, navigator)
            }
        }

        screenNavigator.set(Screen.FIRST) // or set restored stack, see example "backstack"
    }

    override fun onBackPressed() {
        if (screenNavigator.goBack(Transitions.SLIDE_OUT)) {
            return
        }
        finish()
    }
}

class Screen1ViewHolder(container: ViewStackFrameLayout, navigator: Navigator<Screen>) :
    ViewHolder(container, R.layout.view_screen_1) {
    val button: Button = rootView.findViewById(R.id.button)

    init {
        button.setOnClickListener {
            navigator.push(Screen.SECOND, Transitions.SLIDE_IN)
        }
    }
}

class Screen2ViewHolder(container: ViewStackFrameLayout, navigator: Navigator<Screen>) :
    ViewHolder(container, R.layout.view_screen_2) {
    val button: Button = rootView.findViewById(R.id.button)

    init {
        button.setOnClickListener {
            navigator.push(Screen.FIRST, Transitions.SLIDE_IN)
        }
    }
}
```
