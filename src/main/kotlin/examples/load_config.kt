package examples

/*
Result of running the program:

[edit]
lab@vMX-1# show | compare rollback 1
[edit]
+  snmp {
+      community kotlin-example;
+  }

*/

import net.juniper.netconf.Device

// Device host name (or IP), login and password
private const val HOSTNAME = "10.254.0.41"
private const val USERNAME = "lab"
private const val PASSWORD = "lab123"

fun main() {
    // Create a device instance
    val device = Device.builder()
        .hostName(HOSTNAME)
        .userName(USERNAME)
        .password(PASSWORD)
        .strictHostKeyChecking(false)
        .build()

    device.run {
        // The following block of code is executed in the context of Device instance
        connect()
        if (!lockConfig()) {
            println("Could not lock configuration!")
            return
        }
        loadSetConfiguration("set snmp community kotlin-example")
        commit()
        unlockConfig()
        close()
    }
}
