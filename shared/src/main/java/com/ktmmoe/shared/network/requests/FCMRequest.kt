package com.ktmmoe.shared.network.requests

/**
 * Created by ktmmoe on 11, December, 2020
 **/
data class FCMRequest(
    var to: String = "",
    var collapse_key : String = "type_a",
    var data: FCMRequestData = FCMRequestData()
)

data class FCMRequestData(
    var body: String = "First notification",
    var title: String = "notification test",
    var key1 : String = "Data for key one",
    var key2: String = "Hello"
)
