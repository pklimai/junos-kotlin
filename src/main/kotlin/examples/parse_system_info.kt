package examples

/*
 This Kotlin example is similar to the following Java code:
 https://github.com/Juniper/netconf-java/blob/master/examples/parse_system_info.java

 Example RPC response:
 <rpc-reply xmlns:junos="http://xml.juniper.net/junos/18.3R1/junos">
    <system-information>
        <hardware-model>vmx</hardware-model>
        <os-name>junos</os-name>
        <os-version>18.3R1.9</os-version>
        <serial-number>ZZZZZZZZ</serial-number>
        <host-name>vMX-1</host-name>
    </system-information>
    <cli>
        <banner></banner>
    </cli>
  </rpc-reply>

  Example program output:
  Model=vmx, OS=junos, Version=18.3R1.9, Hostname=vMX-1

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
        // Connect
        connect()

        // Send RPC and receive RPC reply as XML
        val rpcReply = executeRPC("get-system-information")

        // Extract data from XML document
        val model = rpcReply.findValue(listOf("system-information", "hardware-model"))
        val osName = rpcReply.findValue(listOf("system-information", "os-name"))
        val osVer = rpcReply.findValue(listOf("system-information", "os-version"))
        val hostName = rpcReply.findValue(listOf("system-information", "host-name"))

        // Print device info
        println("Model=${model}, OS=${osName}, Version=${osVer}, Hostname=${hostName}")

        // Close NETCONF connection
        close()
    }
}
