package com.cr.o.cdc.sandboxAndroid.downdetector.repos

import com.cr.o.cdc.sandboxAndroid.downdetector.model.PingResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class SitesDataSourceTest {

    private val dataSource = SitesDataSource()

    // TODO

    @Test
    fun `assertPingReturnPingResponsePingMalformedURLExceptionWith www palabras com ar`() {
        assertEquals(
            PingResponse.PingError::class,
            dataSource.ping("www.palabras.com.ar")::class
        )
    }

    @Test
    fun `assertPingReturnPingResponsePingIOException https www sdsdsdsd com ar`() {
        assertEquals(
            PingResponse.PingError::class,
            dataSource.ping("https://www.sdsdsdsd.com.ar")::class
        )
    }

    @Test
    fun `assertPingReturnPingResponsePingSuccess https api prod wabicasa com graphiql`() {
        assertEquals(
            PingResponse.PingSuccess::class,
            dataSource.ping("https://api.prod.wabicasa.com/graphiql")::class
        )
    }

    @Test
    fun `assertPingReturnPingResponsePingError https api qa yopago app graphiql`() {
        assertEquals(
            PingResponse.PingError::class,
            dataSource.ping("https://api.qa.yopago.app/graphiql")::class
        )
    }
}
