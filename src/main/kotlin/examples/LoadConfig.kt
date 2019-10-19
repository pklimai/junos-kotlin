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
