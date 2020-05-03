package com.cr.o.cdc.sandboxAndroid.pagination.db.model

import androidx.room.Embedded

data class PagedRecipe(@Embedded val recipe: Recipe, val from: Int)
