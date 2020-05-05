package com.cr.o.cdc.sandboxAndroid.downdetector.repos

import com.cr.o.cdc.sandboxAndroid.downdetector.model.PingResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class SitesDataSourceTest {

    private val dataSource = SitesDataSource()

    @Test
    fun `assertPingReturnPingResponsePingMalformedURLExceptionWith www palabras com ar`() {
        assertEquals(
            dataSource.ping("www.palabras.com.ar")::class,
            PingResponse.PingError::class
        )
    }

    @Test
    fun `assertPingReturnPingResponsePingIOException https www sdsdsdsd com ar`() {
        assertEquals(
            dataSource.ping("https://www.sdsdsdsd.com.ar")::class,
            PingResponse.PingError::class
        )
    }

    @Test
    fun `assertPingReturnPingResponsePingSuccess https api prod wabicasa com graphiql`() {
        assertEquals(
            dataSource.ping("https://api.prod.wabicasa.com/graphiql")::class,
            PingResponse.PingSuccess::class
        )
    }

    @Test
    fun `assertPingReturnPingResponsePingError https api qa yopago app graphiql`() {
        assertEquals(
            dataSource.ping("https://api.qa.yopago.app/graphiql")::class,
            PingResponse.PingError::class
        )
    }
}
