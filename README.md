# EliteMVP

[![](https://jitpack.io/v/com.jemshit/elitemvp.svg)](https://jitpack.io/#com.jemshit/elitemvp)

This is basic, tiny Android MVP library.

### Usage

```java
public interface YourActivityMvp {
    interface View extends EliteView{
        void viewMethod();
    }

    abstract class Presenter extends ElitePresenter<YourActivityMvp.View> {
        public abstract void presenterMethod();
    }
}
```

```java
public class YourActivityPresenter extends YourActivityMvp.Presenter{

    public YourActivityPresenter(){
        super.onCreate();   
    }

    @Override public void presenterMethod() {
        if(isViewAttached())    
            getView().viewMethod();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Clear your references, close your instances if needed ...
    }
}
```

```java
public class YourActivity extends AppCompatActivity implements YourActivityMvp.View { {
    private YourActivityMvp.Presenter presenter;
    
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
       
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

Check [sample](https://github.com/jemshit/EliteMvp/tree/master/sample) for more examples.

### Library Files
There are total 4 Java classes in library.

1. **interface EliteView:** Base Empty *View* interface. You can extend this if you want to build your custom Base *View*
2. **interface EliteCorePresenter:** Base *Presenter* interface with 2 methods, *void attachView(EliteView V)* and *void detachView()*
3. **class ElitePresenter:** Complete Base *Presenter* class. It has *WeakReference<EliteView>* for storing *View* instance and helper methods for *View* instance: 
```
    -EliteView getView(): //returns 'EliteView' instance if exists

    -boolean isViewAttached(): //returns if 'EliteView' is attached
```

Other methods:
```
    -void onCreate(): // needs to be called manually

    -void onDestroy():// needs to be called manually, internally it also calls 'detachView()'

    -void attachView(EliteView V):// stores 'EliteView' instance in 'WeakReference<EliteView>'
                                  // needs to be called manually

    -void detachView(): // needs to be called manually or can be called through 'onDestroy()', removes 'EliteView' instance
```

4. **class EliteRxPresenter:** Complete Base *Presenter* class extends **ElitePresenter**.

It contains two *CompositeSubscription* instances with different lifetimes:
```
    -CompositeSubscription subscriptionsAllLifetime: //lives between onCreate()-onDestroy()
    
    -CompositeSubscription subscriptionsAttachLifetime: //lives between onAttach(V)-onDetach()
```


### Installation

Step 1. Add the JitPack repository to your top level build.gradle file
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Step 2. Add the dependency to your module build.gradle file
```groovy
dependencies {
    ...
    compile 'com.jemshit.elitemvp:1.1.1'
}
```
