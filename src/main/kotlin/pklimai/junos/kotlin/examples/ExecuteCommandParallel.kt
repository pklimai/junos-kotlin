package pklimai.junos.kotlin.examples

import kotlinx.coroutines.*
import pklimai.junos.kotlin.lib.buildDevice

/*
  Similar to ExecuteCommand.kt, but using coroutines
  TODO: not working as it should yet
 */

fun main() = runBlocking {
    val results = mutableMapOf<String, Deferred<String>>()

    for (host in HOSTS_LIST) {
        results[host] = async {
            val dev = buildDevice(host, USERNAME, PASSWORD)
            dev.connect()
            val res = dev.runCliCommand("show interfaces ge-0/0/0.0 terse")
            dev.close()
            println("Closing $host")
            res
        }
    }

    for (host in HOSTS_LIST) {
        val output = results[host]!!.await()
        println(host)
        println(output)
        println()
    }
}
