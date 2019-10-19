package examples

import net.juniper.netconf.Device

/*
This example first shows how you can define an extension function to automatically
open/close NETCONF connection, then executes a simple CLI command

Example output:

                     Temp  CPU Utilization (%)   CPU Utilization (%)  Memory    Utilization (%)
Slot State            (C)  Total  Interrupt      1min   5min   15min  DRAM (MB) Heap     Buffer
  0  Online           Testing  11         0       10     10     10    511        31          0
*/



/*
  This extension function accepts a block of code and executes it on
  the device, also making sure NETCONF connection is opened/closed automatically
*/
fun Device.execute(block: Device.() -> Unit) {
    this.run {
        connect()
        block()
        close()
    }
}

/*
  Build a simple device instance
 */
fun buildDevice(host: String, user: String, passwd: String): Device = Device.builder()
    .hostName(host)
    .userName(user)
    .password(passwd)
    .strictHostKeyChecking(false)
    .build()

/*
  Main function
 */
fun main() {
    buildDevice(HOSTNAME, USERNAME, PASSWORD).execute {
        println(runCliCommand("show chassis fpc 0"))
    }
}
