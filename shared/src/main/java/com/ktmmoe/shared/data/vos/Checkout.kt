package com.ktmmoe.shared.data.vos

import java.io.Serializable

/**
 * Created by ktmmoe on 06, December, 2020
 **/
data class Checkout(
    var address: Address = Address(),
    var deliveryRoutine: DeliveryRoutine = DeliveryRoutine(),
    var id: String = "",
    var total: String = "3000",
    var prescription: List<PrescriptMedicine> = listOf(PrescriptMedicine())
): Serializable

fun Checkout.checkoutMap(): Map<String, Any> =
    hashMapOf(
        "address" to this.address,
        "delivery_routine" to this.deliveryRoutine,
        "id" to this.id,
        "total" to this.total,
        "prescription" to this.prescription
    )