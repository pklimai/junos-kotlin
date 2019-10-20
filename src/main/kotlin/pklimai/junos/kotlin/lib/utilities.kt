package pklimai.junos.kotlin.lib

import net.juniper.netconf.Device

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
