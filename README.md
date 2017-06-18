![Logo](https://raw.githubusercontent.com/jemshit/EliteMvp/master/files/https://raw.githubusercontent.com/jemshit/EliteMvp/master/files/elite_mvp_name.png)

:zap: **Simple, Tiny, Extendable** Android MVP library. No complex Base Activity, Base Fragment or Base View. Just base Presenters and View interface for you to extend from. For MVP Cheatsheet <a href="https://github.com/jemshit/EliteMvp/tree/master/files/mvp_cheatsheet.pdf">read this.</a>

[![](https://jitpack.io/v/com.jemshit/elitemvp.svg)](https://jitpack.io/#com.jemshit/elitemvp)



### Installation

Step 1. Add JitPack repository to your top level **build.gradle** file
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Step 2. Add library dependency to your module **build.gradle** file
```groovy
dependencies {
    ...
    compile 'com.jemshit.EliteMvp:2.0.1'
}
```

### Structure

There are 5 base Presenter classes to extend from and 1 base View interface.

- **EliteView:** empty View interface. Your custom View interfaces must extend this.
- **EliteCorePresenter:** Presenter interface with only 2  methods (`attachView(V)`,`detachView()`). Your custom Presenter can implement this or you can use one of the ready Presenters listed below.
- **ElitePresenter:** Simple Presenter with ready methods (`getView()`,`isViewAttached()`, `onDestroy()`) for holding View reference. Your custom Presenter can extend this and add additional methods.
- **EliteRx1Presenter:** Simple Presenter that has 2 additional methods (`addToOnAttachSubscriptions(s)`, `addToOnCreateSubscriptions(S)`) for adding RxJava 1 `Subscription`s into `CompositeSubscription`.
- **EliteRx2Presenter:** Simple Presenter that has 2 additional methods (`addToOnAttachDisposables(D)`, `addToOnCreateDisposables(D)`) for adding RxJava 2 `Disposable`s into `CompositeDisposable`.
- **EliteNullViewPresenter:** Presenter which returns empty View after View is detached, so no need for `view!=null` check. Returned empty View methods does nothing when called, read more about <a href="https://en.wikipedia.org/wiki/Null_Object_pattern">Null Object Pattern here.</a>


UML Diagram:

![Uml](https://raw.githubusercontent.com/jemshit/EliteMvp/master/files/uml.png)


### Usage

```java
public interface YourActivityContract {
    interface View extends EliteView{
        void viewMethod();
    }

    abstract class Presenter extends ElitePresenter<YourActivityContract.View> {
        public abstract void presenterMethod();
    }
}
```

```java
public class YourActivityPresenter extends YourActivityContract.Presenter{

    @Override public void presenterMethod() {
        if(isViewAttached())    
            getView().viewMethod();
    }
}
```

```java
public class YourActivity extends AppCompatActivity implements YourActivityContract.View { {

    private YourActivityContract.Presenter presenter;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your);

        presenter = new YourActivityPresenter();
        presenter.attachView(this);
        presenter.presenterMethod();
    }

    @Override public void viewMethod(){...}

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
```

:o: Check <a href="https://github.com/jemshit/EliteMvp/tree/master/sample/src/main/java/com/jemshit/elitemvpsample">Samples</a> for more examples.


:o: For Orientation Change, Presenter Retaining, Caching... check applied solutions from community <a href="https://github.com/jemshit/EliteMvp/tree/master/files/mvp_cheatsheet.pdf">here</a>
