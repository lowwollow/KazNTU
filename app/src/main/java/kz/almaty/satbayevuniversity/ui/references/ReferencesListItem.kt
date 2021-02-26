package kz.almaty.satbayevuniversity.ui.references

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kz.almaty.satbayevuniversity.ui.references.history.TestData
import kotlin.math.log

object ReferencesListItem{

    // Object which stores list of items in Shared pref

    private var listOfItems: MutableList<TestData>? = null
    private const val LIST_OF_ITEMS = "LIST_OF_ITEMS"
    private val gson = Gson()


    fun addItem(item:TestData, context: Context){
        val favorites = getListOfItems(context)
        // let is a lambda exp
        favorites?.let {
            //it.inL = true
            favorites.add(item)
            saveListOfItems(LIST_OF_ITEMS, favorites, context)
        }
    }

    fun getListOfItems(context: Context): MutableList<TestData>? {
        if (listOfItems == null) {
            val json = sharedPrefs(context).getString(LIST_OF_ITEMS, "")
            val type = object : TypeToken<MutableList<TestData>>() {}.type
            listOfItems = gson.fromJson<MutableList<TestData>>(json, type) ?: return mutableListOf()
        }
        return listOfItems
    }

    fun removeItem(item: TestData, context: Context) {
        val listOfItems = getListOfItems(context)
        listOfItems?.let {
            //creature.isFavorite = false
            listOfItems.remove(item)
            saveListOfItems(LIST_OF_ITEMS, listOfItems, context)
        }
    }

    private fun saveListOfItems(key: String, list: List<TestData>, context: Context) {
        val json = gson.toJson(list)
        sharedPrefs(context).edit().putString(key, json).apply()
    }

    private fun sharedPrefs(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)


}
