package com.cr.o.cdc.sandboxAndroid.coronavirus.model

import com.cr.o.cdc.sandboxAndroid.coronavirus.db.model.CountryStat

data class CasesByCountry(val countries_stat: List<CountryStat>)