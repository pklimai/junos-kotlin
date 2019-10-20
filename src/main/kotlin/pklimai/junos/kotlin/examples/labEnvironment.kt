package pklimai.junos.kotlin.examples

// Lab device host name (or IP), login and password
const val HOSTNAME = "10.254.0.41"
const val USERNAME = "lab"
const val PASSWORD = "lab123"

// List of lab devices for parallel execution test
val HOSTS_LIST = listOf("10.254.0.41", "10.254.0.42", "10.254.0.35", "10.254.0.37", "10.254.0.31")
