package com.cr.o.cdc.sandboxAndroid.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cr.o.cdc.sandboxAndroid.pagination.model.InfoSearchRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.model.PagedRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.model.Recipe

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(list: List<Recipe>)

    @Query("SELECT * FROM Recipe WHERE uri =:uri")
    fun loadRecipe(uri: String): LiveData<Recipe?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(product: Recipe)

    @Query("DELETE FROM info_search_recipe WHERE search == :search")
    fun delete(search: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveOffSet(items: List<InfoSearchRecipe>)

    @Query(
        "SELECT * FROM info_search_recipe LEFT JOIN recipe ON recipe_uri == uri WHERE search == :search"
    )
    fun loadPaged(search: String): DataSource.Factory<Int, PagedRecipe>
}