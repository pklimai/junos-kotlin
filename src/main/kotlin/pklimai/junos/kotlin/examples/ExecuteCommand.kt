package pklimai.junos.kotlin.examples

import pklimai.junos.kotlin.lib.buildDevice
import pklimai.junos.kotlin.lib.execute

/*
This example executes a simple CLI command. It uses some utility functions
from pklimai.junos.kotlin.lib package (see other folder in the same repository)

Example output:

                     Temp  CPU Utilization (%)   CPU Utilization (%)  Memory    Utilization (%)
Slot State            (C)  Total  Interrupt      1min   5min   15min  DRAM (MB) Heap     Buffer
  0  Online           Testing  11         0       10     10     10    511        31          0
*/

/*
  Main function
 */
fun main() {
    buildDevice(HOSTNAME, USERNAME, PASSWORD).execute {
        println(runCliCommand("show chassis fpc 0"))
    }
}
