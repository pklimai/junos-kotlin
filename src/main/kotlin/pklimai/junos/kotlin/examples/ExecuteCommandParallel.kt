package pklimai.junos.kotlin.examples

import kotlinx.coroutines.*
import pklimai.junos.kotlin.lib.buildDevice
import pklimai.junos.kotlin.lib.execute

/*
  Similar to ExecuteCommand.kt, but using Kotlin coroutines
  Note: Parallel execution works thanks to coroutines using different threads, as NETCONF library
  is using blocking I/O

  Example output:

[DefaultDispatcher-worker-4] INFO net.juniper.netconf.Device - Connecting to host 10.254.0.41 on port 830.
[DefaultDispatcher-worker-1] INFO net.juniper.netconf.Device - Connecting to host 10.254.0.37 on port 830.
[DefaultDispatcher-worker-2] INFO net.juniper.netconf.Device - Connecting to host 10.254.0.42 on port 830.
[DefaultDispatcher-worker-3] INFO net.juniper.netconf.Device - Connecting to host 10.254.0.35 on port 830.
[DefaultDispatcher-worker-2] INFO net.juniper.netconf.Device - Connected to host 10.254.0.42 - Timeout set to 5000 msecs.
[DefaultDispatcher-worker-4] INFO net.juniper.netconf.Device - Connected to host 10.254.0.41 - Timeout set to 5000 msecs.
[DefaultDispatcher-worker-3] INFO net.juniper.netconf.Device - Connected to host 10.254.0.35 - Timeout set to 5000 msecs.
[DefaultDispatcher-worker-1] INFO net.juniper.netconf.Device - Connected to host 10.254.0.37 - Timeout set to 5000 msecs.
10.254.0.35:
Interface               Admin Link Proto    Local                 Remote
ge-0/0/0.0              up    up   inet     10.254.0.35/24
[DefaultDispatcher-worker-3] INFO net.juniper.netconf.Device - Connecting to host 10.254.0.31 on port 830.
10.254.0.37:
Interface               Admin Link Proto    Local                 Remote
ge-0/0/0.0              up    up   inet     10.254.0.37/24
10.254.0.41:
Interface               Admin Link Proto    Local                 Remote
ge-0/0/0.0              up    up   inet     10.1.0.1/24
                                   multiservice
10.254.0.42:
Interface               Admin Link Proto    Local                 Remote
ge-0/0/0.0              up    up   inet     10.1.0.2/24
                                   multiservice
[DefaultDispatcher-worker-3] INFO net.juniper.netconf.Device - Connected to host 10.254.0.31 - Timeout set to 5000 msecs.
10.254.0.31:
Interface               Admin Link Proto    Local                 Remote
ge-0/0/0.0              up    up   inet     10.254.0.31/24

*/

fun main() = runBlocking {
    val jobs = mutableMapOf<String, Job>()
    for (host in HOSTS_LIST) {
        jobs[host] = GlobalScope.launch {

            buildDevice(host, USERNAME, PASSWORD).execute {
                println(host + ":\n" + runCliCommand("show interfaces ge-0/0/0.0 terse"))
            }

            // Does the same as above, but you get explicit warning 'Inappropriate blocking
            // method call' from the IDE
            /*
            val dev = buildDevice(host, USERNAME, PASSWORD)
            dev.connect()
            val res = dev.runCliCommand("show interfaces ge-0/0/0.0 terse")
            dev.close()
            println("$host:\n$res")
            */

        }
    }

    for (host in HOSTS_LIST) {
        jobs[host]!!.join()
    }

}
