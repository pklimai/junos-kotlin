package examples

/*

In this example, XPath expression is used to read Gigabit Ethernet physical interface
list from a Junos device.

Example output:

Physical interface: ge-0/0/0
Physical interface: ge-0/0/1
Physical interface: ge-0/0/2
Physical interface: ge-0/0/3
Physical interface: ge-0/0/4
Physical interface: ge-0/0/5
Physical interface: ge-0/0/6
Physical interface: ge-0/0/7
Physical interface: ge-0/0/8
Physical interface: ge-0/0/9

 */

import net.juniper.netconf.Device
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

fun main() {
    val device = Device.builder()
        .hostName(HOSTNAME)
        .userName(USERNAME)
        .password(PASSWORD)
        .strictHostKeyChecking(false)
        .build()

    device.run {
        connect()

        val interfaceInformation = executeRPC("get-interface-information").toString()

        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
            .parse(InputSource(StringReader(interfaceInformation)))

        val xpathExpr = XPathFactory.newInstance().newXPath().compile(
            "//interface-information/physical-interface[starts-with(normalize-space(name), 'ge')]/name"
        )

        val nodes = xpathExpr.evaluate(doc, XPathConstants.NODESET) as NodeList

        for (i in 0 until nodes.length) {
                println("Physical interface: ${nodes.item(i).textContent.trim()}")
        }

        close()
    }
}
