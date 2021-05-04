Technologies

-Dagger 2 
-Retrofit 
-Coroutine
-Room DB
-MVVM


This project using dagger 2 for this if you want to add new activity you must add AppPages class

Step 1:

For example

@ActivityScoped @ContributesAndroidInjector abstract fun bindACNoteCreated(): ACNoteCreate

This project architecture dependency view models for this if you add the new activity It must contain ViewModel Class and you need to add AppViewModels class

Step 2:

  @Binds
    @IntoMap
    @ViewModelKey(ACNoteCreatedViewModel::class)
    abstract fun bindACNoteCreatedViewModel(repoViewModel: ACNoteCreatedViewModel): ViewMode
Swipe deleted using generically if you want change customize this area you should change SwipeToDeleteCallback

TextView is a custom view

if you want to use this textview you can add style for size and bold italic normal etc.

 <com.igasystem.noteapp.utils.TextView
             style="@style/TextNormal"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:layout_marginTop="8dp"
             android:text="@string/image_url"
             android:textColor="@color/colorPrimary" />
